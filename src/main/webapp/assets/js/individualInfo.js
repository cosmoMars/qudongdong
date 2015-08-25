/**
 * Created by 倩钰 on 2015/8/24.
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

$.get(commonUrl + 'user/retrieveInfo/' + userId_, function (data) {
    var listTemple = Handlebars.compile($('#individualInfo').html());
    $('#ii-main').html(listTemple(data.ret_values));
    $('#text-gender').html(getSex(data.ret_values.sex));
    $(function () {
        $('#tel-btn').on('click', function () {
            $('#ii-tel').modal({
                relatedTarget: this,
                onConfirm: function (e) {
                    $.ajax({
                        url: commonUrl + 'user/editInfo/' + userId_ + '/' + e.data + '/0',
                        type: "POST",
                        success: function (response) {
                            if (response.ret_code == 0) {
                                $('#text-tel').html(e.data);
                                $('#my-alert').modal({relatedTarget: this,})
                            }
                        },
                        error: function () {
                            $('#status').html("修改失败！");
                            $('#my-alert').modal({relatedTarget: this,})
                        }
                    });
                },
                onCancel: function (e) {
                }
            });
        });

        $('#weChat-btn').on('click', function () {
            $('#ii-weChat').modal({
                relatedTarget: this,
                onConfirm: function (e) {
                    $.ajax({
                        url: commonUrl + 'user/editInfo/' + userId_ + '/' + e.data + +'/5',
                        type: "POST",
                        success: function (response) {
                            if (response.ret_code == 0) {
                                $('#text-weChat').html(e.data);
                                $('#my-alert').modal({relatedTarget: this,})
                            }
                        },
                        error: function () {
                            $('#status').html("修改失败！");
                            $('#my-alert').modal({relatedTarget: this,})
                        }
                    });
                },
                onCancel: function (e) {
                }
            });
        });

        $('#gender-btn').on('click', function () {
            $("input[name='gender'][value=" + data.ret_values.sex + "]").attr("checked", true);
            var gender;

            function getGender(type) {
                if (type == "Male") {
                    return "男";
                }
                else if (type == "Female") {
                    return "女";
                }
            };
            $('#ii-gender').modal({
                relatedTarget: this,
                onConfirm: function () {
                    var temp = document.getElementsByName("gender");
                    for (var i = 0; i < temp.length; i++) {
                        if (temp[i].checked)
                            gender = temp[i].value;
                    }
                    $.ajax({
                        url: commonUrl + 'user/editInfo/' + userId_ + '/' + gender + '/1',
                        type: "POST",
                        success: function (response) {
                            if (response.ret_code == 0) {
                                $('#text-gender').html(getGender(gender));
                                $('#my-alert').modal({relatedTarget: this,})
                            }
                        },
                        error: function () {
                            $('#status').html("修改失败！");
                            $('#my-alert').modal({relatedTarget: this,})
                        }
                    });
                },
                onCancel: function (e) {
                }
            });
        });

        $('#age-btn').on('click', function () {
            $('#ii-age').modal({
                relatedTarget: this,
                onConfirm: function (e) {
                    $.ajax({
                        url: commonUrl + 'user/editInfo/' + userId_ + '/' + e.data + '/2',
                        type: "POST",
                        success: function (response) {
                            if (response.ret_code == 0) {
                                $('#text-age').html(e.data + "岁");
                                $('#my-alert').modal({relatedTarget: this,})
                            }
                        },
                        error: function () {
                            $('#status').html("修改失败！");
                            $('#my-alert').modal({relatedTarget: this,})
                        }
                    });
                },
                onCancel: function (e) {
                }
            });
        });

        $('#height-btn').on('click', function () {
            $('#ii-height').modal({
                relatedTarget: this,
                onConfirm: function (e) {
                    $.ajax({
                        url: commonUrl + 'user/editInfo/' + userId_ + '/' + e.data + '/3',
                        type: "POST",
                        success: function (response) {
                            if (response.ret_code == 0) {
                                $('#text-height').html(e.data + "cm");
                                $('#my-alert').modal({relatedTarget: this,})
                            }
                        },
                        error: function () {
                            $('#status').html("修改失败！");
                            $('#my-alert').modal({relatedTarget: this,})
                        }
                    });
                },
                onCancel: function (e) {
                }
            });
        });

        $('#weight-btn').on('click', function () {
            $('#ii-weight').modal({
                relatedTarget: this,
                onConfirm: function (e) {
                    $.ajax({
                        url: commonUrl + 'user/editInfo/' + userId_ + '/' + e.data + '/4',
                        type: "POST",
                        success: function (response) {
                            if (response.ret_code == 0) {
                                $('#text-weight').html(e.data + "kg");
                                $('#my-alert').modal({relatedTarget: this,})
                            }
                        },
                        error: function () {
                            $('#status').html("修改失败！");
                            $('#my-alert').modal({relatedTarget: this,})
                        }
                    });
                },
                onCancel: function (e) {
                }
            });
        });
    });
})

