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
        var div = document.getElementById("sports" + values.id);
        var select = "#sports" + values.id;
        var flag;
        $(select).click(function () {
            if (flag) {
                div.style.backgroundColor = "white";
                div.style.color = "grey";
                info.splice(info.indexOf(values.id), 1);
                console.log(info);
            } else {
                div.style.backgroundColor = "#4EC296";
                div.style.color = "white";
                info.push(values.id);
                console.log(info);
            }
            flag = !flag;
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


