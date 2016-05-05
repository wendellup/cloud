<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>新增游戏需求</title>
    <r:require module="easyui_core"/>
    <r:layoutResources/>
    <link type="text/css" rel="stylesheet" href="${request.contextPath}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/js/jquery-easyui/themes/default/my97.css">
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/js/jquery-easyui/themes/default/global.css">

    <g:render template="/rootpath"></g:render>
    <script type="text/javascript" src="${request.contextPath}/js/common.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/ajaxupload.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/jquery-easyui/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/jquery-easyui/plugins/jquery.my97.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/jquery.form.js"></script>
</head>
<script type="text/javascript">
    $(document).ready(function () {
        attacheUpload("gamePathBtn", "apk", 1);
//        attacheUpload("gameIconBtn", "jpg", 2);
//        attacheUpload("imisPathBtn", "txt", 4);
        attacheAjaxUpload('gameIconBtn',{imageType:2, width:'150', height:'150'},'#gameIcon','#gameIconinfo');
//        alert(1);
    });

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
    function attacheUpload(btn, doc, type) {
        var url = "${createLink(controller: 'ajaxUpload', action: 'uploadFile')}?fileType=" + type;
        var fileType = [];
        if (doc == "doc" || doc == "doc_moban") {
            fileType = ["docx", "doc"];
        } else if (doc == "txt" || doc == "txt_only") {
            fileType = ["txt"];
        } else if (doc == "jpg_only") {
            fileType = ["jpg", "JPG"];
        } else if (doc == "apk") {
            fileType = ["apk"];
        } else {
            fileType = ['jpg', 'png', 'bmp', 'JPG', 'PNG', 'BMP'];
        }

        createFileUpload(btn, url, {}, fileType, function (file, response) {
            if (response.result) {
                if (type == 1) {
                    $("#packageName").val(response.packageName);
                    $("#gameSize").val(response.fileSize);
                    $("#gameDownloadUrl").val(response.downloadPath);
                    var htmlStr = "已上传&nbsp;<a id='gameDownloadUrlInfo' href='"+response.realFilePath+"'>"+response.packageName+"</a>";
                    $("#apk_span").html(htmlStr);
                } else if (type == 2) {
                    $("#gameIcon").val(response.downloadPath);
                    $("#gameIconinfo").attr('src', response.realFilePath);
                } else if (type == 4) {
                    $("#imisUrl").val(response.downloadPath);
                    $("#imsiNum").val(response.imsiNum);
                }
                $.messager.alert('提示', response.msg, 'info');
            } else {
                $.messager.alert('提示', response.msg, 'error');
            }
        });
    }
    ;

    function back() {
        location.href = '${createLink(controller: 'chargeIndex', action: 'indexPreGame')}';
    }

    function submitValidate(){
        var advertTypeVal = $("#advertType").val();
        if (advertTypeVal==null || ""==advertTypeVal || 0==advertTypeVal) {
            $.messager.alert('提示', '请选择推送展现类型！', 'info');
            return false;
        }

        var imsiNumVal = $.trim($("#imsiNum").val());
        if (!/^[0-9]*$/.test(imsiNumVal)
            || Number(imsiNumVal)<=0
            || Number(imsiNumVal)>10000000) {
            $.messager.alert('提示', '推送计划人数只能是1至10000000的整数！', 'info');
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
    }

    //提交新增的常驻内存
    function infoSubmit() {
        var url = "${createLink(controller: 'preMemory',action:'addSubmit')}";
        %{--} else if(submitType == 1) {--}%
        %{--url = "${createLink(controller: 'memory',action:'editSubmit')}";--}%
        %{--}--}%
        $('#fm').form('submit', {
            url: url,
            type: 'post',
            onSubmit: function(){
                var validate = $(this).form('validate');
                if(validate){
                    return submitValidate();
                }
                return validate;
            },
            success: function (data) {
                var json_data = data.replace('[', '{').replace(']', '}');
                var data = eval('(' + json_data + ')');
                if (data['result']) {
//                    $('#dlg').dialog('close');
//                    $("#dg").datagrid('reload');
                    $.messager.alert('提示', data['msg'], 'info', function () {
//                        $("#dg").datagrid('reload');
                        location.href = '${createLink(controller: 'chargeIndex', action: 'indexPreGame')}';
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

</script>

<body style="height: 800px">
<e:layout fit="true">
    <div class="matter_box">
        <h3 class="nopoint"><strong>需求录入管理&nbsp;&gt;&nbsp;游戏需求&nbsp;&gt;&nbsp;新增游戏需求</strong></h3>
        %{--<e:form id="fm">--}%
        <form id="fm">
            %{--游戏类型的objType设置为1--}%
            <input type="hidden" name="objType" id="objType" value="1"/>
            <table class="table2">
                <tr>
                    <td width="30%" align="right"><span class="wordred">*</span>资源游戏标题：</td>
                    <td align="left">
                        <input type="text" name="title" id="title" class="easyui-validatebox input_width200"
                               data-options="required:true,missingMessage:'请输入标题'" onkeyup="checkLength(this, 28)">
                        <span style="color:#FF0000;">营销语标题，14个中文以内</span>
                    </td>
                </tr>
                <tr>
                    <td align="right"><span class="wordred">*</span>资源游戏描述：</td>
                    <td align="left">
                        <input type="text" name="description" id="description" class="easyui-validatebox input_width200"
                               data-options="required:true,missingMessage:'请输入常驻内存描述'" onkeyup="checkLength(this, 40)">
                        <span style="color:#FF0000;">营销语主体，20个中文以内</span>
                    </td>
                </tr>
                <tr>
                    <td align="right"><span class="wordred">*</span>推送展现类型：</td>
                    <td align="left">
                        <g:select id="advertType" name="advertType"  class="easyui-validatebox"  from='${advertTypeList}'
                                  noSelection="${['':'--请选择--']}" optionKey="key" optionValue="value"></g:select>
                    </td>
                </tr>
                <tr>
                    <td align="right"><span class="wordred">*</span>是否静默安装：</td>
                    <td align="left">
                        <g:select id="silentDownload" name="silentDownload"  class="easyui-validatebox"  from='${silentDownloadList}'
                                  optionKey="key" optionValue="value"></g:select>
                    </td>
                </tr>
                <tr>
                    <td align="right"><span class="wordred">*</span>推送任务标识：</td>
                    <td align="left">
                        <g:select id="flag" name="flag"  class="easyui-validatebox"  from='${flagTypeList}'
                                  optionKey="key" optionValue="value"></g:select>
                    </td>
                </tr>
                <tr class="time_">
                    <td align="right"><span class="wordred">*</span>推送计划日期：</td>
                    <td align="left"><input type="text" name="startTime" id="startTime" class="easyui-my97"
                                            data-options="required:true,missingMessage:'请输入开始时间',dateFmt:'yyyy-MM-dd HH:mm:ss'"
                                            readonly="readonly"/></td>
                </tr>

                <tr>
                    <td width="30%" class="tright"><span class="wordred">*</span>资源游戏代码：</td>
                    <td class="tleft">
                        <input type="text" name="linkUrl" id="linkUrl" class="easyui-validatebox input_width200"
                               data-options="required:true,missingMessage:'请输入资源游戏代码'" onkeyup="checkLength(this, 40)">
                        <span style="color:#FF0000;">游戏长ID，12个数字</span>
                    </td>
                </tr>

                <tr>
                    <td width="30%" class="tright"><span class="wordred">*</span>资源游戏名称：</td>
                    <td class="tleft">
                        <input type="text" name="gameName" id="gameName" class="easyui-validatebox input_width200"
                               data-options="required:true,missingMessage:'请输入资源游戏名称'" onkeyup="checkLength(this, 100)">
                        <span style="color:#FF0000;">50个中文以内</span>
                    </td>
                </tr>
                <tr>
                    <td class="tright"><span class="wordred">*</span>资源游戏包体：</td>
                    <td colspan="3">
                        <a class="btn_orange fl" href="javascript:void(0)" id="gamePathBtn">点击上传
                        </a>
                        <input type="hidden" name="packageName" id="packageName">
                        <input type="hidden" name="gameSize" id="gameSize">
                        <input type="hidden" name="gameDownloadUrl" id="gameDownloadUrl"/>
                        <i class="error_tip" style="top:212px; left:5px;"></i>
                        <span id="apk_span" style="float:left; margin-top:15px;">
                            %{--已上传&nbsp;<a id="gameDownloadUrlInfo" href="${preMemoryInfoVo.realGameDownloadUrl}">${preMemoryInfoVo.packageName}</a>--}%
                        </span>
                    </td>
                </tr>
                <tr>
                    <td align="right" valign="top"><span class="wordred">*</span>资源游戏图片：</td>
                    <td align="left" valign="top">
                        <input type="hidden" name="gameIcon" id="gameIcon" data-options="required:true"/>
                        <img id="gameIconinfo" src="${request.contextPath}/images/image_preview.jpg" width="100" height="100"/>
                        <span><a href="javascript:void(0);" id="gameIconBtn">插入图片</a></span>
                        <div class="wordred"></div>
                        <span style="color:#FF0000;">png/jpeg/jpg，150*150px</span>
                    </td>
                </tr>
                <tr>
                    <td width="30%" class="tright"><span class="wordred">*</span>资源渠道代码：</td>
                    <td class="tleft">
                        <input type="text" name="ownName" id="ownName" class="easyui-validatebox input_width200"
                               data-options="required:true,missingMessage:'请输入资源渠道代码(游戏计费渠道号)'" onkeyup="checkLength(this, 40)">
                        <span style="color:#FF0000;">游戏计费渠道号，8个西文字符</span>
                    </td>
                </tr>
                <tr>
                    <td width="30%" class="tright"><span class="wordred">*</span>推送计划人数：</td>
                    <td class="tleft">
                        <input type="text" name="imsiNum" id="imsiNum" class="easyui-validatebox input_width200"
                               data-options="required:true,missingMessage:'请输入推送计划人数(1至10000000的整数)'" onkeyup="checkLength(this, 40)">
                        <span style="color:#FF0000;">1至10000000的整数</span>
                    </td>
                </tr>
                <tr>
                    <td width="30%" class="tright">推送用户描述：</td>
                    <td class="tleft">
                        <input type="text" name="remark" id="remark" class="easyui-validatebox input_width200"
                               onkeyup="checkLength(this, 256)">
                        <span style="color:#FF0000;">128个中文汉字以内</span>
                    </td>
                </tr>
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

