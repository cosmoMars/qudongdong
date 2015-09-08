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
//$.get(commonUrl + 'orderCustomer/listOrderCustomer/' + userId_, function (data) {
$.get(commonUrl + 'orderCustomer/listOrderCustomerByStatus/' + userId_ + '/' + false, function (data) {
    if (data.ret_code == 0) {
        if (data.ret_values.list == "") {
            $('#dr-null1').html("还没有未处理请求哦～");
        }
        else {
            var listTemple = Handlebars.compile($('#request1').html());
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
            $('#tab1').html(listTemple(data.ret_values.list));
        }
    }
    else {
        $('#dr-null1').html(data.ret_values);
    }
});

$.get(commonUrl + 'orderCustomer/listOrderCustomerByStatus/' + userId_ + '/' + true, function (data) {
    if (data.ret_code == 0) {
        if (data.ret_values.list == "") {
            $('#dr-null2').html("还没有已处理的信息哦～");
        }
        else {
            var listTemple = Handlebars.compile($('#request2').html());
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
            $('#tab2').html(listTemple(data.ret_values.list));
        }
    }
    else {
        $('#dr-null2').html(data.ret_values);
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

function tab(n) {
    if (n == 1) {
        document.getElementById('tab1').style.display = "inline";
        document.getElementById('tab2').style.display = "none";
        document.getElementById('dr-null1').style.display = "inline";
        document.getElementById('dr-null2').style.display = "none";
        document.getElementById('span1').style.borderBottom = "1px solid grey";
        document.getElementById('span2').style.borderBottom = "0px solid grey";
    }
    if (n == 2) {
        document.getElementById('tab1').style.display = "none";
        document.getElementById('tab2').style.display = "inline";
        document.getElementById('dr-null1').style.display = "none";
        document.getElementById('dr-null2').style.display = "inline";
        document.getElementById('span1').style.borderBottom = "0px solid grey";
        document.getElementById('span2').style.borderBottom = "1px solid grey";
    }
}