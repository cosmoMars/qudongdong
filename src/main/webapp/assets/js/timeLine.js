/**
 * Created by qqy on 15/8/24.
 */

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return (r[2]);
    return null;
}

var userId = GetQueryString("userId");
if (userId != null) {
    var userId_ = decodeURIComponent(userId);
}

$.get(commonUrl + 'order/listUserOrder/' + userId_, function (data) {
    var listTemple = Handlebars.compile($('#timeLine').html());
    $('#tl-main').html(listTemple(data.ret_values.orders));
    var listTemple = Handlebars.compile($('#title').html());
    $('#tl-title').html(listTemple(data.ret_values));
})
