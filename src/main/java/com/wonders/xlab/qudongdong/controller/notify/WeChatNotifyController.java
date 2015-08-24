package com.wonders.xlab.qudongdong.controller.notify;

import com.fasterxml.jackson.databind.JsonNode;
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
    public void handleMessage(HttpServletRequest request, HttpServletResponse response) {
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
            String msgType = root.elementTextTrim("MsgType");

            if (StringUtils.equals(msgType, "event")) {
                String event = root.elementTextTrim("Event");
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
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
