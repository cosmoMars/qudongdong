package com.wonders.xlab.qudongdong.controller.notify;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wonders.xlab.qudongdong.entity.User;
import com.wonders.xlab.qudongdong.message.response.TextMessage;
import com.wonders.xlab.qudongdong.repository.UserRepository;
import com.wonders.xlab.qudongdong.service.cache.HCCache;
import com.wonders.xlab.qudongdong.utils.MessageUtil;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mars on 15/8/21.
 */
@RestController
@RequestMapping("weChatNotify")
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

    //验证回调
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

    // 回调事件
    @RequestMapping(value = "notify", method = RequestMethod.POST)
    public String handleMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {

//        PrintWriter out = response.getWriter();
//验证用户权限
        //用户消息
//        HashMap<String, String> requestMap = (HashMap<String, String>) request.getAttribute("requestMap");


        String respMessage = null;
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

            //读取微信返回信息
            Document document = DocumentHelper.parseText(context);
            Element root = document.getRootElement();
            String openId = root.elementTextTrim("FromUserName");

            System.out.println("openId = " + openId);

            String originUser = root.elementTextTrim("ToUserName");
            String msgType = root.elementTextTrim("MsgType");


            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(openId);
            textMessage.setFromUserName(originUser);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
//            textMessage.setFuncFlag(0);

            // 获取事件
            if (StringUtils.equals(msgType, "event")) {
                String event = root.elementTextTrim("Event");

//                System.out.println("event = " + event);

                // 关注事件
                if (StringUtils.equals(event, "subscribe")) {

                    // 校验token
                    if (StringUtils.isEmpty(accessTokenCache.getFromCache("token"))) {

                        ResponseEntity<JsonNode> resultNode = restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret, JsonNode.class);
                        JsonNode jsonNode = resultNode.getBody();

//                        System.out.println("token = " + jsonNode.get("access_token").asText());

                        accessTokenCache.addToCache("token", jsonNode.get("access_token").asText());

                    }
                    // 查找user
                    User user = userRepository.findByOpenId(openId);

                    // 没有用户创建用户
                    if (user == null) {
                        System.out.println("openIdnext = " + openId);
                        ResponseEntity<JsonNode> userJson = restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessTokenCache.getFromCache("token") + "&openid=" + openId, JsonNode.class);
                        JsonNode userInfo = userJson.getBody();

                        System.out.println("userInfo = " + userInfo.toString());

                        User u = new User();
                        u.setOpenId(openId);
                        u.setAvatarUrl(userInfo.get("headimgurl").asText());
                        u.setNickName(userInfo.get("nickname").asText());
                        u.setCity(userInfo.get("city").asText());
                        u.setSex(User.Sex.values()[userInfo.get("sex").asInt()]);
                        userRepository.save(u);
                    }
                    String respContent = "欢迎关注奇羽记！奇羽记聚乐部旨在通过羽毛球运动，提供方便的场地预约、教练指导、高手陪练、约战球友、健康检测、运动处方、线上轻问诊等一站式服务。赶紧呼朋唤友，加入我们的会员聚乐部吧！";
//                    StringBuffer contentMsg = new StringBuffer();
//                    contentMsg.append("我们会让您拥有不一样的体验").append("\n");
//                    respContent = respContent + contentMsg.toString();

                    textMessage.setContent(respContent);
                    respMessage = MessageUtil.textMessageToXml(textMessage);

                } /*else if (StringUtils.equals(event, "VIEW")) {

                } else if (StringUtils.equals(event, "CLICK")) {
                    User user = userRepository.findByOpenId(openId);
                    String eventKey = root.elementTextTrim("EventKey");

                    System.out.println("eventKey = " + eventKey);
                    // 我要入会
                    if (StringUtils.equals(eventKey, "joinClub")) {

//                        String responseMsg = MessageUtil.defaultTextMessage(openId, originUser, "温馨提示：暂时不能解析该消息");
//
//                        response.sendRedirect(EventKey);
//                        try {
//                            out = response.getWriter();
//                            out.println(responseMsg);
//                            out.flush();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } finally {
//                            if (null != out) {
//                                out.close();
//                            }
//                        }
                        response.sendRedirect("http://101.231.124.8:45698/qdd/joinMember.html");
                    } else if(StringUtils.equals(eventKey, "needCoach")) {
                        response.sendRedirect("http://101.231.124.8:45698/qdd/datingCoach.html");
                    }
                }*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }


    // 授权回调
    @RequestMapping(value = "recall")
    public void recall(HttpServletRequest request, HttpServletResponse response) {

        // 回调状态码
        String code = request.getParameter("code");
        // 通过状态码请求用户信息
        String responseText = restTemplate.getForObject("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + appSecret + "&code=" + code + "&grant_type=authorization_code", String.class);

//        System.out.println("responseText = " + responseText);
        System.out.println("code = " + code);

        Map resultMap = null;
        try {
            resultMap = objectMapper.readValue(responseText, HashMap.class);
            System.out.println("resultMap = " + resultMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String openId = (String) resultMap.get("openid");

        String state = request.getParameter("state");
//
//        System.out.println("state = " + state);
//        System.out.println("openId = " + openId);

        User user = userRepository.findByOpenId(openId);

        /*if (StringUtils.isEmpty(accessTokenCache.getFromCache("token"))) {

            ResponseEntity<JsonNode> resultNode = restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret, JsonNode.class);
            JsonNode jsonNode = resultNode.getBody();

//            System.out.println("token = " + jsonNode.get("access_token").asText());

            accessTokenCache.addToCache("token", jsonNode.get("access_token").asText());
        }

        if (user == null) {

            ResponseEntity<JsonNode> responseUser = restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessTokenCache.getFromCache("token") + "&openid=" + openId, JsonNode.class);
            JsonNode nodeUser = responseUser.getBody();
            System.out.println("responseUser = " + nodeUser);

            User u = new User();
            u.setOpenId(openId);
            u.setAvatarUrl(nodeUser.get("headimgurl").asText());
            u.setNickName(nodeUser.get("nickname").asText());
            u.setCity(nodeUser.get("city").asText());
            u.setSex(User.Sex.values()[(nodeUser.get("sex").asInt())]);
            user = userRepository.save(u);
        }*/
        try {
            String url = null;
            if (StringUtils.equals(state, "joinClub")) {
                url = "http://101.231.124.8:45698/qdd/joinMember.html";
            } else if (StringUtils.equals(state, "needCoach")) {
                url = "http://101.231.124.8:45698/qdd/datingCoach.html";
            } else if (StringUtils.equals(state, "main")) {
                url = "http://101.231.124.8:45698/qdd/main.html?userId=" + user.getId();
            } else if (StringUtils.equals(state, "addDating")) {
                if (user.isOfficial()) {
                    url = "http://101.231.124.8:45698/qdd/addDatingsOfficial.html?userId=" + user.getId();
                } else {
                    url = "http://101.231.124.8:45698/qdd/addDatings.html?userId=" + user.getId();
                }
            } else if (StringUtils.equals(state, "individualInfo")) {
                url = "http://101.231.124.8:45698/qdd/individualInfo.html?userId=" + user.getId();
            } else if (StringUtils.equals(state, "timeLine")) {
                url = "http://101.231.124.8:45698/qdd/timeLine.html?userId=" + user.getId();
            }

            /*else if (StringUtils.equals(state, "user")) {
                url = "http://101.231.124.8:45698/qdd/individualInfo.html?userId=" + user.getId();
            } else if (StringUtils.equals(state, "friend")) {
                url = "http://101.231.124.8:45698/qdd/datingRequest.html?userId=" + user.getId();
            }*/
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
