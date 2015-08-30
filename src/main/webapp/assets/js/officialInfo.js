/**
 * Created by qqy on 15/8/30.
 */

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return (r[2]);
    return null;
}

var orderId = GetQueryString("orderId");
if (orderId != null) {
    var orderId_ = decodeURIComponent(orderId);
}

$.get(commonUrl + 'order/getOrderHtmlInfo/' + orderId_, function (data) {
    $('#oi-main').html(data.ret_values);
})