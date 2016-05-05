package com.cloud

import cn.egame.common.PageVO
import cn.egame.common.WebConnectionService
import cn.egame.common.util.Utils
import cn.egame.global.constant.AppConstants
import cn.egame.global.utils.Pagination
import org.apache.commons.collections.CollectionUtils
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import org.springframework.jdbc.core.JdbcTemplate

class ParameterTagService {

    JdbcTemplate jdbcTemplate;
    WebConnectionService webConnectionService;

    def list(GrailsParameterMap params) {
        def currPage = params.page ? Integer.valueOf(params.page) : 1;
        def pageSize = params.rows ? Integer.valueOf(params.rows) : AppConstants.DEFAULT_PAGE_SIZE;
        StringBuffer querySql = new StringBuffer();

        querySql.append("select t.id, t.tag_name, t.tag_type, t.remark ")
                .append(" from t_parameter_tag t where enable = 1 ");
        Integer tagType = params.get("tagType") as Integer;
        if(Utils.toInt(tagType,0)!=0){
            querySql.append(" and tag_type = " +tagType);
        }
        querySql.append(" order by t.id desc");
        log.info(querySql);

        Pagination pageList = new Pagination(querySql.toString(), currPage, pageSize, jdbcTemplate);
        return pageList;
    }

    def queryList(GrailsParameterMap params) {
//        def currPage = params.page ? Integer.valueOf(params.page) : 1;
//        def pageSize = params.rows ? Integer.valueOf(params.rows) : AppConstants.DEFAULT_PAGE_SIZE;
//        StringBuffer querySql = new StringBuffer();

        def currPage = params.page ? Integer.valueOf(params.page) : 0;
        def pageSize = params.rows ? Integer.valueOf(params.rows) : AppConstants.DEFAULT_PAGE_SIZE;
        def offset = (currPage - 1) * pageSize;
        params.max = pageSize;
        params.offset = offset;
        StringBuffer hql = new StringBuffer("select t from ParameterTagInfo as t where t.enable=1 and tagType=:tagType");


        def paramMap = [:];
        String tagType = params.get("tagType") as String;
        paramMap.put('tagType', Utils.toInt(tagType,0));
        if (params.tagName) {
            hql.append(" and t.tagName like :tagName");
            paramMap.put('tagName', '%' + params.tagName.trim() + '%');
        }
        if (params.remark) {
            hql.append(" and t.remark like :remark");
            paramMap.put('remark', '%' + params.remark.trim() + '%');
        }

        hql.append(" order by t.id desc");
        log.info(hql.toString());
        def results = ParameterTagInfo.executeQuery(hql.toString(), paramMap, [max: params.max, offset: params.offset]);
        def totalResutls = ParameterTagInfo.executeQuery(hql.toString(), paramMap);
        PageVO page = new PageVO(totalResutls.size(), results);
        return page;
    }

    def convertParameterTagList(List<ParameterTagInfo> parameterTagInfoList) {
        List<TagVO> resultList = new ArrayList<TagVO>();
        for (ParameterTagInfo info : parameterTagInfoList) {
            resultList.add(toVo(info));
        }
        return resultList;
    }

    def convertBeanList(List<Map<String, Object>> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<TagVO> resultList = new ArrayList<TagVO>();
        list.each { info ->
            resultList.add(this.convertBean(info));
        }
        return resultList;
    }

    def convertBean(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        TagVO vo = new TagVO();
        vo.id = map.get("id") as Long;
        vo.tagName = map.get("tag_name") as String;
        vo.tagType = map.get("tag_type") as Integer;
        vo.remark = map.get("remark") as String;
        return vo;
    }

    def addSubmit(GrailsParameterMap params) {
        try {
            ParameterTagInfo parameterTagInfo = new ParameterTagInfo();
            parameterTagInfo.properties = params;
            parameterTagInfo.enable = 1;
            webConnectionService.save(parameterTagInfo);
        } catch (Exception e) {
            log.error("新建内容出错",e)
            throw new RuntimeException(e);
        }
    }

    def toVo(ParameterTagInfo info) {
        TagVO vo = new TagVO();
        vo.id = info.id;
        vo.tagName = info.tagName;
        vo.tagType = info.tagType;
        vo.remark = info.remark;
        return vo;
    }

    def editSubmit(ParameterTagInfo parameterTagInfo, GrailsParameterMap params) {
        try {
            parameterTagInfo.properties = params;
            webConnectionService.save(parameterTagInfo);
        } catch (Exception e) {
            log.error("编辑内容出错",e)
            throw new RuntimeException(e);
        }
    }

    def deleteParameterTagIds(String tagIdsStr) {
        try {
            StringBuffer updateSql = new StringBuffer();
            updateSql.append("update t_parameter_tag set enable=0 ")
//                    .append(" ,operator_id= ").append(Utils.toLong(params.userId, 0))
                    .append(" where id in ( ")
                    .append(tagIdsStr)
                    .append(")");
            jdbcTemplate.execute(updateSql.toString());
        } catch (Exception e) {
            log.error("编辑内容出错",e)
            throw new RuntimeException(e);
        }
    }

}
