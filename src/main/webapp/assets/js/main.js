/**
 * Created by wade on 15/8/24.
 */
$.get(commonUrl + 'order/listSportOrder', function (data) {
    var listTemple = Handlebars.compile($('#listBox').html());
    $('#main-list').html(listTemple(data.ret_values));
})