

<script language="javascript" type="text/javascript">
    document.write("<script type='text/javascript' "
            + "src='${request.getContextPath()}/js/uploadify/jquery.uploadify.js?" + new Date()
            + "'></s" + "cript>");
</script>
<script type="text/javascript">
    function uploadifyInit(btn,url,fileName,imageType,callback){
        var base_path = "${request.getContextPath()}";
        var submitType = "approve";
        var fileTypeExts ;
        if(imageType == 200 || imageType == 203 || imageType == 3|| imageType == 21){
            fileTypeExts = '*.jpg; *.png;*.JPG; *.PNG'
        }else if(imageType == 205 || imageType == 207){
            fileTypeExts = '*.jpg; *.png;*.JPG; *.PNG;*.BMP; *.bmp'
        }else if(imageType == 2){
            fileTypeExts = '*.MP4; *.mp4'
        }else if(imageType == 1000){
            fileTypeExts = ' *.APK; *.apk,*.ZIP; *.zip'
        }
        if (imageType == 3){ //  这个很恶心，主要是为了解决ICON新增时只能上传512*512，更新时可以上传512*512和180*180
            submitType = "update";
            imageType = 200;
        }
        $("#"+btn).uploadify({
            'swf' : base_path + '/js/uploadify/uploadify.swf',
            'uploader' : url,
            'dataType': 'json',
            'buttonText': '上传图片',
            'fileTypeDesc':fileTypeExts,
            'fileTypeExts': fileTypeExts,
            'formData': {submitType:submitType, fileName:fileName, imageType:imageType},
            'auto': true,
            'multi': true,
            'onUploadSuccess':function(file, data, response){
                callback(btn,file, data, response)

            },
            'onComplete':function(event,queueId,fileObj,response,data){

            },
            'onUploadError' : function(file, errorCode, errorMsg, errorString) {
                var uploadBtn = $("#"+btn);
                    uploadBtn.next("div").hide();
                    uploadBtn.show()
                            .siblings("span").show().html(errorString);
            }
        });
    }
    function uploadifyEntity(btn,url,fileName,imageType,width,callback){

        var base_path = "${request.getContextPath()}";
        var submitType = "approve";
        var fileTypeExts = ' *.APK; *.apk';
        $("#"+btn).uploadify({
            'swf' : base_path + '/js/uploadify/uploadify.swf',
            'uploader' : url,
            'dataType': 'json',
            'buttonText': '',
            'fileTypeDesc':fileTypeExts,
            'fileTypeExts': fileTypeExts,
            'formData': {submitType:submitType, fileName:fileName, imageType:imageType},
            'auto': false,
            'multi': false,
            'width':width,
            'height':36,
            'queueSizeLimit':1,
            'onUploadSuccess':function(file, data, response){
                callback(btn,file, data, response)

            },
            'onComplete':function(event,queueId,fileObj,response,data){

            },
            'onUploadError' : function(file, errorCode, errorMsg, errorString) {
                var uploadBtn = $("#"+btn);
                uploadBtn.next("div").hide();
                uploadBtn.show()
                        .siblings("span").show().html(errorString);
            },
            'onDialogOpen':function(){
                $('#entity_btn').uploadify('cancel', '*')
            },

            'onDialogClose':function(queueData){
                $("#fileName").val($("#entity_btn-queue").find(".fileName").html())
                $("#entity_btn-queue").hide();

            },
            'onUploadComplete':function(file, data, response){

            },
            'onUploadStart':function(){
                clearFormData()
            }
        });
    }

</script>