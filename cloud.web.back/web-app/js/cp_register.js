$(document).ready(function(){
    //判断角色
    var existUserAuthority=$("#existUserAuthority").val();
    if(existUserAuthority=="distributor"){
        $("#sb_distributor").removeAttr("href");
        $("#sb_distributor").attr ("style","cursor:default; color:#bbb;");
    }

    //初始化省市
    addProvince("reg_provinceUL",32,"queryProvinceList");
    addCity("reg_cityUL",$("#reg_province").val(),"getCityList");

    addProvince("contract_provinceUL",32,"queryProvinceList");
    addCity("contract_cityUL",$("#contract_province").val(),"getCityList");

    addProvince("account_provinceUL",32,"queryProvinceList","account_cityUL");

    addCity("account_cityUL",$("#account_province").val(),"getCityList");

    //attacheAjaxUpload("contract_path_file","contract_path_a","contract_path");
//  attacheAjaxUpload("form_path_file","form_path_a","form_path");
    $("#form_path_file")[0] && attacheAjaxUploadImg("form_path_file","form_path_a","form_path");
    //attacheAjaxUpload("apply_paper_file","apply_paper_a","apply_paper");
    attacheAjaxUploadImg("legal_id_card_paper_file","legal_id_card_paper_a","legal_id_card_paper");
    attacheAjaxUploadImg("permit_path_file","permit_path_a","permit_path");
    if($("#general_tax_payer_prove_file")[0]){
    	attacheAjaxUploadImg("general_tax_payer_prove_file","general_tax_payer_prove_a","general_tax_payer_prove");
    }
    attacheAjaxUploadImg("land_tax_path_file","land_tax_path_a","land_tax_path");
    attacheAjaxUploadImg("organization_path_file","organization_path_a","organization_path");
    attacheAjaxUploadImg("business_license_path_file","business_license_path_a","business_license_path");
    //attacheAjaxUploadImg("copyright_prove_file","copyright_prove_a","copyright_prove");
    attacheAjaxUploadImg("telecom_license_path_file","telecom_license_path_a","telecom_license_path");
    attacheAjaxUploadImg("national_tax_path_file","national_tax_path_a","national_tax_path");
    attacheAjaxUploadImg("nets_card_path_file","nets_card_path_a","nets_card_path");

    addBlur($("input[valid]"));
    addBlur($("textarea[valid]"));
    addBlur($("div.sel_btn"));

    //billOpen(1,"load")
});

function attacheAjaxUpload(btn,pathhref,path){
    var tempId = "" + $("#tempId").attr("value");
    var url = getRootPath() + "/ajaxUpload/uploadFile";
    createFileUpload(btn,url,{categoryPath:'cp',pathId:tempId},['rar','zip', 'doc','docx','RAR','ZIP', 'DOC','DOCX'],function(file, response){
        if(response.result){
            $("#"+pathhref).show();
            $("#"+pathhref).attr("href",response.url);
            $("#"+path).attr("value",response.filePath);
            $.messager.alert('提示',response.msg,'info');
            //setPageTip(response.msg);
            delErrorTip($("#"+path));
        }else{
            $.messager.alert('提示',response.msg,'info');
            //setPageTip(response.msg);
        }
    });
}

function attacheAjaxUploadImg(btn,pathhref,path){
    var tempId = "" + $("#tempId").attr("value");
    var url = getRootPath() + "/ajaxUpload/uploadFile";
    createFileUpload(btn,url,{categoryPath:'cp',pathId:tempId},['jpg','png','bmp','JPG','PNG','BMP'],function(file, response){
        if (response.result) {
            document.getElementById(pathhref).style.display = 'block';
            $("a[name =" + pathhref + "]").hide();
            var iframe = window.parent.document.getElementById("iframe_detail");
            iframe.style.height = $(iframe).height() + 206 + "px";

            $("#"+pathhref).attr({"src":response.url,"onload":"imgOnload(this)"});
            $("#"+path).attr("value",response.filePath);
            $.messager.alert('提示',response.msg,'info');

            //setPageTip(response.msg);
            delErrorTip($("#"+path));
        }else{
            $.messager.alert('提示',response.msg,'info');
            //setPageTip(response.msg);
        }
    });
}

function addNumber(num){
    var getNum = function(a){
        var n = "一";
        switch(a) {
            case 1:
                n = "一";
                break;
            case 2:
                n = "二";
                break;
            case 3:
                n="三";
                break;
        }
        return n;
    }
    var strArray = [];
    for(var i = 0; i<num; i++){
        var subPhone = "sub_account_phone" + (i+1).toString();
        var subName = "sub_account_name" + (i+1).toString();
        var tr1 = ['<tr class="tr_num"> ',
            ' <td align="right" width="137">子账号'+getNum(i+1)+'：</td><td><span class="in_btn wid_h"><input type="text" placeholder="请输入手机号码"  valid="phone pSame" name="'+ subPhone +'" id="'+ subPhone + '"/></span></td> ',
            '<td align="right" width="466">联系人：</td><td><span class="in_btn wid_h"><input type="text"  valid="require maxLength" length="16" name="'+ subName +'" id="'+subName+'" placeholder="请输入联系人姓名" /></span></td> </tr> '] .join('');
        strArray.push(tr1);
    }
    $("#tb1").empty().append(strArray.join(''));
}
//校验子账号
function validPhone(obj){
    var main_phone = $("#main_phone").html(),
        this_phone = $(obj).val(),
        other_phone = [],
        tr = $("#tb1").find("tr"),
        other_same = false,
        s = true,
        m = "";
    for(var i =0; i<tr.length; i++){
        var strNum = $("#sub_account_phone"+(i+1));
        if(strNum.val() == this_phone && strNum.val() != "" && $(obj).attr("id") != strNum.attr("id")) {
            other_same = true;
        }
    }
    if(delNull(this_phone) == main_phone){
        s = false;
        m = "不能与主账号相同";
    }else if(other_same){
        s = false;
        m = "不能与已填账号相同";
    }else{
        var ch = checkCpUnique(obj);
        s = ch.ss;
        m = ch.mm;
    }
    return {same:s,msg:m};
}

function validPhoneSame(obj){
    var _this = $(obj),
        tb = $("#company_head"),
        inputs = tb.find("input[valid^='same']"),
        isSame = false;
    for(var i = 0; i<inputs.length; i++){
        if( _this.val() != $(inputs[i]).val()){
            isSame = true;    //表示验证通过
        }
    }
    if(isSame){
        for(var i = 0; i<inputs.length; i++){
            delErrorTip(inputs[i]);
        }
    }
    return isSame;
}
function cpSubmitForm(){
    // 判断各处校验

    //内容赋值
    var _son = $("#tb1").find("tr").length;
    $("#sonInfo").attr("value","");
    for(var i=1;i<_son+1; i++){
        var _temp = $("#sonInfo").attr("value");
        var _phone = "sub_account_phone" + i.toString();
        var _name = "sub_account_name" + i.toString();
        $("#sonInfo").attr("value",_temp + $("#"+_phone).attr("value") + "::" + $("#"+_name).attr("value") + "||");
    }
    //提交校验
    return validAllForm('registerCpForm');
}
function billOpenUpdate(type,str){
    var changeli,click_li,
        tli = $("#taxpayer").find("li"),addedValueTaxRateUL = $("#addedValueTaxRateUL");
    var checkLi = function(cli){
            var _cli = $(cli);
            _cli.parent().siblings("span").html(_cli.text());
            _cli.parent().siblings("input").val(_cli.attr("name"));
        },tr1 = $("#gt_tr"),
        tdFun = {
            addTd:function(){
                if(tr1.find("td").length>1){
                    tr1.empty();
                }
                tr1.append(gt_tr_td1);
                tr1.append(gt_tr_td2);
            },
            removeTd:function(){
                tr1.empty();
            }
        };
    for(var i = 0; i<tli.length; i++){
        changeli = $(tli[i]).html() == "营业税纳税人" ?  $(tli[i]) : "";
        //click_li = $(tli[i]).html() == "增值税小规模纳税人" ?  $(tli[i]) : "";
    }
    if(type == 1){//是
        changeli.hide();
        changeli.siblings("li").show();
        var nashuiP = tli.parent().siblings("input").val();
        if(str == "load") {
            checkLi(changeli.siblings("li[name="+nashuiP+"]"));
            checkLi(addedValueTaxRateUL.find("li[name="+addedValueTaxRateUL.siblings("input").val()+"]"));
            taxPayerOpen($("#taxPayerType").val() );
        }else{
            checkLi(changeli.siblings("li")[0]);
            nashuiP = 1;
            taxPayerOpen(1);
        }
        if(nashuiP == 1){
            tdFun.addTd();
            //attacheAjaxUpload("generalTaxPayerProveInput","img","");
            attacheAjaxUploadImg("general_tax_payer_prove_file","general_tax_payer_prove_a","general_tax_payer_prove");
        } else{
            tdFun.removeTd()
        }
        checkLi(tli.find("li"));

    }else{
        changeli.siblings("li").hide();
        changeli.show();
        checkLi(changeli);
        tdFun.removeTd();
        taxPayerOpen(3);
    }
    iframeHeightAuto(window.parent.frames["iframe_detail"]);
}

function taxPayerOpen(type){
    var addedValueTaxRateUL = $("#addedValueTaxRateUL"),
        selectFun = function(array){
            addedValueTaxRateUL.find("li").hide();
            for(var i = 0; i<array.length; i++){
                for(var j = 0; j<addedValueTaxRateUL.find("li").length; j++){
                    var _cli = addedValueTaxRateUL.find("li").eq(j);
                    if(_cli.html() == array[i]){
                        _cli.show();
                        _cli.parent().siblings("span").html(_cli.text());
                        _cli.parent().siblings("input").val(_cli.attr("name"));
                    }
                }
            }
        },tr1 = $("#gt_tr"),tdFun = {
            addTd:function(){
                if(tr1.find("td").length>1){
                    tr1.empty();
                }
                tr1.append(gt_tr_td1);
                tr1.append(gt_tr_td2);
            },
            removeTd:function(){
                tr1.empty();
            }
        };
    //console.log(type+","+(type == 2))
    if(type == 1){
        selectFun(["17","11","6"]);
        tdFun.addTd();
        attacheAjaxUploadImg("general_tax_payer_prove_file","general_tax_payer_prove_a","general_tax_payer_prove");
    }else if(type == 2){
        selectFun(["3"]);
        tdFun.removeTd();
    }else{
        selectFun(["0"]);
        tdFun.removeTd();
    }
    iframeHeightAuto(window.parent.frames["iframe_detail"]);
}

//判断公司全称唯一
function checkCnNameUnique(obj){
    var cnName = $(obj).val();
    var s = true,m = "";
    $.ajax({
        type: "POST",
        url: getRootPath() + "/cpValidate/checkUniqueCompanyName",
        async:false,
        data: "cnName=" + cnName+"&cpId="+$("#tempId").val(),
        success: function (data) {
            if (data.result) {
            } else {
                s = false;
                m = "该公司已注册，请重新输入";
            }
        }
    });
    return {same:s,msg:m};
}

function checkShortNameUnique(obj){
    var shortName = $(obj).val();
    var s = true,m = "";
    $.ajax({
        type: "POST",
        url: getRootPath() + "/cpValidate/checkUniqueShortName",
        async:false,
        data: "shortName=" + shortName+"&cpId="+$("#tempId").val(),
        success: function (data) {
            if (data.result) {
            } else {
                s = false;
                m = "该简称已分配，请重新输入";
            }
        }
    });
    return {same:s,msg:m};
}

//判断cp账号是否唯一
function checkCpUnique(obj){
    var mobilePhone = $(obj).val();
    var same = true;
    var msg = "";
    $.ajax({
        type: "POST",
        url: getRootPath() + "/cpValidate/checkUniqueMobile",
        async:false,
        data: "mobilePhone=" + mobilePhone+"&cpId="+$("#tempId").val(),
        success: function (data) {
            if (data['result']) {
                //delErrorTip($(obj));
            } else {
                same = false;
                msg = "该账号已存在，请核实后重新输入";
            }
        }
    });
    return {ss:same,mm:msg};
}