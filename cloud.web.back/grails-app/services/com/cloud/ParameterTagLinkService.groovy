package com.cloud

import cn.egame.common.WebConnectionService
import cn.egame.common.util.Utils
import cn.egame.utils.DateUtil
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import org.springframework.jdbc.core.JdbcTemplate

class ParameterTagLinkService {

    JdbcTemplate jdbcTemplate;
    WebConnectionService webConnectionService;

    def deleteParameterTagLinkIds(String tagIdsStr, GrailsParameterMap params) {
        try {
            StringBuffer updateSql = new StringBuffer();
            updateSql.append("delete from t_parameter_tag_link where tag_id in ( ")
                    .append(tagIdsStr)
                    .append(")");
            jdbcTemplate.execute(updateSql.toString());
        } catch (Exception e) {
            log.error("编辑内容出错",e)
            throw new RuntimeException(e);
        }
    }

    def deleteParameterTagLinkByBusinessIds(String businessIdStr, GrailsParameterMap params) {
        try {
            StringBuffer updateSql = new StringBuffer();
            updateSql.append("delete from t_parameter_tag_link where business_id in ( ")
                    .append(businessIdStr)
                    .append(")");
            jdbcTemplate.execute(updateSql.toString());
        } catch (Exception e) {
            log.error("编辑内容出错",e)
            throw new RuntimeException(e);
        }
    }

    def addParameterTagLink(String selectedTagId, int articleId, GrailsParameterMap params) {
        try {
            String[] tagIdAry = selectedTagId.split(",");
            for(String tagId : tagIdAry){
                int tagIdVal = Utils.toInt(tagId, 0);
                if(tagIdVal!=0){
                    ParameterTagLink  parameterTagLink = new ParameterTagLink();
                    parameterTagLink.businessId = articleId;
                    parameterTagLink.tagId = tagIdVal;
                    // TODO
                    parameterTagLink.enable = 1;
                    parameterTagLink.updateTime = DateUtil.parseDate2Long(new Date());
                    parameterTagLink.operatorId = params.get("userId") as Long;
                    webConnectionService.save(parameterTagLink);
                }

            }
        } catch (Exception e) {
            log.error("编辑内容出错",e)
            throw new RuntimeException(e);
        }
    }
}
