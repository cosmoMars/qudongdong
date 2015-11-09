package com.wonders.xlab.qudongdong.menu.create;

import com.wonders.xlab.qudongdong.menu.manager.HttpUtil;
import com.wonders.xlab.qudongdong.menu.manager.MenuManager;
import com.wonders.xlab.qudongdong.menu.model.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CreateMenu {
    private static Logger log = LoggerFactory.getLogger(CreateMenu.class);

    public static void main(String[] args) {
//		//我的 服务号  用于测试
//        String appId = "wxab1f6505deb6588c";
//        String appSecret = "8df47114c4ea09f7c29a6abf5678ab83";

        // 奇羽记
        String appId = "wx6ed51189d6932881";
        String appSecret = "1a3105cb73cb6e17864c49dac1fee785";
//	    String access_token="1qXNVOJirXmgvRnIBSFm5ovfg4gAvbTDaC1Vkvlz_PnPz9LWUcxy1ER4yobQBq5RxQ-6l-1g8zPrjAzOJlsgJyvd0WTcu7GmbbsosOvWqPeIFje9ZsE6QkVAXbIWQ0s5V9VZyvN14msvyoZSrbMNsg";
//	    String str = access_token;
//	             调用接口获取access_token  
        AccessToken at = HttpUtil.getAccessToken(appId, appSecret);
        System.out.println(at.getAccess_token());
        if (null != at) {
            String str = at.getAccess_token();
            // 调用接口创建菜单
            int result = HttpUtil.createMenu(MenuManager.getMenuContent(), str);
            // 判断菜单创建结果
            if (0 == result)
                System.out.println("菜单创建成功");
            else
                System.out.println("菜单创建失败，错误码：" + result);
      /*
	    //获取关注者列表
	    String result = HttpUtil.getAllOpenId(str);
	    System.out.println("-------"+result);
	    */
        }
    }

}
