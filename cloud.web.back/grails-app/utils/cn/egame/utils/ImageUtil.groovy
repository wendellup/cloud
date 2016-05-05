package cn.egame.utils

import cn.egame.client.biz.EGameClientBiz
import cn.egame.interfaces.ConstVar
import cn.egame.interfaces.fl.FileInfo
import cn.egame.interfaces.fl.FileUsedType
import cn.egame.interfaces.fl.FileUtils
//import cn.egame.manage.common.Constants
import org.apache.log4j.Logger
import org.springframework.web.multipart.MultipartFile

import javax.swing.*
import java.rmi.RemoteException
import java.security.NoSuchAlgorithmException

/**
 * Description 图片工具类
 * 
 * @ClassName ImageUtil
 *
 * @Copyright 炫彩互动
 * 
 * @Project egame.manage.gh
 * 
 * @Author hanjun
 * 
 * @Create Date  2013年8月15日
 * 
 * @Modified by none
 *
 * @Modified Date 
 */
class ImageUtil {
    
    /**
     * 日志对象
     */
    static Logger logger = Logger.getLogger(ImageUtil.class);

    /**
     * 检查图片格式是否正确
     *
     * @param MultipartFile multipartFile 文件源
     *
     * @param Integer width 规格宽度【不限制则传递null】
     *
     * @param Integer height 规格高度【不限制则传递null】
     *
     * @param Integer maxSize 规格最大不超过【单位kb，不限制则传递null】
     *
     * @param Integer minSize 规格最小不低于【单位kb，不限制则传递null】
     *
     */
    def static validateImageProperties(MultipartFile multipartFile, Integer width, Integer height, Double maxSize, Double minSize) {
        try {
            ImageIcon icon = new ImageIcon(multipartFile.getBytes());

            //宽度不满足既定条件
            if (width != null && width != icon.getIconWidth()) {
                logger.info("图片宽度：" + icon.getIconWidth() + "不满足既定条件：" + width);
                return false;
            }

            //高度不满足既定条件
            if (height != null && height != icon.getIconHeight()) {
                logger.info("图片高度：" + icon.getIconHeight() + "不满足既定条件：" + height);
                return false;
            }

            //判断大小是否满足既定条件
            if (maxSize != null && (multipartFile.getBytes().length/1024.0) > maxSize) {
                logger.info("图片大小：" + multipartFile.getBytes().length/1024.0 + "超过最大限定：" + maxSize);
                return false;
            }

            //判断大小是否满足既定条件
            if (minSize != null && (multipartFile.getBytes().length/1024.0) < minSize) {
                logger.info("图片大小：" + multipartFile.getBytes().length/1024.0 + "不满足最小限定：" + maxSize);
                return false;
            }
        }
        catch (IOException e) {
            logger.error("验证图片属性发生异常：", e);
        }
        return true;
    }

    /**
     * 获取文件地址
     * @param efsId
     * @param fileUsedType
     * @return
     */
//    def static getFileUrlByEfsId(long efsId, FileUsedType fileUsedType) {
//        String fileUrl = "";
//        FileInfo fileInfo;
//        try{
//            fileInfo = EGameClientBiz.getInstance().getFileInfo(Constants.APP_ID, Constants.LOGIN_USER_ID, efsId);
//            if (fileInfo != null && fileInfo.getFileUsedType().value() == fileUsedType.value()){
//                String filePath = FileUtils.getFilePath(fileUsedType, fileInfo.getEFSId(), fileInfo.getFileName());
//                if (filePath != null && !"".equals(filePath)){
//                    fileUrl = ConstVar.DOWNLOAD_URL + filePath;
//                }
//            }
//        }
//        catch (RemoteException e){
//            logger.error("--------根据文件esfId：" + efsId + "和文件类型:" + fileUsedType.value() + "获取文件URL发生异常！", e);
//        }
//        catch (NoSuchAlgorithmException e){
//            logger.error("--------根据文件esfId：" + efsId + "和文件类型:" + fileUsedType.value() + "获取文件URL发生异常！", e);
//        }
//        return fileUrl;
//    }
}
