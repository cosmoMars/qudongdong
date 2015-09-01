/**
 * Created by wade on 15/8/24.
 */
var commonUrl = 'http://101.231.124.8:45698/qdd/';
//var commonUrl = 'http://localhost:8080/';
function getSex(type) {
    if (type == "Male") {
        return "男";
    }
    else if (type == "Female") {
        return "女";
    }
    else
        return "不限"
};

/*!
 * http://xxxxx.com/ 谢俊个人博客
 * 使用前需要配置安全域名 在公众号后台微信功能设置
 * 引用js文件 http://res.wx.qq.com/open/js/jweixin-1.0.0.js 还有 jquery.js
 * Copyright 2015,谢俊
 * 时间:2015年6月9号 10:18
 */
//$.get(commonUrl + 'weChat/getJsConfig/'+location.href, function(data) {
//    wxconfig(data);
//});
var datas = {
    url: location.href
};
$.ajax({
    url: commonUrl + 'weChat/getJsConfig/',
    type: "POST",
    data:datas,
    success: function (response) {
        wxconfig(response);
    },
    error: function () {
    }
});



function wxconfig(data) {
    wx.config({
        debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: data.appId, // 必填，公众号的唯一标识
        timestamp: data.timestamp, // 必填，生成签名的时间戳
        nonceStr: data.nonceStr, // 必填，生成签名的随机串
        signature: data.signature,// 必填，签名，见附录1
        jsApiList: [
            'checkJsApi',
            'onMenuShareTimeline',
            'onMenuShareAppMessage',
            'onMenuShareQQ',
            'onMenuShareWeibo',
            'onMenuShareQZone',
            'hideMenuItems',
            'showMenuItems',
            'hideAllNonBaseMenuItem',
            'showAllNonBaseMenuItem',
            'translateVoice',
            'startRecord',
            'stopRecord',
            'onVoiceRecordEnd',
            'playVoice',
            'onVoicePlayEnd',
            'pauseVoice',
            'stopVoice',
            'uploadVoice',
            'downloadVoice',
            'chooseImage',
            'previewImage',
            'uploadImage',
            'downloadImage',
            'getNetworkType',
            'openLocation',
            'getLocation',
            'hideOptionMenu',
            'showOptionMenu',
            'closeWindow',
            'scanQRCode',
            'chooseWXPay',
            'openProductSpecificView',
            'addCard',
            'chooseCard',
            'openCard'
        ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });
}

wx.ready(function () {
    //是否支持js接口
    wx.hideOptionMenu();
    wx.getLocation({
        type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
        success: function (res) {
            var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
            var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
            var speed = res.speed; // 速度，以米/每秒计
            var accuracy = res.accuracy; // 位置精度
        }

    });
})
