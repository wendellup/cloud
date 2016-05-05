<%--
  Created by IntelliJ IDEA.
  User: zx
  Date: 15-10-28
  Time: 下午3:40
  计费SDK推送管理后台登录页面
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>计费sdk推送后台管理系统</title>
    <r:require module="easyui_core"/>
    <r:layoutResources/>
    <link type="text/css" rel="stylesheet" href="${request.contextPath}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/js/jquery-easyui/themes/default/my97.css">
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/js/jquery-easyui/themes/default/global.css">
    <link type="text/css" rel="stylesheet" href="${request.contextPath}/css/remoteLogin.css" />

    <g:render template="/rootpath"></g:render>
    <script type="text/javascript" src="${request.contextPath}/js/common.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/ajaxupload.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/jquery-easyui/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/jquery-easyui/plugins/jquery.my97.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/jquery.form.js"></script>

    <script type="text/javascript">

        function login() {
            var url = "${createLink(controller: 'user',action:'login')}";
            $('#loginForm').form('submit', {
                url: url,
                type : 'post',
                success: function (data) {
                    var json_data = data.replace('[', '{').replace(']', '}');
                    var data = eval('(' + json_data + ')');
                    if (data['result']) {
//                        alert("登陆成功！");
                        goOn();
                    } else {
                        alert("用户名不存在，或密码错误！");
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
            window.parent.location.href="/cloud.web.back/index";
        }

    </script>
</head>
<body id="cas" class="login_body">
<e:layout fit="true">
    <div class="login_top"></div>
    <div class="login">
        <h2 class="tab_tit" id="tab01">
            %{--<span id="pwdLoginS">&nbsp;&nbsp;&nbsp;&nbsp;</span>--}%
            %{--<span id="smsLoginS">&nbsp;&nbsp;&nbsp;&nbsp;</span>--}%
        </h2>
        <e:form id="loginForm">
            <div class="login_form" id="login">
                <div class="login_pli">
                    <label class="labe">用户名：</label>
                    <input id="username" name="username" class="inpt_Ptxt" tabindex="1" accesskey="n" type="text" value="" size="25" autocomplete="false">
                </div>
                <div class="login_pli">
                    <label class="labe">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
                    <input id="password" name="password" class="inpt_Ptxt" tabindex="1" accesskey="n" type="password" value="" size="25" autocomplete="false">
                </div>
                <div class="tc">
                    <a href="javascript:void(0)" onclick="login()" class="login_btn" id="login_btn">登陆</a>
                </div>
            </div>
        </e:form>
    </div>
</e:layout>
</body>
</html>