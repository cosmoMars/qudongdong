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
$.get(commonUrl + '/weChat/getJsConfig/'+location.href, function(data) {
    wxconfig(data);
});

function wxconfig(data) {
    wx.config({
        debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: data.appId, // 必填，公众号的唯一标识
        timestamp: data.timestamp, // 必填，生成签名的时间戳
        nonceStr: data.nonceStr, // 必填，生成签名的随机串
        signature: data.signature,// 必填，签名，见附录1
        jsApiList: [
            'hideOptionMenu',
        ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
    });
}

wx.ready(function () {
    //是否支持js接口
    document.querySelector('#checkJsApi').onclick = function () {
        wx.checkJsApi({
            jsApiList: [
                'onMenuShareTimeline',
                'onMenuShareAppMessage',
                'onMenuShareQQ',
                'onMenuShareWeibo'
            ],
            success: function (res) {
                alert(JSON.stringify(res));
            }
        });
    };
})