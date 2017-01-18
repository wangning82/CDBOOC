$(function () {
    var dayNames = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
    var mydate = new Date();

    function show() {
        var str = "" + mydate.getFullYear() + "/";
        str += (mydate.getMonth() + 1) + "/";
        str += mydate.getDate();
        return str;
    }

    $("#shijian").html("" + dayNames[mydate.getDay()] + "&nbsp;&nbsp;&nbsp;&nbsp;" + show());

    //头部下拉
    $("#zhm").hover(function () {
        $("#zhcz").toggle();
    });

    //点击弹出
    $(".select_box").click(function (event) {
        event.stopPropagation();
        $(this).find(".option").toggle();
        $(this).parent().siblings().find(".option").hide();
    });

    /*
    $(document).click(function (event) {
        var eo = $(event.target);
        if ($(".select_box").is(":visible") && eo.attr("class") != "option" && !eo.parent(".option").length)
            $('.option').hide();
    });
    */

    /*赋值给文本框*/
    $(".option a").click(function () {
        var value = $(this).text();
        $(this).parent().siblings(".select_txt").text(value);
        $(this).parent().siblings(".select_txt").parent().siblings(".select_value").val(value);
    });

    //底部鼠标滑过
    $("#nav ul:eq(0) li").mouseover(function () {
        $(this).find("div:eq(0)").css("display", "block");
    });
    $("#nav ul:eq(0) li").mouseout(function () {
        $(this).find("div:eq(0)").css("display", "none");
    });

});

// 鼠标滑过效果
function hoverIn(obj) {
    $(obj).find("dt").animate({
        "top": -170
    }, 300);
    $(obj).find(".Play").animate({
        "top": 0
    });
}

// 鼠标滑出效果
function hoverOut(obj) {
    $(obj).find("dt").animate({
        "top": 0
    }, 300);
    $(obj).find(".Play").animate({
        "top": -170
    });
}

// 切换TAB
function showTab(obj) {
    $(obj).siblings().removeClass("index");
    $(obj).addClass("index");
    var index = $(obj).parent("ul").children("li").index(obj);
    $(obj).parent().parent().next().children("div").removeClass().eq(index).addClass("block").siblings().addClass("none");
}

//选择按钮
function hide(obj) {
    if (obj.className) {
        if (obj.className == "b0") {
            obj.className = "b1";
        }
        else {
            obj.className = "b0";
        }
    }
}
//弹出隐藏层
function ShowDiv(show_div, bg_div) {
    document.getElementById(show_div).style.display = 'block';
    document.getElementById(bg_div).style.display = 'block';
    var bgdiv = document.getElementById(bg_div);
    bgdiv.style.width = document.body.scrollWidth;
    $("#" + bg_div).height($(document).height());
}
//关闭弹出层
function CloseDiv(show_div, bg_div) {
    document.getElementById(show_div).style.display = 'none';
    document.getElementById(bg_div).style.display = 'none';
}















