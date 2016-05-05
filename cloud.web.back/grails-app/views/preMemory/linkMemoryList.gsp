<%@ page contentType="text/html;charset=UTF-8"%>
<body>
	<script type="text/javascript">
        $(document).ready(function () {
            $('#servicedg').datagrid({
                url: '${createLink(controller: 'preMemory',action:'queryGamesByCondition')}',
                queryParams: {
					objType: '${objType}'
                },
                columns:[[
                    {field: 'ck', title: '选择',checkbox:true, align: 'center'},
                    {field: 'id', title: '资源ID', width: 60, align: 'center'},
                    {field: 'startTime', title: '推送计划日期', align:'center'},
                    {field: 'title', title: '资源游戏标题', width: 150,align:'center'},
                    {field: 'description', title: '资源游戏描述', width: 150,align:'center'},
                    {field: 'gameName', title: '资源游戏名称', width: 120,align:'center'},
                    {field: 'remark', title: '推送用户描述', width: 120,align:'center'},
                    {field: 'packageName', title: '资源游戏包名',width: 120,align:'center'},
                ]],
                onLoadSuccess:function(data){
                    if(data.total == 0){
                        var body = $(this).data().datagrid.dc.body2;
                        body.find('table tbody').append('<tr><td width="' + body.width() + '" style="height: 25px; text-align: center;">无查询数据</td></tr>');
                    }
                },
                height:350,
                fitColumns: true,
                pagination: true,
                pageSize:10
            });
        });

		function queryService() {
            var queryString = $('#queryStr').val();
            if (queryString == '支持资源ID/资源标题'){
                queryString = '';
            }
			var params = {
		        	queryStr :queryString,
                    objType : $("#objType").val()
		        };
			$('#servicedg').datagrid('options').pageNumber = 1;
	        $('#servicedg').datagrid('getPager').pagination({pageNumber: 1});
			$('#servicedg').datagrid('reload', params);
		}

		function hiddenAllCndDiv() {
			var cndObj = document.getElementsByName("cndType");
			for ( var i = 0; i < cndObj.length; i++) {
				$("#divCnd_" + (i + 1)).hide();
			}
		}

		function exchangeViewDiv(divId) {
			hiddenAllCndDiv();
			$("#divCnd_" + divId).show();
		}
		function checkEnter(e){
		    if(!e) {
		        e = window.event;
		    }
		    if((e.keyCode || e.which) == 13){
		    	queryService();
		   	}
		}
	</script>
	<input type="hidden" name="objType" id="objType" value="${objType}" />
	<e:layout fit="true">
		<form action="" id="frmQuery">
			<table>
				<tr>
					<td>查询添加：</td>
					<td>
						<input name="queryStr" id="queryStr" style="width: 450px" value="支持资源ID/资源标题" onkeydown="checkEnter(event);" onclick="if(this.value=='支持资源ID/资源标题'){this.value=''};"/>
					</td>
					<td>
	                    <!-- 只有一个输入框，点击回车搜索会有问题，故增加一个隐藏的输入框来规避这个问题 -->
                    	<input style="display:none"/>
                    </td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							data-options="iconCls:'icon-search',plain:true"
							onclick="queryService();">查询</a>
					</td>
				</tr>
            <tr><td></td><td colspan="2"><span style="color:#FF0000">用逗号间隔可查询多条数据，例如 id2,id2,... 或者 切西瓜,地铁跑酷,...</span></td></tr>
			</table>
		</form>
		<table id="servicedg">
	    </table>
	</e:layout>
</body>