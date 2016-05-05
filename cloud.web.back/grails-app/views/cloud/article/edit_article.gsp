<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>编辑文章</title>
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

    function back() {
        location.href = '${createLink(controller: 'index', action: 'indexAricle')}';
    }

    //编辑文章提交
    function infoSubmit() {
        var url = "${createLink(controller: 'article',action:'editSubmit')}";

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
                        var paramId = $("#paramId").val();
                        %{--location.href = '${createLink(controller: 'index', action: 'indexArticle', params:[tagType:tagType])}';--}%
                        location.href = "${createLink(controller: 'index', action: 'indexArticle')}?tagType="+paramId;
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
%{--<body>--}%
<e:layout fit="true">
<div class="matter_box">
    <h3 class="nopoint"><strong>Blog管理&nbsp;&gt;&nbsp;文章管理&nbsp;&gt;&nbsp;编辑文章</strong></h3>
    %{--<e:form id="fm">--}%
    <form id="fm">
    <input type="hidden" name="paramId" id="paramId" value="${parameterApp.id}"/>
    <input type="hidden" name="articleId" id="articleId" value="${article.id}"/>
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
                <input type="text" name="title" id="title" value="${article.title}" class="easyui-validatebox input_width600" data-options="required:true,missingMessage:'请输入文章标题'" onkeyup="checkLength(this, 100)">
                <span style="color:#FF0000;">文章标题，50个中文以内</span>
            </td>
        </tr>
        <tr>
            <td class="tright" valign="top" style="padding: 5px 0 10px 0;"><span class="wordred">*</span>文章内容：</td>
            <td class="tleft" style="padding: 5px 0 10px 0;" colspan="3">
                <textarea name="content" id="content" onkeyup="checkLength(this, 1000);" >${article.content}</textarea>
            </td>
        </tr>
        <tr>
            <td width="30%" align="right">选择标签：</td>
            <td class="left">
                <g:each var="tagInfo" in="${parameterTagInfoList}">
                    <input type="checkbox" id="tagId" name="tagId" value="${tagInfo.id}" style="vertical-align:middle;"
                    <g:each var="tagLinkInfo" in="${articleTagLinkList}">
                        <g:if test="${tagInfo.id==tagLinkInfo.tagId}">
                            checked="checked" isSelected="1"
                        </g:if>
                    </g:each>
                    />${tagInfo.tagName}<br/>

                </g:each>

                %{--<g:if test="${articleTagLinkList.contains(tagInfo.id)}">checked="checked" isSelected="1"</g:if>--}%

                %{--<g:each var="tagLinkInfo" in="${articleTagLinkList}">--}%
                %{--<g:if test="${articleTagLinkList.contains(tagLinkInfo.tagId)}">--}%
                        %{--${tagLinkInfo.tagId}--}%
                        %{--<br/>--}%
                %{--</g:each>--}%

                %{--<g:each var="tagInfo" in="${parameterTagInfoList}">--}%
                            %{--<g:each var="tagLinkInfo" in="${articleTagLinkList}">--}%

                                %{--<g:if test="${tagInfo.id==tagLinkInfo.tagId}">--}%
                                    %{--${tagLinkInfo.tagId}--}%
                                    %{--<br/>--}%
                                %{--</g:if>--}%
                            %{--</g:each>--}%
                %{--</g:each>--}%

                %{--<g:each var="tagLinkInfo" in="${articleTagLinkList}">--}%
                    %{--${tagLinkInfo.tag_id}--}%
                %{--</g:each>--}%
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

