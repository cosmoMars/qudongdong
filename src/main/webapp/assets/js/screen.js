/**
 * Created by qqy on 15/9/8.
 */

var listUrl;

$.get(commonUrl + 'areaCode/retrieveParentArea', function (data) {
    //console.log(data.ret_values);
});

function selectCity(id, name) {
    $('#city').removeClass('am-active');
    //document.getElementById("text-city")
    //$('#text-city').val(name);
    $('#text-city').html(name);
    $('#cityId').val(id);
    console.log(id, name);
}

Handlebars.registerHelper('list', function (items, options) {
    var out = '<li><div class="sc-border" onclick="selectCity(0,\'所有地区\')">所有地区</div>';
    for (var i = 0; i < 5; i++) {
        out = out + '<li><div class="sc-border" onclick=' +
            '"selectCity(' + options.data.root[i].id + ',\'' + options.data.root[i].name + '\')">' +
            options.data.root[i].name + '</div></li>';
    }
    return out;
});

$.get(commonUrl + 'areaCode/retrieveAreaCodeByParent/1', function (data) {
    var listTemple = Handlebars.compile($('#cityList').html());
    $('#city-list').html(listTemple(data.ret_values));
});

$.get(commonUrl + 'areaCode/retrieveAreaCodeByParent/1', function (data) {
    var listTemple = Handlebars.compile($('#allCity').html());
    $('#all-city').html(listTemple(data.ret_values));
});

var flag1 = true;
var flag2 = true;
var age = new Array();
var gender = new Array(0);

function selectGender(n) {
    if (flag1 == true && n == 0) {
        document.getElementById("girl1").style.display = "none";
        document.getElementById("girl2").style.display = "inline";
        document.getElementById("boy1").style.display = "inline";
        document.getElementById("boy2").style.display = "none";
        if (gender.length != 0) {
            for (var i = 0; i < gender.length; i++) {
                gender.splice(gender.indexOf(i), 1);
            }
        }
        gender.push(2);
        $('#select-gender').val(gender[0]);
        $('#girl').show();
        $('#boy').hide();
        flag2 = false;
    }
    else if (flag2 == true && n == 1) {
        document.getElementById("girl1").style.display = "inline";
        document.getElementById("girl2").style.display = "none";
        document.getElementById("boy1").style.display = "none";
        document.getElementById("boy2").style.display = "inline";
        //document.getElementById("nan").style.color = "green";
        //document.getElementById("nv").style.color = "grey";
        if (gender.length != 0) {
            for (var i = 0; i < gender.length; i++) {
                gender.splice(gender.indexOf(i), 1);
            }
        }
        gender.push(1);
        $('#select-gender').val(gender[0]);
        $('#girl').hide();
        $('#boy').show();
        flag1 = false;
    }
    else {
        document.getElementById("girl1").style.display = "inline";
        document.getElementById("girl2").style.display = "none";
        document.getElementById("boy1").style.display = "inline";
        document.getElementById("boy2").style.display = "none";
        //document.getElementById("nv").style.color = "grey";
        //document.getElementById("nan").style.color = "grey";
        if (gender.length != 0) {
            for (var i = 0; i < gender.length; i++) {
                gender.splice(gender.indexOf(i), 1);
            }
        }
        gender.push(0);
        $('#select-gender').val(gender[0]);
        flag1 = false;
        flag2 = false;
    }
    flag1 = !flag1;
    flag2 = !flag2;
    console.log(flag1, flag2, n);
}

var flag3 = true;
var flag4 = true;
var flag5 = true;
function selectAge(n) {
    if (flag3 == true && n == 0) {
        document.getElementById("age0-1").style.display = "none";
        document.getElementById("age0-2").style.display = "inline";
        document.getElementById("age1-1").style.display = "inline";
        document.getElementById("age1-2").style.display = "none";
        document.getElementById("age2-1").style.display = "inline";
        document.getElementById("age2-2").style.display = "none";
        document.getElementById("age0-3").style.display = "none";
        document.getElementById("age0-4").style.display = "inline";
        document.getElementById("age1-3").style.display = "inline";
        document.getElementById("age1-4").style.display = "none";
        document.getElementById("age2-3").style.display = "inline";
        document.getElementById("age2-4").style.display = "none";
        if (age.length != 0) {
            for (var i = 0; i < age.length; i++) {
                age.splice(age.indexOf(i), 1);
            }
        }
        age.push(0);
        $('#text-age').val(age[0]);
        flag4 = false;
        flag5 = false;
    }
    else if (flag4 == true && n == 1) {
        document.getElementById("age0-1").style.display = "inline";
        document.getElementById("age0-2").style.display = "none";
        document.getElementById("age1-1").style.display = "none";
        document.getElementById("age1-2").style.display = "inline";
        document.getElementById("age2-1").style.display = "inline";
        document.getElementById("age2-2").style.display = "none";
        document.getElementById("age0-3").style.display = "inline";
        document.getElementById("age0-4").style.display = "none";
        document.getElementById("age1-3").style.display = "none";
        document.getElementById("age1-4").style.display = "inline";
        document.getElementById("age2-3").style.display = "inline";
        document.getElementById("age2-4").style.display = "none";
        if (age.length != 0) {
            for (var i = 0; i < age.length; i++) {
                age.splice(age.indexOf(i), 1);
            }
        }
        age.push(1);
        $('#text-age').val(age[0]);
        flag3 = false;
        flag5 = false;
    }
    else if (flag5 == true && n == 2) {
        document.getElementById("age0-1").style.display = "inline";
        document.getElementById("age0-2").style.display = "none";
        document.getElementById("age1-1").style.display = "inline";
        document.getElementById("age1-2").style.display = "none";
        document.getElementById("age2-1").style.display = "none";
        document.getElementById("age2-2").style.display = "inline";
        document.getElementById("age0-3").style.display = "inline";
        document.getElementById("age0-4").style.display = "none";
        document.getElementById("age1-3").style.display = "inline";
        document.getElementById("age1-4").style.display = "none";
        document.getElementById("age2-3").style.display = "none";
        document.getElementById("age2-4").style.display = "inline";
        if (age.length != 0) {
            for (var i = 0; i < age.length; i++) {
                age.splice(age.indexOf(i), 1);
            }
        }
        age.push(2);
        $('#text-age').val(age[0]);
        flag3 = false;
        flag4 = false;
    }
    else {
        document.getElementById("age0-1").style.display = "inline";
        document.getElementById("age0-2").style.display = "none";
        document.getElementById("age1-1").style.display = "inline";
        document.getElementById("age1-2").style.display = "none";
        document.getElementById("age2-1").style.display = "inline";
        document.getElementById("age2-2").style.display = "none";
        document.getElementById("age0-3").style.display = "inline";
        document.getElementById("age0-4").style.display = "none";
        document.getElementById("age1-3").style.display = "inline";
        document.getElementById("age1-4").style.display = "none";
        document.getElementById("age2-3").style.display = "inline";
        document.getElementById("age2-4").style.display = "none";
        if (age.length != 0) {
            for (var i = 0; i < age.length; i++) {
                age.splice(age.indexOf(i), 1);
            }
        }
        age.push(2);
        $('#text-age').val(age[0]);
        flag3 = false;
        flag4 = false;
        flag5 = false;
    }
    flag3 = !flag3;
    flag4 = !flag4;
    flag5 = !flag5;
    console.log(flag3, flag4, flag5, n);
}

function screenChange(){
    var screenCity = $('#cityId').val();
    var screenAge = $('#text-age').val();
    var screenGender = $('#select-gender').val();
    console.log(screenCity,screenAge,screenGender);
    listUrl='';
}

function cancelScreen(){
    $('#screen').removeClass('am-active');
}


