package cn.egame.charge

import cn.egame.common.Constants
import cn.egame.global.utils.Pagination
import cn.egame.sys.ParameterSysInfo
import cn.egame.user.User
import grails.converters.JSON

/**
 * SDK相关信息控制类
 * @Copyright play.cn
 *
 * @Project push-sdk-back
 *
 * @Author zx
 *
 * @timer 15-10-19 15:30
 *
 * @Version 3.0
 *
 * @JDK version used 6.0
 *
 */

class ChargeSdkController {

    ChargeSdkService chargeSdkService;

    /**
     * sdk列表
     * @return
     */
    def list() {
        def resultMap = [:];
        try {
            Pagination pageList = chargeSdkService.list(params);

            if (pageList.resultList.size() == 0) {
                resultMap << ['total': 0];
                resultMap << ['rows': []];
            } else {
                resultMap << ['total': pageList.totalRows];
                resultMap << ['rows': chargeSdkService.convertBeanList(pageList.getResultList())];
            }
            ParameterSysInfo parameterSysInfo = ParameterSysInfo.findByName("last_execute_time");
            resultMap << ['last_execute_time': parameterSysInfo.value];
        } catch (Exception e) {
            log.error("计费SDK后台管理-->SDK信息列表-->list", e);
            resultMap << ['total': 0];
            resultMap << ['rows': []];
        }
        render(resultMap as JSON);
    }

    /**
     * 新增sdk信息
     * @return
     */
    def addSubmit() {
        User user = session.getAttribute(Constants.USER_IN_SESSION);
        if(user!=null){
            params.put("userId",user.getId());
        }
        def resultMap = [:];
        try {
            chargeSdkService.addSubmit(params);
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
     * 编辑sdk信息
     * @return
     */
    def editSubmit() {
        User user = session.getAttribute(Constants.USER_IN_SESSION);
        if(user!=null){
            params.put("userId",user.getId());
        }
        def resultMap = [:];
        Integer id = params.get("id") as Integer;
        HallFile hallFile = HallFile.get(id);
        if(!id || hallFile == null) {
            resultMap << [result: false];
            resultMap << [msg: '参数出错'];
            render resultMap as JSON;
            return;
        }
        try {
            chargeSdkService.editSubmit(hallFile, params);
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
