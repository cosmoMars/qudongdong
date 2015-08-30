/**
 * Created by qqy on 15/8/28.
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

var info = new Array();
$.get(commonUrl + 'sport/listSport', function (data) {
    var listTemple = Handlebars.compile($('#sports').html());
    $('#ss-main').html(listTemple(data.ret_values));

    $.each(data.ret_values, function selectSports(n, values) {
        //var div1 = document.getElementById("sports" + values.id);
        var div = document.getElementById("sportsCover" + values.id);
        //var flag;
        $("#sports" + values.id).click(function () {
            div.style.display = "block";
            info.splice(info.indexOf(values.id), 1);
        });
        $("#sportsCover" + values.id).click(function () {
            div.style.display = "none";
            info.push(values.id);
        });
    });
});

function selectSports() {
    if (info.length == 0) {
        $('#status').html("请至少选择一个项目！");
        $('#my-alert').modal({relatedTarget: this,})
    }
    else if (info.length > 4) {
        $('#status').html("最多可选择四个项目！");
        $('#my-alert').modal({relatedTarget: this,})
    }
    else {
        var infos = "";
        for (var i = 0; i < info.length; i++) {
            infos = infos + "" + info[i] + ",";
        }
        $.ajax({
            url: commonUrl + 'user/editInfo/' + userId_ + '/' + infos + '/6',
            type: "POST",
            success: function (response) {
                if (response.ret_code == 0) {
                    $('#success').html(response.ret_values);
                    $('#success-alert').modal({relatedTarget: this,})
                }
                else {
                    $('#status').html(response.ret_values);
                    $('#my-alert').modal({relatedTarget: this,})
                }
            },
            error: function () {
                $('#status').html("修改失败！");
                $('#my-alert').modal({relatedTarget: this,})
            }
        });
    }
}

function toPre() {
    location.href = 'individualInfo.html?userId=' + userId_;
}


