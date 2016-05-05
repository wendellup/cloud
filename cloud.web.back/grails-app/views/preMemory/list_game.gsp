<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>游戏推送预配置需求</title>
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

    <script type="text/javascript">
        $(document).ready(function () {
            $('#dg').datagrid({
                url: '${createLink(controller: 'preMemory',action: 'list',params:[objType:1])}',
                columns: [
                    [
                        {field: 'ck', title: '选择',checkbox:true, align: 'center'},
                        {field: 'id', title: '资源ID', width: 60, align: 'center'},
                        {field: 'startTime', title: '推送计划日期', align:'center'},
                        {field: 'title', title: '资源游戏标题', width: 150,align:'center'},
                        {field: 'description', title: '资源游戏描述', width: 150,align:'center'},
                        {field: 'advertTypeName', title: '推送展现类型', width: 80,align:'center'},
                        {field: 'linkUrl', title: '资源游戏代码', width: 120,align:'center'},
                        {field: 'gameName', title: '资源游戏名称', width: 120,align:'center'},
                        {field: 'imsiNum', title: '推送计划人数', width: 60,align:'center'},
                        {field: 'flagName', title: '推送任务标识', width: 50,align:'center'},
                        {field: 'silentDownloadName', title: '静默安装',width: 50,align:'center'},
                        {field: 'remark', title: '推送用户描述', width: 120,align:'center'},
                        {field: 'insertTime', title: '新增时间',align:'center'},
                        {field: 'updateTime', title: '更新时间',align:'center'},
                        {field: 'packageName', title: '资源游戏包名',width: 120,align:'center'},
                        {field: 'realGameDownloadUrl', title: '资源游戏包体',width: 120,align:'center'},
                        {field: 'insertTime', title: '新增时间',align:'center'},
                        {field: 'updateTime', title: '更新时间',align:'center'},
                        {field: 'packageName', title: '资源游戏包名',width: 120,align:'center'},
                        {field: 'realGameDownloadUrl', title: '资源游戏包体',width: 120,align:'center'},
                        {field: 'ownName', title: '资源渠道代码',width: 120,align:'center'}
                    ]
                ],
                onLoadSuccess: function (data) {
                    if (data.total == 0) {
                        var body = $(this).data().datagrid.dc.body2;
                        body.find('table tbody').append('<tr><td width="' + body.width() + '" style="height: 25px; text-align: center;">无查询数据</td></tr>');
                    }
                },
                height: 600,
                fitColumns: false,
                pagination: true,
                pageSize: 20,
                singleSelect:false,
                selectOnCheck:true,
                checkOnSelect:true
            });
        });

        function toAdd(){
            location.href = "${createLink(controller: 'preMemory',action:'addMemory',params:[objType:1])}";
        }

        function toEdit(){
            var rows = $('#dg').datagrid('getSelections');
            if (rows.length!=1){
                $.messager.alert('Info', "请选择一条记录.");
                return;
            }
            location.href = "${createLink(controller: 'preMemory',action:'editMemory')}?id=" + rows[0].id + "&objType=1";
        }

        //新增常驻内存
        function AddMemory() {
            $("#submitType").val(0);
            showDialog({
                id: 'dlg',
                width: 600,
                height: 520,
                title: "新增常驻内存",
                url: "${createLink(controller: 'memory',action:'addMemory',params:[objType:1])}"
            });
        }

        //编辑常驻内存
        function editMemory() {
            var rows = $('#dg').datagrid('getSelections');
            if (rows.length!=1){
                $.messager.alert('Info', "请选择一条记录.");
                return;
            }
            $("#submitType").val(1);


            showDialog({
                id: 'dlg',
                width: 600,
                height: 580,
                title: "编辑常驻内存",
                url: "${createLink(controller: 'memory',action:'editMemory')}?id=" + rows[0].id + "&objType=1"
            });
        }

        //提交新增的常驻内存
        function infoSubmit() {
            var url = "";
            var submitType = $("#submitType").val();
            if(submitType == 0) {
                url = "${createLink(controller: 'memory',action:'addSubmit')}";
            } else if(submitType == 1) {
                url = "${createLink(controller: 'memory',action:'editSubmit')}";
            }
            $('#fm').form('submit', {
                url: url,
                type : 'post',
                success: function (data) {
                    var json_data = data.replace('[', '{').replace(']', '}');
                    var data = eval('(' + json_data + ')');
                    if (data['result']) {
                        $('#dlg').dialog('close');
                        $("#dg").datagrid('reload');
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

        //将常驻内存设为无效
        function deleteMemory() {
            var rows = $('#dg').datagrid('getSelections');

            if (rows.length<=0){
                $.messager.alert('Info', "请至少选择一条记录.");
                return;
            }
            var ids = [];
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i].id);
            }
            $.messager.confirm('info', '确认删除么?', function(r){
                if (r){
                    confirmDeleteMemory(ids);
                }
            });
        }

        function confirmDeleteMemory(ids){
            $("#deleteIds").val(ids);
            var url = "${createLink(controller: 'preMemory',action:'deleteSubmit')}";
            $('#editForm').form('submit', {
                url: url,
                type : 'post',
                success: function (data) {
                    var json_data = data.replace('[', '{').replace(']', '}');
                    var data = eval('(' + json_data + ')');
                    if (data['result']) {
                        $("#dg").datagrid('reload');
                    } else {
                        alert("操作失败！");
                    }
                },
                error: function (data) {
                    var json_data = data.replace('[', '{').replace(']', '}');
                    var data = eval('(' + json_data + ')');
                    $.messager.alert('提示', data['msg'], 'error');
                }
            });
        }

        //用户登出
        function logout() {
            var url = "${createLink(controller: 'user',action:'logout')}";
            $('#editForm').form('submit', {
                url: url,
                type : 'post',
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
            window.parent.window.location.href="/push.sdk.back/";
        }

    </script>
</head>
<body>
<e:layout fit="true">
    <div class="matter_box">
    <h3 class="nopoint"><strong>需求录入管理&nbsp;&gt;&nbsp;游戏需求管理</strong></h3>
    <e:form id="editForm">
        <input type="hidden" id="deleteIds" name="deleteIds">
        <div>
            %{--<a href="javascript:void(0)" id="import_data"  class="btn_orange" onclick="logout()">退出登录</a>--}%
            %{--<span style="float:right">--}%
            <p class="tright">
                <a href="javascript:void(0)" class="btn_orange" onclick="toAdd()">新增</a>
                <a href="javascript:void(0)" class="btn_orange" onclick="toEdit()">修改</a>
                <a href="javascript:void(0)" class="btn_orange" onclick="deleteMemory()">删除</a>
            </p>
            %{--</span>--}%
        </div>
        <div class="matter_text clearfix nobg margin_t_0">
            <table id="dg">
            </table>
        </div>

        <div id="dlg" class="easyui-dialog" style="padding: 10px 20px"
             data-options="buttons:'#edit-dlg-buttons',resizable:true,closed:true">
            <div id="edit-dlg-buttons">
                <input type="hidden" name="submitType" id="submitType"/>
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="infoSubmit()">确定</a>
                <a href="javascript:void(0)" class="easyui-linkbutton"
                   onclick="javascript:$('#dlg').dialog('close')">取消</a>
            </div>
        </div>
    </e:form>
    </div>
</e:layout>
</body>
</html>