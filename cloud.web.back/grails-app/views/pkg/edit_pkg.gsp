<e:form id="fm">
    <input type="hidden" name="id" id="id" value="${pkgInfo.id}"/>
	<table class="table2">
        <tr>
            <td width="30%" align="right"><span class="wordred">*</span>包名：</td>
            <td align="left">
                <input type="text" name="packageName" id="packageName" value="${pkgInfo.packageName}" class="easyui-validatebox input_width200" data-options="required:true,missingMessage:'请输入包名'">
            </td>
        </tr>
   </table>
</e:form>