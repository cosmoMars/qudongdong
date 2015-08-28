/**
 * Created by qqy on 15/8/27.
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

    $('#numOfP-btn').on('click', function () {
        $('#ad-numOfP').modal({
            relatedTarget: this,
            onConfirm: function (e) {
                $('#numOfP-text').html(e.data);
            },
            onCancel: function (e) {
            }
        });
    });

    $('#startTime-btn').on('click', function () {
        openOneSlot('start');
    });

    $('#endTime-btn').on('click', function () {
        openOneSlot('end');
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

//function GetQueryString(name) {
//    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
//    var r = window.location.search.substr(1).match(reg);
//    if (r != null) return (r[2]);
//    return null;
//}
//
//var userId = GetQueryString("userId");
//if (userId != null) {
//    var userId_ = decodeURIComponent(userId);
//}

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

function generateOfficialOrder() {
    var numOfP = $('#numOfP-text').html();
    var location = $('#location-text').html();
    var partner = getPartner($('#partner-text').html());
    var age = getAge($('#age-text').html());
    var startTime = $('#startTime-text').attr('data-time')||'';
    var endTime = $('#endTime-text').attr('data-time')||'';
    if(new Date(startTime)>= new Date(endTime)){
        $('#status').html("开始时间必须小于结束时间哦！");
        $('#my-alert').modal({relatedTarget: this});
        return false;
    }
    var content = $('#content-text').val();
    if (location.length == 0 || partner.length == 0 || age.length == 0 || startTime.length == 0 || endTime.length == 0 || content.length == 0) {
        $('#status').html("您还有字段未填写哦！");
        $('#my-alert').modal({relatedTarget: this,});
    }
    else if (content.length >= 13) {
        $('#status').html("说明仅限12个字哦！");
        $('#my-alert').modal({relatedTarget: this,});
    }
    else if (location.length >= 16) {
        $('#status').html("地点仅限15个字哦！");
        $('#my-alert').modal({relatedTarget: this,});
    }
    else {

        var jsonData = {
            "location": location,
            "sex": partner,
            "ageRange": age,
            "payMethod": 0,
            "transfer": false,
            "carryOne": true,
            "peopleCount": numOfP,
            "content": content,
            "startTime": startTime,
            "endTime": endTime,
            "official": 1
        };

        var data = JSON.stringify(jsonData);

        var generateOrderUrl = commonUrl + 'order/generateOrder/' + '1' + '/1';

        $.ajax({
            url: generateOrderUrl,
            type: "POST",
            data: data,
            dataType: "json",
            headers: {"Accept": "application/json", "Content-Type": "application/json; charset=UTF-8"},
            success: function (response) {
                if (response.ret_code == 0) {
                    $('#status').html(response.ret_values);
                    $('#success-alert').modal({relatedTarget: this,})
                }
                else {
                    $('#status').html(response.ret_values);
                    $('#my-alert').modal({relatedTarget: this,})
                }
            },
            error: function () {
                $('#status').html("发布失败！");
                $('#my-alert').modal({relatedTarget: this,})
            }
        });
    }
}

function toMain() {
    location.href = 'main.html?userId='+userId_;
}

function startDone() {
    var results = SpinningWheel.getSelectedValues();
    var startDate = new Date(), month = startDate.getMonth() + 1,
        startDateString = startDate.getFullYear() + '-' + month + '-' + startDate.getDate() + ' ' + results.keys.join(':');
    var $startTime = $('#startTime-text');
    $startTime.html(results.values.join(''));
    $startTime.attr('data-time', startDateString);
//        document.getElementById('result').innerHTML = 'values: ' + results.values.join(' ') + '<br />keys: ' + results.keys.join(', ');
}
function endDone() {
    var results = SpinningWheel.getSelectedValues();
    var endDate = new Date(), month = endDate.getMonth() + 1,
        endDateString = endDate.getFullYear() + '-' + month + '-' + endDate.getDate() + ' ' + results.keys.join(':');
    //console.log(endDateString);
    var $endTime = $('#endTime-text');
    $endTime.html(results.values.join(''));
    $endTime.attr('data-time', endDateString);
//        document.getElementById('result').innerHTML = 'values: ' + results.values.join(' ') + '<br />keys: ' + results.keys.join(', ');
}

function cancel() {
//        document.getElementById('result').innerHTML = 'cancelled!';
}


window.addEventListener('load', function () {
    setTimeout(function () {
        window.scrollTo(0, 0);
    }, 100);
}, true);

function openOneSlot(type) {
    var hours = {
        0: '00点',
        1: '01点',
        2: '02点',
        3: '03点',
        4: '04点',
        5: '05点',
        6: '06点',
        7: '07点',
        8: '08点',
        9: '09点',
        10: '10点',
        11: '11点',
        12: '12点',
        13: '13点',
        14: '14点',
        15: '15点',
        16: '16点',
        17: '17点',
        18: '18点',
        19: '19点',
        20: '20点',
        21: '21点',
        22: '22点',
        23: '23点'
    };
    var mins = {
        0: '00分',
        5: '05分',
        10: '10分',
        15: '15分',
        20: '20分',
        25: '25分',
        30: '30分',
        35: '35分',
        40: '40分',
        45: '45分',
        50: '50分',
        55: '55分',
    };
    SpinningWheel.addSlot(hours, 'center', 11);
    SpinningWheel.addSlot(mins, 'center', 30);
    SpinningWheel.setCancelAction(cancel);
    if (type === 'start')
        SpinningWheel.setDoneAction(startDone);
    else
        SpinningWheel.setDoneAction(endDone);
    SpinningWheel.open();
}