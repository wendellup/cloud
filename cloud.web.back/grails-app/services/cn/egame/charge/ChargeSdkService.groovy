package cn.egame.charge

import cn.egame.common.WebConnectionService

/**
 * 计费SDK相关信息公共实现方法
 * @Copyright play.cn
 *
 * @Project push-sdk-back
 *
 * @Author zx
 *
 * @timer 15-10-20 9:50
 *
 * @Version 3.0
 *
 * @JDK version used 6.0
 *
 */
import cn.egame.common.Constants
import cn.egame.common.util.Utils
import cn.egame.global.constant.AppConstants
import cn.egame.global.utils.Pagination
import cn.egame.utils.DateUtil
import org.apache.commons.collections.CollectionUtils
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import org.springframework.jdbc.core.JdbcTemplate

class ChargeSdkService {

    JdbcTemplate jdbcTemplate;
    WebConnectionService webConnectionService;

    def list(GrailsParameterMap params) {
        def currPage = params.page ? Integer.valueOf(params.page) : 1;
        def pageSize = params.rows ? Integer.valueOf(params.rows) : AppConstants.DEFAULT_PAGE_SIZE;
        StringBuffer querySql = new StringBuffer();

        querySql.append("select f.file_id, f.file_name, f.file_size, f.download_path, f.introduction, f.remark, f.version, f.version as version_code, f.md5, f.insert_time, f.update_time")
                .append(" from t_hall_filev2 f")
                .append(" order by f.update_time desc")
        log.info(querySql);

        Pagination pageList = new Pagination(querySql.toString(), currPage, pageSize, jdbcTemplate);
        return pageList;
    }

    def convertBeanList(List<Map<String, Object>> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<HallFileVo> resultList = new ArrayList<HallFileVo>();
        list.each { info ->
            resultList.add(this.convertBean(info));
        }
        return resultList;
    }

    def convertBean(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        HallFileVo vo = new HallFileVo();
        vo.id = map.get("file_id") as Integer;
        vo.fileName =  map.get("file_name") as String;
        vo.fileSize =  map.get("file_size") as Integer;
        vo.downloadPath = map.get("download_path") as String;
        Properties properties = Utils.getProperties("common.properties");
        String downloadHeadPath = properties.get("download_url");
        vo.realDownloadPath = downloadHeadPath + vo.downloadPath;
        vo.introduction = map.get("introduction") as String;
        vo.isForced = map.get("is_forced") as Integer;
        vo.remark = map.get("remark") as String;
        vo.operatorId = map.get("operator_id") as Integer;
        vo.insertTime = DateUtil.parseDate(map.get("insert_time") as Date, DateUtil.DATE_TIME_FORMAT);
        vo.updateTime = DateUtil.parseDate(map.get("update_time") as Date, DateUtil.DATE_TIME_FORMAT);
        vo.versionCode = map.get("version_code") as String;
        vo.clientId = map.get("client_id") as Integer;
        vo.efsId = map.get("efs_id") as Long;
        vo.md5 = map.get("md5") as String;
        return vo;
    }

    /**
     * 新建内容保存入库
     * @param kpiReptData
     * @param params
     */
    def addSubmit(GrailsParameterMap params) {
        try{
            HallFile hallFile = new HallFile();
            hallFile.properties = params;
            hallFile.fileName = params.get("fileName") as String;
            hallFile.fileSize = params.get("fileSize") as Integer;
            hallFile.md5 = params.get("md5") as String;
            hallFile.downloadPath = params.get("downloadPath") as String;
            hallFile.isForced = 0;
//            hallFile.operatorId = 0;
            hallFile.operatorId = params.get("userId") as Long;
            hallFile.insertTime = new Date();
            hallFile.updateTime = new Date();
            hallFile.clientId = Constants.PAY_SDK_APP_ID;
            webConnectionService.save(hallFile);
        } catch (Exception e) {
            log.error("新建内容出错",e)
            throw new RuntimeException(e);
        }
    }

    /**
     * 编辑内容保存入库
     * @param kpiReptData
     * @param params
     */
    def editSubmit(HallFile hallFile, GrailsParameterMap params) {
        try {
            hallFile.introduction = params.get("introduction") as String;
            hallFile.remark = params.get("remark") as String;
            hallFile.versionCode = params.get("versionCode") as String;
            if(params.get("fileName") != null && params.get("fileName") != "") {
                hallFile.fileName = params.get("fileName") as String;
                hallFile.fileSize = params.get("fileSize") as Integer;
                hallFile.md5 = params.get("md5") as String;
                hallFile.downloadPath = params.get("downloadPath") as String;
            }
            hallFile.updateTime = new Date();
            hallFile.operatorId = params.get("userId") as Long;
            webConnectionService.save(hallFile);
        } catch (Exception e) {
            log.error("编辑内容出错",e)
            throw new RuntimeException(e);
        }
    }
}
