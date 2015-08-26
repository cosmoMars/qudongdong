package com.wonders.xlab.qudongdong.utils;

import com.bcloud.msg.http.HttpSender;
import org.apache.commons.lang3.StringUtils;
import java.util.Calendar;

/**
 * Created by lixuanwu on 15/8/20.
 */
public class SmsUtils {

    private static final String SMS_SRV_URL = "http://222.73.117.158/msg/";
    private static final String SMS_ACT_USER = "wan-vip4";
    private static final String SMS_ACT_PWD = "Txb123456";
    private static final String SMS_NOTIFY_USER_CONTENT1 = "您于" + DateUtils.convertToYYYYMMDDHHStr(Calendar.getInstance().getTime()) + "在%s约跑的信息已被";
    private static final String SMS_NOTIFY_USER_CONTENT2 = "用户%s点击一起躁。请打开微信躁动微信服务号查看。";
    private static final String SMS_REFUSES_USER_CONTENT = "您于" + DateUtils.convertToYYYYMMDDHHStr(Calendar.getInstance().getTime()) + "邀请的微信用户%s去了冥王星无法回应您的邀约请求，已为您取消。您可以通过躁动服务号和别的小伙伴一起躁啦！";

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
    public static int sendMessage(String mobiles, String userName, String address) {
        try {
            String content = String.format(SMS_NOTIFY_USER_CONTENT1, address) + String.format(SMS_NOTIFY_USER_CONTENT2, userName);
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

}