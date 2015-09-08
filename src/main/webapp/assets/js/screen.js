/**
 * Created by qqy on 15/9/8.
 */

$.get(commonUrl + 'areaCode/retrieveParentArea', function (data) {
    //console.log(data.ret_values);
});

Handlebars.registerHelper('list', function (items, options) {
    var out = '';
    for (var i = 0; i < 6; i++) {
        out = out + '<li><div class="sc-border" id="city' + options.data.root[i].id + '">' + options.data.root[i].name + '</div></li>';
    }
    return out;
});

$.get(commonUrl + 'areaCode/retrieveAreaCodeByParent/1', function (data) {
    var listTemple = Handlebars.compile($('#cityList').html());
    $('#city-list').html(listTemple(data.ret_values));
    //var recommend = document.getElementById("city" + data.ret_values.id);
    //recommend.click(function(){
    //    recommend.style.color="#ff6152";
    //    recommend.style.borderColor="#ff6152";
    //})
});

var flag1 = true;
var flag2 = true;
function selectGender(n) {
    if (flag1 == true && n == 0) {
        document.getElementById("girl1").style.display = "none";
        document.getElementById("girl2").style.display = "inline";
        document.getElementById("boy1").style.display = "inline";
        document.getElementById("boy2").style.display = "none";
        //document.getElementById("nv").style.color = "#ff6152";
        //document.getElementById("nan").style.color = "grey";
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
        flag3 = false;
        flag4 = false;
        flag5 = false;
    }
    flag3 = !flag3;
    flag4 = !flag4;
    flag5 = !flag5;
    console.log(flag3, flag4, flag5, n);
}
