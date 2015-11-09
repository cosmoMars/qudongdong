package com.wonders.xlab.qudongdong.menu.manager;

import com.alibaba.fastjson.JSON;
import com.wonders.xlab.qudongdong.menu.create.MainMenuManager;
import com.wonders.xlab.qudongdong.menu.model.AccessToken;
import com.wonders.xlab.qudongdong.menu.model.Menu;
import com.wonders.xlab.qudongdong.menu.model.ReturnMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;


/**
 * 公众平台通用接口工具类
 *
 * @author Administrator
 * @date 2013-08-09
 */
public class HttpUtil {
    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);

    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public final static String list_user_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
    public final static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    public final static String message_send_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
    public final static String user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    public final static String test_url = "https://mp.weixin.qq.com/cgi-bin/singlesendpage?tofakeid=2776803644&t=message/send&action=index&token=313814487&lang=zh_CN";

    public final static String user_getOpenIdList_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
    //上传群发内容接口
    public final static String uploadnews_url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
    //获取用户分组信息接口
    public final static String usergroup_url = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
    //发送群发消息接口
    public final static String sendall_groupId_url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
    //发送图片消息接口
    public final static String send_image_url = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=image";
    //
    public final static String user_group_id_url = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN&openId=OPENID";
    //发送图片消息接口
    public final static String user_OAuth_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return String 直接返回json字符串   //JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
//	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
    public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
//		JSONObject jsonObject = null;
        String jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {

            //从这里一直到setSSLSocketFactory时为了解决https请求的问题
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST），兼容两种请求方式
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时，兼容有无数据提交两种情况
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
//			jsonObject = JSONObject.fromObject(buffer.toString());
            jsonObject = buffer.toString();
        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("https request error:{}", e);
        }
        return jsonObject;
    }

    //将json数据转化为对象
    public static Object parseJavaObject(String jsonStr, Class clazz) {
        return JSON.toJavaObject(JSON.parseObject(jsonStr), clazz);
    }

    /**
     * 获取access_token
     *
     * @param appid     凭证
     * @param appsecret 密钥
     * @return
     */

    public static AccessToken getAccessToken(String appid, String appsecret) {

        AccessToken accessToken = null;
        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
        String jsonObject = HttpUtil.httpRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
//			try{
            accessToken = (AccessToken) HttpUtil.parseJavaObject(jsonObject, AccessToken.class);
//			}catch(JSONException e){
//				ReturnMsg msg = (ReturnMsg)HttpUtil.parseJavaObject(jsonObject,ReturnMsg.class);
//				log.error("获取token失败 errcode:{} errmsg:{}", msg.getErrcode(), msg.getErrmsg());  
//			}
        }
        return accessToken;
    }

    /**
     * 获取关注者列表 (只有用户的openid)
     *
     * @param access_token
     * @return
     */
    public static String getAllOpenId(String access_token) {

        String result = null;
        String requestUrl = list_user_url.replace("ACCESS_TOKEN", access_token);
        String jsonStr = HttpUtil.httpRequest(requestUrl, "GET", null);
        if (null != jsonStr) {
            result = jsonStr;
        }
        return result;
    }

    /**
     * 获取用户基本信息
     *
     * @param access_token
     * @return
     */
    public static String getUserInfoByOpenId(String access_token, String openId) {
        String result = null;
        String requestUrl = user_info_url.replace("ACCESS_TOKEN", access_token).replace("OPENID", openId);
        String jsonStr = HttpUtil.httpRequest(requestUrl, "GET", null);
        if (null != jsonStr) {
            result = jsonStr;
        }
        return result;
    }

    /**
     * 获取关注者列表
     *
     * @param access_token
     * @param openId
     * @return
     */
    public static String getUserOpenIdList(String access_token) {
        String result = null;
        String requestUrl = user_getOpenIdList_url.replace("ACCESS_TOKEN", access_token);
        String jsonStr = HttpUtil.httpRequest(requestUrl, "GET", null);
        if (null != jsonStr) {
            result = jsonStr;
        }
        return result;
    }

    /**
     * 创建菜单，向微信发送POST请求，然后解析返回码判断成功失败
     *
     * @param menu
     * @param access_token
     * @return
     */
    public static int createMenu(Menu menu, String access_token) {
        int result = 0;
        String url = menu_create_url.replace("ACCESS_TOKEN", access_token);
//		String jsonMenu =JSON.toJSONString(menu, true);
//		String jsonMenu ="{\"button\":[{\"type\":\"click\",\"name\":\"疫苗助手\",\"key\":\"21\"},{\"type\":\"click\",\"name\":\"更多\",\"key\":\"11\"}]}";
//		 String jsonObject = HttpUtil.httpRequest(url,"POST",jsonMenu);
//		String jsonMenu =JSON.toJSONString(menu, true);


//		MenuTest menuTest = new MenuTest();
//		String jsonMenu = menuTest.getMenuJosn();


//		MenuManager menuManager = new MenuManager();
//		String jsonMenu = menuManager.toString();

        MainMenuManager mainMenuManager = new MainMenuManager();
        String jsonMenu = mainMenuManager.getMenuJosn();

//		String jsonMenu = menuTest.getFWHMenuJosn();
        String jsonObject = HttpUtil.httpRequest(url, "POST", jsonMenu);
        if (null != jsonObject) {
            ReturnMsg returnMsg = (ReturnMsg) HttpUtil.parseJavaObject(jsonObject, ReturnMsg.class);
            if (0 != returnMsg.getErrcode()) {
                result = returnMsg.getErrcode();
                log.error("创建菜单失败 errcode:{ } errmsg:{ }", returnMsg.getErrcode(), returnMsg.getErrmsg());
            }
        }
        return result;
    }

}
