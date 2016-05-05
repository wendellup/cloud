function closeDialog(dialogId) {
    $(dialogId).dialog('close')
}



function showDialog(config) {
    var f='#'+config.id;
    var default_left;  
    var default_top;  
    $(f).dialog({
        width: config.width ? config.width : 'auto',
        height: config.height ? config.height : 'auto',
        closed: config.closed ? config.closed : false,
        cache: config.cache ? config.cache : false,
        href: config.url ? config.url : config,
        modal: config.modal ? config.modal : true,
        title: config.title ? config.title : ""
    });
}

/**
 * 表格列元素内容的换行
 * @param value
 * @param row
 * @param index
 * @returns {string}
 */
function cellWrap(value,row,index){
    return 'white-space:pre-wrap;word-break:break-all;';
}

var ie = (function() {   
    var undef, v = 3, div = document.createElement('div'), all = div.getElementsByTagName('i');   
    while (div.innerHTML = '<!--[if gt IE ' + (++v) + ']><i></i><![endif]-->', all[0]);   
    return v > 4 ? v : undef;   
}());   
  
var easyuiPanelOnResize = function(width, height) {
    if (!$.data(this, 'window') && !$.data(this, 'dialog'))
        return;
    if (ie === 8) {   
        var data = $.data(this, "window") || $.data(this, "dialog");   
        if (data.pmask) {   
            var masks = data.window.nextAll('.window-proxy-mask');   
            if (masks.length > 1) {   
                $(masks[1]).remove();   
                masks[1] = null;   
            }   
        }   
    }   
    if ($(this).panel('options').maximized == true) {   
        $(this).panel('options').fit = false;   
    }   
    $(this).panel('options').reSizing = true;   
    if (!$(this).panel('options').reSizeNum) {   
        $(this).panel('options').reSizeNum = 1;   
    } else {   
        $(this).panel('options').reSizeNum++;   
    }   
    var parentObj = $(this).panel('panel').parent();   
    var left = $(this).panel('panel').position().left;   
    var top = $(this).panel('panel').position().top;   
  
    if ($(this).panel('panel').offset().left < 0) {   
        $(this).panel('move', {   
                    left : 0   
                });   
    }   
    if ($(this).panel('panel').offset().top < 0) {   
        $(this).panel('move', {   
                    top : 0   
                });   
    }   
  
    if (left < 0) {   
        $(this).panel('move', {   
                    left : 0   
                }).panel('resize', {   
                    width : width + left   
                });   
    }   
    if (top < 0) {   
        $(this).panel('move', {   
                    top : 0   
                }).panel('resize', {   
                    height : height + top   
                });   
    }   
    if (parentObj.css("overflow") == "hidden") {
        var inline = $.data(this, "window").options.inline;   
        if (inline == false) {   
            parentObj = $(window);   
        }   
  
        if ((width + left > parentObj.width())   
                && $(this).panel('options').reSizeNum > 1) {   
            $(this).panel('resize', {   
                        width : parentObj.width() - left   
                    });   
        }   
  
        if ((height + top > parentObj.height())   
                && $(this).panel('options').reSizeNum > 1) {   
            $(this).panel('resize', {   
                        height : parentObj.height() - top   
                    });   
        }   
    }   
    $(this).panel('options').reSizing = false;   
};   
 
var easyuiPanelOnMove = function(left, top) {   
    if ($(this).panel('options').reSizing)   
        return;   
    var parentObj = $(this).panel('panel').parent();   
    var width = $(this).panel('options').width;   
    var height = $(this).panel('options').height;   
    var right = left + width;   
    var buttom = top + height;   
    var parentWidth = parentObj.width();   
    var parentHeight = parentObj.height();   
  
    if (left < 0) {   
        $(this).panel('move', {   
                    left : 0   
                });   
    }   
    if (top < 0) {   
        $(this).panel('move', {   
                    top : 0   
                });   
    }   
  
    if (parentObj.css("overflow") == "hidden") {   
        var inline = $.data(this, "window").options.inline;   
        if (inline == false) {   
            parentObj = $(window);   
        }   
        if (left > parentObj.width() - width) {   
            $(this).panel('move', {   
                        "left" : parentObj.width() - width   
                    });   
        }   
        if (top > parentObj.height() - height) {   
            $(this).panel('move', {   
                        "top" : parentObj.height() - height   
                    });   
        }   
    }   
};   
  
//$.fn.window.defaults.onResize = easyuiPanelOnResize;
//$.fn.dialog.defaults.onResize = easyuiPanelOnResize;
//$.fn.window.defaults.onMove = easyuiPanelOnMove;
if ($.fn.dialog){
	$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
}

function getWidth(percent){

    return document.body.clientWidth*percent;

} 

// ------------------------------------首页左边菜单点击相关JS方法-------------------------------------//
function cancelBubble(e)
{
	if (e.stopPropagation) e.stopPropagation();
	else e.cancelBubble = true;
}
function tabChangeIframe (src) {
	var jparentDoc = $(window.parent ? window.parent.document : window.document);
    var jiframe = jparentDoc.find("#iframe");
    var randomnumber=Math.floor(Math.random()*100000);
    var search = (src.indexOf("?") != -1) ? '&random='+randomnumber : '?random='+randomnumber;
    jiframe.attr("src",src+search).show();
	showTipLoad(jparentDoc);
}
// iframe高度自适应
function iframeHeightAuto(frameWindow){
    var parent = frameWindow.parent;                                   //定义父窗口变量
    var iframeObj;                                                     //定义父窗口中iframe的对象变量
    var allHeight;                                                     //定义赋值高度
    if(parent != frameWindow){
        allHeight =  document.documentElement.getElementsByTagName("body")[0].offsetHeight;
        iframeObj = parent.document.getElementById(frameWindow.name);
        iframeObj.style.height = allHeight + "px";
        arguments.callee(parent);
    }else{
        allHeight =  frameWindow.frames[0].document.documentElement.getElementsByTagName("body")[0].offsetHeight;
        iframeObj = frameWindow.document.getElementById(frameWindow.frames[0].name);
        iframeObj.style.height = allHeight  + "px";
    }
}
function showTipLoad(obj){
	setTip("页面正在加载中",true);
}
function hideTipLoad(){
	var jparentDoc = $(window.parent ? window.parent.document : window.document);
	jparentDoc.find("#tip").fadeOut().removeClass("load");
}
// 显示提示
function setTip(tiptext,notime){
	var doc = window.parent ? window.parent.document : window.document;
	var tip;
	if($(doc).find("#tip")[0]){
		tip = $(doc).find("#tip");
		tip.show().find("em").html(tiptext);
		if(notime){
			tip.removeClass("load");	
		}
	}
	boxCenter(tip);
	if(notime){
		$(tip).addClass("load");
	}else{
		setTimeout(function(){$(doc).find("#tip").fadeOut()},1500);	
	}
}
// 整体居中
function boxCenter(obj){
	var doc = window.parent,content = $(obj),dom = doc.document.documentElement;
	var left = (parseInt(dom.offsetWidth)-content.width())/2;
	var top = (parseInt( doc.screen.height)-content.height())/2 + dom.scrollTop;
	// alert( window.parent.document.documentElement.scrollTop);
	content.css({"top":(top <0 ? 0 : top)+"px","left":(left < 0 ? 0 : left)+"px"});
	
}
// 正则表达式, 获取 URL 中的参数
function     GetQueryString(name)   
{   
     var     reg     =   new   RegExp("(^|&)"+     name     +"=([^&]*)(&|$)");   
     var     r     =     window.location.search.substr(1).match(reg);   
     if     (r!=null)   return     unescape(r[2]);   return   null;   
}

/**
 *
 * @param divId
 * @param url
 * @param data  {}或者{channelId:26}
 * @param fileTypeArray 所要匹配的文件后缀数组
 * @param completeCallback
 */
function createFileUpload(divId, url, data, fileTypeArray, completeCallback) {
    new AjaxUpload(divId,
        {
            action: url,
            data:data,
            name:'ajaxupload',
            onSubmit: function(file, extension) {
                var fileType = file.substring(file.lastIndexOf(".")+1,file.length);
                if (!in_array(fileType, fileTypeArray)) {
                    $.messager.alert('提示', '请上传' + fileTypeArray.toString() + '类型文件','info');
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

$.extend($.fn.validatebox.defaults.rules, {
    CHS: {
        validator: function (value, param) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message: '请输入汉字'
    },
    ZIP: {
        validator: function (value, param) {
            return /^[1-9]\d{5}$/.test(value);
        },
        message: '邮政编码不存在'
    },
    QQ: {
        validator: function (value, param) {
            return /^[1-9]\d{4,10}$/.test(value);
        },
        message: 'QQ号码不正确'
    },
    mobile: {
        validator: function (value, param) {
//            return /(^(13[0-9]|147|15[^4,\\D]|18[0,5-9,1])\d{8}$)/.test(value);
           return /^\d+$/.test(value) && value.length==11 && value.substring(0,1) == 1;
        },
        message: '手机号码不正确'
    },
    loginName: {
        validator: function (value, param) {
            return /^[\u0391-\uFFE5\w]+$/.test(value);
        },
        message: '登录名称只允许汉字、英文字母、数字及下划线。'
    },
    safepass: {
        validator: function (value, param) {
            return safePassword(value);
        },
        message: '密码由字母和数字组成，至少6位'
    },
    equalTo: {
        validator: function (value, param) {
            return value == $(param[0]).val();
        },
        message: '两次输入的字符不一至'
    },
    number: {
        validator: function (value, param) {
            return /^\d+$/.test(value);
        },
        message: '请输入数字'
    },
    idcard: {
        validator: function (value, param) {
            return idCard(value);
        },
        message:'请输入正确的身份证号码'
    },
    nospace: {
        validator: function (value, param) {
            return !/^\s*$/.test(value);
        },
        message:'请输入正确的信息'
    },
    specialChar: {
        validator: function (value, param) {
            return !/[,'"\t]+/.test(value);
        },
        message:'不能输入英文逗号，引号，制表符'
    }
});

/* 密码由字母和数字组成，至少6位 */
var safePassword = function (value) {
    return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/.test(value));
}

var idCard = function (value) {
    if (value.length == 18 && 18 != value.length) return false;
    var number = value.toLowerCase();
    var d, sum = 0, v = '10x98765432', w = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2], a = '11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91';
    var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/);
    if (re == null || a.indexOf(re[1]) < 0) return false;
    if (re[2].length == 9) {
        number = number.substr(0, 6) + '19' + number.substr(6);
        d = ['19' + re[4], re[5], re[6]].join('-');
    } else d = [re[9], re[10], re[11]].join('-');
    if (!isDateTime.call(d, 'yyyy-MM-dd')) return false;
    for (var i = 0; i < 17; i++) sum += number.charAt(i) * w[i];
    return (re[2].length == 9 || number.charAt(17) == v.charAt(sum % 11));
}

var isDateTime = function (format, reObj) {
    format = format || 'yyyy-MM-dd';
    var input = this, o = {}, d = new Date();
    var f1 = format.split(/[^a-z]+/gi), f2 = input.split(/\D+/g), f3 = format.split(/[a-z]+/gi), f4 = input.split(/\d+/g);
    var len = f1.length, len1 = f3.length;
    if (len != f2.length || len1 != f4.length) return false;
    for (var i = 0; i < len1; i++) if (f3[i] != f4[i]) return false;
    for (var i = 0; i < len; i++) o[f1[i]] = f2[i];
    o.yyyy = s(o.yyyy, o.yy, d.getFullYear(), 9999, 4);
    o.MM = s(o.MM, o.M, d.getMonth() + 1, 12);
    o.dd = s(o.dd, o.d, d.getDate(), 31);
    o.hh = s(o.hh, o.h, d.getHours(), 24);
    o.mm = s(o.mm, o.m, d.getMinutes());
    o.ss = s(o.ss, o.s, d.getSeconds());
    o.ms = s(o.ms, o.ms, d.getMilliseconds(), 999, 3);
    if (o.yyyy + o.MM + o.dd + o.hh + o.mm + o.ss + o.ms < 0) return false;
    if (o.yyyy < 100) o.yyyy += (o.yyyy > 30 ? 1900 : 2000);
    d = new Date(o.yyyy, o.MM - 1, o.dd, o.hh, o.mm, o.ss, o.ms);
    var reVal = d.getFullYear() == o.yyyy && d.getMonth() + 1 == o.MM && d.getDate() == o.dd && d.getHours() == o.hh && d.getMinutes() == o.mm && d.getSeconds() == o.ss && d.getMilliseconds() == o.ms;
    return reVal && reObj ? d : reVal;
    function s(s1, s2, s3, s4, s5) {
        s4 = s4 || 60, s5 = s5 || 2;
        var reVal = s3;
        if (s1 != undefined && s1 != '' || !isNaN(s1)) reVal = s1 * 1;
        if (s2 != undefined && s2 != '' && !isNaN(s2)) reVal = s2 * 1;
        return (reVal == s1 && s1.length != s5 || reVal > s4) ? -10000 : reVal;
    }
};


//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
function forbidBackSpace(e) {
    var ev = e || window.event;
    var obj = ev.target || ev.srcElement;
    var t = obj.type || obj.getAttribute('type');

    var vReadOnly = obj.getAttribute('readonly');
    var vEnabled = obj.getAttribute('enabled');

    vReadOnly = (vReadOnly == null)?false : vReadOnly;
    vEnabled = (vEnabled == null)?true : vEnabled;

    var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea") && (vReadOnly==true || vEnabled!=true))?true:false;

    //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
    var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")?true:false;

    //判断
    if(flag2){       return false;     }
    if(flag1){       return false;     }

}

//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
function forbidBackSpace(e) {
    var ev = e || window.event;
    var obj = ev.target || ev.srcElement;
    var t = obj.type || obj.getAttribute('type');

    var vReadOnly = obj.getAttribute('readonly');
    var vEnabled = obj.getAttribute('enabled');

    vReadOnly = (vReadOnly == null)?false : vReadOnly;
    vEnabled = (vEnabled == null)?true : vEnabled;

    var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea") && (vReadOnly==true || vEnabled!=true))?true:false;

    //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
    var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")?true:false;

    //判断
    if(flag2){       return false;     }
    if(flag1){       return false;     }

}
$(function(){
    document.onkeypress = forbidBackSpace;
    document.onkeydown = forbidBackSpace;
})
function openDetailWin(href){

    var parentWin = function(){
        var obj=window.self;
        while(true)
        {
            if(obj.document.getElementById("iframe_detail"))
            {
                return obj;
            }
            obj=obj.window.parent;
        }
    }
    var wind = parentWin();
    var iframe_detail = $("body",wind.document).find("#iframe_detail_div");

    var iframe = iframe_detail.find("iframe").get(0);
    var heightAuto = function(){
        var eHeight = iframe.contentWindow.document.getElementsByTagName("body")[0].offsetHeight;
        var parentHeight = $("#iframe",wind.document).height();
        iframe.style.height = (eHeight<parentHeight?parentHeight:eHeight) + "px";
        iframe_detail.css({"z-index":2,"opacity":1,height:"auto",overflow:"visible"});
        //alert(eHeight)
    }
    if (window.addEventListener) {
        iframe.addEventListener("load", heightAuto, false);
    }else if (window.attachEvent){
        iframe.attachEvent("onload", heightAuto);
    }else{
        iframe.onload = heightAuto;
    }

    $(iframe).width(iframe_detail.width()-24).attr("src",href);
}
function closeDetailWin(){
    var parentWin = function(){
        var obj=window.self;
        while(true)
        {
            if(obj.document.getElementById("iframe_detail"))
            {
                return obj;
            }
            obj=obj.window.parent;
        }
    }
    var wind = parentWin();
    $("div.iframe_detail",wind.document).css({"z-index":0,"opacity":0,height:0,overflow:"hidden"});
    wind.document.body.scrollTop = 130;
    var iframe = $("#iframe",wind.document).contents();
    if(iframe.find("#tt")[0]){
        var pp = iframe.find('div.panel').eq(iframe.find("#tt").find("li.tabs-selected").index());
       pp.find("iframe").contents().find("#reload_btn").click();
    }else{
        iframe.find("#reload_btn").click();
    }

    //alert(pp.find("iframe").contents().find("#reload_btn").html())
}

function sowTip(obj){
    if($(obj).find(".td_tip")[0]){
        $(obj).find(".td_tip").show();
    }else{
        var div = document.createElement("div");
        div.className = "td_tip";
        div.innerHTML = "<span class='td_top'></span><span class='td_bottom'></span>"+ $(obj).attr("tip");
        $(obj).append(div);
        if($(obj).offset().left+170>document.body.clientWidth){
            div.style.right = 0+"px";
            div.style.left = "auto";
        }else{
            div.style.left = $(obj).offset().left+"px";
        }
        div.style.top =  $(obj).offset().top - $(div).height()-15+"px";
    }
    $(obj).addClass("fc_tip");
}
function hideTip(obj){
    $(obj).find(".td_tip").hide();
    $(obj).removeClass("fc_tip");
}

String.prototype.trim = function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

/**
 *
 * @param divId
 * @param data
 * @param url
 * @param completeCallback
 * @param type  1--图片(jpg格式); 2--视频; 3--excel(xsl格式); 4--txt文件; 5--apk文件; 200-应用/游戏icon
 */
function createUpload(divId, data, url, completeCallback, type) {
    new AjaxUpload(divId,
        {
            action: url,
            data:data ,
            name:'ajaxupload',
            onSubmit: function(file, extension) {
                var fileType = file.substring(file.lastIndexOf(".")+1,file.length);
                if (type == 1){
                    if(!/(\JPG|\jpg)/g.test(fileType)) {
                        $.messager.alert('提示',"请上传jpg格式的图片文件!",'info');
                        return false;
                    }
                }
                else if (type == 2){
                    //暂时不做文件格式校验

                }
                else if (type==3){
                    if(!/(\XLS|\xls)/g.test(fileType)) {
                        $.messager.alert('提示',"请上传xls格式的文件!",'info');
                        return false;
                    }

                }
                else if (type==4){
                    if(!/(TXT|txt)/g.test(fileType)) {
                        $.messager.alert('提示',"请上传txt格式的文件!",'info');
                        return false;
                    }
                }
                else if (type==5){
                    if(!/(\APK|\apk)/g.test(fileType)) {
                        $.messager.alert('提示',"请上传apk格式的文件!",'info');
                        return false;
                    }
                }
                else if (type==200){
                    if((!/(\JPG|\jpg)/g.test(fileType)) && (!/(\PNG|\png)/g.test(fileType))) {
                        $.messager.alert('提示',"请上传jpg/png格式的文件!",'info');
                        return false;
                    }
                }
                else if (type==250){
                    if((!/(\JPG|\jpg)/g.test(fileType)) && (!/(\PNG|\png)/g.test(fileType)) && (!/(\GIF|\gif)/g.test(fileType))&& (!/(\JPEG|\jpeg)/g.test(fileType))) {
                        $.messager.alert('提示',"请上传jpg/png/jpeg/gif格式的文件!",'info');
                        return false;
                    }
                }
                else {
                    if(!/(\JPG|\jpg)/g.test(fileType)) {
                        $.messager.alert('提示',"请上传jpg格式的图片文件!",'info');
                        return false;
                    }
                }
                //var confirmResult = confirm("确定上传文件：" + file + "?", "确定");
                //if (confirmResult){
                //遮罩层，防止用户重复提交
                $('<div id="mask"></div>').appendTo('body').height($("body").height());
                //}
                return true;
            },
            responseType:"json",
            onComplete: function(file, response) {
                $("#mask").remove();
                if(!response.result){
                    $.messager.alert('提示',response.msg,'error');
                    return;
                }
                completeCallback(file, response);
            }
        });
}

//若字符串长度超过要求，截掉多余部分
function checkLength(obj,len)
{
    var str = obj.value;  //获取要处理的字符串
    var curStr = "";  //用于实时存储字符串
    for(var i = 0; i < str.length; i ++)
    {
        curStr += str.charAt(i);  //记录当前遍历过的所有字符
        if(getCharLength(curStr) > len)
        {
            obj.value = str.substring(0,i);
            return;
        }
    }
}
//判断字符串所占的字节数
function getCharLength(str)
{
    var iLength = 0;
    for(var i = 0; i < str.length; i ++)
    {
        if(str.charCodeAt(i) > 255)   //如果当前字符的编码大于255
        {
            iLength += 2;    //所占字节数加2
        }
        else
        {
            iLength += 1;   //否则所占字节数加1
        }
    }
    return iLength;   //返回字符所占字节数
}