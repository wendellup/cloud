var jq_form = jq_form || {};

$(function(){
    jq_form.addBlur($("input[valid]"));
    jq_form.addBlur($("textarea[valid]"));

})

//验证函数 参数val是input的value，type是
jq_form.validForm = function(obj,t){
    var val = $(obj).val(),     //去头尾空格
        types = $(obj).attr("valid"),
        isRight = true,
        tip = "",
        type = types.split(" "),
        regainPlaceholder = function(){
            
        },
        fnValid = function(s,v){
            var va;
            switch (s){
                case "require":                       //是否为空   空返回false
                    va = v != "" && $.trim(v) != "";
                    tip = "您的输入有误，请核实后重新输入";
                    break;
                case "phone":                          //手机号验证 错误返回false
                    va = /^\d+$/.test(v) && v.length==11 && v.substring(0,1) == 1;
//                    va = v.length == 11 && /(^(13[0-9]|147|15[^4,\\D]|18[0,5-9,1])\d{8}$)/.test(v);
                    tip = "请填写正确的手机号码";
                    break;
                case "personId":                       //身份证号码验证
                    va = jq_form.validIDNumber(v);
                    tip = "请输入正确的身份证号码";
                    break;

                case "email":
                    va  = /(([^@]+)@(([a-z0-9A-Z]+([a-z0-9A-Z_-]+)?\.)+[a-zA-Z]{2,}))/.test(v) && v.length < 33;
                    tip = "请填写正确的邮箱";
                    break;
                case "url":
                    var strRegex = '^http://'
                        + '(([0-9]{1,3}.){3}[0-9]{1,3}' // IP形式的URL- 199.194.52.184
                        + '|' // 允许IP和DOMAIN（域名）
                        + '([0-9a-z_!~*\'()-]+.)*' // 域名- www.
                        + '([0-9a-z][0-9a-z-]{0,61})?[0-9a-z].' // 二级域名
                        + '[a-z]{2,6})' // first level domain- .com or .museum
                        + '(:[0-9]{1,4})?' // 端口- :80
                        + '((/?)|' // a slash isn't required if there is no file name
                        + '(/[0-9a-z_!~*\'().;?:@&=+$,%#-]+)+/?)$';
                    var re = new RegExp(strRegex);
                    va = re.test(v);
                    tip="请输入正确的Url地址";
                    break;
                case "selfUrl":
                    va=true;
                    if(v != "" && $.trim(v) != "")
                    {
                        var strRegex = '^http://'
                            + '(([0-9]{1,3}.){3}[0-9]{1,3}' // IP形式的URL- 199.194.52.184
                            + '|' // 允许IP和DOMAIN（域名）
                            + '([0-9a-z_!~*\'()-]+.)*' // 域名- www.
                            + '([0-9a-z][0-9a-z-]{0,61})?[0-9a-z].' // 二级域名
                            + '[a-z]{2,6})' // first level domain- .com or .museum
                            + '(:[0-9]{1,4})?' // 端口- :80
                            + '((/?)|' // a slash isn't required if there is no file name
                            + '(/[0-9a-z_!~*\'().;?:@&=+$,%#-]+)+/?)$';
                        var re = new RegExp(strRegex);
                        va = re.test(v);
                        tip="请输入正确的Url地址";
                    }
                    break;
                case "password":
                    va  = /^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,20}$/.test(v);
                    tip = "请输入正确的密码";
                    break;
                case "code":                            //6位验证码
                    va =  v.length == 6 && !isNaN(v);
                    tip = "请输入正确的验证码";
                    break;
                case "zipCode":                            //邮编
                    va =  v.length == 6 && !isNaN(v);
                    tip = "请输入正确的邮编";
                    break;
                case "number":                          //数字验证
                    va = /^\d+$/.test(v);
                    tip = "只能输入数字";
                    break;
                case "float":                          //浮点数验证
                    va = /^(-?\d+)(\.\d+)?$/.test(v);
                    tip = "只能输入数字";
                    break;

                case "capital":                          //注册资金
                    va = /^(?!(0[0-9]{0,}$))[0-9]{1,10}$/.test(v);
                    tip = "请填写大于0整数";
                    break;
                case "name":                             //姓名验证
                    va = /^[\u4e00-\u9fa5]{2,20}$/.test(v);
                    tip = "请填写正确的渠道商姓名";
                    break;
                case "noName":                             //非中文验证
                    va = !/[\u4E00-\u9FA5]/g.test(v);
                    tip = "不能输入中文";
                    break;
                case "filePath":
                    va = v != "" && $.trim(v) != "" &&  $.trim(v).length < 255;
                    tip = "文件未上传";
                    break;
                case "maxLength":
                    var len = parseInt($(obj).attr("length"));
                    va = jq_form.getByteLen(val) <= len;
                    tip = "输入的字符太多，只能输入" + len+"个字符";
                    break;
                case "fixLine":
                    va = /^0\d{2,3}?\d{7,8}$/.test(v) || (v.length == 11 && /^\d+$/.test(v) && v.substring(0,1)) ;
                    tip = "请输入手机号或固话";
                    break;
                case "special":
                    va = !/[,'"\t]+/.test(v);
                    tip = "不能输入英文逗号，引号，制表符";
                    break;
                case "minLength":
                    var len = parseInt($(obj).attr("minLength"));
                    va = jq_form.getByteLen(val) >= len;
                    tip = "输入的字符太少，至少输入" + len+"个字符";
                    break;
            }
            return va;
        };
    if(types == "" || (t == 1 && val == "")){
        isRight = true;
        regainPlaceholder();
    }else{
        for(var i =0; i<type.length; i++){
            if(!fnValid(type[i],val)){ //如果一项验证没通过就错
                isRight = false;
                !(t && t == "noerror") && jq_form.setErrorTip(obj,tip);
                return false;
            }
        }
        if(isRight && t == 0 && val != ""){
            jq_form.delErrorTip(obj);
        }
    }
    return isRight;    //验证通过返回true ,不通过返回false
}
//
jq_form.getByteLen = function(val) {
    var len = 0;
    for (var i = 0; i < val.length; i++) {
        var a = val.charAt(i);
        if (a.match(/[^\x00-\xff]/ig) != null)
        {
            len += 2;
        }
        else
        {
            len += 1;
        }
    }
    return len;
}
//身份证验证
jq_form.validIDNumber = function(id){
    var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子
    var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值.10代表X
    var fn = {
        IdCardValidate:function(idCard){
            idCard = $.trim(idCard.replace(/ /g, ""));
            if (idCard.length == 15) {
                return fn.isValidityBrithBy15IdCard(idCard);
            } else if (idCard.length == 18) {
                var a_idCard = idCard.split("");// 得到身份证数组
                if(fn.isValidityBrithBy18IdCard(idCard)&&fn.isTrueValidateCodeBy18IdCard(a_idCard)){
                    return true;
                }else {
                    return false;
                }
            } else {
                return false;
            }
        } ,
        isTrueValidateCodeBy18IdCard:function(a_idCard){
            var sum = 0; // 声明加权求和变量
            if (a_idCard[17].toLowerCase() == 'x') {
                a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作
            }
            for ( var i = 0; i < 17; i++) {
                sum += Wi[i] * a_idCard[i];// 加权求和
            }
            valCodePosition = sum % 11;// 得到验证码所位置
            if (a_idCard[17] == ValideCode[valCodePosition]) {
                return true;
            } else {
                return false;
            }
        },
        maleOrFemalByIdCard:function(idCard){
            idCard = $.trim(idCard.replace(/ /g, ""));// 对身份证号码做处理。包括字符间有空格。
            if(idCard.length==15){
                if(idCard.substring(14,15)%2==0){
                    return 'female';
                }else{
                    return 'male';
                }
            }else if(idCard.length ==18){
                if(idCard.substring(14,17)%2==0){
                    return 'female';
                }else{
                    return 'male';
                }
            }else{
                return null;
            }
        } ,
        isValidityBrithBy18IdCard:function(idCard18){
            var year =  idCard18.substring(6,10);
            var month = idCard18.substring(10,12);
            var day = idCard18.substring(12,14);
            var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));
            // 这里用getFullYear()获取年份，避免千年虫问题
            if(temp_date.getFullYear()!=parseFloat(year)
                ||temp_date.getMonth()!=parseFloat(month)-1
                ||temp_date.getDate()!=parseFloat(day)){
                return false;
            }else{
                return true;
            }
        },
        isValidityBrithBy15IdCard:function(idCard15){
            var year =  idCard15.substring(6,8);
            var month = idCard15.substring(8,10);
            var day = idCard15.substring(10,12);
            var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));
            // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
            if(temp_date.getYear()!=parseFloat(year)
                ||temp_date.getMonth()!=parseFloat(month)-1
                ||temp_date.getDate()!=parseFloat(day)){
                return false;
            }else{
                return true;
            }
        }

    }
    return fn.IdCardValidate(id);
}
//一键验证所有按钮
jq_form.validAllForm = function(formId){
    var form = $("#"+formId),
        input = form.find("input[valid]"),
        textarea = form.find("textarea[valid]"),
        isValidin = true,
        isValidtx = true;
    input.each(function(){
        if(!jq_form.validForm($(this),0)){
        isValidin = false;
        return false;
        }
    }) ;
    if(isValidin){
        textarea.each(function(){
            if(!jq_form.validForm($(this),0)) {
            isValidtx = false;
            return false;
            }
        });
    }
    /*if(!(isValidin &&  isValidtx)){pageJumpTo(form.find(".error")[0]); }*/
    return   isValidin &&  isValidtx;
}

//input添加blur事件
jq_form.addBlur = function(obj){
    $(obj).unbind("blur").bind("blur",function(){
        var $this = $(this),
            isdel;
        $this.val() != "" && jq_form.delNull($this);     //去头尾空格
        //console.log($this.val().length)
        isdel = jq_form.validForm($this,1);
        if(isdel){
            jq_form.delErrorTip($this);
            if($this.val() == ""){
                jq_form.delPassed($this);
            }else{
                jq_form.setPassed($this);
            }
        }
    })
}
//去掉前后空格
jq_form.delNull = function(obj){
    //console.log(v.charAt(0)  == ' ')
    var v = typeof(obj) == "object" ? $(obj).val() : obj;
    while( v.charAt(0)  == ' ' )
    {
        v = v.substring(1,v.length);
    }
    // 去除后面的空格
    while( v.charAt(v.length-1)  == ' ' )
    {
        v = v.substring(0,v.length-1);
    }
    if($(obj).parent().hasClass("in_btn")){
        v = v.replace(/\s/g,"");
    }
    //console.log(v.length)
    $(obj).val(v);
    return v ;
}
jq_form.setErrorTip = function(obj,str,errorObj){
    var input = $(obj);
    if($("#error_tip")[0]){
        var errorSpan =  $("#error_tip");
        errorSpan.html(str).show();
    }else{
        var errorSpan = input.parent().siblings('span');
        errorSpan.html() == "" && errorSpan.html(str);
        errorSpan.show();
    }
    errorSpan.html() == "" && errorSpan.html(str) ;
    errorSpan.show();
    if(input.parent().parent().parent().hasClass("select_div")){
        input.parent().parent().parent().removeClass("passed").addClass("error");
    }else{
        input.parent().parent().removeClass("passed").addClass("error");
    }

}
jq_form.delErrorTip = function(obj,errorObj){
    var input = $(obj);
    var errorSpan = $("#error_tip")[0] ? $("#error_tip") : input.parent().siblings('span');
    errorSpan.hide();
    if(input.parent().parent().parent().hasClass("select_div")){
        input.parent().parent().parent().removeClass("passed").removeClass("error");
    }else{
        input.parent().parent().removeClass("passed").removeClass("error");
    }
}
jq_form.setPassed = function(obj,errorObj){
    var input = $(obj);
    var errorSpan = $("#error_tip")[0] ? $("#error_tip") : input.parent().siblings('span');
    errorSpan.hide();
    input.parent().parent().removeClass("error").addClass("passed");
}
jq_form.delPassed = function(obj){
    var input = $(obj);
    input.parent().parent().removeClass("passed");
}