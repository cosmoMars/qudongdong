package com.wonders.xlab.qudongdong.menu.create;

import com.wonders.xlab.qudongdong.menu.model.CommonButton;
import com.wonders.xlab.qudongdong.menu.model.ComplexButton;

/**
 * Created by mars on 15/10/31.
 */
public class MainMenuManager {

    public static void main(String[] args) {
        MainMenuManager menuTest = new MainMenuManager();
        String resString = menuTest.getMenuJosn();
        System.out.println(resString);
    }

    public String getMenuJosn() {

        //      菜单1----------------------------------------------------------------
        CommonButton joinClub = new CommonButton();
        joinClub.setName("我要入会");
        joinClub.setType("view");
//        joinClub.setKey("joinClub");
        joinClub.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxab1f6505deb6588c&redirect_uri=http://101.231.124.8:45698/qdd/weChatNotify/recall&response_type=code&scope=snsapi_base&state=joinClub#wechat_redirect");

        CommonButton needCoach = new CommonButton();
        needCoach.setName("我要私教");
        needCoach.setType("view");
//        needCoach.setKey("needCoach");
        needCoach.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxab1f6505deb6588c&redirect_uri=http://101.231.124.8:45698/qdd/weChatNotify/recall&response_type=code&scope=snsapi_base&state=needCoach#wechat_redirect");

        CommonButton activity = new CommonButton();
        activity.setName("活动发起");
        activity.setType("click");
        activity.setKey("activity");

        CommonButton booking = new CommonButton();
        booking.setName("一站式预约");
        booking.setType("click");
        booking.setKey("booking");

        ComplexButton meet = new ComplexButton();
        meet.setName("我的奇遇");
        meet.setSub_button(new CommonButton[]{joinClub, needCoach, activity, booking});

        //      菜单2----------------------------------------------------------------
        CommonButton healthCustomize = new CommonButton();
        healthCustomize.setName("健康定制");
        healthCustomize.setType("click");
        healthCustomize.setKey("healthCustomize");

        CommonButton inquiry = new CommonButton();
        inquiry.setName("线上问诊");
        inquiry.setType("click");
        inquiry.setKey("inquiry");

        ComplexButton healthManager = new ComplexButton();
        healthManager.setName("健康管理");
        healthManager.setSub_button(new CommonButton[]{healthCustomize, inquiry});

        //      菜单3----------------------------------------------------------------

        CommonButton personalInfo = new CommonButton();
        personalInfo.setName("个人信息");
        personalInfo.setType("click");
        personalInfo.setKey("personalInfo");

        CommonButton sportRecipe = new CommonButton();
        sportRecipe.setName("运动处方");
        sportRecipe.setType("click");
        sportRecipe.setKey("sportRecipe");

        CommonButton healthReport = new CommonButton();
        healthReport.setName("健康报告");
        healthReport.setType("click");
        healthReport.setKey("healthReport");

        CommonButton mySport = new CommonButton();
        mySport.setName("我的约战");
        mySport.setType("click");
        mySport.setKey("mySport");


        ComplexButton myManager = new ComplexButton();
        myManager.setName("我的管家");
        myManager.setSub_button(new CommonButton[]{personalInfo, sportRecipe, healthReport, mySport});

        StringBuffer bf = new StringBuffer();
        bf.append("{");
        bf.append("\"button\":[");

        bf.append("{");
        bf.append("\"name\":\"" + meet.getName() + "\",");
        bf.append("\"sub_button\":[");
        CommonButton[] buttons = (CommonButton[]) meet.getSub_button();
        for (CommonButton commonButton : buttons) {
            bf.append("{");
            if (commonButton.getKey() == null) {
                bf.append("\"name\":\"" + commonButton.getName() + "\",");
                bf.append(" \"type\":\"view\",");
                bf.append("\"url\":\"" + commonButton.getUrl() + "\"");
            } else {
                bf.append("\"name\":\"" + commonButton.getName() + "\",");
                bf.append(" \"type\":\"click\",");
                bf.append("\"key\":\"" + commonButton.getKey() + "\"");
            }
            bf.append("},");
        }
        bf.deleteCharAt(bf.length() - 1);
        bf.append("]");
        bf.append("},");

        bf.append("{");
        bf.append("\"name\":\"" + healthManager.getName() + "\",");
        bf.append("\"sub_button\":[");
        CommonButton[] buttons2 = (CommonButton[]) healthManager.getSub_button();
        for (CommonButton commonButton : buttons2) {
            bf.append("{");
            if (commonButton.getKey() == null) {
                bf.append("\"name\":\"" + commonButton.getName() + "\",");
                bf.append(" \"type\":\"view\",");
                bf.append("\"url\":\"" + commonButton.getUrl() + "\"");
            } else {
                bf.append("\"name\":\"" + commonButton.getName() + "\",");
                bf.append(" \"type\":\"click\",");
                bf.append("\"key\":\"" + commonButton.getKey() + "\"");
            }
            bf.append("},");
        }
        bf.deleteCharAt(bf.length() - 1);
        bf.append("]");
        bf.append("},");

        bf.append("{");
        bf.append("\"name\":\"" + myManager.getName() + "\",");
        bf.append("\"sub_button\":[");
        CommonButton[] buttons3 = (CommonButton[]) myManager.getSub_button();
        for (CommonButton commonButton : buttons3) {
            bf.append("{");
            if (commonButton.getKey() == null) {
                bf.append("\"name\":\"" + commonButton.getName() + "\",");
                bf.append(" \"type\":\"view\",");
                bf.append("\"url\":\"" + commonButton.getUrl() + "\"");
            } else {
                bf.append("\"name\":\"" + commonButton.getName() + "\",");
                bf.append(" \"type\":\"click\",");
                bf.append("\"key\":\"" + commonButton.getKey() + "\"");
            }
            bf.append("},");
        }
        bf.deleteCharAt(bf.length() - 1);
        bf.append("]");
        bf.append("}");

        bf.append("]");
        bf.append("}");
        return bf.toString();
    }
}
