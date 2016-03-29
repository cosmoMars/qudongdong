/**
 * Created by violet_qqy on 16/3/29.
 */

$(document).ready(function () {
    $.ajax({
        type: 'GET',
        url: commonUrl + 'v1/wantToJoin/getAllClubInfos',
        success: function (data) {
            if (data.ret_code === 0) {
                var listTemple = Handlebars.compile($('#main').html());
                $('#body').html(listTemple(data.ret_values));

                var bannerList = [];
                for (var i in data.ret_values.picList) {
                    bannerList.push({"img": data.ret_values.picList[i].picUrl});
                }
                bannerInit(bannerList);
            } else {
                var errorContent = $("<p " +
                    "style='text-align: center;margin-top: 30px;'>啊哦!页面出错啦~</p>");
                errorContent.appendTo($('#body'));
            }
        },
        error: function () {
            var errorContent = $("<p " +
                "style='text-align: center;margin-top: 30px;'>啊哦!页面出错啦~</p>");
            errorContent.appendTo($('#body'));
        }
    });
});

function bannerInit(bannerList) {
    var template = Handlebars.compile($('#banner').text());
    var data = {
        slider: {
            "theme": "a1",
            "content": bannerList
        }
    };
    var html = template(data);
    $('#banner').before(html);

    $.each(['slider'], function (i, m) {
        var module = $.AMUI[m];
        module && module.init && module.init();
    });
}