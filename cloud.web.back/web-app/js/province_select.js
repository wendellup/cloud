//      provinceUL省的id
//
$(function () {
    //异步获取省市信息

   // var provinceId = 32;

   // var requestUrl =  getRootPath() + "/province/queryProvinceList";
   /* $.ajax({url: requestUrl,
        async: false,
        data: "provinceId==" + provinceId,
        type: "post",
        dataType: "json",
        success: function (data, textStatus) {
            if (textStatus == "success") {
                for (var i = 0; i < data.provinceList.length; i++) {
                    $("#provinceUL").append("<li name='" + data.provinceList[i].org_id + "'>" + data.provinceList[i].org_name + "</li>");
                    $("#contractAddressProvinceUL").append("<li name='" + data.provinceList[i].org_id + "'>" + data.provinceList[i].org_name + "</li>");
                    $("#accountProvinceUL").append("<li name='" + data.provinceList[i].org_id + "'>" + data.provinceList[i].org_name + "</li>");
                }
            } else {
                alert("系统忙请稍后再试！");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        }
    });
    */
});
//ajax请求
function ajaxAsk(provinceId,requestUrl){

}
//添加省
function addProvince(ulId,provinceId,requestUrl,cityId){
    $.ajax({url: getRootPath() + "/province/"+requestUrl,
        async: false,
        data: "provinceId=" + provinceId,
        type: "get",
        dataType: "json",
        success: function (data, textStatus) {
            if (textStatus == "success") {
                var ulArray = [],
                    pUlId = $("#"+ulId),
                    cityUlId = cityId ? cityId : pUlId.parent().siblings(".sel_btn").children("ul").attr("id");
                for(var i = 0; i< data.provinceList.length; i++){
                    var li = "<li name='" + data.provinceList[i].org_id + "' onclick='addCity(\""+cityUlId+"\",\""+data.provinceList[i].org_id+"\",\"getCityList\",0)'>" + data.provinceList[i].org_name + "</li>";
                    ulArray.push(li);
                }
                pUlId.append(ulArray.join(""));
            } else {
            alert("系统忙请稍后再试！");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        }
    });
}
//点击省，添加市
function addCity(ulId,provinceId,requestUrl,type){
    $.ajax({url: getRootPath() + "/province/"+requestUrl,
        async: false,
        data: "provinceId=" + provinceId,
        type: "get",
        dataType: "json",
        success: function (data, textStatus) {
            if (textStatus == "success") {
                var ulArray = [],
                    cUlId = $("#"+ulId),
                    span =  cUlId.siblings("span"),
                    input =  cUlId.siblings("input");
                cUlId.empty();
                span.html() == "" || type ==0 ?  span.html(data.cityList[0].org_name) :"" ;
                input.val() == "" || type ==0 ? input.val(data.cityList[0].org_id) : "";
                for (var i = 0; i < data.cityList.length; i++) {
                    var li = "<li name='" + data.cityList[i].org_id + "'>" + data.cityList[i].org_name + "</li>";
                    ulArray.push(li);
                }
                cUlId.append(ulArray.join(""));
            } else {
                alert("系统忙请稍后再试！");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        }
    });
}
 /*
function callBack(li) {
    var parentId = $(li).parent().attr("id");
    if (parentId == "provinceUL" || parentId == "contractAddressProvinceUL" || parentId == "accountProvinceUL") {
        var provinceValue = parentId.substr(0, parentId.length - 2);
        var orgId = $(li).attr("name");
        $("#"+provinceValue).val(orgId);
        var requestUrl = getRootPath() + "/province/getCityList"
        $.ajax({url: requestUrl,
            async: false,
            data: "provinceId=" + orgId,
            type: "post",
            dataType: "json",
            success: function (data, textStatus) {
                if (textStatus == "success") {
                    if(parentId == "provinceUL"){
                        $("#citySpan").html(data.cityList[0].org_name);
                        $("#city").val(data.cityList[0].org_id);
                        $("#cityUL").empty();
                        for (var i = 0; i < data.cityList.length; i++) {
                            $("#cityUL").append("<li name='" + data.cityList[i].org_id + "'>" + data.cityList[i].org_name + "</li>");
                        }
                    }else if(parentId == "contractAddressProvinceUL"){
                        $("#contractAddressCitySpan").html(data.cityList[0].org_name);
                        $("#contractAddressCity").val(data.cityList[0].org_id);
                        $("#contractAddressCityUL").empty();
                        for (var i = 0; i < data.cityList.length; i++) {
                            $("#contractAddressCityUL").append("<li name='" + data.cityList[i].org_id + "'>" + data.cityList[i].org_name + "</li>");
                        }
                    }else if(parentId == "accountProvinceUL"){
                        $("#accountCitySpan").html(data.cityList[0].org_name);
                        $("#accountCity").val(data.cityList[0].org_id);
                        $("#accountCityUL").empty();
                        for (var i = 0; i < data.cityList.length; i++) {
                            $("#accountCityUL").append("<li name='" + data.cityList[i].org_id + "'>" + data.cityList[i].org_name + "</li>");
                        }
                    }
                } else {
                    alert("系统忙请稍后再试！");
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown);
            }
        });
    } else if (parentId == "cityUL") {
        var orgId = $(li).attr("name");
        $("#city").val(orgId);
    } else if (parentId == "channelTypeUL") {
        var codeId = $(li).attr("name");
        $("#channelType").val(codeId);
    } else if (parentId == "companyPropertyUL") {
        var companyProperty = $(li).attr("name");
        $("#companyProperty").val(companyProperty);
    } else if (parentId == "isBillUL") {
        var isBill = $(li).attr("name");
        $("#isBill").val(isBill);
//        if(isBill==1){
//            $("#taxPayerType").val(1);
//            $("#taxPayerTypeSpan").val("增值税一般纳税人");
//        }else{
//            $("#taxPayerType").val(1);
//            $("#taxPayerTypeSpan").val("增值税一般纳税人");
//        }
    }else if (parentId == "contractAddressCityUL") {
        var orgId = $(li).attr("name");
        $("#contractAddressCity").val(orgId);
    }else if (parentId == "accountCityUL") {
        var orgId = $(li).attr("name");
        $("#accountCity").val(orgId);
    }else if (parentId == "addedValueTaxRateUL") {
        var addedValueTaxRate = $(li).attr("name");
        $("#addedValueTaxRate").val(addedValueTaxRate);
    }
}
     */