/**
 * Created by 倩钰 on 2015/8/24.
 */

$(function () {
    /*$('#title-btn').on('click', function () {
     $('#ad-title').modal({
     relatedTarget: this,
     onConfirm: function (e) {
     $('#title-text').html(e.data);
     },
     onCancel: function (e) {
     }
     });
     });*/

    $('#location-btn').on('click', function () {
        $('#ad-location').modal({
            relatedTarget: this,
            onConfirm: function (e) {
                $('#location-text').html(e.data);
            },
            onCancel: function (e) {
            }
        });
    });

    $('#startTime-btn').on('click', function () {
        $('#ad-startTime').modal({
            relatedTarget: this,
            onConfirm: function (e) {
                $('#startTime-text').html(e.data);
            },
            onCancel: function (e) {
            }
        });
    });

    $('#endTime-btn').on('click', function () {
        $('#ad-endTime').modal({
            relatedTarget: this,
            onConfirm: function (e) {
                $('#endTime-text').html(e.data);
            },
            onCancel: function (e) {
            }
        });
    });

    $('#partner-btn').on('click', function () {
        var partner;
        $('#ad-partner').modal({
            relatedTarget: this,
            onConfirm: function () {
                var temp = document.getElementsByName("partner");
                for (var i = 0; i < temp.length; i++) {
                    if (temp[i].checked)
                        partner = temp[i].value;
                }
                $('#partner-text').html(partner);
            },
            onCancel: function () {
            }
        });
    });

    $('#age-btn').on('click', function () {
        var age;
        $('#ad-age').modal({
            relatedTarget: this,
            onConfirm: function () {
                var temp = document.getElementsByName("age");
                for (var i = 0; i < temp.length; i++) {
                    if (temp[i].checked)
                        age = temp[i].value;
                }
                $('#age-text').html(age);
            },
            onCancel: function () {
            }
        });
    });
});

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

function getPartner(type) {
    if (type == "男") {
        return 1;
    }
    else if (type == "女") {
        return 2;
    }
    else
        return 0;
};

function getAge(type) {
    if (type == "18-24岁") {
        return 0;
    }
    else if (type == "25-30岁") {
        return 1;
    }
    else
        return 2;
};

function generateOrder() {
    var location = $('#location-text').html();
    var partner = getPartner($('#partner-text').html());
    var age = getAge($('#age-text').html());
    var startTime = $('#startTime-text').html();
    var endTime = $('#endTime-text').html();
    var context = $('#context-text').val();
    if (location.length == 0 || partner.length == 0 || age.length == 0 || startTime.length == 0 || endTime.length == 0 || context.length == 0&&context.length >= 12) {
        $('#status').html("您还有字段未填写哦！");
        $('#my-alert').modal({relatedTarget: this,})
    }
    else if (context.length >= 12){
        $('#status').html("说明仅限12个字哦！");
        $('#my-alert').modal({relatedTarget: this,})
    }

    var jsonData = {
        "location": location,
        "sex": partner,
        "ageRange": age,
        "payMethod": 0,
        "transfer": false,
        "carryOne": true,
        "peopleCount": 1,
        "content": context,
        "startTime": startTime,
        "endTime": endTime,
        "official": 0
    };
    var data = JSON.stringify(jsonData);

    var generateOrderUrl = commonUrl + 'order/generateOrder/' + userId_ + '/1';
    $.ajax({
        url: generateOrderUrl,
        type: "POST",
        data: data,
        dataType: "json",
        headers: {"Accept": "application/json", "Content-Type": "application/json; charset=UTF-8"},
        success: function (response) {
            if (response.ret_code == 0) {
                $('#my-alert').modal({relatedTarget: this,})
            }
        },
        error: function () {
            $('#status').html("发布失败！");
            $('#my-alert').modal({relatedTarget: this,})
        }
    });
}