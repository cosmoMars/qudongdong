package com.wonders.xlab.qudongdong.menu.create;

import com.wonders.xlab.qudongdong.menu.manager.HttpUtil;
import com.wonders.xlab.qudongdong.menu.model.AccessToken;

public class SendMessageTest {
	public static void main(String[] args) {
		String appId = "wxab1f6505deb6588c";
		String appSecret = "8df47114c4ea09f7c29a6abf5678ab83";
		
		 AccessToken at = HttpUtil.getAccessToken(appId, appSecret);
		    System.out.println(at.getAccess_token());
		    if (null != at) {  
		    	String str = at.getAccess_token();
		    	String url = HttpUtil.message_send_url.replace("ACCESS_TOKEN", str);
		    }
	}
}
