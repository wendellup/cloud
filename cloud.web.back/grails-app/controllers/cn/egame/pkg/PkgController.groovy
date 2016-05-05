package cn.egame.pkg

import cn.egame.global.utils.Pagination
import grails.converters.JSON

class PkgController {

    PkgService pkgService;

    /**
     * 常驻内存列表
     * @return
     */
    def list() {
        def resultMap = [:];
        try {
            Pagination pageList = pkgService.list(params);

            if (pageList.resultList.size() == 0) {
                resultMap << ['total': 0];
                resultMap << ['rows': []];
            } else {
                resultMap << ['total': pageList.totalRows];
                resultMap << ['rows': pkgService.convertBeanList(pageList.getResultList())];
            }
        } catch (Exception e) {
            log.error("计费SDK后台管理-->常驻内存信息列表-->list", e);
            resultMap << ['total': 0];
            resultMap << ['rows': []];
        }
        render(resultMap as JSON);
    }

    /**
     * 新增包名采集
     * @return
     */
    def addPkg() {
        render(view: "/pkg/add_pkg");
    }

    def addSubmit() {
        def resultMap = [:];
        try {
            pkgService.addSubmit(params);
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
     * 编辑常驻内存信息
     * @return
     */
    def editPkg() {
        Long id = params.get("id") as Long;
        PackageNameManagerInfo pkgInfo = PackageNameManagerInfo.get(id);
        if(!id || pkgInfo == null) {
            log.error("参数出错");
            return;
        }
        Map<String, Object> model = new HashMap<String, Object>();
        model << ["pkgInfo" : pkgService.toVo(pkgInfo)];
        render(view: "/pkg/edit_pkg", model:model);
    }

    /**
     * 提交编辑包名
     * @return
     */
    def editSubmit() {
        def resultMap = [:];
        Long id = params.get("id") as Long;
        PackageNameManagerInfo pkgInfo = PackageNameManagerInfo.get(id);
        if(!id || pkgInfo == null) {
            resultMap << [result: false];
            resultMap << [msg: '参数出错'];
            render resultMap as JSON;
            return;
        }
        try {
            pkgService.editSubmit(pkgInfo, params);
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
     * 包名置为无效
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
        try {
            pkgService.deleteMemoryByIds(deleteIds);
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
