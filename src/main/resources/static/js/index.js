// JavaScript Document
//日期
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
});

//头部下拉
$(function () {
    $("#zhm").hover(function () {
        $("#zhcz").toggle();
    })
});
//主题效果
$(function () {
//$(".zhuti").hover(function(){
//	
//	$(".zhuti:first").children(".Play").removeClass("Play_1")
//
//	$(this).children(".Play").addClass("Play_1")
//	
//	},function(){
//		$(this).children(".Play").removeClass("Play_1")
//	})
    $(".zhuti").hover(function () {
        $("dt", this).animate({
            "top": -170
        }, 300);
        $(".Play", this).animate({
            "top": 0
        })
    }, function () {
        $("dt", this).animate({
            "top": 0
        }, 300);
        $(".Play", this).animate({
            "top": -170
        })
    })


});
//切换tab
$(function () {
    $(".thead ul li").click(function () {
        $(this).addClass("index").siblings().removeClass("index")
        var ind = $(".thead ul li ").index(this);
        $(".tbody").children("div")
            .removeClass()
            .eq(ind)
            .addClass("block")
            .siblings()
            .addClass("none")
    })
});
//点击弹出
$(function () {
    $(".select_box").click(function (event) {
        event.stopPropagation();
        $(this).find(".option").toggle();
        $(this).parent().siblings().find(".option").hide();
    });
    $(document).click(function (event) {
        var eo = $(event.target);
        if ($(".select_box").is(":visible") && eo.attr("class") != "option" && !eo.parent(".option").length)
            $('.option').hide();
    });
    /*赋值给文本框*/
    $(".option a").click(function () {
        var value = $(this).text();
        $(this).parent().siblings(".select_txt").text(value);
        $(this).parent().siblings(".select_txt").parent().siblings(".select_value").val(value);
    })
});
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
//底部鼠标滑过
window.onload = function () {
    var aLi = document.getElementById('nav').getElementsByTagName('li');
    for (var i = 0; i < aLi.length; i++) {
        aLi[i].onmouseover = function () {
            showMenu(this, 'block');
        };
        aLi[i].onmouseout = function () {
            showMenu(this, 'none');
        }
    }
    function showMenu(obj, stName) {
        var oDiv = obj.getElementsByTagName('div')[0];
        if (oDiv) {
            oDiv.style.display = stName;
        }
    }

};















