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
    $('#imgUrl').attr('href','addDatings.html?userId='+userId_);
}

$.get(commonUrl + 'order/listSportOrder/'+userId_, function (data) {
    var listTemple = Handlebars.compile($('#listBox').html());
    $('#main-list').html(listTemple(data.ret_values));
    if(data.ret_values.sex=="Female"){


    }
})