import cn.egame.common.util.Utils
import cn.egame.global.core.ComponentManager
import grails.util.Environment
import org.apache.log4j.Logger

import javax.servlet.ServletContext

class BootStrap {
    def grailsApplication;
    def logger = Logger.getLogger(BootStrap.class);
    def init = { ServletContext servletContext ->
        String webRoot = servletContext.getRealPath("/WEB-INF");
        Utils.setAppRoot(webRoot);
        String configPath = servletContext.getRealPath("") + File.separator + "config" + File.separator;
        if (Environment.currentEnvironment != Environment.PRODUCTION) configPath += Environment.currentEnvironment.name;
        if (Environment.current == Environment.PRODUCTION) configPath = webRoot + "/" + "config";
        try {
            println "configPath==" + configPath;
            def file = new File(configPath);
            def templateMap = [:];
            file.eachFile { eachFile ->
                Properties properties = new Properties();
                properties.load(new FileInputStream(eachFile));
                templateMap << properties;
            }
            this.replaceServer(templateMap);
            grailsApplication.flatConfig.putAll(templateMap);
            //initRptAppRelation();
        } catch (Exception e) {
            logger.error("BootStrap init exception", e);
        }
        logger.info('BootStrap end ,grailsApplication ==' + grailsApplication)
        ComponentManager.getInstance().setGrailsApplication(grailsApplication);
        ComponentManager.getInstance().setServletContext(servletContext);
    }
    def replaceServer = { templateMap ->
        String casServerStr = "@cas.server@";
        String openServerStr = "@open.server@";
        def casServer = templateMap.get("cas.server");
        def openServer = templateMap.get("open.server");
        def temp = [:]
        templateMap.each { String key, String value ->
            if (value.contains(casServerStr)) {
                value = value.replaceAll(casServerStr, casServer);
                temp << [(key): (value)];
            }
            if (value.contains(openServerStr)) {
                value = value.replaceAll(openServerStr, openServer);
                temp << [(key): (value)];
            }
        }
        templateMap << temp;
        grailsApplication.flatConfig << templateMap;

    }
    def destroy = {
    }
}
