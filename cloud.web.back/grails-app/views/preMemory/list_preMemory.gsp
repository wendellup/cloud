<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>推送内容预配置信息</title>
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
                url: '${createLink(controller: 'preMemory',action: 'list')}',
                columns: [
                    [
                        {field: 'ck', title: '选择',checkbox:true, align: 'center'},
                        {field: 'id', title: 'ID', width: 50, align: 'center'},
                        {field: 'startTime', title: '开始时间', width: 120, align:'center'},
                        {field: 'title', title: '标题', width: 150,align:'center'},
                        {field: 'description', title: '描述', width: 150,align:'center'},
                        {field: 'gameName', title: '名称', width: 120,align:'center'},
                        {field: 'remark', title: '备注', width: 120,align:'center'},
                        {field: 'insertTime', title: '上传时间', width: 120,align:'center'},
                        {field: 'updateTime', title: '更新时间', width: 120,align:'center'},
                        {field: 'realGameDownloadUrl', title: '链接地址',width: 120},
                        {field: 'objTypeName', title: '推送类型',width: 120},
                    ]
                ],
                onLoadSuccess: function (data) {
                    if (data.total == 0) {
                        var body = $(this).data().datagrid.dc.body2;
                        body.find('table tbody').append('<tr><td width="' + body.width() + '" style="height: 25px; text-align: center;">无查询数据</td></tr>');
                    }
                    iframeHeightAuto(window.parent.frames["iframe5"]);
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

        //新增广告
        function addMemory() {
            $("#submitType").val(0);
            showDialog({
                id: 'dlg',
                width: 600,
                height: 580,
                title: "新增常驻内存",
                url: "${createLink(controller: 'memory',action:'addMemory',params:[type:1,objType:3])}"
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
                %{--url: "${createLink(controller: 'memory',action:'editMemory',params:['type':1,'id':rowId])}?id=rowId&type=1"--}%
                url: "${createLink(controller: 'memory',action:'editMemory')}?id="+rows[0].id + "&type=1&objType=3"
            });
        }

        function validateValue() {
            //验证常驻内存类型
            if($("#advertType").val()==""){
                $.messager.alert('提示', "请选择类型", 'error');
                return false;
            }

            //验证标题
            if(!$("#tr_title").is(":hidden")){
                var titleVal = $("#title").val().trim();
                if(titleVal==""){
//                    alert();
                    $.messager.alert('提示', "请填写标题", 'error');
                    return false;
                }
            }
            //验证描述
            if(!$("#tr_description").is(":hidden")){
                var descriptionVal = $("#description").val().trim();
                if(descriptionVal==""){
//                    alert("请填写描述");
                    $.messager.alert('提示', "请填写描述", 'error');
                    return false;
                }
            }
            return true;
        }

        //提交新增的常驻内存
        function infoSubmit() {
            if(!validateValue()){
                return;
            }

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
            var url = "${createLink(controller: 'memory',action:'deleteSubmit')}";
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
    <e:form id="editForm">
        <input type="hidden" id="deleteIds" name="deleteIds">
        <div>
            <a href="javascript:void(0)" id="import_data"  class="btn_orange" onclick="logout()">退出登录</a>
            <span style="float:right">
                <a href="javascript:void(0)" class="btn_orange" onclick="addMemory()">新增</a>
                <a href="javascript:void(0)" class="btn_orange" onclick="editMemory()">修改</a>
                <a href="javascript:void(0)" class="btn_orange" onclick="deleteMemory()">置为无效</a>
            </span>
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