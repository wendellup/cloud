<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>新增文章</title>
    <r:require module="easyui_core"/>
    <r:layoutResources/>
    <link type="text/css" rel="stylesheet" href="${request.contextPath}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/js/jquery-easyui/themes/default/my97.css">
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/js/jquery-easyui/themes/default/global.css">
    <link type="text/css" rel="stylesheet" href="${request.contextPath}/js/CLEditor/jquery.cleditor.css"/>


    <g:render template="/rootpath"></g:render>
    <script type="text/javascript" src="${request.contextPath}/js/CLEditor/jquery.cleditor.min.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/common.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/ajaxupload.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/jquery-easyui/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/jquery-easyui/plugins/jquery.my97.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/jquery.form.js"></script>
</head>
<script type="text/javascript">
	$(document).ready(function () {
        $("#content").cleditor({width : 550, height: 200});
//        attacheUpload("gamePathBtn", "apk", 1);
//        attacheUpload("gameIconBtn", "jpg", 2);
//        attacheUpload("imisPathBtn", "txt", 4);
//        attacheAjaxUpload('gameIconBtn',{imageType:2, width:'150', height:'150'},'#gameIcon','#gameIconinfo');
    });

    //初始化常驻内存类型,推送标识类型
    function initSEL(data) {
        var advertType = data.advertType;
        $("select[id='advertType']").find("option").each(function () {
            if (this.value == advertType) {
                this.selected = "selected";
                $("#advertType").val(advertType);
            }
        });

        var flag= data.flag;
        $("select[id='flag']").find("option").each(function () {
            if (this.value == flag) {
                this.selected = "selected";
                $("#flag").val(flag);
            }
        });

        var silentDownload = data.silentDownload;
        $("select[id='silentDownload']").find("option").each(function () {
            if (this.value == silentDownload) {
                this.selected = "selected";
                $("#silentDownload").val(silentDownload);
            }
        });
    }

    function attacheAjaxUpload(btn,param,efsIdObj,previewObj){
        var url = "${createLink(controller: 'ajaxUpload',action:'uploadImg')}";
        createUpload(btn,param,url,function(file, response){
            if(response.result){
                $.messager.alert('提示',response.msg,'info');
                jQuery(efsIdObj).val(response.downloadPath);
                jQuery(previewObj).attr("src",response.realFilePath);
//                $('#gameIcon').attr("value", data.gameIcon);
//                $('#gameIconinfo').attr("src", data.realGameIcon);
            }else{
                $.messager.alert('提示',response.msg,'error');
            }
        },200);
    }

    //上传文件
    function attacheUpload(btn, doc, type){
        var url = "${createLink(controller: 'ajaxUpload', action: 'uploadFile')}?fileType=" + type;
        var fileType = [];
        if (doc=="doc" || doc=="doc_moban"){
            fileType = ["docx","doc"];
        } else if (doc=="txt" || doc=="txt_only"){
            fileType = ["txt"];
        } else if (doc == "jpg_only"){
            fileType = ["jpg", "JPG"];
        } else if (doc == "apk") {
            fileType = ["apk"];
        } else {
            fileType = ['jpg','png','bmp','JPG','PNG','BMP'];
        }

        createFileUpload(btn,url,{},fileType,function(file, response){
            if(response.result){
                if(type == 1) {
                    $("#packageName").val(response.packageName);
                    $("#gameSize").val(response.fileSize);
                    $("#gameDownloadUrl").val(response.downloadPath);
                    var htmlStr = "已上传&nbsp;<a id='gameDownloadUrlInfo' href='"+response.realFilePath+"'>"+response.packageName+"</a>";
                    $("#apk_span").html(htmlStr);
                } else if(type == 2) {
                    $("#gameIcon").val(response.downloadPath);
                    $("#gameIconinfo").attr('src', response.realFilePath);
                } else if(type == 4) {
                    $("#imisUrl").val(response.downloadPath);
                    $("#imsiNum").val(response.imsiNum);
                }
                $.messager.alert('提示', response.msg, 'info');
            }else{
                $.messager.alert('提示', response.msg, 'error');
            }
        });
    };

    function back() {
        location.href = '${createLink(controller: 'index', action: 'indexAricle')}';
    }

    function submitValidate(){
        var advertTypeVal = $("#advertType").val();
        if (advertTypeVal==null || ""==advertTypeVal || 0==advertTypeVal) {
            $.messager.alert('提示', '请选择推送展现类型！', 'info');
            return false;
        }
        var packageNameVal = $("#packageName").val();
        var gameSizeVal = $("#gameSize").val();
        var gameDownloadUrlVal = $("#gameDownloadUrl").val();
        if (packageNameVal==null || ""==packageNameVal
            || gameSizeVal==0 || gameSizeVal==null || ""==gameSizeVal
            || gameDownloadUrlVal==null || ""==gameDownloadUrlVal) {
            $.messager.alert('提示', '请上传资源包体！', 'info');
            return false;
        }
        var gameIconVal = $("#gameIcon").val();
        if (gameIconVal==null || ""==gameIconVal) {
            $.messager.alert('提示', '请上传资源图片！', 'info');
            return false;
        }
        var imisUrlVal = $("#imisUrl").val();
        if (imisUrlVal==null || ""==imisUrlVal) {
            $.messager.alert('提示', '请上传imsi文件！', 'info');
            return false;
        }
    }

    //提交新增的常驻内存
    function infoSubmit() {
        var url = "${createLink(controller: 'article',action:'addSubmit')}";

        var selectedTagId = '';
        $('input[name="tagId"]:checked').each(function() {
            if(selectedTagId.indexOf($(this).val()) == -1) {
                selectedTagId = selectedTagId + "," + $(this).val();
            }
        });
        if (selectedTagId != '') {
            selectedTagId = selectedTagId.substring(1);
        }
        $("#selectedTagId").attr("value", selectedTagId);
        //遮罩层，防止用户重复提交
        $('<div id="mask"></div>').appendTo('body').height($("body").height());

        $('#fm').form('submit', {
            url: url,
            type : 'post',
//            onSubmit: function(){
//                var validate = $(this).form('validate');
//                if(validate){
//                    return submitValidate();
//                }
//                return validate;
//            },
            success: function (data) {
                var json_data = data.replace('[', '{').replace(']', '}');
                var data = eval('(' + json_data + ')');
                if (data['result']) {
//                    $('#dlg').dialog('close');
//                    $("#dg").datagrid('reload');
                    $.messager.alert('提示', data['msg'], 'info', function () {
//                        $("#dg").datagrid('reload');
                        location.href = '${createLink(controller: 'index', action: 'indexArticle')}';
                    });
//                    $.messager.alert('提示', data['msg'], 'ok');
                } else {
                    $.messager.alert('提示', data['msg'], 'error');
                }
            },
            error: function (data) {
                var json_data = data.replace('[', '{').replace(']', '}');
                var data = eval('(' + json_data + ')');
                $.messager.alert('提示', data['msg'], 'error');
            }
        });
    }

    function openDialog() {
        showDialog({
            id:'dlg',
            width: 860,
            height: 500,
            title: "游戏需求列表",
            url: '${createLink(controller: 'preMemory',action:'serviceMemoryList',params:['objType':1] )}'
        });
    }

    function fillMemory() {
        var rows = $('#servicedg').datagrid('getSelections');
        if (rows.length!=1){
            $.messager.alert('Info', "请选择一条记录.");
            return;
        }
        if (rows[0].id != -1) {
            $('<div id="mask"></div>').appendTo('body').height($("body").height());
            $.ajax({
                type: "POST",
                url: "${createLink(controller: 'preMemory', action: 'getPreMemory')}",
                data: {
                    id: rows[0].id
                },
                dataType:'json',
                success: function (data) {
//                    var classifyName = data['classifyName'];
//                    var title = data['title'];
                    $('#title').attr("value", data.title);
                    $('#description').attr("value", data.description);
                    $('#linkUrl').attr("value", data.linkUrl);
                    $('#ownName').attr("value", data.ownName);
                    $('#gameName').attr("value", data.gameName);
                    $('#packageName').attr("value", data.packageName);
                    $('#gameSize').attr("value", data.gameSize);
                    $('#gameDownloadUrl').attr("value", data.gameDownloadUrl);
                    $('#gameIcon').attr("value", data.gameIcon);
                    $('#gameIconinfo').attr("src", data.realGameIcon);
                    if(data.remark){
                        $('#remark').attr("value", data.imsiNum + data.remark);
                    }else{
                        $('#remark').attr("value", data.imsiNum);
                    }
                    var htmlStr = "已上传&nbsp;<a id='gameDownloadUrlInfo' href='"+data.realGameDownloadUrl+"'>"+data.packageName+"</a>";
                    $("#apk_span").html(htmlStr);
                    initSEL(data);
                    $("#mask").remove();
                    $('#dlg').dialog('close');
                },
                error: function (data) {
                    $("#mask").remove();
                }
            });
        }
    }
	</script>
<body style="height: 800px">
%{--<body>--}%
<e:layout fit="true">
<div class="matter_box">
    <h3 class="nopoint"><strong>Blog管理&nbsp;&gt;&nbsp;文章管理&nbsp;&gt;&nbsp;新增文章</strong></h3>
    %{--<e:form id="fm">--}%
    <form id="fm">
    <input type="hidden" name="paramId" id="paramId" value="${parameterApp.id}"/>
    <input type="hidden" id="selectedTagId" name="selectedTagId" />
	<table class="table2">
        <tr>
            <td width="30%" align="right">所属节点：</td>
            <td align="left">
                ${parameterApp.name}
            </td>
        </tr>
        <tr>
            <td width="30%" align="right"><span class="wordred">*</span>标题：</td>
            <td align="left">
                <input type="text" name="title" id="title" class="easyui-validatebox input_width600" data-options="required:true,missingMessage:'请输入文章标题'" onkeyup="checkLength(this, 100)">
                <span style="color:#FF0000;">文章标题，50个中文以内</span>
            </td>
        </tr>
        <tr>
            <td class="tright" valign="top" style="padding: 5px 0 10px 0;"><span class="wordred">*</span>文章内容：</td>
            <td class="tleft" style="padding: 5px 0 10px 0;" colspan="3">
                <textarea name="content" id="content" onkeyup="checkLength(this, 1000);"></textarea>
            </td>
        </tr>
        <tr>
            <td width="30%" align="right">选择标签：</td>
            <td class="left">
                <g:each var="tagInfo" in="${parameterTagInfoList}">
                    <input type="checkbox" id="tagId" name="tagId" value="${tagInfo.id}" style="vertical-align:middle;" />${tagInfo.tagName}<br/>
                </g:each>
                %{--<input type="checkbox" id="logo_switch" name="floatId" value="831" style="vertical-align:middle;" onclick="changtType(1);" />浮标（V241版本及以上）<br/>--}%
                %{--<input type="checkbox" id="gift_switch" name="floatId" value="832" style="vertical-align:middle;" />礼包<br/>--}%
                %{--<input type="checkbox" id="strategy_switch" name="floatId" value="833" style="vertical-align:middle;" onclick="changtType(2);" />攻略<br/>--}%
            </td>
        </tr>
        <br/>


   </table>
%{--</e:form>--}%
    </form>
    <div style="text-align: center">
        <input class="btn_orange" type="button" value="确定" onclick="infoSubmit()"/>
        <input class="btn_orange" type="button" value="取消" onclick="back()"/>
    </div>
</div>


</e:layout>
</body>
</html>

