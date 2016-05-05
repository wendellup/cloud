<%--
  Created by IntelliJ IDEA.
  User: zx
  Date: 15-10-19
  Time: 上午15:30
  计费SDK管理后台
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>计费SDK后台管理系统</title>
    <r:require module="easyui_core"/>
    <r:layoutResources/>
    <link type="text/css" rel="stylesheet" href="${request.contextPath}/css/common.css"/>
    <g:render template="/rootpath"></g:render>
    <script type="text/javascript" src="${request.contextPath}/js/common.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#tt').tabs({
                onSelect:function(title){
                    var tab = $('#tt').tabs('getSelected');
                    var index = $('#tt').tabs('getTabIndex',tab);
                    $('#iframe'+index).attr('src', $('#iframe'+index).attr("src")+"?tabIndx=${idx}");
//                    $('#p'+index).attr('href', $('#p'+index).attr("href"));
                    $('#iframe_index').val(index);
                    %{--switch(index) {--}%
                        %{--case 0:--}%
                            %{--if($("#iframe0").attr('src') == ""){--}%
                                %{--$("#iframe0").attr('src', '${createLink(controller: 'chargeIndex',action:'indexSdk')}');--}%
                            %{--}--}%
                            %{--break;--}%
                        %{--case 1:--}%
                            %{--if($("#iframe1").attr('src') == ""){--}%
                                %{--$("#iframe1").attr('src', '${createLink(controller: 'chargeIndex',action:'indexMemory')}');--}%
                            %{--}--}%
                            %{--break;--}%
                        %{--case 2:--}%
                            %{--if($("#iframe2").attr('src') == ""){--}%
                                %{--$("#iframe2").attr('src', '${createLink(controller: 'chargeIndex',action:'indexApp')}');--}%
                            %{--}--}%
                            %{--break;--}%
                        %{--case 3:--}%
                            %{--if($("#iframe3").attr('src') == ""){--}%
                                %{--$("#iframe3").attr('src', '${createLink(controller: 'chargeIndex',action:'indexAdvert')}');--}%
                            %{--}--}%
                            %{--break;--}%
                        %{--case 4:--}%
                            %{--if($("#iframe4").attr('src') == ""){--}%
                                %{--$("#iframe4").attr('src', '${createLink(controller: 'chargeIndex',action:'indexPkg')}');--}%
                            %{--}--}%
                            %{--break;--}%
                        %{--case 5:--}%
                            %{--if($("#iframe5").attr('src') == ""){--}%
                                %{--$("#iframe5").attr('src', '${createLink(controller: 'chargeIndex',action:'indexPreMemory')}');--}%
                            %{--}--}%
                            %{--break;--}%
                    %{--}--}%
                }
            });
            %{--if ('${fromer}' == 'sdk'){--}%
                %{--$("#tt").tabs("select",'计费SDK信息');--}%
            %{--} else if ('${fromer}' == 'memory'){--}%
                %{--$("#tt").tabs("select",'常驻内存推送信息');--}%
            %{--}--}%
        });
    </script>
</head>
<body>
    <input type="hidden" id="iframe_index"/>
    <div class="matter_box">
        <h3 class="nopoint"><strong>计费SDK后台管理系统</strong></h3>
        <div id="tt" class="easyui-tabs" data-options="tools:'#tab-tools'">
            <g:each var="resInfo" in="${resInfoList}" status="idx">
            %{--<g:if test="${idx==0}">--}%
                %{--<div title="${resInfo?.menuName}" data-options="cache:false" class="fn_overflow">--}%
                <div title="${resInfo?.menuName}"  class="fn_overflow">
                    <iframe scrolling="yes" frameborder="0" id="iframe${idx}" name="iframe${idx}" src="${request.contextPath}${resInfo?.menuUrl}" style="width:100%"></iframe>
                    %{--<iframe scrolling="yes" frameborder="0" id="iframe${idx}" name="iframe${idx}" src="${createLink(controller: 'chargeIndex',action:'\${resInfo.menuUrl}',params:[tabIndex:'\${idx}'])}" style="width:100%"></iframe>--}%

                </div>
            %{--</g:if>--}%
            %{--<g:else>--}%
            %{--<div title="${resInfo?.menuName}" data-options="cache:false" class="fn_overflow">--}%
            %{--<iframe scrolling="yes" frameborder="0" id="iframe${idx}" name="iframe${idx}" src="" style="width:100%"></iframe>--}%
            %{--</div>--}%
            %{--</g:else>--}%

            </g:each>

            %{--<g:each var="resInfo" in="${resInfoList}" status="idx">--}%
                %{--<div title="${resInfo.menuName}" data-options="cache:false" class="fn_overflow">--}%
                    %{--<iframe scrolling="yes" frameborder="0" id="iframe"+${idx} name="iframe"+${idx} src="${request.contextPath}${resInfo.menuUrl}" style="width:100%"></iframe>--}%
                %{--</div>--}%
            %{--</g:each>--}%
            %{--<div title="计费SDK信息" data-options="cache:false" class="fn_overflow">--}%
                %{--<iframe scrolling="yes" frameborder="0" id="iframe0" name="iframe0" src="${request.contextPath}/chargeIndex/indexSdk" style="width:100%"></iframe>--}%
            %{--</div>--}%
            %{--<div title="游戏推送信息" data-options="cache:false" class="fn_overflow">--}%
                %{--<iframe scrolling="yes" frameborder="0" id="iframe1" name="iframe1" style="width:100%" src=""></iframe>--}%
            %{--</div>--}%
            %{--<div title="应用推送信息" data-options="cache:false" class="fn_overflow">--}%
                %{--<iframe scrolling="yes" frameborder="0" id="iframe2" name="iframe2" style="width:100%" src=""></iframe>--}%
            %{--</div>--}%
            %{--<div title="广告推送信息" data-options="cache:false" class="fn_overflow">--}%
                %{--<iframe scrolling="yes" frameborder="0" id="iframe3" name="iframe3" style="width:100%" src=""></iframe>--}%
            %{--</div>--}%
            %{--<div title="包名采集管理" data-options="cache:false" class="fn_overflow">--}%
                %{--<iframe scrolling="yes" frameborder="0" id="iframe4" name="iframe4" style="width:100%" src=""></iframe>--}%
            %{--</div>--}%
            %{--<div title="推送信息预配置管理" data-options="cache:false" class="fn_overflow">--}%
                %{--<iframe scrolling="yes" frameborder="0" id="iframe5" name="iframe5" style="width:100%" src=""></iframe>--}%
            %{--</div>--}%
        </div>
    </div>
</body>
</html>