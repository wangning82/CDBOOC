$(function () {
    //底部鼠标滑过
    $("#nav ul:eq(0) li").mouseover(function () {
        $(this).find("div:eq(0)").css("display", "block");
    });
    $("#nav ul:eq(0) li").mouseout(function () {
        $(this).find("div:eq(0)").css("display", "none");
    });
});

function findOptions(obj) {
    $(obj).find(".option").toggle();
    $(obj).parent().siblings().find(".option").hide();
}

function showOptions(obj) {
    var value = $(obj).text();
    $(obj).parent().siblings(".select_txt").text(value);
    $(obj).parent().siblings(".select_txt").parent().siblings(".select_value").val(value);
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

Array.prototype.indexof = function (value, fromindex) {
    var arr = this.valueOf(), len = this.length;
    //如果arr不是数组或者第一个参数为空或者undefined，则返回false
    if (toString.call(arr) !== '[object Array]' || value === '' || value === undefined || toString.call(value) === '[object Function]') {
        return false;
    }
    //默认第一个参数为0
    if (fromindex === undefined) {
        fromindex = 0;
    }
    //第二个参数不是数字返回false
    if (toString.call(fromindex) !== '[object Number]') {
        return false;
    }
    //判断第二个参数是否为负数
    if (fromindex < 0) {
        fromindex = Math.abs(fromindex);
        //超过搜索范围
        if (len < fromindex) {
            return -1;
        } else {
            //负数则从后面开始向后搜索
            fromindex = len - fromindex;
        }
    }
    //开始查找
    for (var i = 0 + fromindex; i < len; i++) {
        if (value === arr[i]) {
            return i;
        } else {
            //判断数据类型相等
            if (toString.call(arr[i]) === toString.call(value)) {
                //判断数据值相等
                if (JSON.stringify(arr[i]) === JSON.stringify(value)) {
                    return i;
                } else {
                    return -1;
                }
            }
        }
    }
    return -1;
};















