// JavaScript Document
var userId;
$(function(){
    bodyResize();
    $("#main_right").height($("#menu").height());
    window.onresize = bodyResize;
	var iframe = document.getElementById("iframe");
            if (iframe.attachEvent){
                iframe.attachEvent("onload", function(){
                    iframeHeightAuto(window.frames["iframe"]);
                    hideTipLoad();
		        });
	} else {
		iframe.onload = function(){
			iframeHeightAuto(window.frames["iframe"]);
			hideTipLoad();
		};
	}
    //左侧菜单
    $("#menu").find("ul.menu_inner").children("li").bind("click",function(){
        var $this = $(this);
        if($this.children("ul")[0]){
            var ul = $this.children("ul");
            if ($this.hasClass("close")) {
                ul.slideDown();
                $this.removeClass("close").siblings("li").children("ul").slideUp().parent().addClass("close");
            }else{
                ul.slideUp();
                $this.addClass("close");
            }
        }
    });
    $("#menu").find("li").each(function(i){
        var $this = $(this);
        if($this.children("ul")[0]) return ;
        else $this.attr("name",i);
    });

    $("#main_right").find("span").each(function(){
        var $this = $(this);
        if($this.parent().is("h3")){
            $this.bind("click",function(){
                if($this.parent().parent().find("div.matter_text")[0]){
                    var text =  $this.parent().parent().find("div.matter_text");
                    if(text.is(":visible")){
                        $this.parent().addClass("close");
                        if($this.parent().parent().hasClass("fixed_h")){
                            $this.parent().parent().addClass("auto_h");
                        }
                        text.slideUp("slow");
                    }else{
                        $this.parent().removeClass("close");
                        text.slideDown("slow",function(){
                            $this.parent().parent(".fixed_h").removeClass("auto_h");
                        });
                    }
                }
            });
        }
    });
    
    // 设置用户欢迎信息
	userId = GetQueryString("userId");
	$("#userName").html(GetQueryString("userName"));
	// 启动时钟
	getData();
});
function getData(){
	 //设置系统时间
    var dateTime=new Date();
    var hh=dateTime.getHours();
    var mm=dateTime.getMinutes();
    var ss=dateTime.getSeconds();

    var yy=dateTime.getFullYear();
    var MM=dateTime.getMonth()+1;  //因为1月这个方法返回为0，所以加1
    var dd=dateTime.getDate();
    //获取星期
    var week = dateTime.getDay();
    switch(week){
		case 0:week = "日";break;
		case 1:week = "一";break;
		case 2:week = "二";break;
		case 3:week = "三";break;
		case 4:week = "四";break;
		case 5:week = "五";break;
		case 6:week = "六";break;
    }
	$("#time").html(yy+"-"+MM+"-"+dd+"&nbsp;"+hh+":"+mm+":"+ss+"&nbsp;&nbsp;星期"+week);
	setTimeout("getData()",1000);
}
function bodyResize(){
    var menu = $("#menu"),detial = $("#detail_index"),width,iframe = $("#iframe"),tab_index = $("#tab_index");
    if($.browser.msie && $.browser.version == '6.0'){
        width = document.body.clientWidth-parseInt(menu.width())-69;
    }else{
        width = document.body.clientWidth-parseInt(menu.width())-60;
    }
    if(width<800){
        detial.width(800).parent().width(800+menu.width()+20);
        iframe.width(800);
		tab_index.width(800);
    }else{
        detial.width(width).parent().width("");
        iframe.width(width);
		tab_index.width(width);
    }
}
function changeIframe(obj,e,src){
    $("#iframe_detail_div").css({"z-index":0,"opacity":0,height:0,overflow:"hidden"});
    $("#tab_index2").hide();
    $("#tab_index").show();
    var funs = {
        menuSelect:function(text){
            var menu = $("#menu");
            menu.find("li.hover").removeClass("hover");
            menu.find("li").each(function(){
                var $li = $(this);
                var txt = $li.attr("name");
                if(txt == text){
                    menu.find("li.hover").removeClass("hover");
                     $li.addClass("hover");
                     funs.openParent($li);
                }
            });
        },
        openParent:function(obj){
            if($(obj).parent("ul").is(":hidden")) {
                $(obj).parent("ul").slideDown();
                if($(obj).parent("ul").parent("li").hasClass("close")) {
                    $(obj).parent("ul").parent("li").removeClass("close");
                    $(obj).parent("ul").parent("li").siblings("li").addClass("close").children("ul").slideUp();
                }
                else {
                    $(obj).parent("ul").parent("li").addClass("open_li").removeClass("close_li");
                    $(obj).parent("ul").parent("li").siblings("li.open_li").removeClass("open_li").addClass("close_li").children("ul").slideUp();
                }
                funs.openParent($(obj).parent("ul").parent("li"));
            }
        },
        resizeLi:function(){
            var ul = $("#tab_index").children("ul");
            var liswidth = 0;
            ul.children("li").each(function(){
                var width = $(this).outerWidth();
                liswidth += parseInt(width)+33;
            });
            if(liswidth >ul.width()){
                ul.children("li").width(Math.floor(ul.width()/(ul.children("li").length+1)) -33);
            }else{
                ul.children("li").width("");
            }
        }
    };
    $("#tab_home").hide();
    $("#main_right").hide();
    var ul = $("#tab_index").children("ul");
    if(ul.children("li").length == 13){
        setTip("只能打开12个选项卡");
    }else{
        $("#menu").find("li.hover").removeClass("hover");
        $(obj).addClass("hover");
        var iframe =  document.getElementById("iframe");
        //$(iframe).attr("src",src+"?user_id="+user_id).show();
        tabChangeIframe(src);
        var liNow = false;
        ul.children("li").each(function(){
            var $this = $(this);
            if($this.attr("id") == $(obj).attr("name")){
                $this.siblings("li.selected").removeClass("selected");
                $this.addClass("selected");
                liNow = true;
            }
        });
        if(!liNow){
            ul.find("li.selected").removeClass("selected");
            var li = document.createElement("li");
            li.className = "pos selected"; li.id = $(obj).attr("name");
            li.innerHTML = "<span class='tab_close_btn'></span>"+$(obj).text();
            $(li).attr("name",src);
            ul.append(li);
            funs.resizeLi();
            $(li).bind("click",function(){
                var $this = $(this);
                if(!$this.hasClass("selected")){
                    $this.siblings("li.selected").removeClass("selected");
                    $this.addClass("selected");
                    tabChangeIframe($this.attr("name"));
                    funs.menuSelect($this.attr("id"));
                }
            });
            $(li).children("span").bind("click",function(e){
                var $this = $(this);
                $(obj).removeClass("hover");
                if(ul.children("li").length == 2) {
                    $this.parent().remove();
                    tabChangeIframe("home.html");
                    $("#tab_home").show();
                }else{
                    var prev_li;
                    if(!$this.parent("li").prev().children("span")[0]) {
                        prev_li = $this.parent("li").next();
                    }else{
                        prev_li = $this.parent("li").prev();
                    }
                    if($this.parent().hasClass("selected")){
                        $this.parent().remove();
                        prev_li.addClass("selected");
                        tabChangeIframe(prev_li.attr("name"));
                        funs.menuSelect(prev_li.attr("id"));
                    }else{
                        $this.parent().remove();
                    }
                }
                funs.resizeLi();
                cancelBubble(e);
            });
        }
    }
    cancelBubble(e);

}

function changeLastli(obj,e){
    $(obj).siblings("li").each(function(){
        var $this = $(this);
        if($this.hasClass("open_li")){
            $this.removeClass("open_li").addClass("close_li");
            $this.children("ul").slideUp();}
    });
    if($(obj).children('ul').is(":visible")){
        $(obj).removeClass("open_li").addClass("close_li");
        $(obj).children('ul').slideUp();
    }else{
        $(obj).addClass("open_li").removeClass("close_li");
        $(obj).children('ul').slideDown();
    }
    cancelBubble(e);
}


function backIndex(){
    $("#main_right").show();
    $("#iframe").hide();
}
