$(function () {
    //底部鼠标滑过
    $("#nav ul:eq(0) li").mouseover(function () {
        $(this).find("div:eq(0)").css("display", "block");
    });
    $("#nav ul:eq(0) li").mouseout(function () {
        $(this).find("div:eq(0)").css("display", "none");
    });

    $(".left ul:eq(0) li").click(function () {
        $(".left ul:eq(0) li").removeClass("S_xxjs");
        $(this).addClass("S_xxjs");
    });

});

function findOptions(obj) {
    $(obj).find(".option").toggle();
    $(obj).parent().siblings().find(".option").hide();
}

function showOptions(obj) {
    var festival = $(obj).text();
    $(obj).parent().siblings(".select_txt").text(festival);
    // 查询节日计划
    $.ajax({
        type: "POST",
        url: "/festivalPlan",
        data: {
            festival:festival
        },
        dataType: "json",
        success: function (data) {
            $(obj).parents(".festival_ul").find("li:eq(3) .txtBeginDate").val(data[0].startDate);
            $(obj).parents(".festival_ul").find("li:eq(5) .txtEndDate").val(data[0].endDate);
            $(obj).parents(".festival_ul").find("li:eq(6) .Save").attr("id", data[0].id);
            $(obj).parents(".fesdiv").find(".fplan").children().remove();
            $(obj).parents(".fesdiv").find(".fplan").append("<ul class='grzlk_ul grzlk_a'><li>时段</li><li>时间</li><li class='xingq'>日期</li></ul>");
            for(var i = 0 ; i < data.length; i ++){
                var str = "<ul class='grzlk_ul grzlk_a'>";
                str += "<li>" + data[i].timestep.timestepName + "</li>";
                str += "<li>" + data[i].timestep.starttime + " - " + data[i].timestep.endtime + "</li>";
                str += "<li class='xingq'>" + parseWeek(data[i].week) + "</li>";
                $(obj).parents(".fesdiv").find(".fplan").append(str);
            }
        }
    });
}

// 保存节日
function saveFestival(obj) {
    $.ajax({
        type: "POST",
        url: "/saveFestival",
        data: {
            id: $(obj).attr("id"),
            startDate: $(obj).parents(".festival_ul").find("li:eq(3) .txtBeginDate").val(),
            endDate: $(obj).parents(".festival_ul").find("li:eq(5) .txtEndDate").val()
        },
        dataType: "json",
        success: function () {}
    });
}

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

function parseWeek(number) {
    if (number != null) {
        return number.replace("1", "星期日").replace("2", "星期一").replace("3", "星期二").replace("4", "星期三").replace("5", "星期四").replace("6", "星期五").replace("7", "星期六");
    } else {
        return "";
    }
}

function indexOf(array, title) {
    for(var index = 0; index < array.length; index ++){
        if(title == array[index].title){
            return index;
        }
    }
    return -1;
}















