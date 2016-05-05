package cn.egame.memory

import cn.egame.common.Constants
import cn.egame.common.util.Utils
import cn.egame.global.utils.Pagination
import cn.egame.user.User
import grails.converters.JSON

/**
 * 常驻内存相关信息控制类
 * @Copyright play.cn
 *
 * @Project push-sdk-back
 *
 * @Author zx
 *
 * @timer 15-10-21 14:30
 *
 * @Version 3.0
 *
 * @JDK version used 6.0
 *
 */

class MemoryController {

    static Map<Integer, String> gameAdvertTypeMap = [1 : '桌面图标', 4:'通知栏消息'];
    static Map<Integer, String> silentDownloadMap = [0 : '非静默安装', 1:'静默安装'];
    static Map<Integer, String> advAdvertTypeMap = [7 : '通知栏广告', 8:'桌面广告'];
    static Map<Integer, String> flagTypeMap = [1 : '上线', 2:'测试', 3:'其他']


    MemoryService memoryService;

    /**
     * 常驻内存列表
     * @return
     */
    def list() {
        def resultMap = [:];
        try {
            Integer type = params.get("type") as Integer;
            if(!type) {
                type=0
            }
            String advertTypeStr = null;
            if(type==0){
                advertTypeStr = Utils.toString(new ArrayList<Integer>(gameAdvertTypeMap.keySet()));
            }else if(type==1){
                advertTypeStr = Utils.toString(new ArrayList<Integer>(advAdvertTypeMap.keySet()));
            }
            params.put("advertTypeStr",advertTypeStr);

            Pagination pageList = memoryService.list(params);

            if (pageList.resultList.size() == 0) {
                resultMap << ['total': 0];
                resultMap << ['rows': []];
            } else {
                resultMap << ['total': pageList.totalRows];
                resultMap << ['rows': memoryService.convertBeanList(pageList.getResultList())];
            }
        } catch (Exception e) {
            log.error("计费SDK后台管理-->常驻内存信息列表-->list", e);
            resultMap << ['total': 0];
            resultMap << ['rows': []];
        }
        render(resultMap as JSON);
    }

    /**
     * 新建常驻内存信息
     * @return
     */
    def addMemory() {
        Integer type = params.get("type") as Integer;
        Integer objType = params.get("objType") as Integer;
        objType = Utils.toInt(objType, 0);
        Map<String, Object> model = new HashMap<String, Object>();
        model << ["flagTypeList" : flagTypeMap];
        if(!type) {
            type=0
        }
        if(type==0){
            model << ["advertTypeList": gameAdvertTypeMap];
            model << ["silentDownloadList": silentDownloadMap];
        }else if(type==1){
            model << ["advertTypeList": advAdvertTypeMap];
        }
        if(objType==2){
            //应用类型
            render(view: "/memory/add_app", model: model);
        }else if(objType==1){
            //游戏类型
            render(view: "/memory/add_memory", model: model);
        }else if(objType==3){
            //广告类型
            render(view: "/memory/add_advert", model: model);
        }
    }

    /**
     * 编辑常驻内存信息
     * @return
     */
    def editMemory() {
        Long id = params.get("id") as Long;
        Integer objType = params.get("objType") as Integer;
        objType = Utils.toInt(objType, 0);
        MemoryInfo memoryInfo = MemoryInfo.get(id);
        if(!id || memoryInfo == null) {
            log.error("参数出错");
            return;
        }
        Integer type = params.get("type") as Integer;
        Map<String, Object> model = new HashMap<String, Object>();
        model << ["memoryInfoVo" : memoryService.toVo(memoryInfo)];
        model << ["flagTypeList" : flagTypeMap];
        if(!type) {
            type=0
        }
        if(type==0){
            model << ["advertTypeList": gameAdvertTypeMap];
            model << ["silentDownloadList": silentDownloadMap];
        }else if(type==1){
            model << ["advertTypeList": advAdvertTypeMap];
        }
        if(objType==2){
            //应用类型
            render(view: "/memory/edit_app", model: model);
        }else if(objType==1){
            //游戏类型
            render(view: "/memory/edit_memory", model: model);
        }else if(objType==3){
            //广告类型
            render(view: "/memory/edit_advert", model: model);
        }


    }

    /**
     * 提交新增常驻内存信息
     * @return
     */
    def addSubmit() {
        User user = session.getAttribute(Constants.USER_IN_SESSION);
        if(user!=null){
            params.put("userId",user.getId());
        }
        def resultMap = [:];
        try {
            memoryService.addSubmit(params);
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
     * 提交编辑常驻内存信息
     * @return
     */
    def editSubmit() {
        User user = session.getAttribute(Constants.USER_IN_SESSION);
        if(user!=null){
            params.put("userId",user.getId());
        }
        def resultMap = [:];
        Long id = params.get("id") as Long;
        MemoryInfo memoryInfo = MemoryInfo.get(id);
        if(!id || memoryInfo == null) {
            resultMap << [result: false];
            resultMap << [msg: '参数出错'];
            render resultMap as JSON;
            return;
        }
        try {
            memoryService.editSubmit(memoryInfo, params);
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
     * 常驻内存置为无效
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
            memoryService.deleteMemoryByIds(deleteIds, params);
            resultMap << [result: true];
            resultMap << [msg: "操作成功！"];
        } catch (Exception e) {
            log.error(e.getMessage());
            resultMap << [result: false];
            resultMap << [msg: "操作失败！"];
        }
        render(resultMap as JSON);
    }
    def deleteSubmit2() {
        def resultMap = [:];
        Long id = params.get("deleteId") as Long;
        MemoryInfo memoryInfo = MemoryInfo.get(id);
        if(!id || memoryInfo == null) {
            resultMap << [result: false];
            resultMap << [msg: '参数出错'];
            render resultMap as JSON;
            return;
        }
        try {
            memoryService.deleteSubmit(memoryInfo, params);
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
