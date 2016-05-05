package com.cloud

import cn.egame.common.PageVO
import cn.egame.common.util.Utils
import cn.egame.global.utils.Pagination
import grails.converters.JSON

class ParameterTagController {

    ParameterTagService parameterTagService;
    ParameterTagLinkService parameterTagLinkService;

    def queryList() {
        def resultMap = [:];
        try {
//            Pagination pageList = parameterTagService.queryList(params);
            PageVO pageVO = parameterTagService.queryList(params);
//            resultMap << ['total': pageVO.total];
//            resultMap << ['rows': preMemoryService.convertPreMemoryList(pageVO.results)];
            if (pageVO.results.size() == 0) {
                resultMap << ['total': 0];
                resultMap << ['rows': []];
            } else {
                resultMap << ['total': pageVO.total];
                resultMap << ['rows': parameterTagService.convertParameterTagList(pageVO.results)];
            }
        } catch (Exception e) {
            log.error("Blog后台管理-->标签管理-->list", e);
            resultMap << ['total': 0];
            resultMap << ['rows': []];
        }
        render(resultMap as JSON);
    }

    def list() {
        def resultMap = [:];
        try {
            Pagination pageList = parameterTagService.list(params);
            if (pageList.resultList.size() == 0) {
                resultMap << ['total': 0];
                resultMap << ['rows': []];
            } else {
                resultMap << ['total': pageList.totalRows];
                resultMap << ['rows': parameterTagService.convertBeanList(pageList.getResultList())];
            }
        } catch (Exception e) {
            log.error("Blog后台管理-->标签管理-->list", e);
            resultMap << ['total': 0];
            resultMap << ['rows': []];
        }
        render(resultMap as JSON);
    }

    /**
     * 新增标签
     * @return
     */
    def addTag() {
        Integer tagType = params.get("tagType") as Integer;
        Map<String, Object> model = new HashMap<String, Object>();
        tagType = Utils.toInt(tagType, 0);
        model << ["tagType" : tagType];
        render(view: "/cloud/tag/add_tag", model: model);
    }

    def addSubmit() {
        def resultMap = [:];
        try {
            parameterTagService.addSubmit(params);
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
     * 编辑标签
     * @return
     */
    def editTag() {
        Long id = params.get("id") as Long;
        ParameterTagInfo parameterTagInfo = ParameterTagInfo.get(id);
        if(!id || parameterTagInfo == null) {
            log.error("参数出错");
            return;
        }
        Map<String, Object> model = new HashMap<String, Object>();
//        model << ["parameterTagInfo" : parameterTagService.toVo(parameterTagInfo)];
        model << ["parameterTagInfo" : parameterTagInfo];
        render(view: "/cloud/tag/edit_tag", model:model);
    }

    /**
     * 提交编辑包名
     * @return
     */
    def editSubmit() {
        def resultMap = [:];
        Long id = params.get("id") as Long;
        ParameterTagInfo parameterTagInfo = ParameterTagInfo.get(id);
        if(!id || parameterTagInfo == null) {
            resultMap << [result: false];
            resultMap << [msg: '参数出错'];
            render resultMap as JSON;
            return;
        }
        try {
            parameterTagService.editSubmit(parameterTagInfo, params);
            resultMap << [result: true];
            resultMap << [msg: "操作成功！"];
        } catch (Exception e) {
            log.error(e.getMessage());
            resultMap << [result: false];
            resultMap << [msg: "操作失败！"];
        }
        render(resultMap as JSON);
    }

    def delTagSubmit() {
        def resultMap = [:];
        def deleteIds = params.get("deleteIds");
        if (deleteIds == null) {
            resultMap << [result: false];
            resultMap << [msg: '参数出错'];
            render resultMap as JSON;
            return;
        }
        try {
            parameterTagService.deleteParameterTagIds(deleteIds);
            parameterTagLinkService.deleteParameterTagLinkIds(deleteIds, params);

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
