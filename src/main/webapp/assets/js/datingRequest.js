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
    var listTemple = Handlebars.compile($('#request').html());
    $('#dr-main').html(listTemple(data.ret_values));
});

function responseCustomer(result) {
    var customerId = document.getElementById("customerId").value;
    console.log(customerId);
    var responseCustomerUrl = commonUrl + 'orderCustomer/responseCustomer/' + customerId + '/' + result;
    $.get(responseCustomerUrl, function (data) {
        if (data.ret_code == 0) {
            if(result){
                $('#status').html('您已同意约练！');
            }
            else{
                $('#status').html('您已成功拒绝！');
            }
            $('#my-alert').modal({relatedTarget: this,})
        }
        else {
            $('#status').html("请求失败！");
            $('#my-alert').modal({relatedTarget: this,})
        }
    });
}
