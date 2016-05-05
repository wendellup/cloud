<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>常驻内存信息</title>
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
});
	</script>
<body style="height: 800px">
%{--<body>--}%
<e:layout fit="true">
<div class="matter_box">
    <h3 class="nopoint"><strong>Blog管理&nbsp;&gt;&nbsp;文章管理&nbsp;&gt;&nbsp;新增文章</strong></h3>
    %{--<e:form id="fm">--}%
    <form id="fm">
	<table class="table2">
        <tr>
            <td width="30%" align="right"><span class="wordred">*</span>文章内容：</td>
            <td align="left">
                <textarea name="content" id="content" onkeyup="checkLength(this, 1000);"></textarea>
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

