package com.wonders.xlab.qudongdong.menu.create;


import com.wonders.xlab.qudongdong.menu.manager.HttpUtil;
import com.wonders.xlab.qudongdong.menu.model.AccessToken;

public class GetDyhUserInfoTest {
	public static void main(String[] args) {
		String appId = "wx5800fb2e1b46d9ad";
		String appSecret = "0ae88ff25cb86828649ae0fae78bd310";
		String openId = "o5-kWuMYi72oAsxMYOiwrrhH_Vso";
		AccessToken at = HttpUtil.getAccessToken(appId, appSecret);
		if (null != at) {  
		    String str = at.getAccess_token();
		    System.out.println(str);
//		    String openIdList = HttpUtil.getUserOpenIdList(str);
//		    System.out.println(openIdList);
		    String result = HttpUtil.getUserInfoByOpenId(str, openId);
		    System.out.println(result);
		    
//		    返回值为“{"errcode":48001,"errmsg":"api unauthorized"}”
		}
	}
}
