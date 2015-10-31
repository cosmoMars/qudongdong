package com.wonders.xlab.qudongdong.menu.create;


import com.wonders.xlab.qudongdong.menu.manager.HttpUtil;
import com.wonders.xlab.qudongdong.menu.model.AccessToken;

public class GetUserInfoTest {
	public static void main(String[] args) {
//		String appId = "wx5800fb2e1b46d9ad";
//		String appSecret = "0ae88ff25cb86828649ae0fae78bd310";
		String appId ="wxb473d188b518c7a0";
		String appSecret = "e0f85ba37645437e58f5e7a28dda136c";
//		String openId = "o5-kWuJQi-0eRe8-PzeKw8aTsN2Q";
		//服务号openId：让小侠o5-kWuKSs_XBD-ASBtM--5uz9mo8
//		"openid":["o5-kWuAX18F6uGb4bBoHFmPjeBVg","o5-kWuMYi72oAsxMYOiwrrhH_Vso",
//		"o5-kWuDenLwmhlyY26BVdJmHlorM","o5-kWuI7kQd9iQXji7fj0jRzu_hU","o5-kWuKSs_XBD-ASBtM--5uz9mo8",
//		"o5-kWuNFQ27K76GLI0kMJvU0b2xI","o5-kWuBmZEg0wvytdp2i0kcqejaI","o5-kWuEdCTA70YBYOU_-rO6ykkAo",
//		"o5-kWuN0JskL08xi3mBIMoh9ClLQ","o5-kWuN7gBe5iM43A0LwmJ8f7Du4","o5-kWuA8qz8MOnj618nZTHrYi7s4",
//		"o5-kWuBdLUk2dquqQFFqlvEjDN_k","o5-kWuOoUbg_MQ2fP9Y2dm-V5akw","o5-kWuIfPMjYPAMiwvzu0ZT9HIpo",
//		"o5-kWuBoNKVZMXSaivcAmT7C3Bfc","o5-kWuJ9y6XhF1ikq1aO-dxDabyw","o5-kWuOg0OuNL8pZx9tuVHXHZXuk",
//		"o5-kWuAuTe9-u7sh2eFe-23K0HoI程琳琳","o5-kWuExwWhSJk7i8dEKZY9NjkV4","o5-kWuPXpVuEsz76mW2YWNRa_flo",
//		"o5-kWuEzkGiRCOtNIW-wGid57NTk","o5-kWuJCLEUOZlY88sexUHmmZy4o","o5-kWuMQFA1r5fWbyRkg9p1yFlwo",
//		"o5-kWuCXcaxFkFyf_utX_NZDEPPQ","o5-kWuHWq4vXL2gkkZXszN0UcZWs","o5-kWuDjfLJwZxim4BSSfhebDxJU",
//		"o5-kWuJQi-0eRe8-PzeKw8aTsN2Q","o5-kWuBwXbNrU2eshGjd_lp30-as","o5-kWuFVPvrau8cXRy89HqRhJl_k",
//		"o5-kWuAXWhTMvF95RVPujnOiQ-X0 刘泽龙","o5-kWuJpLzPc85lO2ZQeElLD7quM 冬冬"]},"next_openid":"o5-kWuJpLzPc85lO2ZQeElLD7quM"}

		
		AccessToken at = HttpUtil.getAccessToken(appId, appSecret);
		if (null != at) {  
		    String str = at.getAccess_token();
//		    GetUserInfoTest infoTest = new GetUserInfoTest();
//		    String result = infoTest.sendTextMessage(str);
//		    System.out.println(str);
//		    String openIdList = HttpUtil.getUserOpenIdList(str);
//		    System.out.println(openIdList);
		    
		    String[] openIdDetail = new String[]{"o5-kWuAX18F6uGb4bBoHFmPjeBVg","o5-kWuMYi72oAsxMYOiwrrhH_Vso",
		    		"o5-kWuDenLwmhlyY26BVdJmHlorM","o5-kWuI7kQd9iQXji7fj0jRzu_hU","o5-kWuKSs_XBD-ASBtM--5uz9mo8",
             		"o5-kWuNFQ27K76GLI0kMJvU0b2xI","o5-kWuBmZEg0wvytdp2i0kcqejaI","o5-kWuEdCTA70YBYOU_-rO6ykkAo",
             		"o5-kWuN0JskL08xi3mBIMoh9ClLQ","o5-kWuN7gBe5iM43A0LwmJ8f7Du4","o5-kWuA8qz8MOnj618nZTHrYi7s4",
             		"o5-kWuBdLUk2dquqQFFqlvEjDN_k","o5-kWuOoUbg_MQ2fP9Y2dm-V5akw","o5-kWuIfPMjYPAMiwvzu0ZT9HIpo",
             		"o5-kWuBoNKVZMXSaivcAmT7C3Bfc","o5-kWuJ9y6XhF1ikq1aO-dxDabyw","o5-kWuOg0OuNL8pZx9tuVHXHZXuk",
             		"o5-kWuAuTe9-u7sh2eFe-23K0HoI","o5-kWuExwWhSJk7i8dEKZY9NjkV4","o5-kWuPXpVuEsz76mW2YWNRa_flo",
             		"o5-kWuEzkGiRCOtNIW-wGid57NTk","o5-kWuJCLEUOZlY88sexUHmmZy4o","o5-kWuMQFA1r5fWbyRkg9p1yFlwo",
             		"o5-kWuCXcaxFkFyf_utX_NZDEPPQ","o5-kWuHWq4vXL2gkkZXszN0UcZWs","o5-kWuDjfLJwZxim4BSSfhebDxJU",
             		"o5-kWuJQi-0eRe8-PzeKw8aTsN2Q","o5-kWuBwXbNrU2eshGjd_lp30-as","o5-kWuFVPvrau8cXRy89HqRhJl_k",
             		"o5-kWuAXWhTMvF95RVPujnOiQ-X0","o5-kWuJpLzPc85lO2ZQeElLD7quM"};
		    
		    for (String openId : openIdDetail) {
		    	 String result = HttpUtil.getUserInfoByOpenId(str, openId);
				    System.out.println(result);
			}
		    
//	    	 String result = HttpUtil.getUserInfoByOpenId(str, openId);
//			 System.out.println(result);
		    
//		    返回值为“{"errcode":48001,"errmsg":"api unauthorized"}”
		}
	}
	
	public String sendTextMessage(String access_token){
		String SendTextUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
		String message = "{\"touser\":\"o5-kWuA8qz8MOnj618nZTHrYi7s4\",  \"msgtype\":\"text\", \"text\":{ \"content\":\"Hello World\" }}";
		String requestUrl = SendTextUrl.replace("ACCESS_TOKEN", access_token);  
		String jsonStr = HttpUtil.httpRequest(requestUrl, "GET", message); 
		return jsonStr;
	}
}
/*
 * 
 * {"subscribe":1,"openid":"o5-kWuAX18F6uGb4bBoHFmPjeBVg","nickname":"wli","sex":1,"language":"zh_CN","city":"武汉","province":"湖北","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/V6z5UXnP1pPLNVicXyrxleicwGNX2wIvCq4oUdQ98ABRbaCwCE5NiaFQYtib5vqQkl36cmRt6U84LgwKvSHk9gd7ug\/0","subscribe_time":1400742526,"remark":""}
{"subscribe":1,"openid":"o5-kWuMYi72oAsxMYOiwrrhH_Vso","nickname":"听世界","sex":1,"language":"zh_CN","city":"深圳","province":"广东","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/mv36IibPLXYiarlSXW5wuHO8QQd348c0ictryicrXAVHvkc3ibGM6oWQ5meicCpS7NFZ12VZpqus4kD8z7oGR89icEMng\/0","subscribe_time":1399364298,"remark":""}
{"subscribe":1,"openid":"o5-kWuDenLwmhlyY26BVdJmHlorM","nickname":"Multifaceted Swan","sex":2,"language":"zh_CN","city":"洛杉矶","province":"加利福尼亚","country":"美国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/ajNVdqHZLLClvV6cvSoY2zPe2c3meEWmtxnVE6DQwicrF0yju17nMfpmBA54KpePFj3Eo6GM97m6ZhfWCqpAwKw\/0","subscribe_time":1402361750,"remark":""}
{"subscribe":0,"openid":"o5-kWuI7kQd9iQXji7fj0jRzu_hU"}
{"subscribe":1,"openid":"o5-kWuKSs_XBD-ASBtM--5uz9mo8","nickname":"让小侠","sex":2,"language":"zh_CN","city":"武汉","province":"湖北","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/ajNVdqHZLLCnKLTYq390hPxRPEhKY5ic1XGJUfFVibXFSPic8ZFxFp0muoNvUraMS9NRaQ70tKqbm7gibHLTE5kBFQ\/0","subscribe_time":1399592609,"remark":""}
{"subscribe":1,"openid":"o5-kWuNFQ27K76GLI0kMJvU0b2xI","nickname":"邵慧斌","sex":1,"language":"zh_CN","city":"武汉","province":"湖北","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/ajNVdqHZLLByuISFEamv7PGIEP47jMW8Ene4MgDRzxgf8GljAJgmicichuPJ47FtypeRBsChNBXLsEVT8o10raTA\/0","subscribe_time":1403747915,"remark":""}
{"subscribe":0,"openid":"o5-kWuBmZEg0wvytdp2i0kcqejaI"}
{"subscribe":1,"openid":"o5-kWuEdCTA70YBYOU_-rO6ykkAo","nickname":"hmily","sex":2,"language":"zh_CN","city":"虹口","province":"上海","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/Q3auHgzwzM4ia20gx72h7kLSCNgtA4Z1LO3wVRjQqGBP9mzuwWA868HoVU2d8sOeD9yp5gq0ZVIjYNADyczXnAQ\/0","subscribe_time":1397558300,"remark":""}
{"subscribe":1,"openid":"o5-kWuN0JskL08xi3mBIMoh9ClLQ","nickname":"高航","sex":1,"language":"zh_CN","city":"朝阳","province":"北京","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/PiajxSqBRaEI3jj7S7pd5du4znMKScMdF5JH8vexhKD48h8fFOrpwYDWibO1LaJPj9rty7vDyFk5tFoBLaA6ibMlQ\/0","subscribe_time":1400835219,"remark":""}
{"subscribe":1,"openid":"o5-kWuN7gBe5iM43A0LwmJ8f7Du4","nickname":"风向标","sex":2,"language":"zh_CN","city":"浦东新区","province":"上海","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/V6z5UXnP1pPaphTKFr2s1wkyrzWDxQKobXoVG41tqwHOZiabUPW2Ae4KQnAe4WXM8ZmvibHcwZEaMy8UTky1lGSg\/0","subscribe_time":1400482855,"remark":""}
{"subscribe":1,"openid":"o5-kWuA8qz8MOnj618nZTHrYi7s4","nickname":"沙沙","sex":2,"language":"zh_CN","city":"郑州","province":"河南","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/PiajxSqBRaEIoB8Ezrhsibc3XcyC8r2DT6zfQAjRwAMoTk4FoWpqyMBf4tjH65EtdkDvtz7V8Yv3P0kswR6rRwtA\/0","subscribe_time":1403156117,"remark":""}
{"subscribe":1,"openid":"o5-kWuBdLUk2dquqQFFqlvEjDN_k","nickname":"颖大心","sex":2,"language":"zh_CN","city":"济宁","province":"山东","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/ajNVdqHZLLAu94xo7M2Sdo9vAnDHzFCwelVfMs28uCnGybBOaTvVWDNWV8ribKgoqCnn4M8ymNU0gqmBuWHoIicw\/0","subscribe_time":1399738028,"remark":""}
{"subscribe":1,"openid":"o5-kWuOoUbg_MQ2fP9Y2dm-V5akw","nickname":"施勤文","sex":1,"language":"zh_CN","city":"宝山","province":"上海","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/V6z5UXnP1pPOGy46LsbUGNf3tqsyTShtI3WekuzBSv54edBiaZIlBYta1ygU6ZhB2HVFQehJJ9EH7LYYo8GV5pa5V9xKE5ib9V\/0","subscribe_time":1399554408,"remark":""}
{"subscribe":1,"openid":"o5-kWuIfPMjYPAMiwvzu0ZT9HIpo","nickname":"姗","sex":2,"language":"zh_CN","city":"邯郸","province":"河北","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/V6z5UXnP1pP66Riapu8kYVXib7xcAWMbgS2SUmBRUpib2ic7qW3luy1xAwfqwqRMoePJic1ZezK0Ie8IyEqxVMNI44iboTqNS9WDiaP\/0","subscribe_time":1403651650,"remark":""}
{"subscribe":1,"openid":"o5-kWuBoNKVZMXSaivcAmT7C3Bfc","nickname":"阿龙","sex":1,"language":"zh_CN","city":"邢台","province":"河北","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/V6z5UXnP1pPOGy46LsbUGLu2qy960QW9NNgcoXViaJIXMusmPwaFFYKruB23q5GryQTXl3iaibyZlF0Fibns2TRc0L6tNKqAvJicZ\/0","subscribe_time":1403161439,"remark":""}
{"subscribe":1,"openid":"o5-kWuJ9y6XhF1ikq1aO-dxDabyw","nickname":"spring_xsy","sex":2,"language":"zh_CN","city":"闵行","province":"上海","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/kBCyznggU08cdmdiaVH3OYvqicPeU71Z0dxSB0z0FqzaZgib0jKJS9icVyDUvzPIiaOFWbYrboHkOia3Pl1KKuXX8UrYLTrGuWviaic9\/0","subscribe_time":1405996492,"remark":""}
{"subscribe":1,"openid":"o5-kWuOg0OuNL8pZx9tuVHXHZXuk","nickname":"小瓜子","sex":2,"language":"en","city":"纽约市","province":"纽约","country":"美国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/mv36IibPLXYhQ3hEOworNDKjJYWXkcrlqia86xtLahMjbZywg9b1UFc3P7pJBiaullCszIcaAMJR3thIozcCt5uYwY3ibak0w9Ak\/0","subscribe_time":1398794299,"remark":""}
{"subscribe":1,"openid":"o5-kWuAuTe9-u7sh2eFe-23K0HoI","nickname":"程琳琳","sex":2,"language":"zh_CN","city":"株洲","province":"湖南","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/mv36IibPLXYgVEZdaB3V3v4Jt8StrhL0XAR8RjHR8niad7MfYial8sKOeU3ZApGATLaibC62jvHEbyFzGw5xTgibDKLoefcf4RQC2\/0","subscribe_time":1400649612,"remark":""}
{"subscribe":1,"openid":"o5-kWuExwWhSJk7i8dEKZY9NjkV4","nickname":"遇见","sex":2,"language":"zh_CN","city":"","province":"上海","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/kDBicAOLjNiavjc4c3hmLQ78U4cIZBFtM8Tiaiax1QVogX0dnPcI20yr4xmsuhicCnHmpKOVicEfA7zxCNX7FE6iaJeMboibiciag2AkCs\/0","subscribe_time":1399275708,"remark":""}
{"subscribe":1,"openid":"o5-kWuPXpVuEsz76mW2YWNRa_flo","nickname":"+DA+","sex":1,"language":"zh_CN","city":"普陀","province":"上海","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/mv36IibPLXYgVEZdaB3V3v4UJiatYoib25eibM05cSLDfalrLqftUhGB1n66zyVQZhAkrleax7uVKgoFyas5F4am52adgJtNxgC8\/0","subscribe_time":1401001087,"remark":""}
{"subscribe":1,"openid":"o5-kWuEzkGiRCOtNIW-wGid57NTk","nickname":"梦之殇","sex":1,"language":"zh_CN","city":"普陀","province":"上海","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/kBCyznggU08cdmdiaVH3OYu98vQQiboMQv5pbXTJxRaicPZz8jPKdZKy6ze1CWrmq7pxHJdmwWiaPvlvtibUZicBVEZic8ichBQlribBt\/0","subscribe_time":1414130485,"remark":""}
{"subscribe":0,"openid":"o5-kWuJCLEUOZlY88sexUHmmZy4o"}
{"subscribe":1,"openid":"o5-kWuMQFA1r5fWbyRkg9p1yFlwo","nickname":"普拉普拉","sex":0,"language":"zh_CN","city":"","province":"","country":"","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/V6z5UXnP1pPOGy46LsbUGGUmjYLibNX8mFgNKDOTdgreMiaut7EaeHo6XibO2vxibns8HfHRLTSobnMheskltcaYnPS9Lkia81Ziam\/0","subscribe_time":1397457386,"remark":""}
{"subscribe":1,"openid":"o5-kWuCXcaxFkFyf_utX_NZDEPPQ","nickname":"朱小新","sex":2,"language":"zh_CN","city":"卢湾","province":"上海","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/kBCyznggU08cdmdiaVH3OYolymF0xjRr0yTQmda5SnqSzB1qkGcGaICll4wjzKRC5iaCiboxjc1Pbcale3kBibWvV7s6usMUwaa6\/0","subscribe_time":1397643484,"remark":""}
{"subscribe":1,"openid":"o5-kWuHWq4vXL2gkkZXszN0UcZWs","nickname":"吴方","sex":0,"language":"zh_CN","city":"","province":"","country":"","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/mv36IibPLXYiayIgwtwJicWOQLHVIBdECCgyriazq2sEibTuV6O5Yvg0eWnDds0UUu8BU1OFoyeSzTVNaticPKccjytwRoicib3Fy6fb\/0","subscribe_time":1403604888,"remark":""}
{"subscribe":1,"openid":"o5-kWuDjfLJwZxim4BSSfhebDxJU","nickname":"天空之辰","sex":2,"language":"en","city":"闵行","province":"上海","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/uVOmibgibARax5uTKwFJ2OBklIMvMrjLddxiaJUos2XGZeU2HZWW3AaiaPQnFsCkpTXsAJwpHnt0v0TdZy0xoEZSwrtHZqy6nUOl\/0","subscribe_time":1407938559,"remark":""}
{"subscribe":1,"openid":"o5-kWuJQi-0eRe8-PzeKw8aTsN2Q","nickname":"俞振华_Ace","sex":1,"language":"zh_CN","city":"宝山","province":"上海","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/V6z5UXnP1pPOGy46LsbUGFq3rV2IwHgQicUZT4WG0WHLuMn6R8vOOcbHB22VLAwFmoiaSUOJ4vNk4w0JgDGRu6A5r2qA774ElM\/0","subscribe_time":1395838671,"remark":""}
{"subscribe":1,"openid":"o5-kWuBwXbNrU2eshGjd_lp30-as","nickname":"妮~","sex":0,"language":"zh_CN","city":"","province":"","country":"","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/mv36IibPLXYjAzBLVXE3XZOVAthAGO4icABDB29caEQH0lB0a1RnINtFpMMT4LnQYA949LnEl11loKYbibYoTe6zTSQQ5nZsH1e\/0","subscribe_time":1400219025,"remark":""}
{"subscribe":1,"openid":"o5-kWuFVPvrau8cXRy89HqRhJl_k","nickname":"回","sex":2,"language":"zh_CN","city":"莆田","province":"福建","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/kDBicAOLjNiavjc4c3hmLQ73IpAlERic2Teic0luEYQCdBUSzopPax1Oiad0fr5gdUbZOWgNcicdNPn0rn1z668PoBFsWuV1QTIohia\/0","subscribe_time":1409101152,"remark":""}
{"subscribe":1,"openid":"o5-kWuAXWhTMvF95RVPujnOiQ-X0","nickname":"超级不文艺的一个人","sex":1,"language":"zh_CN","city":"长宁","province":"上海","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/mv36IibPLXYgVEZdaB3V3vzU89lKVtwTmyWLuibvEIP6OWSric7cmOTdI3jliaNZ9Z9BCckYEeLmkkicppx3nfCfibFmplAm8hpicdE\/0","subscribe_time":1398648286,"remark":""}
{"subscribe":1,"openid":"o5-kWuJpLzPc85lO2ZQeElLD7quM","nickname":"冬冬","sex":1,"language":"zh_CN","city":"闵行","province":"上海","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/V6z5UXnP1pPOGy46LsbUGK2NDYRxZhWSicu4JsgZtmVcbdq3rlsCe2PA966nn4lHTnJYudANPLBtSn306lQibGXpWnbzcibibnar\/0","subscribe_time":1407937494,"remark":""}
 */
