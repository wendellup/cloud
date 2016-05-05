$(function(){
    //上传文件
    attacheAjaxUpload("formPathInput","file","合作申请表");
    attacheAjaxUpload("businessLisPathInput","img","");
    attacheAjaxUpload("organizationPathInput","img","");
    attacheAjaxUpload("permitPathInput","img","");
    attacheAjaxUpload("landTaxPathInput","img","");
    if($("#generalTaxPayerproveInput")[0]){
        attacheAjaxUpload("generalTaxPayerproveInput","img","");
    }
    attacheAjaxUpload("addedValueLicenseInput","img","");
    attacheAjaxUpload("netscardPathInput","img","");
    attacheAjaxUpload("nationalTaxPathInput","img","");
    if($("#elecPackInput")[0]){
    attacheAjaxUpload("elecPackInput","file","电子合同");
    }
    if($("#demoRuleInput")[0]){
    attacheAjaxUpload("demoRuleInput","file","渠道素材规范");
    }

    addBlur($("input[valid]"));
    addBlur($("textarea[valid]"));
    addBlur($("div.sel_btn"));
});

function attacheAjaxUpload(btn,fileType,showFileName){
    var distributorId=$("#distributorId").val();
    var inputBtn=  $("#"+btn);
    var fileTypeArray = [];
    if(fileType=="img"){
        fileTypeArray =  ['jpg','png','bmp','JPG','PNG','BMP'];
    }else if (fileType=="elec"){ // 电子合同
        fileTypeArray = ['pdf','rar','zip', 'doc','docx'];
    }else{
        fileTypeArray = ['rar','zip', 'doc','docx'];
    }
    var url = getRootPath() + "/ajaxUpload/uploadFile?categoryPath=distributor&pathId=" +distributorId;
//    var date ={categoryPath:'distributor',pathId:distributorId};
    createFileUpload(btn,url,{},fileTypeArray,function(file, response){
        if(response.result){
            if(fileType=="img"){
                inputBtn.siblings("img").attr({"src":response.url,"onload":"imgOnload(this)"});
            }else{
                inputBtn.siblings("a.fc_blue").remove();
                inputBtn.after("<a href='"+ response.url +"' class='fc_blue'>"+showFileName+"</a>" );
            }
            $("#"+btn.substr(0,btn.length-5)).val(response.filePath);
            $.messager.alert('提示',response.msg,'info');
        }else{
            $.messager.alert('提示',response.msg,'info');
        }
        $("#"+btn).next("span").remove();
    });
}
//增值纳税相关下拉框

//增值税发票联动
function billOpen(type,str){
    var changeli,click_li,
        tli = $("#taxpayer").find("li"),addedValueTaxRateUL = $("#addedValueTaxRateUL");
    var checkLi = function(cli){
        var _cli = $(cli);
        _cli.parent().siblings("span").html(_cli.text());
        _cli.parent().siblings("input").val(_cli.attr("name"));
    },tr = $("#gt_tr"),
    tdFun = {
        addTd:function(){
            if(tr.find("td").length>2){
               $(tr.find("td")[2]).remove();
                $(tr.find("td")[2]).remove();
            }
            tr.append(gt_tr1);
            tr.append(gt_tr2);
            attacheAjaxUpload("generalTaxPayerproveInput","img","");
            $("#general_td1").show();
            $("#general_td2").show();
        },
        removeTd:function(){
            $(tr.find("td")[2]).remove();
            $(tr.find("td")[2]).remove();
            tr.append("<td></td><td></td>");
        }
    };
    for(var i = 0; i<tli.length; i++){
        changeli = $(tli[i]).html() == "营业税纳税人" ?  $(tli[i]) : "";
        //click_li = $(tli[i]).html() == "增值税小规模纳税人" ?  $(tli[i]) : "";
    }
    if(str == "load") {
        tli.live("click",function(){
            var _this = $(this);
            if(_this.text() == "增值税小规模纳税人" || _this.text() == "营业税纳税人"){
                tdFun.removeTd();
            }else{
                tdFun.addTd();
            }
        })
    }
    if(type == 1){//是
        changeli.hide();
        changeli.siblings("li").show();
        checkLi(changeli.siblings("li")[0]);
        addedValueTaxRateUL.find("li").show();
        checkLi(addedValueTaxRateUL.find("li")[1]);
        tdFun.addTd();

    }else{         //否
        changeli.siblings("li").hide();
        changeli.show();
        checkLi(changeli);
        addedValueTaxRateUL.find("li").hide();
        addedValueTaxRateUL.find("li[name=0]").show();
        checkLi(addedValueTaxRateUL.find("li")[0]);
        tdFun.removeTd();
    }
}


//验证渠道商全称是否已存在
function checkDisFullName(obj){
    var s = true,m = "";
    var fullName = $(obj).val();
    var distributorId = $("#distributorId").val();
    var url = getRootPath() + "/distributor/checkDisFullName";
    $.ajax({url: url,
        data: "fullName=" + fullName +"&distributorId=" + distributorId,
        type: "post",
        dataType: "json",
        async:false,
        success: function (data, textStatus) {
            if (data.result) {
                s = false;
                m = data['msg'];
            }
        }
    });
    return {same:s,msg:m};
}