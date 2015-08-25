/**
 * Created by 倩钰 on 2015/8/24.
 */

$(function () {
    $('#title-btn').on('click', function () {
        $('#ad-title').modal({
            relatedTarget: this,
            onConfirm: function (e) {
                $('#title-text').html(e.data);
            },
            onCancel: function (e) {
            }
        });
    });

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
        $('#ad-partner').modal({
            relatedTarget: this,
            onConfirm: function (e) {
                $('#partner-text').html(e.data);
            },
            onCancel: function (e) {
            }
        });
    });

    $('#age-btn').on('click', function () {
        $('#ad-age').modal({
            relatedTarget: this,
            onConfirm: function (e) {
                $('#age-text').html(e.data);
            },
            onCancel: function (e) {
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

function generateOrder() {
    var location = $('#location-text').html();
    var partner = $('#partner-text').html();
    var age = $('#age-text').html();
    var startTime = $('#startTime-text').html();
    var endTime = $('#endTime-text').html();
    var context = $('#context-text').val();
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