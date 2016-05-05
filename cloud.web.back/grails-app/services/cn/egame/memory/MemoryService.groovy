package cn.egame.memory

import cn.egame.common.Constants
import cn.egame.common.WebConnectionService
import cn.egame.common.util.Utils
import cn.egame.global.constant.AppConstants
import cn.egame.global.utils.Pagination
import cn.egame.utils.DateUtil
import org.apache.commons.collections.CollectionUtils
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import org.springframework.jdbc.core.JdbcTemplate

/**
 * 常驻内存相关信息公共实现方法
 * @Copyright play.cn
 *
 * @Project push-sdk-back
 *
 * @Author zx
 *
 * @timer 15-10-21 14:50
 *
 * @Version 3.0
 *
 * @JDK version used 6.0
 *
 */

class MemoryService {

    JdbcTemplate jdbcTemplate;
    WebConnectionService webConnectionService;

    def list(GrailsParameterMap params) {
        def currPage = params.page ? Integer.valueOf(params.page) : 1;
        def pageSize = params.rows ? Integer.valueOf(params.rows) : AppConstants.DEFAULT_PAGE_SIZE;
        StringBuffer querySql = new StringBuffer();

        querySql.append(" select m.id, m.start_time, m.title, m.advert_type, m.description, m.link_url, m.insert_time, m.update_time, m.del_status, m.game_name, m.game_download_url, m.game_size, m.package_name, m.imsi_num, m.flag, m.remark, m.silent_download, m.own_name, m.pricing_way, m.activation_condition, m.recycling_price "
                + " ,m.effect_imsi_num, m.effect_time, m.effect_flag ")
                .append(" from t_memory m where m.del_status=0");
        String advertTypeStr = params.get("advertTypeStr");
        Integer objType = params.get("objType") as Integer;
        if(!Utils.stringIsNullOrEmpty(advertTypeStr)){
                querySql.append(" and advert_type in ("+advertTypeStr+") ");
        }
        if(Utils.toInt(objType,0)!=0){
            querySql.append(" and obj_type = " +objType);
        }
        querySql.append(" order by m.id desc");
        log.info(querySql);

        Pagination pageList = new Pagination(querySql.toString(), currPage, pageSize, jdbcTemplate);
        return pageList;
    }

    def convertBeanList(List<Map<String, Object>> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<MemoryVo> resultList = new ArrayList<MemoryVo>();
        list.each { info ->
            resultList.add(this.convertBean(info));
        }
        return resultList;
    }

    def convertBean(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        Properties properties = Utils.getProperties("common.properties");
        String downloadHeadPath = properties.get("download_url");
        MemoryVo vo = new MemoryVo();
        vo.id = map.get("id") as Long;
        vo.startTime = DateUtil.parseDate2String(map.get("start_time") as Long, DateUtil.DATE_TIME_FORMAT);
        vo.title =  map.get("title") as String;
        vo.advertType = map.get("advert_type") as Integer;
        vo.gameDownloadUrl = map.get("game_download_url") as String;
        vo.realGameDownloadUrl = downloadHeadPath + vo.gameDownloadUrl;
        if(vo.advertType==1 || vo.advertType==4){
            vo.realGameDownloadUrl = downloadHeadPath + vo.gameDownloadUrl;
        }else{
            vo.realGameDownloadUrl = vo.gameDownloadUrl;
        }
        switch (vo.advertType) {
            case 1 :
                vo.advertTypeName = "桌面图标";
                break;
            case 4 :
                vo.advertTypeName = "通知栏消息";
                break;
            case 7 :
                vo.advertTypeName = "通知栏广告";
                break;
            case 8 :
                vo.advertTypeName = "桌面广告";
                break;
        }
        vo.description = map.get("description") as String;
        vo.linkUrl = map.get("link_url") as String;
        vo.insertTime = DateUtil.parseDate2String(map.get("insert_time") as Long, DateUtil.DATE_TIME_FORMAT);
        vo.updateTime = DateUtil.parseDate2String(map.get("update_time") as Long, DateUtil.DATE_TIME_FORMAT);
        vo.delStatus = map.get("del_status") as Integer;
        switch (vo.delStatus) {
            case 0 :
                vo.delStatusName = "正常";
                break;
            case 1 :
                vo.delStatusName = "删除";
                break;
        }
        vo.gameName = map.get("game_name") as String;

        vo.gameSize = map.get("game_size") as Long;
        vo.packageName = map.get("package_name") as String;
        vo.imsiNum = map.get("imsi_num") as Integer;
        vo.flag = map.get("flag") as Integer;
        switch (vo.flag) {
            case 1 :
                vo.flagName = "上线";
                break;
            case 2 :
                vo.flagName = "测试";
                break;
            case 3 :
                vo.flagName = "其他";
                break;
        }
        vo.remark = map.get("remark") as String;
        vo.silentDownload = map.get("silent_download") as Integer;
        if(vo.silentDownload==0){
            vo.silentDownloadName="非静默";
        }else if(vo.silentDownload==1){
            vo.silentDownloadName="静默";
        }
        vo.ownName = map.get("own_name") as String;
        vo.pricingWay = map.get("pricing_way") as String;
        vo.activationCondition = map.get("activation_condition") as String;
        vo.recyclingPrice = map.get("recycling_price") as Double;
        vo.effectImsiNum = map.get("effect_imsi_num") as Integer;
        vo.effectTime = DateUtil.parseDate2String(map.get("effect_time") as Long, DateUtil.DATE_TIME_FORMAT);
//        if(!Utils.stringIsNullOrEmpty(map.get("effect_time"))){
//
//        }
        vo.effectFlag = map.get("effect_flag") as Integer;
        if(vo.effectFlag==0){
            vo.effectFlagName="未生效";
        }else if(vo.effectFlag==1){
            vo.effectFlagName="已经生效";
        }
        return vo;
    }

    def toVo(MemoryInfo info) {
        Properties properties = Utils.getProperties("common.properties");
        String downloadHeadPath = properties.get("download_url");
        MemoryVo vo = new MemoryVo();
        vo.id = info.id;
        vo.switchType = info.switchType;
        vo.advertType = info.advertType;
        vo.gameDownloadUrl = info.gameDownloadUrl;
        if(vo.advertType==1 || vo.advertType==4){
            vo.realGameDownloadUrl = downloadHeadPath + vo.gameDownloadUrl;
        }else{
            vo.realGameDownloadUrl = vo.gameDownloadUrl;
        }
        switch (vo.advertType) {
            case 1 :
                vo.advertTypeName = "桌面图标";
                break;
            case 4 :
                vo.advertTypeName = "通知栏消息";
                break;
            case 7 :
                vo.advertTypeName = "通知栏广告";
                break;
            case 8 :
                vo.advertTypeName = "桌面广告";
                break;
        }
        vo.filePath = info.filePath;
        vo.realFilePath = Constants.DOWNLOAD_URL + info.filePath;
        vo.startTime = DateUtil.parseDate2String(info.startTime, DateUtil.DATE_TIME_FORMAT);
        vo.endTime = info.endTime;
        vo.title = info.title;
        vo.description = info.description;
        vo.linkUrl = info.linkUrl;
        vo.insertTime = info.insertTime;
        vo.updateTime = info.updateTime;
        vo.operatorId = info.operatorId;
        vo.status = info.status;
        vo.layOut = info.layOut;
        vo.hasBgcolor = info.hasBgcolor;
        vo.delStatus = info.delStatus;
        switch (vo.delStatus) {
            case 0 :
                vo.delStatusName = "正常";
                break;
            case 1 :
                vo.delStatusName = "删除";
                break;
        }
        vo.appId = info.appId;
        vo.gameName = info.gameName;
        vo.gameIcon = info.gameIcon;
        vo.realGameIcon = downloadHeadPath + vo.gameIcon;
        vo.gameSize = info.gameSize;
        vo.packageName = info.packageName;
        vo.imsiNum = info.imsiNum;
        vo.flag = info.flag;
        switch (vo.flag) {
            case 1 :
                vo.flagName = "上线";
                break;
            case 2 :
                vo.flagName = "测试";
                break;
            case 3 :
                vo.flagName = "其他";
                break;
        }
        vo.remark = info.remark;
        vo.silentDownload = info.silentDownload;
        if(vo.silentDownload==0){
            vo.silentDownloadName="非静默";
        }else if(vo.silentDownload==1){
            vo.silentDownloadName="静默";
        }
        vo.objType = info.objType;
        vo.ownName = info.ownName;
        vo.pricingWay = info.pricingWay;
        vo.activationCondition = info.activationCondition;
        vo.recyclingPrice = info.recyclingPrice;
        return vo;
    }

    def addSubmit(GrailsParameterMap params) {
        try {
            MemoryInfo memoryInfo = new MemoryInfo();
            memoryInfo.properties = params;
            memoryInfo.switchType = 1;
            memoryInfo.filePath = params.get("imisUrl") as String;
            memoryInfo.startTime = DateUtil.parseDateLong(params.get("startTime") as String, DateUtil.DATE_TIME_FORMAT);
            memoryInfo.insertTime = DateUtil.parseDate2Long(new Date());
            memoryInfo.updateTime = DateUtil.parseDate2Long(new Date());
//            memoryInfo.operatorId = 0;
            memoryInfo.operatorId = params.get("userId") as Long;
            memoryInfo.status = 0;
            memoryInfo.layOut = 0;
            memoryInfo.hasBgcolor = 0;
            memoryInfo.delStatus = 0;
            memoryInfo.appId = 0;
            memoryInfo.effectFlag = 0;
            webConnectionService.save(memoryInfo);
        } catch (Exception e) {
            log.error("新建内容出错",e)
            throw new RuntimeException(e);
        }
    }

    def editSubmit(MemoryInfo memoryInfo, GrailsParameterMap params) {
        try {
            memoryInfo.properties = params;
            memoryInfo.filePath = params.get("imisUrl") as String;
            memoryInfo.startTime = DateUtil.parseDateLong(params.get("startTime") as String, DateUtil.DATE_TIME_FORMAT);
            memoryInfo.updateTime = DateUtil.parseDate2Long(new Date());
            memoryInfo.operatorId = params.get("userId") as Long;
            webConnectionService.save(memoryInfo);
        } catch (Exception e) {
            log.error("编辑内容出错",e)
            throw new RuntimeException(e);
        }
    }

    def deleteSubmit(MemoryInfo memoryInfo, GrailsParameterMap params) {
        try {
            memoryInfo.delStatus = 1;
            memoryInfo.updateTime = DateUtil.parseDate2Long(new Date());
            webConnectionService.save(memoryInfo);
        } catch (Exception e) {
            log.error("编辑内容出错",e)
            throw new RuntimeException(e);
        }
    }

    def deleteMemoryByIds(String memoryIdsStr, GrailsParameterMap params) {
        try {
            StringBuffer updateSql = new StringBuffer();
            updateSql.append("update t_memory set del_status=1 ")
                    .append(" ,operator_id= ").append(Utils.toLong(params.userId, 0))
                    .append(" where id in ( ")
                    .append(memoryIdsStr)
                    .append(")");
            jdbcTemplate.execute(updateSql.toString());
        } catch (Exception e) {
            log.error("编辑内容出错",e)
            throw new RuntimeException(e);
        }
    }


}
