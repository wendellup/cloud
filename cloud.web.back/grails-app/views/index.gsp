<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>push配置系统</title>
    <r:require module="easyui_core"/>
    <r:layoutResources/>
    <link type="text/css" rel="stylesheet" href="${request.contextPath}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/js/jquery-easyui/themes/default/my97.css">
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/js/jquery-easyui/themes/default/global.css">

    <g:render template="/rootpath"></g:render>
    <script type="text/javascript" src="${request.contextPath}/js/index.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/common.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/ajaxupload.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/jquery-easyui/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/jquery-easyui/plugins/jquery.my97.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/jquery.form.js"></script>

    <script type="text/javascript">
        //用户登出
        function logout() {
            var url = "${createLink(controller: 'user',action:'logout')}";
            $('#editForm').form('submit', {
                url: url,
                type: 'post',
                success: function (data) {
                    var json_data = data.replace('[', '{').replace(']', '}');
                    var data = eval('(' + json_data + ')');
                    if (data['result']) {
                        alert("登出成功！");
                        goOn();
                    } else {
                        alert("登出失败！");
                        //                        $.messager.alert('提示', data['msg'], 'error');
                    }
                },
                error: function (data) {
                    var json_data = data.replace('[', '{').replace(']', '}');
                    var data = eval('(' + json_data + ')');
                    $.messager.alert('提示', data['msg'], 'error');
                }
            });
        }

        function goOn() {
            window.parent.window.location.href = "/cloud.web.back/";
        }
    </script>
</head>

<body class="index_body">
<div class="header pt20 clearfix">
    <span class="fl" id="time"></span>
    <span class="exit fr"><a href="javascript:void(0)" onclick="logout()">注销</a></span>
    <span class="fr">游戏运营中心欢迎您：小伙伴们</span>
</div>

<div class="index_top pt20 tr" id="index_top">

</div>
<form id="editForm">
    %{--<input type="submit" name="submit" id="submit"/>--}%
<form>
<div class="content clearfix">
    <div class="menu fl" id="menu" style="height: 900px;">
        <g:render template="/menu"></g:render>
    </div>

    <div class="details fl" id="detail_index">
        <span class="details_rt"></span>
        <span class="details_rb"></span>
        <iframe frameborder="0" src="" id="iframe" style="height: 900px;"></iframe>

        <div id="main_right" style="display:none;"><div class="matter_box"></div></div>
    </div>
</div>
</body>
</html>
