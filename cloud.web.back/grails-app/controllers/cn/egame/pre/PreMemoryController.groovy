package cn.egame.pre

import cn.egame.common.PageVO
import cn.egame.common.util.Utils
import cn.egame.global.utils.Pagination
import cn.egame.user.User
import grails.converters.JSON
import cn.egame.common.Constants

class PreMemoryController {

    PreMemoryService preMemoryService

    static Map<Integer, String> gameAdvertTypeMap = [1 : '桌面图标', 4:'通知栏消息'];
    static Map<Integer, String> silentDownloadMap = [0 : '非静默安装', 1:'静默安装'];
    static Map<Integer, String> advAdvertTypeMap = [7 : '通知栏广告', 8:'桌面广告'];
    static Map<Integer, String> flagTypeMap = [1 : '上线', 2:'测试', 3:'其他']

    /**
     * 常驻内存列表
     * @return
     */
    def list() {
//        System.out.println("currentUser->"+UserHolder.getCurrentUser());
        def resultMap = [:];
        try {
            Pagination pageList = preMemoryService.list(params);
            if (pageList.resultList.size() == 0) {
                resultMap << ['total': 0];
                resultMap << ['rows': []];
            } else {
                resultMap << ['total': pageList.totalRows];
                resultMap << ['rows': preMemoryService.convertBeanList(pageList.getResultList())];
            }
        } catch (Exception e) {
            log.error("计费SDK后台管理-->常驻内存信息预配置列表-->list", e);
            resultMap << ['total': 0];
            resultMap << ['rows': []];
        }
        render(resultMap as JSON);
    }

    /**
     * 新增录入需求
     * @return
     */
    def addMemory() {
        Integer type = params.get("type") as Integer;
        Integer objType = params.get("objType") as Integer;
        objType = Utils.toInt(objType, 0);
        type = Utils.toInt(type, 0);
        Map<String, Object> model = new HashMap<String, Object>();
        model << ["flagTypeList" : flagTypeMap];
        if(type==0){
            model << ["advertTypeList": gameAdvertTypeMap];
            model << ["silentDownloadList": silentDownloadMap];
        }else if(type==1){
            model << ["advertTypeList": advAdvertTypeMap];
        }
        if(objType==2){
            //应用类型
            render(view: "/preMemory/add_app", model: model);
        }else if(objType==1){
            //游戏类型
            render(view: "/preMemory/add_game", model: model);
        }else if(objType==3){
            //广告类型
            render(view: "/preMemory/add_advert", model: model);
        }
    }

    /**
     * 提交需求录入信息
     * @return
     */
    def addSubmit() {
//        HttpSession session = request.getSession(false);
//        params.setAttribute("userId",session.getAttribute("userId"));
//        User user = UserHolder.getCurrentUser();
        User user = session.getAttribute(Constants.USER_IN_SESSION);
        if(user!=null){
            params.put("userId",user.getId());
        }
        def resultMap = [:];
        try {
            preMemoryService.addSubmit(params);
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
     * 编辑需求录入信息
     * @return
     */
    def editMemory() {
        Integer type = params.get("type") as Integer;
        type = Utils.toInt(type, 0);

        Long id = params.get("id") as Long;
        Integer objType = params.get("objType") as Integer;
        objType = Utils.toInt(objType, 0);
        PreMemory preMemory = PreMemory.get(id);
        if(!id || preMemory == null) {
            log.error("参数出错");
            return;
        }
        Map<String, Object> model = new HashMap<String, Object>();
        if(type==0){
            model << ["advertTypeList": gameAdvertTypeMap];
            model << ["silentDownloadList": silentDownloadMap];
        }else if(type==1){
            model << ["advertTypeList": advAdvertTypeMap];
        }
        model << ["preMemoryInfoVo" : preMemoryService.toVo(preMemory)];
        model << ["flagTypeList" : flagTypeMap];

        if(objType==2){
            //应用类型
            render(view: "/preMemory/edit_app", model: model);
        }else if(objType==1){
            //游戏类型
            render(view: "/preMemory/edit_game", model: model);
        }else if(objType==3){
            //广告类型
            render(view: "/preMemory/edit_advert", model: model);
        }
    }

    /**
     * 提交需求录入信息
     * @return
     */
    def editSubmit() {
        User user = session.getAttribute(Constants.USER_IN_SESSION);
        if(user!=null){
            params.put("userId",user.getId());
        }
        def resultMap = [:];
        Long id = params.get("id") as Long;
        PreMemory preMemory = PreMemory.get(id);
//        MemoryInfo memoryInfo = MemoryInfo.get(id);
        if(!id || preMemory == null) {
            resultMap << [result: false];
            resultMap << [msg: '参数出错'];
            render resultMap as JSON;
            return;
        }
        try {
//            memoryService.editSubmit(memoryInfo, params);
            preMemoryService.editSubmit(preMemory, params);
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
     * 需求录入信息置为无效
     */
    def deleteSubmit() {
        User user = session.getAttribute(Constants.USER_IN_SESSION);
        if(user!=null){
            params.put("userId",user.getId());
        }
        def resultMap = [:];
        def deleteIds = params.get("deleteIds");
        if (deleteIds == null) {
            resultMap << [result: false];
            resultMap << [msg: '参数出错'];
            render resultMap as JSON;
            return;
        }
        try {
            preMemoryService.deleteMemoryByIds(deleteIds, params);
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
     * 跳转到预配置信息查询页面
     * @return 页面
     */
    def serviceMemoryList() {
        def objType = params.int("objType");
        if(objType==1){
//            render(view: "linkMemoryList", model: ["objType" : objType]);
            render(view: "linkGameQuery", model: ["objType" : objType]);
        }else if(objType==2){
//            render(view: "linkMemoryList", model: ["objType" : objType]);
            render(view: "linkAppQuery", model: ["objType" : objType]);
        }else if(objType==3){
//            render(view: "linkMemoryList", model: ["objType" : objType]);
            render(view: "linkAdvertQuery", model: ["objType" : objType]);
        }

    }

    /**
     * @Description: 按条件查询预配置列表方法
     * @return
     * @Author yc
     * @Create Date 2016.3.23
     * @Modified by none
     * @Modified Date
     */
    def queryGamesByCondition() {
        def resultMap = [:];
        try {
//            Pagination pageList = preMemoryService.list(params);
            PageVO pageVO = preMemoryService.queryListByCondition(params);
            resultMap << ['total': pageVO.total];
            resultMap << ['rows': preMemoryService.convertPreMemoryList(pageVO.results)];
            render(resultMap as JSON);

        } catch (Exception e) {
            log.error("计费SDK后台管理-->常驻内存信息预配置列表-->list", e);
            resultMap << ['total': 0];
            resultMap << ['rows': []];
        }
        render(resultMap as JSON);
    }

    def getPreMemory() {
        Long id = params.get("id") as Long;
        PreMemory preMemory = PreMemory.get(id);
        PreMemoryVO preMemoryVO = preMemoryService.toVo(preMemory);
//        def map = [:];
//        def id = params.get("id");
//        if (id) {
//            String iconUrl = gameService.getIconUrlByGameId(appId, loginUserId, Utils.toInt(gameId, 0));
//            String classifyName = parameterAppService.getClassifyName(Utils.toInt(gameId, 0));
//            PreMemoryVO preMemoryVO =
//            map << ['iconUrl': iconUrl];
//            map << ['classifyName': classifyName];
            render preMemoryVO as JSON;
//        }
    }

}
