/**
 * Created by liuzl on 14-6-9.
 */
//判断产品包名唯一
function checkPackageUnique(obj){
    var apkPackage = $(obj).val();
    var appId = $("#appId").val();
    var popularizeType = $("#popularizeType").val();
    var s = true,m = "";
    $.ajax({
        type: "POST",
        url: getRootPath() + "/allApp/checkPackage",
        async:false,
        data: "apkPackage=" + apkPackage + "&appId=" + appId + "&popularizeType=" + popularizeType,
        success: function (data) {
            if (data.result) {
                $(obj).parent().siblings(".sel_btn_tip").hide();
            } else {
                s = false;

                var repitTip = data.repeatMap;
                var mArray = [];
                for(var i = 0 ; i<repitTip.length; i++){
                    mArray.push('<li>'+repitTip[i]+'</li>');
                }
                if($(obj).parent().siblings(".sel_btn_tip")[0]){
                    $(obj).parent().siblings(".sel_btn_tip").html('<li class="fc_blue">已存在如下同名游戏包：<a href="javascript:void(0)" onclick="$(this).parent().parent(\'ul\').hide()"></a></li>'+mArray.join('')).show();
                }else{
                    $(obj).parent().after('<ul class="sel_btn_tip fl"><li class="fc_blue">已存在如下同名游戏包：<a href="javascript:void(0)" onclick="$(this).parent().parent(\'ul\').hide()"></a></li>'+mArray.join('')+'</ul>');
                    $(obj).parent().addClass("fl");
                }
                m = "该产品包名已注册，请重新输入";
            }
        }
    });
    return {same:s,msg:m};
}
//自研，代理交互
function selectStyle(obj, num) {
    var copyright = $("#copyright_effective"),
        copyright_time = $("#copyright_time"),
        agency = $("#agency");
    if (num == 1) {
        copyright.addClass("din");
        copyright_time.html("版权有效期");
        agency.val("");
        agency.removeAttr("valid");
        agency.removeAttr("length");
    } else {
        copyright.removeClass("din");
        copyright_time.html("代理有效期");
        agency.attr("valid", "require maxLength");
        agency.attr("length", 32);
    }
    iframeHeightAuto(window.parent["iframe_detail"]);
}

function isRanking(obj, num) {
    var ranking_select = $("#ranking_select"), ranking_text = $("#ranking_text"), rankUtil = $("#rankUtil");
    if (num == 1) {
        ranking_select.show();
        ranking_text.show().attr("valid", ranking_text.attr("tested"));
        rankUtil.attr("valid", rankUtil.attr("tested"));
    } else {
        ranking_text.hide().attr("valid", "");
        ranking_select.hide();
        rankUtil.attr("valid", "");
        $("#rankUtil").val("");
    }
    $("#rankFlag").val(num);
    iframeHeightAuto(window.parent["iframe_detail"]);
}

//版权证明

function attacheAjaxUploadCopyright(btn,path){
    var tempId = "" + $("#appId").val();
    var url = getRootPath() + "/ajaxUpload/uploadCoreFile?size="+150;
    createFileUpload(btn,url,{categoryPath:'product',pathId:tempId},['jpg','JPG'],function(file, response){
        if(response.result){
            var fileType = file.substring(file.lastIndexOf(".")+1,file.length);
            var fileTypeArray = ['jpg','JPG'];
            var cip = $("#copyrightImage");
            var czp = $("#copyrightZipPath");
            if (in_array(fileType, fileTypeArray)) {
                czp.addClass("din");
                cip.attr({"src":response.filePath,"value":response.efsId}).delay(1000).show(1);
                 $("#copyrightPathBtn").siblings("i").hide();
                var iframe = window.parent.document.getElementById("iframe");
                var iframe_detail = window.parent.document.getElementById("iframe_detail");
                $(iframe).height($(iframe).height()+206);
                $(iframe_detail).height($(iframe_detail).height()+206);
            }else{
                cip.addClass("din");
                czp.removeClass("din");
                czp.attr("href",response.filePath);
                czp.attr("value",response.efsId);
                czp.html("版权证明."+fileType);
                var iframe = window.parent.document.getElementById("iframe");
                $(iframe).height($(iframe).height()+20);
            }
            $("#copyrightPath").val(response.efsId);
        }else{
            $.messager.alert('提示',response.msg,'info');
        }
    });
}

function attacheAjaxUploadSnsImage(btn,img,path,picType){
    var tempId = "" + $("#appId").val();
    var url = getRootPath() + "/ajaxUpload/uploadSnsPicFile";
    createFileUpload(btn,url,{categoryPath:'product',pathId:tempId,picType:picType},['jpg','png','JPG','PNG'],function(file, response){
        if(response.result){
            var imgObject = $("#"+img);
            imgObject.unbind("load").bind("load",function(){imgOnload(this)});
            imgObject.attr({"src":response.filePath,"value":response.efsId}).delay(1000).show(1);
            $("#" + path).val(response.efsId);
            iframeHeightAuto(window.parent["iframe_detail"]);
            $.messager.alert('提示',response.msg,'info');
        }else{
            $.messager.alert('提示',response.msg,'info');
        }

    });
}

function selectScreenType(obj, num, type) {
    $("#publicityImageType").val(num);
    var publicityImageBtn0 = $("#publicityImageBtn0"),
        publicityImageBtn1 = $("#publicityImageBtn1"),
        publicityImage = $("#publicityImage");
    if (num == 0) {
        publicityImageBtn0.removeClass("din").attr("valid", publicityImageBtn0.attr("tested"));
        publicityImageBtn1.addClass("din").attr("valid", "");
        publicityImage.attr("src", getRootPath() + "/images/default_bg_h.png")
    } else {
        publicityImageBtn0.addClass("din").attr("valid", "");
        publicityImageBtn1.removeClass("din").attr("valid", publicityImageBtn0.attr("tested"));
        publicityImage.attr("src", getRootPath() + "/images/default_bg_s.png")
    }
    $("#publicityImagePath").val("");
    iframeHeightAuto(window.parent["iframe_detail"]);
}
// 游戏DEMO
function attacheAjaxUploadDemo(btn, pathhref, path) {
    var tempId = "" + $("#appId").val();
    var url = getRootPath() + "/ajaxUpload/uploadCoreFile";
    createFileUpload(btn, url, {categoryPath: 'product', pathId: tempId}, ['rar', 'zip', 'apk', 'RAR', 'ZIP', 'APK'], function (file, response) {
        if (response.result) {
            $("#" + pathhref).show();
            //$("#" + pathhref).attr("href", response.filePath);
            $("#" + pathhref).attr("onclick","downLoadDemoByEfsId("+tempId+","+response.efsId+")");
            $("#" + path).attr("value", response.efsId);
            $.messager.alert('提示',response.msg,'info');
            delErrorTip($("#" + btn));
        } else {
            $.messager.alert('提示',response.msg,'info');
        }
        $("#" + btn).next("span").remove();
    });
}

function selectAntOrSdk(obj, num) {
    var _this = $(obj), name = _this.attr("name");
    if (_this.attr("checked") == 'checked'){
        $("#" + name + "_head").show();
        $("#" + name + "_p").show();
        var table = $("#" + name + "_table");
        table.show();
        table.find("input[tested]").each(function () {//添加验证
            var $this = $(this);
            $this.attr("valid", $this.attr("tested"));
        })
    } else {
        $("#" + name + "_head").hide();
        $("#" + name + "_p").hide();
        var table = $("#" + name + "_table");
        table.hide();
        table.find("input[tested]").each(function () {//删除验证
            var $this = $(this);
            $this.attr("valid", "");
        })
    }
    iframeHeightAuto(window.parent["iframe_detail"]);
    if (($('#antCheckBox').attr("checked") == 'checked') && ($('#sdkCheckBox').attr("checked") == 'checked')){
        $("#embeddingSdkType").val("3");
    } else if ($('#sdkCheckBox').attr("checked") == 'checked'){
        $("#embeddingSdkType").val("2");
    } else if ($('#antCheckBox').attr("checked") == 'checked'){
        $("#embeddingSdkType").val("1");
    } else {
        $("#embeddingSdkType").val("0");
    }
}

function selectAntOrSdkLoad(obj, num) {
    var _this = $(obj), name = _this.attr("name");
    _this.attr("checked",'true');
    if (_this.attr("checked") == 'checked'){
        $("#" + name + "_head").show();
        var table = $("#" + name + "_table");
        table.show();
        table.find("input[tested]").each(function () {//添加验证
            var $this = $(this);
            $this.attr("valid", $this.attr("tested"));
        })
    } else {
        $("#" + name + "_head").hide();
        var table = $("#" + name + "_table");
        table.hide();
        table.find("input[tested]").each(function () {//删除验证
            var $this = $(this);
            $this.attr("valid", "");
        })
    }
    iframeHeightAuto(window.parent["iframe_detail"]);
}

//是否选择蚂蚁屋  1是选择，2是不选
function selectAnt (obj, num) {
    var _this = $(obj), name = "ant";
    if (num == 2) {
        $("#" + name + "_head").addClass("din");
        $("#" + name + "_p").addClass("din");
        var table = $("#" + name + "_table");
        table.addClass("din");
        table.find("input[tested]").each(function () {//删除验证
            var $this = $(this);
            $this.attr("valid", "");
        })
        $("input[name='isSnsGame'][value='0']").attr("checked",true);
    } else {
        $("#" + name + "_head").removeClass("din");
        $("#" + name + "_table").removeClass("din");
        $("#" + name + "_p").removeClass("din");
        var table = $("#" + name + "_table");
        table.find("input[tested]").each(function () {//添加验证
            var $this = $(this);
            $this.attr("valid", $this.attr("tested"));
        })
        $("input[name='isSnsGame'][value='1']").attr("checked",true);
    }
    iframeHeightAuto(window.parent["iframe_detail"]);
}
//是否自费 1免费，2收费
function selectPay (obj, num){
    var pay_detail = $("#pay_detail");
    if(num == 2){
        pay_detail.show();
        if ($("#telnetCheckBox").is(":checked")){
            $("#embeddingSdkType").val(1);
        } else if($("#threeCheckBox").is(":checked")){
            $("#embeddingSdkType").val(2);
        }  else if($("#onlyCheckBox").is(":checked")){
            $("#embeddingSdkType").val(3);
        }
    }else{
        pay_detail.hide();
        $("#embeddingSdkType").val(0);
    }
    $("#datailTableId").find("textarea").css("border","none");
    iframeHeightAuto(window.parent["iframe_detail"]);
}
//TV游戏引入资费类型 0免费，1收费
function selectTvPay (obj, num){
    var pay_detail = $("#pay_detail");
    if(num == 1){
        pay_detail.show();
        $("#embeddingSdkType").val(3);
    }else{
        pay_detail.hide();
        $("#embeddingSdkType").val(0);
    }
    $("#datailTableId").find("textarea").css("border","none");
    iframeHeightAuto(window.parent["iframe_detail"]);
}
//计费方式 1是电信，2是三网3是仅电信
function selectNetwork(obj,num){
    if(num == 2){
        $("#embeddingSdkType").val(2);
    }else if (num == 1){
        $("#embeddingSdkType").val(1);
    } else if (num == 3){
        $("#embeddingSdkType").val(3);
    }
    iframeHeightAuto(window.parent["iframe_detail"]);
}
//是否用户SDK
function isUserSdk(obj, num) {
    if (num == 2){
        $("input[name='isUserSdk'][value='0']").attr("checked",true);
    } else {
        $("input[name='isUserSdk'][value='1']").attr("checked",true);
    }
}

// 设置等级
function setLevel(){
    var score = $("#score").val();
    if (score != ''){
        var scoreInt = parseInt(score);
        if (scoreInt >= 90 && scoreInt <= 100){
            $("#levelTd").html("S");
            $("#level").val(1);
        } else if (scoreInt >= 80 && scoreInt < 90){
            $("#levelTd").html("A");
            $("#level").val(2);
        } else if (scoreInt >= 60 && scoreInt < 80){
            $("#levelTd").html("B");
            $("#level").val(3);
        } else if (scoreInt >= 0 && scoreInt < 60){
            $("#levelTd").html("C");
            $("#level").val(4);
        } else {
            $("#levelTd").html("");
            $("#level").val('');
        }
    } else {
        $("#levelTd").html("");
        $("#level").val('');
    }
}

//下载计费
function selectPayStatus (obj, num) {
    var payStatus = $("#downPayStatus"), payStatusTr = $("#payStatusTr"), payStatusDescTr = $("#payStatusDescTr");
    payStatus.val(num);
    $(obj).addClass("sled3").siblings("span").removeClass("sled3");
    if (num == 0) {
        payStatusTr.addClass("din")
        payStatusTr.find("input").attr("valid", "");
        delErrorTip(payStatusTr.find("input"));

        payStatusDescTr.addClass("din")
        payStatusDescTr.find("input").attr("valid", "");
        delErrorTip(payStatusDescTr.find("input"))

    } else {
        payStatusTr.removeClass("din");
        payStatusTr.find("input").attr("valid", "require capital");

        payStatusDescTr.removeClass("din");
        payStatusDescTr.find("input").attr("valid", "require maxLength");
    }
    delErrorTip(payStatus)
    iframeHeightAuto(window.parent["iframe_detail"]);
}
//下载计费tV
function selectPayStatusTV (obj, num) {
    var payStatus = $("#downPayStatus"), payStatusTr = $("#payStatusTr"), payStatusDescTr = $("#payStatusDescTr"),payBuildTr = $("#payBuildTr"),payNoBuildTr = $("#payNoBuildTr");
    payStatus.val(num);
    $(obj).addClass("sled3").siblings("span").removeClass("sled3");
    if (num == 0) {//否
        payStatusTr.addClass("din")
        payStatusTr.find("input").attr("valid", "");
        delErrorTip(payStatusTr.find("input"));
        payStatusDescTr.addClass("din")
        payStatusDescTr.find("input").attr("valid", "");
        delErrorTip(payStatusDescTr.find("input"))
        payBuildTr.addClass("din");
        payNoBuildTr.removeClass("din");
       // payNoBuildTr.find("input:first").attr("checked",true).siblings("input").attr("checked",false);
    } else {
        payStatusTr.removeClass("din");
        payStatusTr.find("input").attr("valid", "require capital");

        payStatusDescTr.removeClass("din");
        payStatusDescTr.find("input").attr("valid", "require maxLength");
        payBuildTr.removeClass("din");
        payNoBuildTr.addClass("din");

    }
    delErrorTip(payStatus)
    iframeHeightAuto(window.parent["iframe_detail"]);
}
