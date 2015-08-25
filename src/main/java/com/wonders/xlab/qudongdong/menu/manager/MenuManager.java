package com.wonders.xlab.qudongdong.menu.manager;

import com.wonders.xlab.qudongdong.menu.model.Button;
import com.wonders.xlab.qudongdong.menu.model.CommonButton;
import com.wonders.xlab.qudongdong.menu.model.ComplexButton;
import com.wonders.xlab.qudongdong.menu.model.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
  
	public static Menu getMenuContent(){
		return getMenu();
	}
	
    /** 
     * 组装菜单数据 
     *  
     * @return 
     */  
    private static Menu getMenu() {  
//    	"{\"button\":[{\"type\":\"click\",\"name\":\"疫苗助手\",\"key\":\"21\"},{\"name\":\"健康订阅\",\"sub_button\":[{\"type\":\"click\",\"name\":\"添加订阅\",\"key\":\"11\"},{\"type\":\"click\",\"name\":\"查看订阅\",\"key\":\"12\"}]}]}";
        CommonButton btn11 = new CommonButton();  
        btn11.setName("更多");  
        btn11.setType("click");
        btn11.setKey("11");
//        btn11.setUrl("http://101.231.124.13/JiaDingWeiXin/testServlet");
  
        CommonButton btn12 = new CommonButton();  
        btn12.setName("订阅管理");  
        btn12.setType("click");
        btn12.setKey("12");
        
        CommonButton btn13 = new CommonButton();  
        btn13.setName("候诊查询");  
        btn13.setType("view");  
        btn13.setUrl("http://101.231.124.13/JiaDingWeiXin/waiting.jsp");
  
        CommonButton btn14 = new CommonButton();
        btn14.setName("预约挂号");  
        btn14.setType("view");  
        btn14.setUrl("http://101.231.124.13/JiaDingWeiXin/imgnews/order.jpg");
  
        CommonButton btn15 = new CommonButton();  
        btn15.setName("管理查询");  
        btn15.setType("click");  
        btn15.setKey("15");  
        
        CommonButton btn21 = new CommonButton();  
        btn21.setName("疫苗助手");  
        btn21.setType("click");  
        btn21.setKey("21");  
  
       CommonButton btn22 = new CommonButton();  
        btn22.setName("专家排班");  
        btn22.setType("click");  
        btn22.setKey("22");  
  
        CommonButton btn23 = new CommonButton();  
        btn23.setName("便民服务");  
        btn23.setType("click");  
        btn23.setKey("23");  
  
        CommonButton btn24 = new CommonButton();  
        btn24.setName("健康顾问");  
        btn24.setType("click");  
        btn24.setKey("24");  
        
        CommonButton btn25 = new CommonButton();  
        btn25.setName("服务指南");  
        btn25.setType("click");  
        btn25.setKey("25");  
  
        CommonButton btn31 = new CommonButton();  
        btn31.setName("卫生新闻");  
        btn31.setType("click");  
        btn31.setKey("31");  
  
        CommonButton btn32 = new CommonButton();  
        btn32.setName("医院活动");  
        btn32.setType("click");  
        btn32.setKey("32");  
  
        CommonButton btn33 = new CommonButton();  
        btn33.setName("区域卫生");  
        btn33.setType("click");  
        btn33.setKey("33");
        
        CommonButton btn34 = new CommonButton();  
        btn34.setName("微门户");  
        btn34.setType("view");  
        btn34.setUrl("http://101.231.124.13/JiaDingWeiXin/imgnews/main.jpg");
        
        CommonButton btn35 = new CommonButton();  
        btn35.setName("商城");  
        btn35.setType("view");  
        btn35.setUrl("http://101.231.124.13/JiaDingWeiXin/onlineMart.html");
  
        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("功能");  
        mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14, btn15});  
  
        ComplexButton mainBtn2 = new ComplexButton();  
        mainBtn2.setName("服务");  
        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23, btn24, btn25});  
  
        ComplexButton mainBtn3 = new ComplexButton();  
        mainBtn3.setName("新闻中心");  
        mainBtn3.setSub_button(new CommonButton[] { btn31, btn32, btn33, btn34, btn35});  
  
        /** 
         * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br> 
         *  
         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br> 
         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br> 
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
         */  
        Menu menu = new Menu();  
        menu.setButton(new Button[] { btn11, btn21});
  
        return menu;  
    }  
}
