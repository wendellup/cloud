package com.cloud

import cn.egame.common.Constants
import cn.egame.common.PageVO
import cn.egame.common.util.Utils
import cn.egame.memory.MemoryInfo
import cn.egame.user.User
import grails.converters.JSON

class ArticleController {

    ArticleService articleService;
    ParameterTagLinkService parameterTagLinkService;

    def queryList() {
        def resultMap = [:];
        try {
            PageVO pageVO = articleService.queryList(params);
            if (pageVO.results.size() == 0) {
                resultMap << ['total': 0];
                resultMap << ['rows': []];
            } else {
                resultMap << ['total': pageVO.total];
                resultMap << ['rows': articleService.convertArticleList(pageVO.results)];
            }
        } catch (Exception e) {
            log.error("Blog后台管理-->标签管理-->list", e);
            resultMap << ['total': 0];
            resultMap << ['rows': []];
        }
        render(resultMap as JSON);
    }

    /**
     * 跳转到新建文章页面
     * @return
     */
    def addArticle() {
        String tagType = params.get("tagType") as String;
        int tagTypeVal = Utils.toInt(tagType,0)
        ParameterApp parameterApp = ParameterApp.findById(tagTypeVal);
        List<ParameterTagInfo> parameterTagInfoList = ParameterTagInfo.findAllByTagTypeAndEnable(tagTypeVal, true);
        Map<String, Object> model = new HashMap<String, Object>();
        model << ["parameterApp" : parameterApp];
        model << ["parameterTagInfoList" : parameterTagInfoList];
        render(view: "/cloud/article/add_article", model: model);
    }

    /**
     * 新增文章提交
     * @return
     */
    def addSubmit() {
        User user = session.getAttribute(Constants.USER_IN_SESSION);
        if(user!=null){
            params.put("userId",user.getId());
        }
        def resultMap = [:];
        def selectedTagId = params.get("selectedTagId");
        try {
            //新增文章
            int articleId = articleService.addSubmit(params);
            //新增文章和标签关联信息
            parameterTagLinkService.addParameterTagLink(selectedTagId, articleId , params);

            resultMap << [result: true];
            resultMap << [msg: "操作成功！"];
        } catch (Exception e) {
            log.error(e.getMessage());
            resultMap << [result: false];
            resultMap << [msg: "操作失败！"];
        }
        render(resultMap as JSON);
    }

    /**
     * 批量删除文章(逻辑删),同时删除文章和标签的关联关系(物理删)
     */
    def deleteSubmit() {
        def resultMap = [:];
        def deleteIds = params.get("deleteIds");
        if (deleteIds == null) {
            resultMap << [result: false];
            resultMap << [msg: '参数出错'];
            render resultMap as JSON;
            return;
        }
        User user = session.getAttribute(Constants.USER_IN_SESSION);
        if(user!=null){
            params.put("userId",user.getId());
        }
        try {
            articleService.deleteArticleByIds(deleteIds, params);
            parameterTagLinkService.deleteParameterTagLinkByBusinessIds(deleteIds, params);
            resultMap << [result: true];
            resultMap << [msg: "操作成功！"];
        } catch (Exception e) {
            log.error(e.getMessage());
            resultMap << [result: false];
            resultMap << [msg: "操作失败！"];
        }
        render(resultMap as JSON);
    }

    /**
     * 跳转到编辑文章界面
     * @return
     */
    def editArticle() {
        Integer articleId = params.get("articleId") as Integer;
        String tagType = params.get("tagType") as String;
        int tagTypeVal = Utils.toInt(tagType,0)
        ParameterApp parameterApp = ParameterApp.findById(tagTypeVal);
        Article article = Article.findById(articleId);
        if(parameterApp==null || article==null){
            throw new RuntimeException("parameterApp==null || article==null");
        }
        List<ParameterTagInfo> parameterTagInfoList = ParameterTagInfo.findAllByTagTypeAndEnable(tagTypeVal, true);
        List<ParameterTagInfo> articleTagLinkList = ParameterTagLink.findAllByBusinessIdAndEnable(articleId, true);
        Map<String, Object> model = new HashMap<String, Object>();
        model << ["parameterApp" : parameterApp];
        model << ["article" : article];
        model << ["parameterTagInfoList" : parameterTagInfoList];
        model << ["articleTagLinkList" : articleTagLinkList];
        render(view: "/cloud/article/edit_article", model: model);
    }

    /**
     * 编辑文章提交
     * @return
     */
    def editSubmit() {
        User user = session.getAttribute(Constants.USER_IN_SESSION);
        if(user!=null){
            params.put("userId",user.getId());
        }
        def selectedTagId = params.get("selectedTagId");
        def resultMap = [:];
        Integer articleId = params.get("articleId") as Integer;
        Article article = Article.findById(articleId);
        if(!articleId || article == null) {
            resultMap << [result: false];
            resultMap << [msg: '参数出错'];
            render resultMap as JSON;
            return;
        }
        try {
            // 1.编辑的文章信息提交
            articleService.editSubmit(article, params);
            // 2.删除之前的文章标签关联信息
            parameterTagLinkService.deleteParameterTagLinkByBusinessIds(String.valueOf(articleId), params);
            // 3.新增文章和标签关联信息
            parameterTagLinkService.addParameterTagLink(selectedTagId, articleId , params);
            resultMap << [result: true];
            resultMap << [msg: "操作成功！"];
        } catch (Exception e) {
            log.error(e.getMessage());
            resultMap << [result: false];
            resultMap << [msg: "操作失败！"];
        }
        render(resultMap as JSON);
    }
}
