var pForm = pForm || {};

$(function(){
    pForm.formImportInit($("input[type=text]"));
    pForm.formSelectInit($("div.sel_btn"));
    //menuClick($("#menu"),$("#selected_bg"),0);//左侧菜单点击滑动
    menuClick($("#tab_ul"),$("#tab_seld"),1);//tab切换点击滑动
    setCustomer();
})


//设置客服高度
function setCustomer(){
    var customer = $("#customer"),
        doc = document.documentElement,
        t =  function(){
            var yScroll;
            if (self.pageYOffset) {
                yScroll = self.pageYOffset;
            } else if (doc && doc.scrollTop){
                yScroll = doc.scrollTop;
            } else if (document.body) {
                yScroll = document.body.scrollTop;
            }
            return yScroll;
        },
        setH = function(){
            customer.css("top",doc.clientHeight+t()-100);
        };
    window.onscroll = function(){
        setH();
    }
    setH();
}
//表单输入提示交互
pForm.formImportInit = function(selector){
    var jInput = $(selector);

    jInput.each(function(){
        var $this = $(this),
            pla = $this.attr("placeholder") || "";
        $this.bind("focus",function(){
            $this.attr("placeholder","");
            $this.css("color","#333");
        })
        if($this.attr("placeholder") == "" || typeof ($this.attr("placeholder")) == "undefined"){
            $this.css("color","#333");
        }
    })

};

//表单下拉框交互
function selectClick(obj){
    var _this = $(obj);
    if(_this.siblings("ul").is(":visible")){
        _this.siblings("ul").hide();
        _this.parent().removeClass("sel_btn_click");
    }else{
        _this.parent().get(0).focus();
        _this.siblings("ul").show();
        _this.siblings("ul").height( _this.siblings("ul").height()>200 ? 300 :"")
        _this.parent().addClass("sel_btn_click");
    }
    _this.parent().find("li").unbind("click").bind("click",function(){
        var $this = $(this);
        $this.parent().siblings("span").html($this.text());
        $this.parent().siblings("input").val($this.attr("name"));
        $this.parent().hide();
        $this.parent().parent().removeClass("sel_btn_click");
        $this.parent().parent().get(0).blur();
        callBack($this);
    })
}
function selectBlur(obj){
    var _this = $(obj);
    _this.find("ul").hide();
    _this.removeClass("sel_btn_click");
}
//表单下拉框交互
pForm.formSelectInit = function(selector){
    var selectSpan = $(selector);
    selectSpan.attr("onblur","selectBlur(this)").find("span").attr("onclick","selectClick(this)")

    var span_zindex = selectSpan.length;
    for(var i = 0; i<selectSpan.length; i++){
        $(selectSpan[i]).css({"z-index":span_zindex});
        $(selectSpan[i]).attr({"tabindex":(i+1),"hidefocus":true});
        --span_zindex;
    }
}

function callBack(t){

}
//菜单点击事件
function menuClick(m,s,style){
    var menu = $(m),
        bgroud = $(s),
        divs = [],
        closeDiv = function(s){
            for(var i =0; i<divs.length; i++){
                var d = $("#"+divs[i]);
                if(d.is(":visible")){
                    d.hide();
                }
            }
            $("#"+s).show();
        };
    menu.find("li").each(function(){
        var $this = $(this);
        divs.push($this.attr("name"));
        $this.bind("click",function(){
            if($this.hasClass("menu_node") || $this.hasClass("selected")){return ;}
            else{
                menu.find("li.selected").removeClass("selected");
                if(style == 0){//纵向滑动
                    var top = $this.offset().top-433;
                    bgroud.animate({"top":top+"px"},top);
                }else{
                    var left = $this.offset().left-396;
                    bgroud.animate({"left":left+"px"},"slow");
                }
                $this.addClass("selected");
                closeDiv($this.attr("name"));
            }
        })
    })

}

String.format = function(str) {
    var args = arguments, re = new RegExp("%([1-" + args.length + "])", "g");
    return String(str).replace(re,function($1, $2) {
        return args[$2];
    });
};

//选择公司或个人
function selectStyle(obj,style){
    if(!$(obj).hasClass("sled3")){
        $(obj).addClass("sled3");
        $(obj).siblings("span").removeClass("sled3");
        if(style == 1){
            $("#company").show();
            $("#personal").hide();
        }else{
            $("#personal").show();
            $("#company").hide();
        }
        $("#distributorClass").val(style);
    }
}


//checkbox点选事件
function checkThis(obj){
    var $this = $(obj);
    $this.hasClass("checkbox_sled")? $this.removeClass("checkbox_sled"):$this.addClass("checkbox_sled");
}
//全选
function checkAll(obj){
    var checkboxs = $(obj).parents("table").find(".checkbox"),
        checkFn = function(ischeck){
            for(i = 0; i<checkboxs.length; i++){
                if(ischeck){          //全选
                    $(checkboxs[i]).attr("class","checkbox checkbox_sled");
                }else{               //全不选
                    $(checkboxs[i]).attr("class","checkbox");
                }
            }
        } ;
    $(obj).hasClass("checkbox_sled")? checkFn(false) :checkFn(true);
}

////获取项目服务路径
//function getRootPath(){
//    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
//    var curWwwPath=window.document.location.href;
//    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
//    var pathName=window.document.location.pathname;
//    var pos=curWwwPath.indexOf(pathName);
//    //获取主机地址，如： http://localhost:8083
//    var localhostPaht=curWwwPath.substring(0,pos);
//    //获取带"/"的项目名，如：/uimcardprj
//    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
//    return(localhostPaht+projectName);
//}

function createFileUpload(divId, url, data, fileTypeArray, completeCallback) {
    new AjaxUpload(divId,
        {
            action: url,
            data:data,
            name:'ajaxupload',
            onSubmit: function(file, extension) {
                var fileType = file.substring(file.lastIndexOf(".")+1,file.length);
                if (!in_array(fileType, fileTypeArray)) {
                    $.messager.alert('提示','请上传' + fileTypeArray.toString() + '类型文件','info');
                    return false;
                }
                return confirm("确定上传文件：" + file + "?", "确定");
            },
            responseType:"json",
            onComplete: function(file, response) {
                if(!response.result){
                    $.messager.alert('提示',response.msg,'info');
                    return;
                }
                completeCallback(file, response);
            }
        });
}

/**
 * 判断文件后缀名在匹配的数组中是否存在
 * @param suffix 需要判断的文件名后缀
 * @param fileTypeArray   匹配的数组
 * @returns {boolean} true:存在 false：不存在
 */
function in_array(suffix, fileTypeArray) {
    for(var i in fileTypeArray) {
        if(fileTypeArray[i] == suffix) {
            return true;
        }
    }
    return false;
}

//添加输入框错误提示   参数 id是输入框的id（下拉框输入class为sel_btn的div的id），msg是错误信息
function errorTip(id,msg){
    var _input = $("#"+id),
        heights = function(){
            if(_input.parent().hasClass("area_btn")){
                return 115;
            }else if(_input.parent().hasClass("sel_btn")){
                return 58;
            }else if(_input.siblings("a.file_a")[0]){
                return 42;
            }else{
                return 34;
            }
        },
        error_span = '<i class="error_tip" style="top:'+heights()+'px; left:0;">'+msg+'</i>',
        addError = function(b){

        };
    if(_input.siblings("a.file_a")[0]){
        var fileA = $(_input.siblings("a.file_a"));
        if(fileA.find("i.error_tip")[0]){
            fileA.find("i.error_tip").html(msg);
        }else{
            fileA.append(error_span);
        }
    }else{
        if(_input.siblings("i.error_tip")[0]){
            _input.siblings("i.error_tip").html(msg);
            _input.parent().addClass("error");
        }else{
            _input.parent().addClass("error").append(error_span);
        }
    }
}
//解除错误提示
function delErrorTip(obj){
    var _input = $(obj);
    if(_input.siblings("a.file_a")[0]){
        $(_input.siblings("a.file_a")[0]).find("i").html("");
    }
    else{
        _input.parent().removeClass("error");
        _input.siblings("i.error_tip").html("");
    }
}

// 获取地址栏的参数数组
function getUrlParams()
{
    var search = window.location.search ;
    // 写入数据字典
    var tmparray = search.substr(1,search.length).split("&");
    var paramsArray = new Array;
    if( tmparray != null)
    {
        for(var i = 0;i<tmparray.length;i++)
        {
            var reg = /[=|^==]/;    // 用=进行拆分，但不包括==
            var set1 = tmparray[i].replace(reg,'&');
            var tmpStr2 = set1.split('&');
            var array = new Array ;
            array[tmpStr2[0]] = tmpStr2[1] ;
            paramsArray.push(array);
        }
    }
    // 将参数数组进行返回
    return paramsArray ;
}

// 根据参数名称获取参数值
function getParamValue(name)
{
    var paramsArray = getUrlParams();
    if(paramsArray != null)
    {
        for(var i = 0 ; i < paramsArray.length ; i ++ )
        {
            for(var  j in paramsArray[i] )
            {
                if( j == name )
                {
                    return paramsArray[i][j] ;
                }
            }
        }
    }
    return null ;
}

//页面提示，2秒消失
function setPageTip(msg){
    var doc = document,
        toast = doc.createElement("div");
    toast.className = "toast";
    toast.innerHTML = msg;
    doc.body.appendChild(toast);
    $(toast).attr({"wid":351,"hei":92});
    setCenter($(toast));
    $(toast).show();
    setTimeout(function(){$(toast).remove()},2000);
}


//设置居中
function setCenter(obj){
    var win = window.parent!=window ? window.parent: window,
        doc = window.parent!=window ?  window.parent.document : document,
        docB = doc.compatMode === "BackCompat" && doc.body || doc.documentElement,
        cw = docB.clientWidth,
        ch = docB.clientHeight,
        est = ('pageYOffset' in win) ? win.pageYOffset : docB.scrollTop;
    $(obj).css({"top":(parseInt((ch) / 2) + est-140)+"px","left":((cw-$(obj).attr("wid")-360)/2+190)+"px"});
    return  ch;
}
function showImg(id){
    var doc = window.parent!=window ?  window.parent.document : document,
        img_div = $("#"+id).clone();
    $(doc.body).append(img_div);
    setCenter(img_div.find("img"));
    img_div.find("div").height(doc.body.offsetHeight);
    img_div.click(function(){
        $(this).remove();
    })
    img_div.show();
}
//图片加载大小缩放
//图片加载大小缩放
function imgReSize(obj,width,height){
    var _this = obj;
    var h = parseInt(_this.height) == 0 ? $(_this).height() : parseInt(_this.height),
        w = parseInt(_this.width) == 0 ?  $(_this).width() : parseInt(_this.width),
        ww = typeof (width) == "undefined" ? 337 : width ,hh = typeof (height) == "undefined" ? 206 : height;    
    h = h>600 ? 600 : h;
    w = w>800 ? 800 : w;
    $(_this).attr({"hei":h,"wid":w});
    $(_this).height(hh);
    $(_this).width(ww);
    //等比缩放 注释
   /* if(h>hh && w<ww){
        _this.style.height = hh+"px";
        _this.style.width = hh*w/h+"px";
    }else if(h<hh && w>ww){
        _this.style.width = ww+"px";
        _this.style.height = ww*h/w+"px";
    }else if(h>hh && w>ww){
        if(h/w > hh/ww){
            _this.style.height = hh+"px";
            _this.style.width = hh*w/h+"px";
        }else{
            _this.style.width = ww+"px";
            _this.style.height = ww*h/w+"px";
        }
    }*/
}
//图片加载大小缩放和点击看大图
function imgOnload(image,width,height){
    $(image).attr({"hei":"","wid":""}).css({"height":"","width":""});
    imgReSize(image,width,height)
    $(image).attr("title","点击原图")
        .unbind("click").bind("click",function(){
            var _this = $(this),
                doc = window.parent!=window ?  window.parent.document : document,
                img_div = doc.createElement("div"),
                this_img = _this.clone();
            img_div.id = "img_div";
            $("#img_div",doc.body).remove();
            this_img.height(_this.attr("hei"));
            this_img.width(_this.attr("wid"));
            this_img.attr({"title":"","onload":""});
            img_div.className = "img_enlarge";
            img_div.innerHTML = '<div class="mask"></div>';
            $(img_div).append(this_img);
            var clientH = setCenter(this_img);
            $(img_div).find("div").height(doc.body.offsetHeight);
            $(img_div).click(function(){
                $(this).fadeOut().remove();
            })
            doc.body.appendChild(img_div);
        });
}

function tabKeyDown(e){
    var et=e||window.event;
    var keycode=et.charCode||et.keyCode;
    if(keycode==13){
        if(window.event){
            window.event.returnValue = false;
        }else{
            e.preventDefault();
        }
    }
}

