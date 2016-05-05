<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>包名采集管理</title>
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
                url: '${createLink(controller: 'pkg',action: 'list')}',
                columns: [
                    [
                        {field: 'ck', title: '选择',checkbox:true, align: 'center'},
                        {field: 'id', title: 'ID', hidden:'true'},
                        {field: 'packageName', title: '采集的包名', width: 160, align:'center'},
                        {field: 'insertTime', title: '上传时间', width: 160,align:'center'},
                        {field: 'updateTime', title: '更新时间', width: 160,align:'center'},
                    ]
                ],
                onLoadSuccess: function (data) {
                    if (data.total == 0) {
                        var body = $(this).data().datagrid.dc.body2;
                        body.find('table tbody').append('<tr><td width="' + body.width() + '" style="height: 25px; text-align: center;">无查询数据</td></tr>');
                    }
                    iframeHeightAuto(window.parent.frames["iframe4"]);
                },
                height: 500,
                fitColumns: false,
                pagination: true,
                pageSize: 20,
                singleSelect:false,
                selectOnCheck:true,
                checkOnSelect:true
            });
        });

        //新增包名采集
        function addPkg() {
            var data = $('#dg').datagrid('getData');
            if(data.total>=10){
                $.messager.alert('提示', "包名不能超过10条", 'error');
                return;
            }
            $("#submitType").val(0);
            showDialog({
                id: 'dlg',
                width: 600,
                height: 200,
                title: "新增包名",
                url: "${createLink(controller: 'pkg',action:'addPkg')}"
            });
        }

        //编辑包名
        function editPkg() {
            var rows = $('#dg').datagrid('getSelections');
            if (rows.length!=1){
                $.messager.alert('Info', "请选择一条记录.");
                return;
            }
            $("#submitType").val(1);


            showDialog({
                id: 'dlg',
                width: 600,
                height: 200,
                title: "编辑包名",
                url: "${createLink(controller: 'pkg',action:'editPkg')}?id=" + rows[0].id
            });
        }

        //提交新增的包名
        function infoSubmit() {
            var url = "";
            var submitType = $("#submitType").val();
            if(submitType == 0) {
                url = "${createLink(controller: 'pkg',action:'addSubmit')}";
            } else if(submitType == 1) {
                url = "${createLink(controller: 'pkg',action:'editSubmit')}";
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
        function deletePkg() {
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
                    confirmDeletePkg(ids);
                }
            });
        }

        function confirmDeletePkg(ids){
            $("#deleteIds").val(ids);
            var url = "${createLink(controller: 'pkg',action:'deleteSubmit')}";
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
        <h3 class="nopoint"><strong>推送管理&nbsp;&gt;&nbsp;包名采集管理</strong></h3>
        <e:form id="editForm">
            <input type="hidden" id="deleteIds" name="deleteIds">
            <div>
                <p class="tright">
                    <a href="javascript:void(0)" class="btn_orange" onclick="addPkg()">新增</a>
                    <a href="javascript:void(0)" class="btn_orange" onclick="editPkg()">修改</a>
                    <a href="javascript:void(0)" class="btn_orange" onclick="deletePkg()">删除</a>
                </p>
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