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

function SpotMusic(id, startTime, intervalTime, cycleTimes) {
    this.id = id;
    this.startTime = startTime;
    this.intervalTime = intervalTime;
    this.cycleTimes = cycleTimes;
}

// 保存插播
function saveSpot(obj) {
    var spotList = new Array();
    $(obj).parents(".row790").find(".grzlk_tr ul").each(function (index, e) {
        if(index > 0){
            var id = $(e).find("li:eq(0) input").val();
            var startTime = $(e).find("li:eq(3) input").val();
            var intervalTime = $(e).find("li:eq(4) input").val();
            var cycleTimes = $(e).find("li:eq(5) input").val();
            var spot = new SpotMusic(id, startTime, intervalTime, cycleTimes);
            spotList.push(spot);
        }
    });
    $.ajax({
        type: "POST",
        url: "/saveSpot",
        data: JSON.stringify(spotList),
        dataType: "json",
        contentType:"application/json",
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

function Music(id, title, mp3, duration, volume, position, favorite, voice){
    this.id = id;
    this.title = title;
    this.mp3 = mp3;
    this.duration = duration; // 音乐时长
    this.volume = volume; // 音量
    this.position = position; // 播放位置
    this.favorite = favorite; // 收藏标志
    this.voice = voice; // 人声
}

// 选择人声(0男声，1女声，3合唱，4乐器)
function chooseMusic(array, voice){
    chooseVoiceFlag = true;
    var result = new Array();
    for(var index = 0; index < array.length; index ++){
        if(voice == array[index].voice){
            result.push(array[index]);
        }
    }
    return result;
}

function playByPeople(voice){
    chooseVoiceFlag = false;
    cleanPlaylist();
    var musicArray;
    if(personalPlayFlag){
        musicArray = chooseMusic(personalMusicList, voice);
    }else{
        musicArray = chooseMusic(planMusicList, voice);
    }
    myPlaylist.setPlaylist(musicArray);
    myPlaylist.play(0);
    $(".liedui_top .lie_h4").html("播放队列（共" + musicArray.length + "首歌）");
    $("#nav .num_1").html(musicArray.length);
}

function playAllPeople(){
    cleanPlaylist();
    var musicArray;
    if(personalPlayFlag){
        musicArray = personalMusicList;
    }else{
        musicArray = planMusicList;
    }
    myPlaylist.setPlaylist(musicArray);
    myPlaylist.play(0);
    $(".liedui_top .lie_h4").html("播放队列（共" + musicArray.length + "首歌）");
    $("#nav .num_1").html(musicArray.length);

}

// 整点重新加载新计划
function monitorTime() {
    var mydate = new Date();
    if(mydate.getMinutes() == 0){
        loadPlanMusic();
    }
}

// 恢复播放计划
function restorePlayPlan() {
    myPlaylist.setPlaylist(planMusicList);
    $(".liedui_top .lie_h4").html("播放队列（共" + planMusicList.length + "首歌）");
    $("#nav .num_1").html(planMusicList.length);
}

// 清除播放列表
function cleanPlaylist() {
    myPlaylist.remove(); // 删除所有音乐
    $(".liedui_top .lie_h4").html("播放队列（共 0 首歌）");
    $("#nav .num_1").html("0");
}

// 加载播放计划音乐
function loadPlanMusic() {
    $.ajax({
        type: "POST",
        url: "/planMusic",
        data: {},
        dataType: "json",
        success: function (data) {
            cleanPlaylist();
            planMusicList = [];
            for(var i = 0 ; i < data.length; i ++){
                var music = new Music(data[i].id, data[i].musicName, data[i].path, data[i].duration, data[i].volume, 0, data[i].favorite, data[i].voice);
                planMusicList.push(music);
                myPlaylist.add(music);
            }
            myPlaylist.play(0);
            $(".liedui_top .lie_h4").html("播放队列（共" + data.length + "首歌）");
            $("#nav .num_1").html(data.length);
        }
    });
}

// 加载插播音乐
function loadSpotMusic() {
    $.ajax({
        type: "POST",
        url: "/spotMusic",
        data: {},
        dataType: "json",
        success: function (data) {
            spotmusic = new Music(data.id, data.musicName, data.path, data.duration, data.volume, 0, data.favorite);
        }
    });
}

// 查找插播
function findSpotMusic() {
    if(!spotflag){
        loadSpotMusic();
        if (typeof(spotmusic) != "undefined" && spotmusic != null) {
            planMusicList[planPlayIndex].position = $("#jquery_jplayer_1").data("jPlayer").status.currentTime; // 获取播放位置，暂时没用
            cleanPlaylist();
            myPlaylist.add({
                title: spotmusic.title,
                mp3: spotmusic.mp3
            });
            $(".liedui_top .lie_h4").html("播放队列（共 1 首歌）");
            $("#nav .num_1").html("1");
            spotflag = true;
            myPlaylist.play(0);
        }
    }
}

// 查找店铺信息
function showShop() {
    $(".right div").remove();
    var shop = $(".supper").clone();
    $(".right").append(shop);
    shop.css("display", "block");
}

// 查找收藏
function findFavorite() {
    $.ajax({
        type: "POST",
        url: "/favorite",
        data: {},
        dataType: "json",
        success: function (data) {
            // 歌曲收藏
            $("#myFavorite .block").children().remove();
            if (data.musicList.length == 0) {
                $("#myFavorite .block").append("<div class='Wu'></div>");
            } else {
                personalMusicList = [];
                var musicButtons = "<div class='sc_Btn'>";
                musicButtons += "<input class='Btn_A playall' type='button' value='播放全部' onclick='playAll()'/>";
                musicButtons += "</div>";
                $("#myFavorite .block").append(musicButtons);
                var musicDiv = $("<div></div>");
                musicDiv.addClass("grzlk_tr");
                musicDiv.append("<ul class='grzlk_ul grzlk_pd'><li>歌名</li><li>歌手</li><li>专辑</li><li class='w566'>时长</li>");
                for (var i = 0; i < data.musicList.length; i++) {
                    var str = "<ul class='grzlk_ul grzlk_pd'><li>";
                    str += data.musicList[i].musicName;
                    str += "</li><li>";
                    str += data.musicList[i].actor;
                    str += "</li><li>";
                    str += data.musicList[i].special;
                    str += "</li><li class='w566'>";
                    str += data.musicList[i].duration;
                    str += "</li>";
                    musicDiv.append(str);

                    var music = new Music(data.musicList[i].id, data.musicList[i].musicName, data.musicList[i].path, data.musicList[i].duration, data.musicList[i].volume, 0, data.musicList[i].favorite, data.musicList[i].voice);
                    personalMusicList.push(music);
                }
                musicDiv.appendTo($("#myFavorite .block"));
            }

            // 频道收藏
            $("#myFavorite .none").children().remove();
            if (data.channelList.length == 0) {
                $("#myFavorite .none").append("<div class='Wu'></div>");
            } else {
                var channelButtons = "<div class='sc_Btn'>";
                // TODO 收藏频道操作按钮
                channelButtons += "</div>";
                $("#myFavorite .none").append(channelButtons);
                for (var i = 0; i < data.channelList.length; i++) {
                    var str = "<dl class='Style Style_xin' style='cursor: pointer;' onclick='findMusic(" + data.channelList[i].id + ")'>";
                    str += "<dt class='ash_mo'><a href=''><img src='" + data.channelList[i].photoPath + "'/></a></dt>";
                    str += "<dd class='ashow'></dd>";
                    str += "<dd class='Text'>" + data.channelList[i].channelName + "</dd>";
                    $("#myFavorite .none").append(str);
                }
            }
            $(".right div").remove();
            var favorite = $("#myFavorite").clone();
            $(".right").append(favorite);
            favorite.css("display", "block");
        }
    });
}

// 播放收藏音乐
function playAll() {
    cleanPlaylist();
    $(".liedui_top .lie_h4").html("播放队列（共" + personalMusicList.length + "首歌）");
    $("#nav .num_1").html(personalMusicList.length);
    myPlaylist.setPlaylist(personalMusicList);
    personalPlayFlag = true;
    myPlaylist.play(0);
}

// 查找音乐
function queryMusic(keyword) {
    $.ajax({
        type: "POST",
        url: "/queryMusic",
        data: {
            keyword: keyword
        },
        dataType: "json",
        success: function (data) {
            // 歌曲收藏
            $("#queryResult .block").children().remove();
            if (data.length == 0) {
                $("#queryResult .block").append("<div class='Wu'></div>");
            } else {
                personalMusicList = [];
                var musicButtons = "<div class='sc_Btn'>";
                musicButtons += "<input class='Btn_A playall' type='button' value='播放全部' onclick='playAll()'/>";
                musicButtons += "</div>";
                $("#queryResult .block").append(musicButtons);
                var musicDiv = $("<div></div>");
                musicDiv.addClass("grzlk_tr");
                musicDiv.append("<ul class='grzlk_ul grzlk_pd'><li>歌名</li><li>歌手</li><li>专辑</li><li class='w566'>时长</li>");
                for (var i = 0; i < data.length; i++) {
                    var str = "<ul class='grzlk_ul grzlk_pd'><li>";
                    str += data[i].musicName;
                    str += "</li><li>";
                    str += data[i].actor;
                    str += "</li><li>";
                    str += data[i].special;
                    str += "</li><li class='w566'>";
                    str += data[i].duration;
                    str += "</li>";
                    musicDiv.append(str);

                    var music = new Music(data[i].id, data[i].musicName, data[i].path, data[i].duration, data[i].volume, 0, data[i].favorite, data[i].voice);
                    personalMusicList.push(music);
                }
                musicDiv.appendTo($("#queryResult .block"));
            }
            $(".right div").remove();
            var queryResult = $("#queryResult").clone();
            $(".right").append(queryResult);
            queryResult.css("display", "block");
        }
    });
}

// 查找频道
function findChannel(scene) {
    $.ajax({
        type: "POST",
        url: "/channel",
        data: {
            scene:scene
        },
        dataType: "json",
        success: function (data) {
            $("#ztc").children().remove(); // 主题
            $("#fgc").children().remove(); // 风格
            $("#jrc").children().remove(); // 节日
            if (data.festival.length != 0) {
                for (var i = 0; i < data.festival.length; i++) {
                    var str = "<dl class='zhuti' onmousemove='hoverIn(this)' onmouseout='hoverOut(this)' onclick='findMusic(" + data.festival[i].id + ")'>";
                    str += "<dt><a href='#'><img src='" + data.festival[i].photoPath+ "'/></a></dt>";
                    str += "<dd class='icon_bg'></dd>";
                    str += "<dd class='icon_dd'><i class='icon'></i><span>4万</span></dd>";
                    str += "<dd class='Text'>" + data.festival[i].channelName + "</dd>";
                    str += "<dd class='Play'><img src='images/play.png'/></dd>";
                    str += "</dl>";
                    $("#jrc").append(str);
                }
            }
            if (data.theme.length != 0) {
                for (var i = 0; i < data.theme.length; i++) {
                    var str = "<dl class='zhuti' onmousemove='hoverIn(this)' onmouseout='hoverOut(this)' onclick='findMusic(" + data.theme[i].id + ")'>";
                    str += "<dt><a href='#'><img src='" + data.theme[i].photoPath+ "'/></a></dt>";
                    str += "<dd class='icon_bg'></dd>";
                    str += "<dd class='icon_dd'><i class='icon'></i><span>4万</span></dd>";
                    str += "<dd class='Text'>" + data.theme[i].channelName + "</dd>";
                    str += "<dd class='Play'><img src='images/play.png'/></dd>";
                    str += "</dl>";
                    $("#ztc").append(str);
                }
            }

            if (data.manner.length != 0) {
                for (var i = 0; i < data.manner.length; i++) {
                    var str = "<dl class='Style Style_xin' onmousemove='hoverIn(this)' onmouseout='hoverOut(this)' onclick='findMusic(" + data.manner[i].id + ")'>";
                    str += "<dt class='ash_mo'><a href='#'><img src='" + data.manner[i].photoPath+ "'/></a></dt>";
                    str += "<dd class='ashow'></dd>";
                    str += "<dd class='Text'>" + data.manner[i].channelName + "</dd>";
                    str += "</dl>";
                    $("#fgc").append(str);
                }
            }
            $(".right div").remove();
            var channel = $("#cl").clone();
            $(".right").append(channel);
            channel.css("display", "block");
        }
    });
}

// 查找频道歌曲
function findMusic(channelId) {
    $.ajax({
        type: "POST",
        url: "/music",
        data: {
            channelId:channelId
        },
        dataType: "json",
        success: function (data) {
            $("#myChannel .channelName").html(data.channel.channelName);
            $("#myChannel .profile").html(data.channel.remarks);
            $("#myChannel .Btn_c").attr("channelId", data.channel.id);
            $("#myChannel .Btn_c").attr("favorite", data.channel.favorite);
            if(data.channel.favorite == "0"){
                $("#myChannel .Btn_c").val("收藏");
            }else if(data.channel.favorite == "1"){
                $("#myChannel .Btn_c").val("取消收藏");
            }
            $("#myChannel .gqsl").html(data.musicList.length);
            $("#myChannel .zhuti_menu img").attr("src", data.channel.photoPath);
            $("#myChannel .grzlk_tr").children().remove();
            $("#myChannel .grzlk_tr").append("<ul class='grzlk_ul'><li>歌名</li><li>歌手</li><li>专辑</li><li class='w566'>时长</li><li class='w566'>操作</li></ul>");
            for (var i = 0; i < data.musicList.length; i++) {
                var str = "<ul class='grzlk_ul'>";
                str += "<li>" + data.musicList[i].musicName + "</li>";
                str += "<li>" + data.musicList[i].actor + "</li>";
                str += "<li>" + data.musicList[i].special + "</li>";
                str += "<li class='w566'>" + data.musicList[i].duration + "</li>";
                str += "<li class='w566 w577'>";
                if(data.musicList[i].favorite == "0"){
                    str += "<a href='#' musicId='" + data.musicList[i].id + "' favorite='" + data.musicList[i].favorite + "' onclick='doFavoriteMusic(this)' class='down_1'></a>";
                }else if(data.musicList[i].favorite == "1"){
                    str += "<a href='#' musicId='" + data.musicList[i].id + "' favorite='" + data.musicList[i].favorite + "' onclick='doFavoriteMusic(this)' class='down_3'></a>";
                }
                str += "<a href='#' musicId='" + data.musicList[i].id + "' channelId='" + channelId + "' onclick='deleteMusic(this)' class='down_1 down_2'></a></li>";
                $("#myChannel .grzlk_tr").append(str);
            }
            $("#myChannel .mu_2").click(function () {
                cleanPlaylist();
                personalMusicList = [];
                $(".liedui_top .lie_h4").html("播放队列（共" + data.musicList.length + "首歌）");
                $("#nav .num_1").html(data.musicList.length);
                for (var i = 0; i < data.musicList.length; i++) {
                    var music = new Music(data.musicList[i].id, data.musicList[i].musicName, data.musicList[i].path, data.musicList[i].duration, data.musicList[i].volume, 0, data.musicList[i].favorite, data.musicList[i].voice);
                    myPlaylist.add(music);
                    personalMusicList.push(music);
                }
                personalPlayFlag = true;
                myPlaylist.play(0);
            });
        }
    });
    $(".right div").remove();
    var channel = $("#myChannel").clone();
    $(".right").append(channel);
    channel.css("display", "block");
}

// 查找计划
function findPlan() {
    $(".right div").remove();
    $.ajax({
        type: "POST",
        url: "/plan",
        data: {},
        dataType: "json",
        success: function (data) {
            // 节日
            $("#myPlan .select_txt").html(data.festival[0].channel.themeConcreteType);
            $("#myPlan .txtBeginDate").val(data.festival[0].startDate);
            $("#myPlan .txtEndDate").val(data.festival[0].endDate);
            $("#myPlan .Save").attr("id", data.festival[0].id);
            $("#myPlan .fplan").children().remove();
            $("#myPlan .fplan").append("<ul class='grzlk_ul grzlk_a'><li>时段</li><li>时间</li><li class='xingq'>日期</li></ul>");
            for(var i = 0 ; i < data.festival.length; i ++){
                var str = "<ul class='grzlk_ul grzlk_a'>";
                str += "<li>" + data.festival[i].timestep.timestepName + "</li>";
                str += "<li>" + data.festival[i].timestep.starttime + " - " + data.festival[i].timestep.endtime + "</li>";
                str += "<li class='xingq'>" + parseWeek(data.festival[i].week) + "</li>";
                $("#myPlan .fplan").append(str);
            }
            // 主题
            $("#myPlan .tplan").children().remove();
            $("#myPlan .tplan").append("<ul class='grzlk_ul grzlk_a'><li>时段</li><li>时间</li><li class='xingq'>日期</li></ul>");
            for(var i = 0 ; i < data.theme.length; i ++){
                var str = "<ul class='grzlk_ul grzlk_a'>";
                str += "<li>" + data.theme[i].timestep.timestepName + "</li>";
                str += "<li>" + data.theme[i].timestep.starttime + " - " + data.theme[i].timestep.endtime + "</li>";
                str += "<li class='xingq'>" + parseWeek(data.theme[i].week) + "</li>";
                $("#myPlan .tplan").append(str);
            }
            // 风格
            $("#myPlan .mplan").children().remove();
            $("#myPlan .mplan").append("<ul class='grzlk_ul grzlk_a'><li>时段</li><li>时间</li><li class='xingq'>日期</li></ul>");
            for(var i = 0 ; i < data.manner.length; i ++){
                var str = "<ul class='grzlk_ul grzlk_a'>";
                str += "<li>" + data.manner[i].timestep.timestepName + "</li>";
                str += "<li>" + data.manner[i].timestep.starttime + " - " + data.manner[i].timestep.endtime + "</li>";
                str += "<li class='xingq'>" + parseWeek(data.manner[i].week) + "</li>";
                $("#myPlan .mplan").append(str);
            }
        }
    });
    var plan = $("#myPlan").clone();
    $(".right").append(plan);
    plan.css("display", "block");
}

// 查找插播计划
function findSpot() {
    $(".right div").remove();
    $.ajax({
        type: "POST",
        url: "/spotPlan",
        data: {},
        dataType: "json",
        success: function (data) {
            $("#mySpot .grzlk_tr").children().remove();
            $("#mySpot .grzlk_tr").append("<ul class='grzlk_ul'><li>名称</li><li>时长</li><li style='width: 200px;'>播放日期</li><li>播放时间</li><li>重复时间</li><li>循环次数</li></ul>");
            for(var i = 0 ; i < data.length; i ++){
                var str = "<ul class='grzlk_ul chabo_list'>";
                str += "<li><input type='hidden' value='" + data[i].id + "'/>" + data[i].playName+ "</li>";
                if(data[i].channel.musicList.length != 0){
                    str += "<li>" + data[i].channel.musicList[0].duration + "</li>";
                }else{
                    str += "<li>00:00:00</li>";
                }
                str += "<li style='width: 200px;'>" + data[i].startDate + " - " + data[i].endDate + "</li>";
                str += "<li><input type='text' value='" + data[i].timestep.starttime + "' class='inp_1'/></li>";
                str += "<li><input type='text' value='" + data[i].intervalTime + "' class='inp_1 inp_2'/></li>";
                str += "<li><input type='text' value='" + data[i].cycleTimes + "' class='inp_1 inp_2'/></li>";
                str += "</ul>";
                $("#mySpot .grzlk_tr").append(str);
            }
        }
    });

    var spot = $("#mySpot").clone();
    $(".right").append(spot);
    spot.css("display", "block");
}

// 收藏频道
function doFavoriteChannel(obj) {
    var favorite = $(obj).attr("favorite");
    if(favorite == "0"){
        $.ajax({
            type: "POST",
            url: "/doFavoriteChannel",
            data: {
                channelId : $(obj).attr("channelId")
            },
            dataType: "json",
            success: function () {}
        });
        $(obj).val("取消收藏");
        $(obj).attr("favorite", "1");
    }else if(favorite == "1"){
        $.ajax({
            type: "POST",
            url: "/doNotFavoriteChannel",
            data: {
                channelId : $(obj).attr("channelId")
            },
            dataType: "json",
            success: function () {}
        });
        $(obj).val("收藏");
        $(obj).attr("favorite", "0");
    }
}

// 收藏或者取消收藏音乐
function doFavoriteMusic(obj) {
    var favorite = $(obj).attr("favorite");
    if(favorite == "0"){
        $.ajax({
            type: "POST",
            url: "/doFavoriteMusic",
            data: {
                musicId : $(obj).attr("musicId")
            },
            dataType: "json",
            success: function () {}
        });
        $(obj).attr("favorite", "1");
        $(obj).attr("class", "down_3");
    }else if(favorite == "1"){
        $.ajax({
            type: "POST",
            url: "/doNotFavoriteMusic",
            data: {
                musicId : $(obj).attr("musicId")
            },
            dataType: "json",
            success: function () {}
        });
        $(obj).attr("favorite", "0");
        $(obj).attr("class", "down_1");
    }
}

// 删除音乐
function deleteMusic(obj) {
    $.ajax({
        type: "POST",
        url: "/deleteMusic",
        data: {
            musicId : $(obj).attr("musicId")
        },
        dataType: "json",
        success: function () {}
    });
    $(obj).parents(".grzlk_ul").remove();
}

// 查询下载消息
function findMessage() {
    $.ajax({
        type: "POST",
        url: "/findMessage",
        data: {},
        dataType: "json",
        success: function (data) {
            $("#downloadMessage").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;下载音乐：" + data.message);
        }
    });
}
