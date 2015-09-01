package com.wonders.xlab.qudongdong.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.wonders.xlab.qudongdong.service.cache.HCCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by mars on 15/8/31.
 */
@RestController
@RequestMapping("weChat")
public class WeChatController {

    @Value("${myToken}")
    private String token;

    @Value("${appId}")
    private String appId;

    @Value("${appSecret}")
    private String appSecret;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier(value = "accessTokenCache")
    private HCCache<String, String> accessTokenCache;

    @RequestMapping(value = "getJsConfig", method = RequestMethod.POST)
    public Map<String, String> getJsConfig(@RequestParam String url) {

        if (StringUtils.isEmpty(accessTokenCache.getFromCache("token"))) {

            ResponseEntity<JsonNode> resultNode = restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret, JsonNode.class);
            JsonNode jsonNode = resultNode.getBody();

            System.out.println("token = " + jsonNode.get("access_token").asText());

            accessTokenCache.addToCache("token", jsonNode.get("access_token").asText());

        }

        JsonNode ticketNode = restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessTokenCache.getFromCache("token") + "&type=jsapi", JsonNode.class);

        if (!StringUtils.isEmpty(ticketNode.get("ticket").asText())) {
            return sign(ticketNode.get("ticket").asText(), url);
        }

        return null;
    }

    public Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        ret.put("appId", appId);
        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }


}
