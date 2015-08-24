/**
 * Created by qqy on 15/8/24.
 */
$.get(commonUrl + 'order/listUserOrder/1', function (data) {
    var listTemple = Handlebars.compile($('#timeLine').html());
    $('#tl-main').html(listTemple(data.ret_values.orders));
    var listTemple = Handlebars.compile($('#title').html());
    $('#tl-title').html(listTemple(data.ret_values));
})
