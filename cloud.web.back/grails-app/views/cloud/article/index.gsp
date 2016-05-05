<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Blog文章管理</title>
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
        var id = '';
        var parentId = '';
        var isLeaf = true;
        var selectIds = null;

        $(document).ready(function () {
            $('#dg').datagrid({
                url: '${createLink(controller: 'article',action: 'queryList')}',
                onLoadSuccess:function(data){
                    if(data.total == 0){
                        var body = $(this).data().datagrid.dc.body2;
                        body.find('table tbody').append('<tr><td width="' + body.width() + '" style="height: 25px; text-align: center;">无查询数据</td></tr>');
                    }
                    //选中记录上下移后，仍然置为选中状态
//                    if (selectIds != null){
//                        for (var i = 0; i < selectIds.length; i ++){
//                            $("input[name='gameId']:checkbox").each(function(){
//                                if ($(this).val() == selectIds[i]){
//                                    $(this).click();
//                                    $(this).parent().parent().parent().addClass('datagrid-row-selected');
//                                }
//                            });
//                        }
//                    }
                },
                height:450,
                fitColumns: true,
                pagination: true,
                pageList:[10,20,30,40,50,100,200,500],
                pageSize:20
            });
            $('#clientMenuTree').tree({
                onClick: function (node) {
                    isLeaf = $('#clientMenuTree').tree('isLeaf', node.target);
                    if (isLeaf) {
                        id = node.id;
                    }
                    else {
                        id = '';
                    }
                    var parentNode = $('#clientMenuTree').tree('getParent', node.target);
                    if (parentNode) {
                        parentId = parentNode.id;
                    }
                    else {
                        parentId = '';
                    }
                    $("#tagType").val(id);
                    refeshGameList(id);
                    if (id==701 || id==702 || id==760 || id==761 || id==762 || id==707 || id==717 || parentId==764  || parentId == 765){
                        $("#_tag").show();
                        if (id==760 || id==761 || id==762 || parentId == 765){
                            //$("#quality_div").show();
                        }
                        else {
                            $("#quality_div").hide();
                        }
                    }
                    else{
                        $("#_tag").hide();
                    }
                }
                ,onLoadSuccess:function(){
                    var roots=$('#clientMenuTree').tree('getRoots'),children,i,j;
                    for(i=0;i<roots.length;i++){
//                        alert(roots[i].text);
                        children=$('#clientMenuTree').tree('getChildren',roots[i].target);
                        for(j=0;j<children.length;j++){
                            if(children[j].id==$("#tagType").val()){
                                alert(children[j].id);
//                                $(this).tree('toggle', children[j].target);
                                $(this).tree('select', children[j].target);
//                                $(this).tree('click', children[j].target);
                                parentId=roots[i].id
                                refeshGameList(children[j].id);
                                break;
                            }
                        }

//                            alert(children[j].text);
                    }
                }
                });
        });

        // 清除查询条件
        function clearQueryCondition(){
            $("#gameId").val('');
            $("#gameName").val('');
            $("#qualityTagStr").val('');
            $("#modeTagStr").val('');
            $("#statusTagStr").val('');
            $("#recommendWord").val('');
            $("#networkType").val('');
        }

        // 根据不同的列表刷新数据，显示不同的列
        function refeshGameList(id) {
            alert(parentId);
            selectIds = null;
            clearQueryCondition();
            $('#dg').datagrid('options').pageNumber = 1;
            $('#dg').datagrid('getPager').pagination({pageNumber: 1});
            if (parentId == 800) {
                $('#dg').datagrid({
                    queryParams: {
                        tagType : id
                    },
                    columns: [
                        [
                            {field: 'ck', title: '选择',checkbox:true, align: 'center'},
                            {field: 'id', title: 'ID', hidden:'true'},
                            {field: 'paramId', title: '菜单类型', width: 160, align:'center'},
                            {field: 'title', title: '标题', width: 160,align:'center'},
                            {field: 'insertTime', title: '插入时间', width: 160,align:'center'},
                            {field: 'updateTime', title: '更新时间', width: 160,align:'center'},
                        ]
                    ]
                });
            }
        }

        function addArticle() {
            if (!isLeaf || id == '') {
                $.messager.alert('提示','请选择叶子菜单进行操作！','info');
                return;
            }
            selectIds = null;
            var tagType = $("#tagType").val();
            location.href = "${createLink(controller: 'article',action:'addArticle')}?tagType="+tagType;
        }

        //编辑文章
        function editArticle() {
            var rows = $('#dg').datagrid('getSelections');
            if (rows.length!=1){
                $.messager.alert('Info', "请选择一条记录.");
                return;
            }
            var tagType = $("#tagType").val();
            location.href = "${createLink(controller: 'article',action:'editArticle')}?tagType="+tagType+"&articleId="+rows[0].id;
        }

        //新增和修改提交
        function infoSubmit() {
            var url = "";
            var submitType = $("#submitType").val();
            if(submitType == 0) {
                url = "${createLink(controller: 'parameterTag',action:'addSubmit')}";
            } else if(submitType == 1) {
                url = "${createLink(controller: 'parameterTag',action:'editSubmit')}";
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
        function delArticle() {
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
                    confirmDeleteTag(ids);
                }
            });
        }

        function confirmDeleteTag(ids){
            $("#deleteIds").val(ids);
            var url = "${createLink(controller: 'article',action:'deleteSubmit')}";
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

        function queryList() {
            selectIds = null;
            $('#dg').datagrid('options').pageNumber = 1;
            $('#dg').datagrid('getPager').pagination({pageNumber: 1});
            $('#dg').datagrid('reload', {
                id : $('#id').val(),
                tagName : $('#tagName').val(),
                tagType : $('#tagType').val(),
                remark : $('#remark').val()
            });
        }
    </script>
</head>
<body>
<e:layout fit="true">
    <div class="matter_box">
    <e:form id="editForm">
        <input type="hidden" id="tagType" name="tagType" value="${params.tagType}"/>
        <input type="hidden" id="deleteIds" name="deleteIds">
        <h3 class="nopoint"><strong>Blog管理&nbsp;&gt;&nbsp;文章标签管理</strong></h3>
        <div style="float:left;">
            <div id="sub_menu" class="sub_menu" style="width: 200px;height:560px;">
                %{--<g:if test="${pageType=='1'}">
                    <g:render template="/clientMenu/clientMenuTreeGameTV"></g:render>
                </g:if>
                <g:elseif test="${pageType=='4'}">
                    <g:render template="/clientMenu/clientMenuTreeGameChannel"></g:render>
                </g:elseif>
                <g:elseif test="${pageType=='5'}">
                    <g:render template="/clientMenu/clientMenuTreeTvRank"></g:render>
                </g:elseif>
                <g:else>--}%
                    <g:render template="/cloud/clientMenu/clientMenuTreeBlogTag"></g:render>
                %{--</g:else>--}%
            </div>
        </div>
        %{--<e:form id="editForm">
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
        </e:form>--}%
        <div class="search" style="margin:10px 10px 0 240px;">
            <table style="height: 50px">
                <tr style="width: 100%">
                    <td>
                        <table id="searchForm">
                            <tr>
                                <td style="text-align: right; width: 180px;">
                                    标签名
                                    <input name="tagName" id="tagName" class="input_width100" maxlength="9"/>
                                </td>
                                <td style="text-align: right;  width: 180px;">
                                    标签描述
                                    <input name="remark" id="remark" class="input_width100"/>
                                </td>
                                <td id="networkType_td" style="display: none; text-align: right; width: 180px;">
                                    游戏类型
                                    <g:select class="input_width100" id="networkType" name="networkType" from='${networkTypeList}'
                                              noSelection="${['':'--请选择--']}"	optionKey="key" optionValue="value"></g:select>
                                </td>
                                <td id="recommendWord_td" style="display: none; text-align: right; width: 180px;">
                                    推荐语
                                    <select class="input_width100" name="recommendWord" id="recommendWord">
                                        <option value="">--请选择--</option>
                                        <option value="1">有</option>
                                        <option value="0">无</option>
                                    </select>
                                </td>
                                <td>
                                    <a id="searchBtn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="queryList();">查询</a>
                                </td>
                            </tr>
                            <tr id="game_tag_tr" style="display: none">
                                <td id="game_quality_tag_td" style="display: none; text-align: right;">
                                    品质标签
                                    <g:select class="input_width100" id="qualityTagStr" name="qualityTagStr" from='${qualityTagList}'
                                              noSelection="${['':'--请选择--']}" optionKey="id" optionValue="name"></g:select>
                                </td>
                                <td style="text-align: right;">
                                    状态标签
                                    <g:select class="input_width100" id="modeTagStr" name="modeTagStr" from='${modeTagList}'
                                              noSelection="${['':'--请选择--']}" optionKey="id" optionValue="name"></g:select>
                                </td>
                                <td style="text-align: right;">
                                    角标
                                    <g:select class="input_width100" id="statusTagStr" name="statusTagStr" from='${statusTagList}'
                                              noSelection="${['':'--请选择--']}" optionKey="id" optionValue="name"></g:select>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </div>
        <div class="tright">
            <span style="color:#FF0000;display:none;padding-right:10px" id="suggestionsSpan">建议：配置5~10款游戏</span>
            <input type="hidden" name="submitType" id="submitType"/>
            <a href="javascript:void(0)" class="btn_orange" onclick="addArticle()">新增文章</a>
            <a href="javascript:void(0)" class="btn_orange" onclick="editArticle()">编辑文章</a>
            <a href="javascript:void(0)" class="btn_orange" onclick="delArticle()">删除文章</a>
            <span id="preview_span" style="display:none;"><a href="javascript:void(0)" class="btn_orange" onclick="openPreviewDialog()">预览排行</a></span>
            <span id="import_span" style="display:none;"><a href="javascript:void(0)" class="btn_orange" id="importGame">批量导入</a></span>
            <span style="color:#FF0000;display:none;" id="promptSpan">excel格式：游戏id 游戏名称</span>
        </div>
        <div class="matter_text clearfix nobg margin_t_0">
            <table id="dg">

            </table>
        </div>
    </e:form>
    </div>
    <div id="dlg" class="easyui-dialog" style="padding: 10px 20px" data-options="buttons:'#dlg-buttons',resizable:true,closed:true">
        <div id="dlg-buttons">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="infoSubmit()">确定</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')">取消</a>
        </div>
    </div>

    <div id="previewDlg" class="easyui-dialog" style="padding: 10px 20px" data-options="buttons:'#previewDlg-buttons',resizable:true,closed:true">
        <div id="previewDlg-buttons">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#previewDlg').dialog('close')">确定</a>
        </div>
    </div>

    <div id="sortNo_dlg" class="easyui-dialog" style="padding: 10px 20px" data-options="buttons:'#dlg-buttons',resizable:true,closed:true">
        <div id="dlg-buttons">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="moveSpecified();">确定</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#sortNo_dlg').dialog('close')">取消</a>
        </div>
    </div>
    </div>
</e:layout>
</body>
</html>