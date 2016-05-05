$(function () {
    addBlur($("input[valid]"));
    addBlur($("textarea[valid]"));
    addBlur($("div.sel_btn"));
})
//input添加blur事件

function addBlur(obj){
    $(obj).unbind("blur").live("blur",function(){
        var $this = $(this),
            isdel;
        if ($this.hasClass("sel_btn")) {
            if ($this.find("input[valid]")[0]) {
                isdel = validForm($this.find("input"));
            }
        } else {
            delNUllStr($this);
            isdel = validForm($this);
        }
        if (isdel) {
            $this.hasClass("sel_btn") ? delErrorTip($this.find("input")) : delErrorTip($this);
        }
    })
}
//去掉前后空格
function delNull(v) {
    return  v.replace(/(^\s*)|(\s*$)/g, "");
}

function delNUllStr(obj) {
    //console.log(v.charAt(0)  == ' ')
    var v = typeof(obj) == "object" ? $(obj).val() : obj;
    while (v.charAt(0) == ' ') {
        v = v.substring(1, v.length);

    }
    // 去除后面的空格
    while (v.charAt(v.length - 1) == ' ') {
        v = v.substring(0, v.length - 1);
    }
    if ($(obj).parent().hasClass("in_btn")) {
        v = v.replace(/\s/g, "");
    }
    //console.log(v.length)
    $(obj).val(v);
    return v;
}
//验证函数 参数val是input的vlue，type是
function validForm(obj) {
    var val = $(obj).val().replace(/(^\s*)|(\s*$)/g, ""),     //去头尾空格
        types = $(obj).attr("valid"),
        isRight = true,
        tip = "",
        type = types.split(" "),
        fnValid = function (t, v) {
            var va;
            switch (t) {
                case "require":                       //是否为空   空返回false
                    va = v != "" && $.trim(v) != "";
                    tip = "您的输入有误，请核实后重新输入";
                    break;
                case "phone":                          //手机号验证 错误返回false
//                    va = v.length == 11 && /(^(13[0-9]|147|15[^4,\\D]|18[0,5-9,1])\d{8}$)/.test(v);
                    va = /^\d+$/.test(v) && v.length == 11 && v.substring(0, 1) == 1;
                    tip = "请填写正确的手机号码";
                    break;
                case "personId":                       //身份证号码验证
                    va = validIDNumber(v);
                    tip = "请输入正确的身份证号码"
                    break;

                case "email":
                    va = /[@]+/.test(v) && v.length < 33;
                    //va  = /^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+\.(?:com|cn)$/.test(v);
                    tip = "请填写正确的邮箱";
                    break;
                case "code":                            //6位验证码
                    va = v.length == 6 && !isNaN(v);
                    tip = "请输入正确的邮编";
                    break;
                case "number":                          //数字验证
                    va = /^\d+$/.test(v);
                    tip = "只能输入数字";
                    break;
                case "capital":                          //注册资金
                    va = /^(?!(0[0-9]{0,}$))[0-9]{1,10}$/.test(v);
                    tip = "请填写大于0整数";
                    break;
                case "name":                             //姓名验证
                    va = /^[\u0391-\uFFE5\w]+$/.test(v);
                    tip = "请填写正确的姓名";
                    break;
                // default :
                case "same":
                    va = validPhoneSame($(obj));
                    tip = "该手机号已存在，请核实后重新输入";
                    break;
                case "pSame":
                    va = validPhone($(obj)).same;
                    tip = validPhone($(obj)).msg;
                    break;
                case "samePackage":                          //包名验证
                    va = checkPackageUnique($(obj)).same;
                    tip = checkPackageUnique($(obj)).msg;
                    break;
                case "sameCompany":
                    var sameCompany = checkCnNameUnique($(obj));
                    va = sameCompany.same;
                    tip = sameCompany.msg;
                    break;
                case "sameShortName":
                    var sameShortName = checkShortNameUnique($(obj));
                    va = sameShortName.same;
                    tip = sameShortName.msg;
                    break;
                case "sameDisFullName":
                    va = checkDisFullName($(obj)).same;
                    tip = checkDisFullName($(obj)).msg;
                    break;
                case "filePath":
                    va = v != "" && $.trim(v) != "" && $.trim(v).length < 255;
                    tip = "文件上传有误，请重新上传";
                    break;
                case "maxLength":
                    var len = parseInt($(obj).attr("length"));
                    va = val.length <= len;
                    tip = "输入的字符太多，只能输入" + len + "个字符";
                    break;
                case "checkDisShortName":
                    var result = checkDisShortName($(obj));
                    va = result.same;
                    tip = result.msg;
                    break;
                case "special":
                    va = !/[,'"\t]+/.test(v);
                    tip = "不能输入英文逗号，引号，制表符";
                    break;
                case "samePhone":                          //判断同一个任务中手机号码是否相同
                    va = checkMobilePhone($(obj)).same;
                    tip = checkMobilePhone($(obj)).msg;
                    break;
                case "num_30":
                    var name = validNumCount($(obj));
                    va = name.same;
                    tip = name.msg;
                    break;
                case "chineseMaxlength":
                    var len = parseInt($(obj).attr("length"));
                    va = getByteLen(val) <= len;
                    tip = "输入的字符太多，只能输入" + len+"个字符";
                    break;

            }
            return va;
        };
    if (types == "") {
        isRight = true;
    } else {
        for (var i = 0; i < type.length; i++) {
            if (!fnValid(type[i], val)) { //如果一项验证没通过就错
                isRight = false;
                setErrorTip(obj, tip);
            }
        }
    }
    return isRight;    //验证通过返回true ,不通过返回false
}

function getByteLen(val) {
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
//验证渠道商简称是否已存在
function checkDisShortName(obj) {
    var shortName = $(obj).val();
    var s = true, m = "";
    var distributorId = $("#distributorId").val();
    var url = getRootPath() + "/distributor/checkDisShortName";
    $.ajax({url: url,
        data: "shortName=" + shortName + "&distributorId=" + distributorId,
        type: "post",
        async: false,
        dataType: "json",
        success: function (data, textStatus) {
            if (textStatus == "success") {
                if (data['result']) {
                    //  setErrorTip("#"+inputId,data['msg']);
                    s = false;
                    m = data['msg'];
                } else {
                    s = true;
                    m = data['msg'];
                }
            } else {
                s = false;
                m = "系统忙请稍后再试！";
            }
        }
    });
    return {same: s, msg: m};
}


//身份证验证
function validIDNumber(id) {
    var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子
    var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值.10代表X
    var fn = {
        IdCardValidate: function (idCard) {
            idCard = $.trim(idCard.replace(/ /g, ""));
            if (idCard.length == 15) {
                return fn.isValidityBrithBy15IdCard(idCard);
            } else if (idCard.length == 18) {
                var a_idCard = idCard.split("");// 得到身份证数组
                if (fn.isValidityBrithBy18IdCard(idCard) && fn.isTrueValidateCodeBy18IdCard(a_idCard)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        },
        isTrueValidateCodeBy18IdCard: function (a_idCard) {
            var sum = 0; // 声明加权求和变量
            if (a_idCard[17].toLowerCase() == 'x') {
                a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作
            }
            for (var i = 0; i < 17; i++) {
                sum += Wi[i] * a_idCard[i];// 加权求和
            }
            valCodePosition = sum % 11;// 得到验证码所位置
            if (a_idCard[17] == ValideCode[valCodePosition]) {
                return true;
            } else {
                return false;
            }
        },
        maleOrFemalByIdCard: function (idCard) {
            idCard = $.trim(idCard.replace(/ /g, ""));// 对身份证号码做处理。包括字符间有空格。
            if (idCard.length == 15) {
                if (idCard.substring(14, 15) % 2 == 0) {
                    return 'female';
                } else {
                    return 'male';
                }
            } else if (idCard.length == 18) {
                if (idCard.substring(14, 17) % 2 == 0) {
                    return 'female';
                } else {
                    return 'male';
                }
            } else {
                return null;
            }
        },
        isValidityBrithBy18IdCard: function (idCard18) {
            var year = idCard18.substring(6, 10);
            var month = idCard18.substring(10, 12);
            var day = idCard18.substring(12, 14);
            var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
            // 这里用getFullYear()获取年份，避免千年虫问题
            if (temp_date.getFullYear() != parseFloat(year)
                || temp_date.getMonth() != parseFloat(month) - 1
                || temp_date.getDate() != parseFloat(day)) {
                return false;
            } else {
                return true;
            }
        },
        isValidityBrithBy15IdCard: function (idCard15) {
            var year = idCard15.substring(6, 8);
            var month = idCard15.substring(8, 10);
            var day = idCard15.substring(10, 12);
            var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
            // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
            if (temp_date.getYear() != parseFloat(year)
                || temp_date.getMonth() != parseFloat(month) - 1
                || temp_date.getDate() != parseFloat(day)) {
                return false;
            } else {
                return true;
            }
        }

    }
    return fn.IdCardValidate(id);
}
//一键验证所有按钮
function validAllForm(formId) {
    var form = $("#" + formId),
        input = form.find("input[valid]"),
        textarea = form.find("textarea[valid]"),
        isValidin = true,
        isValidtx = true;
    input.each(function () {
        if (!validForm($(this)))
            isValidin = false;
    });
    textarea.each(function () {
        if (!validForm($(this)))
            isValidtx = false;
    });
    return   isValidin && isValidtx;
}


//验证渠道商简称是否已存在
function checkDisShortName_bak(inputId) {
    var shortName = $("#" + inputId).val();
    var distributorId = $("#distributorId").val();
    var url = getRootPath() + "/distributor/checkDisShortName";
    $.ajax({url: url,
        data: "shortName=" + shortName + "&distributorId=" + distributorId,
        type: "post",
        dataType: "json",
        success: function (data, textStatus) {
            if (textStatus == "success") {
                if (data['result']) {
                    setErrorTip("#" + inputId, data['msg']);
                }
            } else {
                setPageTip("系统忙请稍后再试！");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            setPageTip(errorThrown);
        }
    });
}

function checkAccount(inputId) {
    var phone_num = $("#" + inputId).val();
    var distributorId = $("#distributorId").val();
    var url = getRootPath() + "/distributor/checkAccount";
    $.ajax({url: url,
        data: "phone_num=" + phone_num + "&distributorId=" + distributorId,
        type: "post",
        dataType: "json",
        success: function (data, textStatus) {
            if (textStatus == "success") {
                if (data['result']) {
                    setErrorTip("#" + inputId, data['msg']);
                }
            } else {
                setPageTip("系统忙请稍后再试！");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            setPageTip(errorThrown);
        }
    });
}

//获取form的json值
function getFormData(formId) {
    var form = $("#" + formId),
        dataArray = [],
        isSuccess;
    form.find("input[name]").each(function () {
        var _this = $(this);
        var isRight = validForm(_this);//先验证每项
        isSuccess = isRight ? true : false;
        dataArray.push(_this.attr("name") + "=" + _this.val());
    })
    return {isValid: isSuccess, data: dataArray.join("&")};
}
function isFirfox() {        //判断火狐浏览器
    if ($.browser.mozilla) {
        return true;
    }
}
//添加输入框错误提示   参数 id是输入框的id（下拉框输入class为sel_btn的div的id），msg是错误信息
function setErrorTip(obj, msg) {
    var _input = $(obj),
        heights = function () {
            if (_input.parent().hasClass("area_btn")) {
                return 115;
            } else if (_input.parent().hasClass("sel_btn")) {
                return 58;
            } else if (_input.parent().hasClass("file_a")) {
                return 42;
            } else {
                return 34;
            }
        },
        error_span = '<i class="error_tip" style="top:' + heights() + 'px; left:5px;">' + msg + '</i>',
        addError = function (b) {

        };
    if (_input.siblings("a.file_a")[0]) {
        var fileA = $(_input.siblings("a.file_a"));
        if (fileA.find("i.error_tip")[0]) {
            fileA.find("i.error_tip").html(msg);
        } else {
            fileA.append(error_span);
        }
    } else {
        if (_input.siblings("i.error_tip")[0]) {
            _input.siblings("i.error_tip").html(msg);
            _input.parent().addClass("error");
        } else {
            _input.parent().addClass("error").append(error_span);
        }
    }
}
//解除错误提示     参数 id是输入框的id（下拉框输入class为sel_btn的div的id）
function delErrorTip(obj) {
    var _input = $(obj);
    if (_input.siblings("a.file_a")[0]) {
        $(_input.siblings("a.file_a")[0]).find("i").html("");
    }
    else {
        _input.parent().removeClass("error");
        _input.siblings("i.error_tip").html("");
    }
}
var phoneInput, btnSendCode, time;//验证码的按钮和输入框
//发送验证码
function sendValidateCode() {
    //验证号码
    var phoneNum = phoneInput.val();
    //发送短信验证码
    var requestUrl = getRootPath() + "/register/sendValidateCode";
    $.ajax({url: requestUrl,
        data: "phoneNum=" + phoneNum,
        type: "post",
        dataType: "json",
        success: function (data, textStatus) {
            if (textStatus == "success") {
                if (data['result']) {
                    setPageTip(data['msg']);
                } else {
                    setErrorTip(phoneInput, data['msg']);
                }
                SetRemainTime(60);
            } else {
                setPageTip("系统忙请稍后再试！");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            setPageTip(errorThrown);
        }
    });

}
//timer处理函数
function SetRemainTime(i) {
    if (i == -1) {
        btnSendCode.removeClass("a_gray").html("重新发送验证码").unbind("click").bind("click", sendValidateCode);
        time = null;
        clearTimeout(time);
    } else {
        btnSendCode.attr("onclick", "").unbind("click").addClass("a_gray").html(i + "秒后可再次发送");
        time = setTimeout(function () {
            SetRemainTime(--i, btnSendCode);
        }, 1000);
    }
}
//输入框的keyUP，onmouseUP
function phoneKeyUp(obj) {
    btnSendCode = $(obj).parent().siblings("a");
    phoneInput = $(obj);
    if (validForm($(obj))) {
        btnSendCode.removeClass("a_gray").unbind("click").bind("click", sendValidateCode);
    } else {
        btnSendCode.addClass("a_gray").unbind("click");
    }
    ;
}
//输入1-30之间的整数校验
function validNumCount(obj){
    var v = $(obj).val(),m = "请输入1-30之间的整数",s = true;
    if(v<1 || v>30){
        s = false;
    }
    return {same:s,msg:m}
}