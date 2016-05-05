package cn.egame.common

import cn.egame.client.biz.EGameClientBiz
import cn.egame.common.util.Utils
import cn.egame.global.constant.FtpServiceConstants
import cn.egame.global.utils.AppEntityParseUtil
import cn.egame.interfaces.ConstVar
import cn.egame.interfaces.fl.FileUsedType
import cn.egame.interfaces.fl.FileUtils
import cn.egame.utils.ImageUtil
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.fileupload.disk.DiskFileItem
import org.apache.commons.io.IOUtils
import org.apache.commons.lang.StringUtils
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile

/**
 * 上传公共方法控制类
 * @Copyright play.cn
 *
 * @Project push-sdk-back
 *
 * @Author zx
 *
 * @timer 15-10-19 15:30
 *
 * @Version 3.0
 *
 * @JDK version used 6.0
 *
 */

class AjaxUploadController {

    /**
     * @Description: 页面AJAX上传图片公共方法，根据imageType对不同类型的图片做校验
     * @Author hanjun
     * @Create Date 2013年8月16日
     * @Modified by none
     * @Modified Date
     */
    def uploadImg() {
        def resultMap = [:]
        try{
            MultipartRequest multipartRequest = (MultipartRequest) request;
            Map requestMap = multipartRequest.getFileMap();
            Set entrySet = requestMap.entrySet();
            Iterator iterator = entrySet.iterator();
            MultipartFile multipartFile = null;
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                multipartFile = (MultipartFile) entry.getValue();
                break;
            }

            String fileName = multipartFile.originalFilename;
            Properties properties = Utils.getProperties("common.properties");
            String downloadHeadPath = properties.get("download_url");

            boolean flag = false;
            def imageType = params.get("imageType");
            if (StringUtils.isBlank(imageType)) {
                resultMap << [result: false];
                resultMap << [msg: '参数错误'];
                render resultMap;
                return;
            }
            //imageType=-1:不做图片大小限制
            if ("-1".equals(imageType)) {
                flag = ImageUtil.validateImageProperties(multipartFile, null, null, null, null);
            }
            //imageType=0:广告图片，根据页面关联广告位的width和height限制图片宽度和大小
            else if ("0".equals(imageType)) {
                def width = params.get("width");
                def height = params.get("height");
                def maxSize = params.get("maxSize");
                def minSize = params.get("minSize");
                flag = ImageUtil.validateImageProperties(multipartFile, width? Integer.valueOf(width) : null, height? Integer.valueOf(height) : null, maxSize? Double.valueOf(maxSize) : null, minSize? Double.valueOf(minSize) : null);
            }
            //其他图片限制
            else {
                String[] imageTypeArr = imageType.split(",");
                for (String type : imageTypeArr) {
                    def imageSize = Constants.imageSize.get(Integer.valueOf(type));
                    flag = ImageUtil.validateImageProperties(multipartFile, imageSize[0], imageSize[1], imageSize[2], imageSize[3]);
                    if (flag) {
                        break;
                    }
                }
            }
            if (flag) {
//                boolean compressFlag = false;
                //文件类型：200--游戏icon
                //上传文件类型
                Integer fileType = FileUsedType.PUSH_PHOTO_FILE;

//                FileUsedType type = null;
                if ("2".equals(imageType)) {
                    fileType = FileUsedType.PUSH_PHOTO_FILE;
                }
//                else if ("300".equals(imageType)) {
//                    type = FileUsedType.video_image;
//                }
//                else if ("31".equals(imageType)) {
//                    type = FileUsedType.comment_photo;
//                    compressFlag = true;
//                }
//                else {
//                    type = FileUsedType.adver_photo;
//                }
//                def pos = multipartFile.originalFilename.lastIndexOf(".");
//                String imageFileName = System.currentTimeMillis() + "." + multipartFile.originalFilename.substring(pos + 1);
//                long efsId = EGameClientBiz.getInstance().writeToFile(multipartFile.getInputStream(), type.value, Constants.APP_ID, Constants.LOGIN_USER_ID, imageFileName, compressFlag);
//                if (efsId == -1L) {
//                    resultMap << [result: false];
//                    resultMap << [msg: '上传失败'];
//                    render resultMap;
//                    return;
//                }

                String downloadPath = EGameClientBiz.getInstance().writeToPushFile(multipartFile.getInputStream(), fileType, Constants.PAY_SDK_APP_ID, Constants.LOGIN_USER_ID, fileName);
                String realFilePath = downloadHeadPath + downloadPath;
                log.info("filePath:" + realFilePath);

//                String url = ConstVar.DOWNLOAD_URL + FileUtils.getFilePath(type, efsId, imageFileName);
                resultMap << [result: true];
                resultMap << [msg: '上传成功'];
//                resultMap << [efsId: efsId];
//                resultMap << [url: url];
                resultMap << [downloadPath: downloadPath];
                resultMap << [realFilePath: realFilePath];
            } else {
                resultMap << [result: false];
                resultMap << [msg: '图片规格不符合要求，请检查！']
            }
        }
        catch (Exception e){
            resultMap << [result: false];
            resultMap << [msg: '上传失败'];
            log.error("上传文件发生异常", e);
        }
        render resultMap;
    }

    /**
     * 上传文件
     * @return
     */
    def uploadFile() {
        def resultMap = [:];
        try {
            MultipartRequest multipartRequest = (MultipartRequest) request;
            Map requestMap = multipartRequest.getFileMap();
            Set entrySet = requestMap.entrySet();
            Iterator iterator = entrySet.iterator();
            MultipartFile multipartFile = null;
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                multipartFile = (MultipartFile) entry.getValue();
                break;
            }
            Properties properties = Utils.getProperties("common.properties");
            String downloadHeadPath = properties.get("download_url");
            String fileName = multipartFile.originalFilename;
            Long fileSize = multipartFile.getSize();
            String md5 = DigestUtils.md5Hex(IOUtils.toByteArray(multipartFile.getInputStream()));
            //上传文件类型
            Integer fileType = params.get("fileType") as Integer;
//            String[] androidInfoArr = new String[3];
            String versionCode = "";
            String packageName = "";
            int imsiNum = 0;
            if(fileType == 1) {
                //游戏包
                fileType = FileUsedType.PUSH_GAME_FILE;
                //转化为文件，再解析包名及版本号
                CommonsMultipartFile cf = (CommonsMultipartFile)multipartFile;
                DiskFileItem diskFileItem = (DiskFileItem)cf.getFileItem();
                File file = diskFileItem.getStoreLocation();
                String[] apkInfo = AnalysisApk.getApkInfo(file);
                versionCode = apkInfo[0];
                packageName = apkInfo[1];
//                androidInfoArr = AppEntityParseUtil.getInstance().parseAndroidInfo(FtpServiceConstants.APP_ENTITY_PARSE_TEMP_PATH + "/" + fileName);
//                String packageName1 = androidInfoArr.length > 0 ? androidInfoArr[0] : "";
            } else if(fileType == 2) {
                //游戏图片
                fileType = FileUsedType.PUSH_PHOTO_FILE;
            } else if(fileType == 3) {
                //sdk包
                fileType = FileUsedType.PUSH_HALL_FILE;
            } else if(fileType == 4) {
                //imis文件
                fileType = FileUsedType.PUSH_ISMI_FILE;

                /*
                //转化为文件，再读取imsi条数
                CommonsMultipartFile cf = (CommonsMultipartFile)multipartFile;
                DiskFileItem diskFileItem = (DiskFileItem)cf.getFileItem();
                File file = diskFileItem.getStoreLocation();
                BufferedReader br = null;
                try{
                    br = new BufferedReader(new InputStreamReader(
                            new FileInputStream(file),"utf-8"));
                    String readLine = null;
                    while ((readLine = br.readLine()) != null) {
                        readLine = readLine.trim();
                        if ("".equals(readLine)) {
                            continue;
                        }
                        imsiNum++;
                    }
                }catch (Exception e){
                    throw e;
                }finally{
                    if(br != null){
                        br.close();
                    }
                }
                */
            }
            String downloadPath = EGameClientBiz.getInstance().writeToPushFile(multipartFile.getInputStream(), fileType, Constants.PAY_SDK_APP_ID, Constants.LOGIN_USER_ID, fileName);
            String realFilePath = downloadHeadPath + downloadPath;
            log.info("filePath:" + downloadPath);
            if(fileType == FileUsedType.PUSH_ISMI_FILE){
//                String filePath = "E:\\"+ConstVar.UPLOAD_WRITE_ADDRESS + downloadPath;
                String filePath = ConstVar.UPLOAD_WRITE_ADDRESS + downloadPath;
                BufferedReader br = null;
                try{
                    br = new BufferedReader(new InputStreamReader(
                            new FileInputStream(filePath),"utf-8"));
                    String readLine = null;
                    while ((readLine = br.readLine()) != null) {
                        readLine = readLine.trim();
                        if ("".equals(readLine)) {
                            continue;
                        }
                        imsiNum++;
                    }
                }catch (Exception e){
                    throw e;
                }finally{
                    if(br != null){
                        br.close();
                    }
                }
            }

            resultMap << [result: true];
            resultMap << [msg: '上传成功'];
            resultMap << [fileName: fileName];
            resultMap << [fileSize: fileSize];
            resultMap << [md5: md5];
            resultMap << [downloadPath: downloadPath];
            resultMap << [realFilePath: realFilePath];
            resultMap << [versionCode: versionCode];
            resultMap << [packageName: packageName];
            resultMap << [imsiNum: imsiNum];
        } catch (Exception e) {
            resultMap << [result: false];
            resultMap << [msg: '上传失败'];
            log.error("上传文件发生异常", e);
        }
        render resultMap;
    }
}
