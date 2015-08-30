package com.wonders.xlab.qudongdong.utils;

import com.bcloud.msg.http.HttpSender;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by lixuanwu on 15/8/20.
 */
public class SmsUtils {

    private static final String SMS_SRV_URL = "http://222.73.117.158/msg/";
    private static final String SMS_ACT_USER = "wan-vip4";
    private static final String SMS_ACT_PWD = "Txb123456";

    private static final String SMS_NOTIFY_USER_CONTENT1 = "微信名为“%s”的朋友愿意“piapia”地过来一起躁,请打开微信躁动服务号,";
    private static final String SMS_NOTIFY_USER_CONTENT2 = "查看您发布于%s";
    private static final String SMS_NOTIFY_USER_CONTENT3 = "在%s约跑的信息。";

    private static final String SMS_NOTIFY_USER_SUCCEED_CONTENT1 = "棒呆! 微信用户“%s”,已被您成功约到。";
    private static final String SMS_NOTIFY_USER_SUCCEED_CONTENT2 = "对方微信号：%s，快去添加Ta为微信好友深入交流吧！";
//    private static final String SMS_NOTIFY_USER_SUCCEED_CONTENT3 = "%s赴约，不要迟到咯!";


//    棒呆! 微信用户“%s”,已被您成功约到。对方微信号：%s，快去添加Ta为微信好友深入交流吧！

    private static final String SMS_REFUSES_USER_CONTENT = "微信用户“%s”去了冥王星，无法赴约。我们已为您取消。您可以通过躁动微信服务号寻找其他小伙伴一起躁！";

    private SmsUtils() {

    }


    /**
     * 发送成功预约信息
     *
     * @param mobiles
     * @param userName
     * @param address
     * @return
     */
    public static int sendInviteMessage(String mobiles, String userName, String date, String address) {
        try {
            String content = String.format(SMS_NOTIFY_USER_CONTENT1, userName) + String.format(SMS_NOTIFY_USER_CONTENT2, date) + String.format(SMS_NOTIFY_USER_CONTENT3, address);
            String resultCode = HttpSender.batchSend(SMS_SRV_URL, SMS_ACT_USER, SMS_ACT_PWD, mobiles, content, true, null, null);
            String status = resultCode.substring(resultCode.indexOf(",") + 1, resultCode.indexOf(",") + 2);
            if (StringUtils.equals(status, "0")) {
                return 0;
            }
        } catch (Exception e) {
            return 1;
        }
        return 1;
    }

    /**
     * 发送接受预约信息
     *
     * @param mobiles
     * @param userName
     * @return
     */
    public static int sendInviteSucceedMessage(String mobiles, String userName, String toUser) {
        try {
            String content = String.format(SMS_NOTIFY_USER_SUCCEED_CONTENT1, userName) + String.format(SMS_NOTIFY_USER_SUCCEED_CONTENT2, toUser);
            String resultCode = HttpSender.batchSend(SMS_SRV_URL, SMS_ACT_USER, SMS_ACT_PWD, mobiles, content, true, null, null);
            String status = resultCode.substring(resultCode.indexOf(",") + 1, resultCode.indexOf(",") + 2);
            if (StringUtils.equals(status, "0")) {
                return 0;
            }
        } catch (Exception e) {
            return 1;
        }
        return 1;
    }

    /**
     * 发送拒绝预约信息
     *
     * @param mobiles
     * @param userName
     * @return
     */
    public static int sendRefusesMessage(String mobiles, String userName) {
        try {
            String content = String.format(SMS_REFUSES_USER_CONTENT, userName);
            String resultCode = HttpSender.batchSend(SMS_SRV_URL, SMS_ACT_USER, SMS_ACT_PWD, mobiles, content, true, null, null);
            String status = resultCode.substring(resultCode.indexOf(",") + 1, resultCode.indexOf(",") + 2);
            if (StringUtils.equals(status, "0")) {
                return 0;
            }
        } catch (Exception e) {
            return 1;
        }
        return 1;
    }

    public static void main(String[] args) {
        sendRefusesMessage("13917823781", "芋头大大");
//        sendInviteSucceedMessage("13917823781", "芋头大大", "2015-8－22:17:30", "世纪公园");
        sendInviteMessage("13917823781", "芋头大大", "2015-8－22:17:30", "世纪公园");
    }

}