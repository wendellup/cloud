package com.cloud

import cn.egame.common.PageVO
import cn.egame.common.WebConnectionService
import cn.egame.common.util.Utils
import cn.egame.global.constant.AppConstants
import cn.egame.memory.MemoryInfo
import cn.egame.utils.DateUtil
import org.apache.commons.collections.CollectionUtils
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import org.springframework.jdbc.core.JdbcTemplate

class ArticleService {

    JdbcTemplate jdbcTemplate;
    WebConnectionService webConnectionService;

    def queryList(GrailsParameterMap params) {
        def currPage = params.page ? Integer.valueOf(params.page) : 0;
        def pageSize = params.rows ? Integer.valueOf(params.rows) : AppConstants.DEFAULT_PAGE_SIZE;
        def offset = (currPage - 1) * pageSize;
        params.max = pageSize;
        params.offset = offset;
        StringBuffer hql = new StringBuffer("select t from Article as t where t.enable=1 and paramId=:tagType");
        def paramMap = [:];
        String tagType = params.get("tagType") as String;
        paramMap.put('tagType', Utils.toInt(tagType,0));
        if (params.title) {
            hql.append(" and t.title like :title");
            paramMap.put('title', '%' + params.title.trim() + '%');
        }

        hql.append(" order by t.id desc");
        log.info(hql.toString());
        def results = Article.executeQuery(hql.toString(), paramMap, [max: params.max, offset: params.offset]);
        def totalResutls = Article.executeQuery(hql.toString(), paramMap);
        PageVO page = new PageVO(totalResutls.size(), results);
        return page;
    }

    def convertArticleList(List<Article> articleList) {
        List<TagVO> resultList = new ArrayList<TagVO>();
        for (Article info : articleList) {
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
        ArticleVO vo = new ArticleVO();
        vo.id = map.get("id") as Integer;
        vo.paramId = map.get("param_id") as String;
        vo.title = map.get("title") as String;
        vo.content = map.get("content") as String;
        vo.enable = map.get("enable") as Boolean;
        vo.startTime = map.get("start_time") as Long;
        vo.endTime = map.get("end_time") as Long;
//        vo.insertTime = map.get("insert_time") as Long;
//        vo.updateTime = map.get("update_time") as Long;
        vo.insertTime = DateUtil.parseDate2String((Long)map.get("insert_time"), DateUtil.DATE_TIME_FORMAT);
        vo.updateTime = DateUtil.parseDate2String((Long)map.get("update_time"), DateUtil.DATE_TIME_FORMAT);
        vo.operatorId = map.get("operator_id") as Long;
        return vo;
    }

    def toVo(Article info) {
        ArticleVO vo = new ArticleVO();
        vo.id = info.id;
        vo.paramId = info.paramId;
        vo.title = info.title;
        vo.content = info.content;
        vo.enable = info.enable;
        vo.startTime = info.startTime;
        vo.endTime = info.endTime;
        vo.insertTime = DateUtil.parseDate2String(info.insertTime, DateUtil.DATE_TIME_FORMAT);
        vo.updateTime = DateUtil.parseDate2String(info.updateTime, DateUtil.DATE_TIME_FORMAT);
        vo.operatorId = info.operatorId;
        return vo;
    }

    def addSubmit(GrailsParameterMap params) {
        try {
            Article article = new Article();
            article.properties = params;
            article.enable = 1;
            article.insertTime = DateUtil.parseDate2Long(new Date());
            article.updateTime = DateUtil.parseDate2Long(new Date());
            article.operatorId = params.get("userId") as Long;
            webConnectionService.save(article);
            return article.id;
        } catch (Exception e) {
            log.error("新建内容出错",e)
            throw new RuntimeException(e);
        }
        return 0;
    }

    def deleteArticleByIds(String articleIdsStr, GrailsParameterMap params) {
        try {
            StringBuffer updateSql = new StringBuffer();
            updateSql.append("update t_article set enable=0 ")
                    .append(" ,operator_id= ").append(Utils.toLong(params.userId, 0))
                    .append(" where id in ( ")
                    .append(articleIdsStr)
                    .append(")");
            jdbcTemplate.execute(updateSql.toString());
        } catch (Exception e) {
            log.error("编辑内容出错",e)
            throw new RuntimeException(e);
        }
    }

    def editSubmit(Article article, GrailsParameterMap params) {
        try {
            article.properties = params;
            article.updateTime = DateUtil.parseDate2Long(new Date());
            article.operatorId = params.get("userId") as Long;
            webConnectionService.save(article);
        } catch (Exception e) {
            log.error("编辑内容出错",e)
            throw new RuntimeException(e);
        }
    }
}
