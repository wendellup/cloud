<e:form id="fm">
    <input type="hidden" id="tagType" name="tagType" value="${tagType}"/>
	<table class="table2">
        <tr>
            <td width="30%" align="right"><span class="wordred">*</span>标签：</td>
            <td align="left">
                <input type="text" name="tagName" id="tagName" class="easyui-validatebox input_width200" data-options="required:true,missingMessage:'请输入标签名'">
            </td>
        </tr>
        <tr>
            <td width="30%" align="right"><span class="wordred">*</span>描述：</td>
            <td align="left">
                <input type="text" name="remark" id="remark" class="easyui-validatebox input_width200" data-options="required:true,missingMessage:'请输入标签描述'">
            </td>
        </tr>
   </table>
</e:form>