/**
 * Created by wade on 15/8/24.
 */
var openid;

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return (r[2]);
    return null;
}

openid = GetQueryString("openId");
if (openid != null) {
    openid_ = decodeURIComponent(openid);
}

$.get(commonUrl + 'order/listSportOrder', function (data) {
    var listTemple = Handlebars.compile($('#listBox').html());
    $('#main-list').html(listTemple(data.ret_values));
    if(data.ret_values.sex=="Female"){


    }
})