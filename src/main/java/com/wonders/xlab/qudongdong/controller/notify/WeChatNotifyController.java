package com.wonders.xlab.qudongdong.controller.notify;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wonders.xlab.qudongdong.entity.User;
import com.wonders.xlab.qudongdong.repository.UserRepository;
import com.wonders.xlab.qudongdong.service.cache.HCCache;
import com.wonders.xlab.qudongdong.utils.SignUtil;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mars on 15/8/21.
 */
@RestController
@RequestMapping("wechatNotify")
public class WeChatNotifyController {

    @Value("${myToken}")
    private String token;

    @Value("${appId}")
    private String appId;

    @Value("${appSecret}")
    private String appSecret;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier(value = "accessTokenCache")
    private HCCache<String, String> accessTokenCache;

    @RequestMapping(value = "notify", method = RequestMethod.GET)
    public void valiateToken(@RequestParam String signature,
                             @RequestParam String timestamp,
                             @RequestParam String nonce,
                             @RequestParam String echostr,
                             HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            if (SignUtil.checkSignature(token, signature, timestamp, nonce)) {
                out.print(echostr);
            } else {
                out.print("failure");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "notify", method = RequestMethod.POST)
    public void handleMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
//验证用户权限
        //用户消息
//        HashMap<String, String> requestMap = (HashMap<String, String>) request.getAttribute("requestMap");

        byte[] buffer = new byte[64 * 1024];
        InputStream in = null;
        try {
            in = request.getInputStream();
            int length = in.read(buffer);
            String encode = request.getCharacterEncoding();

            byte[] data = new byte[length];
            System.arraycopy(buffer, 0, data, 0, length);
            String context = new String(data, encode);

            System.out.println("context = " + context);

            Document document = DocumentHelper.parseText(context);
            Element root = document.getRootElement();
            String openId = root.elementTextTrim("FromUserName");

            String originUser = root.elementTextTrim("ToUserName");
            String msgType = root.elementTextTrim("MsgType");

            if (StringUtils.equals(msgType, "event")) {
                String event = root.elementTextTrim("Event");

                System.out.println("event = " + event);

                if (StringUtils.equals(event, "subscribe")) {
                    if (StringUtils.isEmpty(accessTokenCache.getFromCache("token"))) {

                        ResponseEntity<JsonNode> resultNode = restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret, JsonNode.class);
                        JsonNode jsonNode = resultNode.getBody();

                        System.out.println("token = " + jsonNode.get("access_token").asText());

                        accessTokenCache.addToCache("token", jsonNode.get("access_token").asText());

                    }
                    User user = userRepository.findByOpenId(openId);

                    if (user == null) {
                        ResponseEntity<JsonNode> userJson = restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessTokenCache.getFromCache("token") + "&openid=" + openId, JsonNode.class);
                        JsonNode userInfo = userJson.getBody();

                        User u = new User();
                        u.setOpenId(userInfo.get("openid").asText());
                        u.setAvatarUrl(userInfo.get("headimgurl").asText());
                        u.setNickName(userInfo.get("nickname").asText());
                        u.setCity(userInfo.get("city").asText());
                        u.setSex(User.Sex.values()[userInfo.get("sex").asInt()]);
                        userRepository.save(u);
                    }
                } else if (StringUtils.equals(event, "VIEW")) {

                } /*else if (StringUtils.equals(event, "CLICK")) {
                    String EventKey = root.elementTextTrim("EventKey");
                    if (StringUtils.equals(EventKey, "Self_Info")) {

                        String responseMsg = MessageUtil.defaultTextMessage(openId, originUser, "温馨提示：暂时不能解析该消息");

                        response.sendRedirect(EventKey);
                        try {
                            out = response.getWriter();
                            out.println(responseMsg);
                            out.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (null != out) {
                                out.close();
                            }
                        }
                    }
                }*/
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "recall")
    public void recall(HttpServletRequest request, HttpServletResponse response) {

        String code = request.getParameter("code");
        System.out.println("code = " + code);
        String responseText = restTemplate.getForObject("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + appSecret + "&code=" + code + "&grant_type=authorization_code", String.class);

        System.out.println("responseText = " + responseText);

        Map resultMap = null;
        try {
            resultMap = objectMapper.readValue(responseText, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String openId = (String) resultMap.get("openid");

        System.out.println("openId = " + openId);
        String state = request.getParameter("state");

        User user = userRepository.findByOpenId(openId);
        if (StringUtils.isEmpty(accessTokenCache.getFromCache("token"))) {

            ResponseEntity<JsonNode> resultNode = restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret, JsonNode.class);
            JsonNode jsonNode = resultNode.getBody();

            System.out.println("token = " + jsonNode.get("access_token").asText());

            accessTokenCache.addToCache("token", jsonNode.get("access_token").asText());

        }

        if (user == null) {

            ResponseEntity<JsonNode> responseUser = restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessTokenCache.getFromCache("token") + "&openid=" + openId, JsonNode.class);
            JsonNode nodeUser = responseUser.getBody();
            System.out.println("responseUser = " + nodeUser);

//            Map resultUser = null;
//            try {
//                resultUser = objectMapper.readValue(responseUser.getBody(), HashMap.class);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            User u = new User();
            u.setOpenId(openId);
            u.setAvatarUrl(nodeUser.get("headimgurl").asText());
            u.setNickName(nodeUser.get("nickname").asText());
            u.setCity(nodeUser.get("city").asText());
            u.setSex(User.Sex.values()[(nodeUser.get("sex").asInt())]);
            user = userRepository.save(u);
        }
        try {
            String url = null;
            if (StringUtils.equals(state, "list")) {
                url = "http://101.231.124.8:45698/qdd/main.html?userId=" + user.getId();
            } else if (StringUtils.equals(state, "user")) {
                url = "http://101.231.124.8:45698/qdd/individualInfo.html?userId=" + user.getId();
            } else if (StringUtils.equals(state, "friend")) {
                url = "http://101.231.124.8:45698/qdd/datingRequest.html?userId=" + user.getId();
            }
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
