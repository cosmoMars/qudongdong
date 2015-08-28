/**
 * Created by 倩钰 on 2015/8/24.
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
$.get(commonUrl + 'orderCustomer/listOrderCustomer/' + userId_, function (data) {
    if (data.ret_code == 0) {
        if (data.ret_values == "") {
            $('#dr-null').html("还没有小伙伴请求哦～");
        }
        else {
            var listTemple = Handlebars.compile($('#request').html());
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
            Handlebars.registerHelper("ifNull", function (v1, options) {
                console.log(v1);
                if (v1.length == 0) {
                    //满足添加继续执行
                    return options.fn(this);
                }
                else {
                    return options.inverse(this);
                }
            });
            $('#dr-main').html(listTemple(data.ret_values));
        }
    }
    else {
        $('#dr-null').html(data.ret_values);
    }
});

function toReload() {
    location.reload();
}

function responseCustomer(result, customerId) {
    var responseCustomerUrl = commonUrl + 'orderCustomer/responseCustomer/' + customerId + '/' + result;
    $.get(responseCustomerUrl, function (data) {
        if (data.ret_code == 0) {
            if (result) {
                $('#success').html(data.ret_values);
            }
            else {
                $('#success').html(data.ret_values);
            }
            $('#success-alert').modal({relatedTarget: this,})

        }
        else {
            $('#status').html(data.ret_values);
            $('#my-alert').modal({relatedTarget: this,})
        }
    });
}

$('#main').attr('href', 'main.html?userId=' + userId_);
$('#individualInfo').attr('href', 'individualInfo.html?userId=' + userId_);
