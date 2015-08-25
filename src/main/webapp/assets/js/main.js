/**
 * Created by wade on 15/8/24.
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
    $('#imgUrl').attr('href', 'addDatings.html?userId=' + userId_);
}

$.get(commonUrl + 'order/listSportOrder/' + userId_, function (data) {
    var listTemple = Handlebars.compile($('#listBox').html());
    $('#main-list').html(listTemple(data.ret_values));
})

function generateOrderCustomer() {
    var orderId = document.getElementById("orderId").value;
    var generateOrderCustomerUrl = commonUrl + 'orderCustomer/generateOrderCustomer/ ' + orderId + '/' + userId_;
    $.get(generateOrderCustomerUrl, function (data) {
        if (data.ret_code == 0) {
            $('#my-alert').modal({relatedTarget: this,})
        }
        else {
            $('#status').html("加入失败！");
            $('#my-alert').modal({relatedTarget: this,})
        }
    });
}