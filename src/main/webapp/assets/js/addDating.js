/**
 * Created by 倩钰 on 2015/8/24.
 */

$(function() {
    $('#title-btn').on('click', function() {
        $('#ad-title').modal({
            relatedTarget: this,
            onConfirm: function(e) {
                $('#title-text').html(e.data);
            },
            onCancel: function(e) {
            }
        });
    });

    $('#location-btn').on('click', function() {
        $('#ad-location').modal({
            relatedTarget: this,
            onConfirm: function(e) {
                $('#location-text').html(e.data);
            },
            onCancel: function(e) {
            }
        });
    });

    $('#startTime-btn').on('click', function() {
        $('#ad-startTime').modal({
            relatedTarget: this,
            onConfirm: function(e) {
                $('#startTime-text').html(e.data);
            },
            onCancel: function(e) {
            }
        });
    });

    $('#endTime-btn').on('click', function() {
        $('#ad-endTime').modal({
            relatedTarget: this,
            onConfirm: function(e) {
                $('#endTime-text').html(e.data);
            },
            onCancel: function(e) {
            }
        });
    });

    $('#partner-btn').on('click', function() {
        $('#ad-partner').modal({
            relatedTarget: this,
            onConfirm: function(e) {
                $('#partner-text').html(e.data);
            },
            onCancel: function(e) {
            }
        });
    });

    $('#age-btn').on('click', function() {
        $('#ad-age').modal({
            relatedTarget: this,
            onConfirm: function(e) {
                $('#age-text').html(e.data);
            },
            onCancel: function(e) {
            }
        });
    });
});
var openid;

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return (r[2]);
    return null;
}

openid = GetQueryString("openId");
if (openid != null) {
    openid_ = decodeURIComponent(openid);
}

function generateOrder(){
    var location=$('#location-text').html();
    var partner=$('#partner-text').html();
    var age=$('#age-text').html();
    var startTime=$('#startTime-text').html();
    var endTime=$('#endTime-text').html();
    var context=$('#context-text').val();
    var jsonData = {
        "location":location,
        "sex":partner,
        "ageRange":age,
        "payMethod":0,
        "transfer":false,
        "carryOne":true,
        "peopleCount":1,
        "content":context,
        "startTime": startTime,
        "endTime": endTime,
        "official":0
    };
    var data = JSON.stringify(jsonData);

    var generateOrderUrl = commonUrl + 'order/generateOrder/1/1';
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

//$(function() {
//    var nowTemp = new Date();
//    var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
//    var $myStart2 = $('#startTime-btn');
//
//    var checkin = $myStart2.datepicker({
//        onRender: function(date) {
//            return date.valueOf() < now.valueOf() ? 'am-disabled' : '';
//        }
//    }).on('changeDate.datepicker.amui', function(ev) {
//        if (ev.date.valueOf() > checkout.date.valueOf()) {
//            var newDate = new Date(ev.date)
//            newDate.setDate(newDate.getDate() + 1);
//            checkout.setValue(newDate);
//        }
//        checkin.close();
//        $('#endTime-btn')[0].focus();
//    }).data('amui.datepicker');
//
//    var checkout = $('#endTime-btn').datepicker({
//        onRender: function(date) {
//            return date.valueOf() <= checkin.date.valueOf() ? 'am-disabled' : '';
//        }
//    }).on('changeDate.datepicker.amui', function(ev) {
//        checkout.close();
//    }).data('amui.datepicker');
//
//})
