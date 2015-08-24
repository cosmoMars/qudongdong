/**
 * Created by 倩钰 on 2015/8/24.
 */
$.get(commonUrl + 'orderCustomer/listOrderCustomer/1', function (data) {
    var listTemple = Handlebars.compile($('#request').html());
    $('#dr-main').html(listTemple(data.ret_values));
})
