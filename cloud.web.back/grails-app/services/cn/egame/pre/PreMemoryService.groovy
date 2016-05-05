package cn.egame.pre

import cn.egame.common.Constants
import cn.egame.common.PageVO
import cn.egame.common.WebConnectionService
import cn.egame.common.util.Utils
import cn.egame.global.constant.AppConstants
import cn.egame.global.utils.Pagination
import cn.egame.memory.MemoryVo
import cn.egame.utils.DateUtil
import org.apache.commons.collections.CollectionUtils
import org.apache.commons.lang.StringUtils
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import org.springframework.jdbc.core.JdbcTemplate

import java.util.regex.Pattern

class PreMemoryService {

    JdbcTemplate jdbcTemplate;
    WebConnectionService webConnectionService;

    def list(GrailsParameterMap params) {
        def currPage = params.page ? Integer.valueOf(params.page) : 1;
        def pageSize = params.rows ? Integer.valueOf(params.rows) : AppConstants.DEFAULT_PAGE_SIZE;
        StringBuffer querySql = new StringBuffer();

        querySql.append("select m.id, m.start_time,m.end_time, m.title, m.description, m.link_url, m.insert_time, m.update_time, m.del_status ")
                .append(" , m.game_name, m.game_download_url, m.game_size, m.package_name, m.remark, m.obj_type, m.imsi_num, m.own_name, m.pricing_way, m.activation_condition, m.recycling_price ")
                .append(" , m.advert_type, m.flag, m.silent_download ")
                .append(" from t_pre_memory m where m.del_status=0");

        Integer objType = params.get("objType") as Integer;
        if(Utils.toInt(objType,0)!=0){
            querySql.append(" and obj_type = " +objType);
        }
        querySql.append(" order by m.id desc");

        log.info(querySql);

        Pagination pageList = new Pagination(querySql.toString(), currPage, pageSize, jdbcTemplate);
        return pageList;
    }

    def convertPreMemoryList(List<PreMemory> preMemoryList) {
        List<PreMemoryVO> resultList = new ArrayList<PreMemoryVO>();
        for (PreMemory info : preMemoryList) {
            resultList.add(toVo(info));
        }
        return resultList;
    }

    def convertBeanList(List<Map<String, Object>> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<PreMemoryVO> resultList = new ArrayList<PreMemoryVO>();
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
        PreMemoryVO vo = new PreMemoryVO();
        vo.id = map.get("id") as Long;
        vo.startTime = DateUtil.parseDate2String(map.get("start_time") as Long, DateUtil.DATE_TIME_FORMAT);
        vo.title =  map.get("title") as String;
        vo.gameDownloadUrl = map.get("game_download_url") as String;
        vo.realGameDownloadUrl = downloadHeadPath + vo.gameDownloadUrl;
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
        vo.remark = map.get("remark") as String;
        vo.objType =map.get("obj_type") as Integer;
        switch (vo.objType) {//1:游戏,2:应用,3:广告
            case 1 :
                vo.delStatusName = "游戏";
                break;
            case 2 :
                vo.delStatusName = "应用";
                break;
            case 3 :
                vo.delStatusName = "广告";
                break;
        }
        vo.ownName = map.get("own_name") as String;
        vo.pricingWay = map.get("pricing_way") as String;
        vo.activationCondition = map.get("activation_condition") as String;
        vo.recyclingPrice = map.get("recycling_price") as Double;

        vo.advertType = map.get("advert_type") as Integer;
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
        vo.silentDownload = map.get("silent_download") as Integer;
        if(vo.silentDownload==0){
            vo.silentDownloadName="非静默";
        }else if(vo.silentDownload==1){
            vo.silentDownloadName="静默";
        }
        return vo;
    }

    def addSubmit(GrailsParameterMap params) {
        try {
            PreMemory preMemory = new PreMemory();
//            MemoryInfo memoryInfo = new MemoryInfo();
            preMemory.properties = params;
//            memoryInfo.filePath = params.get("imisUrl") as String;
            preMemory.startTime = DateUtil.parseDateLong(params.get("startTime") as String, DateUtil.DATE_TIME_FORMAT);
            preMemory.insertTime = DateUtil.parseDate2Long(new Date());
            preMemory.updateTime = DateUtil.parseDate2Long(new Date());
            preMemory.operatorId = params.get("userId") as Long;
            preMemory.status = 0;
            preMemory.delStatus = 0;
            webConnectionService.save(preMemory);
        } catch (Exception e) {
            log.error("新建内容出错",e)
            throw new RuntimeException(e);
        }
    }

    def toVo(PreMemory info) {
        Properties properties = Utils.getProperties("common.properties");
        String downloadHeadPath = properties.get("download_url");
        PreMemoryVO vo = new PreMemoryVO();
        vo.id = info.id;
        vo.gameDownloadUrl = info.gameDownloadUrl;
        vo.realGameDownloadUrl = downloadHeadPath + vo.gameDownloadUrl;
        vo.startTime = DateUtil.parseDate2String(info.startTime, DateUtil.DATE_TIME_FORMAT);
        vo.endTime = info.endTime;
        vo.title = info.title;
        vo.description = info.description;
        vo.linkUrl = info.linkUrl;
        vo.insertTime = info.insertTime;
        vo.updateTime = info.updateTime;
        vo.operatorId = info.operatorId;
        vo.status = info.status;
        vo.delStatus = info.delStatus;
        switch (vo.delStatus) {
            case 0 :
                vo.delStatusName = "正常";
                break;
            case 1 :
                vo.delStatusName = "删除";
                break;
        }
        vo.gameName = info.gameName;
        vo.gameIcon = info.gameIcon;
        vo.realGameIcon = downloadHeadPath + vo.gameIcon;
        vo.gameSize = info.gameSize;
        vo.packageName = info.packageName;
        vo.imsiNum = info.imsiNum;
        vo.remark = info.remark;
        vo.objType = info.objType;
        vo.ownName = info.ownName;
        vo.pricingWay = info.pricingWay;
        vo.activationCondition = info.activationCondition;
        vo.recyclingPrice = info.recyclingPrice;

        vo.advertType = info.advertType;
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
        vo.silentDownload = info.silentDownload;
        if(vo.silentDownload==0){
            vo.silentDownloadName="非静默";
        }else if(vo.silentDownload==1){
            vo.silentDownloadName="静默";
        }
        return vo;
    }

    def editSubmit(PreMemory preMemory, GrailsParameterMap params) {
        try {
            preMemory.properties = params;
            preMemory.startTime = DateUtil.parseDateLong(params.get("startTime") as String, DateUtil.DATE_TIME_FORMAT);
            preMemory.updateTime = DateUtil.parseDate2Long(new Date());
            preMemory.operatorId = params.get("userId") as Long;
            webConnectionService.save(preMemory);
        } catch (Exception e) {
            log.error("编辑内容出错",e)
            throw new RuntimeException(e);
        }
    }

    def deleteMemoryByIds(String memoryIdsStr, GrailsParameterMap params) {
        try {
            StringBuffer updateSql = new StringBuffer();
            updateSql.append("update t_pre_memory set del_status=1 ")
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

    /**
     * 根据查询条件获取预配置列表
     * @return PageVO 分页对象
     */
    def queryListByCondition(def params) {
        def currPage = params.page ? Integer.valueOf(params.page) : 0;
        def pageSize = params.rows ? Integer.valueOf(params.rows) : Constants.PAGE_SIZE;
        def offset = (currPage - 1) * pageSize;
        params.max = pageSize;
        params.offset = offset;

        StringBuffer hql = new StringBuffer("select a from PreMemory as a where 1=1 and a.delStatus =0 ");
        def paramMap = [:]
        def orderByIdField = false;
        def orderByNameField = false;

        //关联游戏列表的查询条件，一个文本框内用户输入多个ID或游戏名称，用逗号隔开
        String[] ids = null;
        if (StringUtils.isNotEmpty(params.queryStr)) {
            Pattern pattern = Pattern.compile("[0-9]*");
            ids = params.queryStr.replace("，", ",").split(",");
            if (pattern.matcher(params.queryStr.replace("，", "").replace(",", "")).matches()) {
                Long[] idsLong = new Long[ids.length];
                for (int i = 0; i < ids.length; i++) {
                    idsLong[i] = Long.parseLong(ids[i]);
                }
                hql.append(" and a.id in :ids");
                paramMap.put('ids', idsLong);
                orderByIdField = true;
            }
            else {
                if (ids.length == 1) {
                    hql.append(" and a.title like :title");
                    paramMap.put('title', '%' + ids[0].trim() + '%')
                }
                else {
                    hql.append(" and a.title in :titleList");
                    paramMap.put('titleList', ids);
                    orderByNameField = true;
                }
            }
        }

        if (params.objType) {
            hql.append(" and a.objType=:objType ");
            paramMap.put('objType',Integer.parseInt(params.objType));
        }

        //关联页面查询时，输入多个游戏ID或游戏名称，查询结果要按输入ID或名称的顺序展示。利用MYSQL order by FIELD关键字实现。
//        if (orderByIdField) {
//            hql.append(" order by FIELD(a.id, " + ids.toString().substring(1, ids.toString().length() - 1) + ")");
//        }
//        else if (orderByNameField) {
//            String gNameStr = '';
//            for (int i = 0; i < ids.length; i++) {
//                gNameStr = gNameStr + "','" + ids[i];
//            }
//            hql.append(" order by FIELD(a.title, " + gNameStr.toString().substring(2) + "')");
//        }
//        else if (params.sort && params.order) {
//            hql.append(" order by a." + params.sort + " " + params.order)
//        }
//        else {
//            hql.append(" order by a.updateTime desc");
//        }
        hql.append(" order by a.id desc");
        log.info(hql.toString());

        def results = PreMemory.executeQuery(hql.toString(), paramMap, [max: params.max, offset: params.offset]);
        def totalResutls = PreMemory.executeQuery(hql.toString(), paramMap)
//        Pagination pageList = new Pagination(querySql.toString(), currPage, pageSize, jdbcTemplate);

        PageVO page = new PageVO(totalResutls.size(), results);
        return page;

    }
}
