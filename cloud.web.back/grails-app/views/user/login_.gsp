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
    <g:render template="/rootpath"></g:render>
    <script type="text/javascript" src="${request.contextPath}/js/common.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/ajaxupload.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/jquery-easyui/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/jquery-easyui/plugins/jquery.my97.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/jquery.form.js"></script>

    <style>
    body{
        background: #ebebeb;
        font-family: "Helvetica Neue","Hiragino Sans GB","Microsoft YaHei","\9ED1\4F53",Arial,sans-serif;
        color: #222;
        font-size: 12px;
    }
    *{padding: 0px;margin: 0px;}
    .top_div{
        background: #008ead;
        width: 100%;
        height: 400px;
    }
    .ipt{
        border: 1px solid #d3d3d3;
        padding: 10px 10px;
        width: 290px;
        border-radius: 4px;
        padding-left: 35px;
        -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
        -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
        -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s
    }
    </style>

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
                        alert("登陆成功！");
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
            window.location.href="/push.sdk.back/chargeIndex/index";
        }

    </script>
</head>
<body>
<e:layout fit="true">
    <DIV class="top_div"></DIV>
    <DIV style="background: rgb(255, 255, 255); margin: -100px auto auto; border: 1px solid rgb(231, 231, 231); border-image: none; width: 400px; height: 200px; text-align: center;">
        <DIV style="width: 165px; height: 96px; position: absolute;">
            <DIV class="tou"></DIV>
            <DIV class="initial_left_hand" id="left_hand"></DIV>
            <DIV class="initial_right_hand" id="right_hand"></DIV>
        </DIV>
        <e:form id="loginForm">
            <P style="padding: 30px 0px 10px; position: relative;"><SPAN class="u_logo"></SPAN>
                <INPUT class="ipt" type="text" id="username" name="username" placeholder="请输入用户名或邮箱" value="">
            </P>
            <P style="position: relative;"><SPAN class="p_logo"></SPAN>
                <INPUT class="ipt" type="password" id="password" name="password" placeholder="请输入密码" value="">
            </P>

            <DIV style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
                <P style="margin: 0px 35px 20px 45px;">
                    %{--<SPAN style="float: left;"><A style="color: rgb(204, 204, 204);" href="#">忘记密码?</A></SPAN>--}%
                    <SPAN style="float: right;">
                        %{--<A style="color: rgb(204, 204, 204); margin-right: 10px;" href="#">注册</A>--}%
                        <A style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;"
                           href="javascript:void(0)" onclick="login()">登录</A>
                    </SPAN>
                </P>
            </DIV>
        </e:form>
    </DIV>
</e:layout>
</body>
</html>