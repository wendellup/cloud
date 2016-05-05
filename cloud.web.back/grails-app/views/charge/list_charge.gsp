<%--
  Created by IntelliJ IDEA.
  User: zx
  Date: 15-10-19
  Time: 下午7:40
  计费SDK信息
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>SDK信息</title>
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
                url: '${createLink(controller: 'chargeSdk',action: 'list')}',
                columns: [
                    [
                        {field: 'id', title: 'ID', hidden: true, width: 60, align: 'center'},
                        {field: 'sort', title: '序号', width: 60, align: 'center',
                            formatter: function (value, row, index) {
                                return index + 1;
                            }
                        },
                        {field: 'fileName', title: '文件名称', width: 120,align:'center'},
                        {field: 'fileSize', title: '文件大小', width: 120,align:'center'},
                        {field: 'realDownloadPath', title: '下载路径', width: 150,align:'center'},
                        {field: 'introduction', title: '描述', width: 120,align:'center'},
                        {field: 'remark', title: '备注信息', width: 120,align:'center'},
                        {field: 'versionCode', title: '版本号', width: 60,align:'center'},
                        {field: 'md5', title: 'MD5', width: 60,align:'center'},
                        {field: 'insertTime', title: '上传时间', align:'center'},
                        {field: 'updateTime', title: '更新时间', align:'center'},
                        {field: 'opt', title: '操作',align:'center',
                            formatter: function (value, rec) {
                                return '<a href="javascript:void(0)" class="a_link" onclick="openEditDialog(\'' + rec.id + '\', \''+rec.introduction+'\', \''+rec.remark+'\', \''+rec.versionCode+'\');">修改</a>';
                            }
                        }
                    ]
                ],
                onLoadSuccess: function (data) {
                    if (data.total == 0) {
                        var body = $(this).data().datagrid.dc.body2;
                        body.find('table tbody').append('<tr><td width="' + body.width() + '" style="height: 25px; text-align: center;">无查询数据</td></tr>');
                    }
                    $("#span_last_exe_time").html("定时任务最后生效时间:"+data.last_execute_time);
                    %{--${last_execute_time}--}%
//                    iframeHeightAuto(window.parent.frames["iframe0"]);
                },
                height: 600,
                fitColumns: true,
                pagination: true,
                pageSize: 20
            });
            //批量导入评审结果
//            attacheAjaxUpload('import_data');
        });
        function reloadTable(){
            $('#dg').datagrid('clearSelections');
            $('#dg').datagrid('reload');
        }

        // 页面初始化加载上传文件按钮
        $(document).ready(function () {
            attacheUploadFile("sdkPathBtn", "apk_zip");
        });

        // 弹出新增窗口
        function openAddDialog() {
            $("#introduction").val('');
            $("#remark").val('');
            $("#versionCode").val('');
            $("#fileName").val('');
            $("#fileSize").val('');
            $("#md5").val('');
            $("#downloadPath").val('');
            $("#type").val(0);
            $('#editdlg').panel("header").find("div.panel-title").html('新增计费SDK信息');
            $('#editdlg').dialog('open');
        }

        // 弹出编辑窗口
        function openEditDialog(id, introduction, remark, versionCode) {
            $("#id").val(id);
            $("#introduction").val(introduction);
            $("#remark").val(remark);
            $("#versionCode").val(versionCode);
            $("#fileName").val('');
            $("#fileSize").val('');
            $("#md5").val('');
            $("#downloadPath").val('');
            $("#type").val(1);
            $("#editdlg").panel("header").find("div.panel-title").html('编辑计费SDK信息');
            $("#editdlg").dialog('open');
        }

        //sdk包上传
        function attacheUploadFile(btn, doc){
            var url = "${createLink(controller: 'ajaxUpload', action: 'uploadFile')}?fileType=" + 3;
            var fileType = [];
            if (doc=="doc" || doc=="doc_moban"){
                fileType = ["docx","doc"];
            } else if (doc=="txt" || doc=="txt_only"){
                fileType = ["txt"];
            } else if (doc == "jpg_only"){
                fileType = ["jpg", "JPG"];
            } else if (doc == "apk") {
                fileType = ["apk"];
            } else if (doc == "apk_zip") {
                fileType = ["apk", "zip"];
            } else {
                fileType = ['jpg','png','bmp','JPG','PNG','BMP'];
            }

            createFileUpload(btn,url,{},fileType,function(file, response){
                if(response.result){
                    $("#fileName").val(response.fileName);
                    $("#fileSize").val(response.fileSize);
                    $("#md5").val(response.md5);
                    $("#downloadPath").val(response.downloadPath);
                    $.messager.alert('提示', response.msg, 'info');
                }else{
                    $.messager.alert('提示', response.msg, 'error');
                }
            });
        }

        //图片上传
        function attacheUploadPrintScreen(btn,imageType){
            var url = "${createLink(controller: 'ajaxUpload',action:'upload2Local2Efs')}";
            createFileUpload(btn,url,{imageType:imageType,submitType:'update'},['jpg','png','bmp','JPG','PNG','BMP','rar','zip','RAR','ZIP'],function(file, response){
                $("#"+btn).siblings("span.loading").remove();
                if(response.result){
                    var _a = $("#"+btn);
                    _a.parent().find("input").val(response.efsId + "#" + response.width + "#" + response.height + "#" + response.suffix);
                    _a.siblings("img").attr("src",response.filePath);
                    _a.find("i").hide();
                }else{
                    var _a = $("#"+btn);
                    _a.find("i").show().html(response.msg);
                    //setPageTip(response.msg);
                }
            });
        }

        // 提交修改表单
        function editSubmit() {
            var url = "";
            var type = $("#type").val();
            if(type == 0) {
                url = "${createLink(controller: 'chargeSdk',action:'addSubmit')}";
            } else if(type == 1) {
                url = "${createLink(controller: 'chargeSdk',action:'editSubmit')}";
            }
            $('#editForm').form('submit', {
                url: url,
                type : 'post',
//                onSubmit: function () {
//                    var validate = $(this).form('validate');
//                    return validate;
//                },
                success: function (data) {
                    var json_data = data.replace('[', '{').replace(']', '}');
                    var data = eval('(' + json_data + ')');
                    if (data['result']) {
                        $('#editdlg').dialog('close');
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

        // 关闭编辑窗口
        function cancel() {
            $("#introduction").val('');
            $("#remark").val('');
            $("#versionCode").val('');
            $("#fileName").val('');
            $("#fileSize").val('');
            $("#md5").val('');
            $("#downloadPath").val('');
            $('#editdlg').dialog('close');
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
        <h3 class="nopoint"><strong>推送管理&nbsp;&gt;&nbsp;计费SDK升级管理</strong></h3>
        <div>
            <p class="tright">
                <span id="span_last_exe_time"></span>
                <a href="javascript:void(0)" class="btn_orange" onclick="openAddDialog()">新增</a>
            </p>
        </div>

        <div class="matter_text clearfix nobg margin_t_0">
            <table id="dg">
            </table>
        </div>
        <div id="editdlg" class="easyui-dialog" data-options="buttons:'#dlg-buttons',resizable:true,closed:true,modal: true" style="width: 1000px">
            <e:form id="editForm">
                <input type="hidden" name="id" id="id"/>
                <input type="hidden" name="type" id="type"/>
                <input type="hidden" name="fileName" id="fileName"/>
                <input type="hidden" name="fileSize" id="fileSize"/>
                <input type="hidden" name="md5" id="md5"/>
                <input type="hidden" name="downloadPath" id="downloadPath"/>
                <table id="table2" class="table2">
                    <tr>
                        <td align="right" width="200"></td>
                        <td width="280"></td>
                        <td class="tr" width="280"></td>
                        <td width="280"></td>
                    </tr>

                    <tr>
                        <td align="right">描述：</td>
                        <td colspan="3"><input id="introduction" name="introduction" size="100"> </td>
                    </tr>
                    <tr>
                        <td align="right">备注信息：</td>
                        <td colspan="3"><input id="remark" name="remark" size="100"> </td>
                    </tr>
                    <tr>
                        <td align="right">版本号：</td>
                        <td colspan="3"><input id="versionCode" name="versionCode" size="50"> </td>
                    </tr>
                    <tr>
                        <td align="right">上传计费SDK：</td>
                        <td colspan="3">
                            <a class="btn_orange fl"  href="javascript:void(0)" id="sdkPathBtn">点击上传
                                <i class="error_tip" style="top:212px; left:5px;"></i>
                            </a>
                        </td>
                    </tr>
                </table>
                <div id="dlg-buttons">
                    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="editSubmit()">确定</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="cancel()">取消</a>
                </div>
            </e:form>
        </div>
    </div>
</e:layout>
</body>
</html>