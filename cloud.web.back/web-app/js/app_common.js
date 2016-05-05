/**
 * Created by Administrator on 14-5-9.
 */
function tabVad(obj) {
    var vad = $(obj).attr("vad");
    var val = $(obj).val();
    $(obj).val(val.replace(/[\n]/ig,''));
    if (vad.indexOf("length") > -1) {
        if ($(obj).val().length > parseInt(vad.substring(6))) {
            $(obj).css("border","1px solid red").attr("text","1");
        }else if(/[,'"\t]+/.test($(obj).val())){
            $(obj).css("border","1px solid red").attr("text","1");
            setPageTip("不能输入英文逗号，引号，制表符");
        } else {
            $(obj).css("border", "none").attr("text", "0");
        }
    }else if(vad.indexOf("byte") > -1){
        if (getByteLen($(obj).val()) > 20) {
            $(obj).css("border","1px solid red").attr("text","1");
        }else {
            $(obj).css("border", "none").attr("text", "0");
        }
    }  else {
        if(vad == "number"){
            if (($(obj).val() > 0 && /^[1-9]\d*$/.test($(obj).val()) && parseInt($(obj).val()) < 31) || $(obj).val() == 0.1) {
                $(obj).css("border", "none").attr("text", "0");
            } else {
                $(obj).css("border", "1px solid red").attr("text", "1");
            }
        }

    }
}

function getByteLen (val) {
    var len = 0;
    for (var i = 0; i < val.length; i++) {
        var a = val.charAt(i);
        if (a.match(/[^\x00-\xff]/ig) != null)
        {
            len += 2;
        }
        else
        {
            len += 1;
        }
    }
    return len;
}
//SDK计费
var  addLoopNumber = 0;
var z_Index = 100;
//获取节点对象的指定的父节点或间接父节点
function searchParentNode(nodeObj,tagNameStr){
    var tagNamePar = tagNameStr.toUpperCase();
    var parentObj;
    var parentObjName;
    var error = new RangeError();
    var msgArray = [];
    msgArray.push("此节点的文档树无");
    msgArray.push(tagNameStr);
    msgArray.push("节点");
    error.message = msgArray.join("");
    try{
        parentObj = nodeObj.parentNode;                                        //获取传入参数nodeObj的父节点
        parentObjName = parentObj.nodeName.toUpperCase();
        if(tagNamePar == parentObjName){
            return parentObj;                                                //返回递归函数值。
        }else{
            return arguments.callee(parentObj,tagNameStr);                  //处理递归函数值，用arguments对象的callee属性来执行递归。
        }
    }catch(e){
        alert(error.message);                                               //无此父节点时弹出的错误。
        iframeHeightAuto(window.parent["iframe"]);
        iframeHeightAuto(window.parent["iframe_detail"]);
    }
}
//添加子节点
function addChildrenNode(){
    var parentObj = document.getElementById("datailTableId");
    var cloneParentObj =  document.getElementById("datailCloneTableId");
    var childObj =cloneParentObj.getElementsByTagName("tr")[0].cloneNode(true);   //复制隐藏节点的及子节点和间接子节点。
    var len = parentObj.getElementsByTagName("tr").length;
    var hiddenAreaSubObj = document.getElementById("count_consume");
    hiddenAreaSubObj.setAttribute("value",++addLoopNumber);//获取父节点的子节点length。
    childObj.getElementsByTagName("td")[0].innerHTML = len+1;                       //将第一个单元格的内容赋值给length.
    $(childObj).find(".sel_btn").css("z-index",z_Index -=1);
    $("#datailTrFirst").find(".sel_btn").css("z-index",100);
    againCallName(childObj,len+1);
    if((len+1)%2 != 0){
        childObj.className = "even";
    }else{
        childObj.className = "";
        //iframeHeightAuto(window.parent["iframe"]);
        //iframeHeightAuto(window.parent["iframe_detail"]);
    }
    parentObj.appendChild(childObj);
    for(var i= 0; i<len; i++){
        againName(parentObj.getElementsByTagName("tr")[i], i+1);
    }
    iframeHeightAuto(window.parent["iframe"]);
    iframeHeightAuto(window.parent["iframe_detail"]);
}
//删除子节点
function delChildrenNode(nodeObj){
    var parentObj = document.getElementById("datailTableId");
    var childObj = searchParentNode(nodeObj,"tr");                                  //获取间接父节点“tr”节点,
    var len = parentObj.getElementsByTagName("tr").length;
    var otherChildObj;
    var nameStr;
    parentObj.removeChild(childObj);
    childObj = null;
    for(var i= 0,j=1; i<len; i++, j++){
        otherChildObj = parentObj.getElementsByTagName("tr")[i];
        if(otherChildObj == childObj){
            j = j-1;
            continue;
        }
        if(j%2 != 0){
            otherChildObj.className = "even";
        }else{
            otherChildObj.className = "";
        }
        otherChildObj.getElementsByTagName("td")[0].innerHTML = j;
    }
    againName(parentObj);
    iframeHeightAuto(window.parent["iframe"]);
    iframeHeightAuto(window.parent["iframe_detail"]);
}
//将textarea的name属性值改变及赋值
function againCallName(parentObj,value){
    var textareaSubObjs = parentObj.getElementsByTagName("textarea");
    var inputSubObjs =  parentObj.getElementsByTagName("input");
    var selectSubObjs =  parentObj.getElementsByTagName("select");
    var nameStr;
    for(var i=0; i<inputSubObjs.length; i++){  //将textarea的name属性值改变及复制
        nameStr = inputSubObjs[i].getAttribute("name") + String(value);
        inputSubObjs[i].setAttribute("name",nameStr);
    }
    for(var i=0; i<textareaSubObjs.length; i++){  //将textarea的name属性值改变及复制
        nameStr = textareaSubObjs[i].getAttribute("name") + String(value);
        textareaSubObjs[i].setAttribute("name",nameStr);
    }
    for(var i=0; i<selectSubObjs.length; i++){  //将select的name属性值改变及复制
        nameStr = selectSubObjs[i].getAttribute("name") + String(value);
        selectSubObjs[i].setAttribute("name",nameStr);
    }
}
//重置已有tr内name
function againtrname(parentObj,value){
    var textareaSubObjs = parentObj.getElementsByTagName("textarea");
    var inputSubObjs =  parentObj.getElementsByTagName("input");
    var selectSubObjs =  parentObj.getElementsByTagName("select");
    var nameStr;
    function removeNum(str){
        return str.replace(/\d+/,"");
    }
    for(var i=0; i<inputSubObjs.length; i++){  //将textarea的name属性值改变及复制
        nameStr = removeNum(inputSubObjs[i].getAttribute("name")) + String(value);
        inputSubObjs[i].setAttribute("name",nameStr);
    }
    for(var i=0; i<textareaSubObjs.length; i++){  //将textarea的name属性值改变及复制
        nameStr = removeNum(textareaSubObjs[i].getAttribute("name")) + String(value);
        textareaSubObjs[i].setAttribute("name",nameStr);
    }
    for(var i=0; i<selectSubObjs.length; i++){  //将select的name属性值改变及复制
        nameStr = removeNum(selectSubObjs[i].getAttribute("name")) + String(value);
        selectSubObjs[i].setAttribute("name",nameStr);
    }
}
//将计费提交的域重新命名
function againName(conObj){
    var items = conObj.getElementsByTagName("tr");
    for(var i=0; i<items.length; i++){  //将textarea的name属性值改变及复制
        againtrname(items[i],i+1);
    }
}
//单机游戏计费点验证
validAppSdk = function () {
    var isV = true;
    if ($("#appClass").val() == 11 && $("#embeddingSdkType").val() != 0){
        var tbodys = $("#datailTableId");
        tbodys.find("textarea").each(function () {
            var _area = $(this);

            if (_area.val() == "") {
                if (_area.attr("name").indexOf("remark")>-1){
                } else {
                    _area.css("border", "1px solid red");
                    isV = false;
                    return false;
                }
            } else if (_area.attr("text") == 1) {
                isV = false;
                return false;
            }
        })
    }

    return isV;
}

//单机游戏计费点验证
validProxyAppSdk = function () {
    var isV = true;
    if ($("#appClass").val() == 11){
        var tbodys = $("#datailTableId");
        tbodys.find("textarea").each(function () {
            var _area = $(this);

            if (_area.val() == "") {
                if (_area.attr("name").indexOf("remark")>-1){
                } else {
                    _area.css("border", "1px solid red");
                    isV = false;
                    return false;
                }
            } else if (_area.attr("text") == 1) {
                isV = false;
                return false;
            }
        })
    }

    return isV;
}

/** 游戏图片的CHECKBOX功能开始*/
//游戏上传截图
function attacheAjaxAppImageUpload(btn, type){
    var appId = "" + $("#appId").val();
    var url = getRootPath() + "/appImageUpload/uploadImage";
    createFileUpload(btn,url,{appId:appId,submitType:type},['jpg','png','JPG','PNG','zip','ZIP'],function(file, response){
        if(response.result){
            var apt = $("#appPicTable").find("tbody");
            for(var i = 0; i<response.fileList.length; i++){
                var file = response.fileList[i];
                var imageName = file.fileName;
                if(file.fileType==200){
                    var first_tr = apt.find("tr:first");
                    first_tr.find("a.checkbox").attr("onclick","").unbind("click").bind("click",function(){checkThis(this)}).attr("name",file.efsId);
                    first_tr.find("td").eq(1).html("已上传");
                    first_tr.find("td").eq(2).html("pic1<br/><img src='"+ file.filePath +"' onload='imgOnload(this,50,50)' />");
                    first_tr.find("td").eq(3).html(imageName.split(".")[1]);
                    first_tr.find("td").eq(4).html(file.width + "*" + file.height);
                    first_tr.find("td").eq(7).html(Math.round(file.fileSize/1024) + "KB");
                }else if(file.fileType==203){
                    var num = imageName.substring(imageName.indexOf(".")-1, imageName.indexOf(".")) - 1;
                    var trs = apt.find("tr").eq(num+1);
                    trs.find("a.checkbox").attr("onclick","").unbind("click").bind("click",function(){checkThis(this)}).attr("name",file.efsId)
                    trs.find("td").eq(1).html("已上传");
                    trs.find("td").eq(2).html(imageName.split(".")[0]+"<br/><img src='"+ file.filePath +"' onload='imgOnload(this,50,50)' />");
                    trs.find("td").eq(3).html(imageName.split(".")[1]);
                    trs.find("td").eq(4).html(file.height + "*" + file.width);
                    trs.find("td").eq(7).html(Math.round(file.fileSize/1024)+"KB");
                }else if(file.fileType==400){
                    // var num = imageName.substring(imageName.indexOf(".")-1, imageName.indexOf(".")) - 1;
                    var trs = apt.find("tr").eq(7);
                    trs.find("a.checkbox").attr("onclick","").unbind("click").bind("click",function(){checkThis(this)}).attr("name",file.efsId)
                    trs.find("td").eq(1).html("已上传");
                    trs.find("td").eq(2).html(imageName.split(".")[0]+"<br/><img src='"+ file.filePath +"' onload='imgOnload(this,50,50)' />");
                    trs.find("td").eq(3).html(imageName.split(".")[1]);
                    trs.find("td").eq(4).html(file.width + "*" + file.height);
                    trs.find("td").eq(7).html(Math.round(file.fileSize/1024)+"KB");
                }

                var iframe = window.parent.document.getElementById("iframe_detail");
                var iframe1 = window.parent.document.getElementById("iframe");
                $(iframe).height($(iframe).height()+70);
                $(iframe1).height($(iframe1).height()+70);
            }
            $("#"+btn).find("i").html("");
            $.messager.alert('提示',response.msg,'info');
        }else{
            $.messager.alert('提示',response.msg,'error');
        }
    });
}
//checkbox点选事件
checkThis = function(obj){
    var $this = $(obj);
    $this.hasClass("checkbox_sled")? $this.removeClass("checkbox_sled"):$this.addClass("checkbox_sled");
}
checkAllPic = function (obj) {
    var checkboxs = $(obj).parents("table.list_table").children("tbody").find(".checkbox"),
        checkFn = function (ischeck) {
            for (i = 0; i < checkboxs.length; i++) {
                if (ischeck && typeof ($(checkboxs[i]).attr("name")) == "string" && $(checkboxs[i]).attr("name") != "") {          //全选
                    $(checkboxs[i]).addClass("checkbox_sled");
                } else {               //全不选
                    $(checkboxs[i]).removeClass("checkbox_sled");
                }
            }
        };
    if ($(obj).hasClass("checkbox_sled")) {
        checkFn(false);
        $(obj).removeClass("checkbox_sled");
    } else {
        checkFn(true);
        $(obj).addClass("checkbox_sled");
    }
}
//删除勾选图片（截图或者图标）
deleteAppImage = function () {
    var appImageIds = "",
        apt = $("#appPicTable");
    apt.find("thead").find("a.checkbox_sled").removeClass("checkbox_sled");
    apt.find("tbody").find("a.checkbox_sled").each(
        function () {
            var checkBox = $(this);
            appImageIds = appImageIds + checkBox.attr("name") + ",";
            checkBox.removeClass("checkbox_sled").unbind("click").attr({"name":"","onclick":""});
            var tr = checkBox.parent().parent();
            tr.find("td").eq(1).html("未上传");
            tr.find("td").eq(2).find("img").remove();
            tr.find("td").eq(7).html("未上传");
        }
    )
    appImageIds = appImageIds.substr(0, appImageIds.length - 1)
    var requestUrl = getRootPath() + "/appImageUpload/deleteAppImage"
    $.ajax({
        type: "POST",
        url: requestUrl,
        async: false,
        data: "appImageId=" + appImageIds + "&appId=" + $("#appId").val(),
        success: function (data) {
            if (data.result) {

            } else {
                $.messager.alert('提示',response.msg,'info');
            }
        }
    });
}
validAppImageUploadDemo = function () {
    var listTab = $("#appPicTable").find("tbody"),
        firstTr = listTab.find("tr:first"),
        lastTr = listTab.find("tr:last"),
        foreTr = listTab.find("tr"),
        file_a = $("#appPicBtn"),
        screenshot = 0;
    for(var i = 1; i<foreTr.length; i++){
        if($(foreTr[i]).find("a").attr("name") != "" && typeof ($(foreTr[i]).find("a").attr("name")) != "undefined"){
            screenshot ++ ;
        }
    }
    if ((firstTr.find("a").attr("name") == "" || typeof (firstTr.find("a").attr("name")) == "undefined")  && !firstTr.find("a").hasClass("din")) {
        //file_a.find("i").html("至少上传一张游戏icon");
        return  "至少上传一张游戏icon";
    } else if (screenshot<4) {
        //file_a.find("i").html("至少上传四张游戏截图");
        return "至少上传四张游戏截图";
    }  else {
        return true;
    }
}
/** 游戏图片的CHECKBOX功能结束*/