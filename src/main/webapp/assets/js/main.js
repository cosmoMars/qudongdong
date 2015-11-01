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
    $.get(commonUrl + 'user/validateUser/' + userId_, function (data) {
        if (data.ret_code == -1) {
            $('#imgAdd').click(function () {
                $('#status').html(data.ret_values);
                $('#my-alert').modal({relatedTarget: this,})
            });
        }
        else {
            $('#imgUrl').attr('href', 'addDatings.html?userId=' + userId_);
        }
    })
}

$('#datingRequest').attr('href', 'datingRequest.html?userId=' + userId_);
$('#individualInfo').attr('href', 'individualInfo.html?userId=' + userId_);

$.get(commonUrl + 'order/listSportOrder/' + userId_, function (data) {
    var listTemple = Handlebars.compile($('#listBox').html());
    Handlebars.registerHelper("compare", function (v1, options) {
        if (v1 == 0) {
            //满足添加继续执行
            return options.fn(this);
        }
        else {
            return options.inverse(this);
        }
    });
    Handlebars.registerHelper("judge", function (v1, options) {
        if (v1 == 1) {
            //满足添加继续执行
            return options.fn(this);
        }
        else {
            return options.inverse(this);
        }
    });
    if (data.ret_values.sportOrder.length == 0) {
        $('#main-null').html("骚年，给你抢沙发哦～");
    } else {
        $('#main-list').html(listTemple(data.ret_values));
    }
})

function generateOrderCustomer(orderId) {
    var generateOrderCustomerUrl = commonUrl + 'orderCustomer/joinActivity/' + orderId + '/' + userId_;
    $.get(generateOrderCustomerUrl, function (data) {
        if (data.ret_code == 0) {
            $('#status').html(data.ret_values);
            $('#my-alert').modal({relatedTarget: this,})
        }
        else {
            $('#status').html(data.ret_values);
            $('#my-alert').modal({relatedTarget: this,})
        }
    });
}

function toReload() {
    location.href = 'main.html?userId=' + userId_;
}