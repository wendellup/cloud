package cn.egame.pkg
import cn.egame.cn.egame.PkgVo
import cn.egame.common.WebConnectionService
import cn.egame.global.constant.AppConstants
import cn.egame.global.utils.Pagination
import cn.egame.utils.DateUtil
import org.apache.commons.collections.CollectionUtils
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import org.springframework.jdbc.core.JdbcTemplate

class PkgService {

    JdbcTemplate jdbcTemplate;
    WebConnectionService webConnectionService;

    def list(GrailsParameterMap params) {
        def currPage = params.page ? Integer.valueOf(params.page) : 1;
        def pageSize = params.rows ? Integer.valueOf(params.rows) : AppConstants.DEFAULT_PAGE_SIZE;
        StringBuffer querySql = new StringBuffer();

        querySql.append("select p.id, p.package_name, p.insert_time, p.update_time ")
                .append(" from t_package_name_manager p ");
        querySql.append(" order by p.id desc");
        log.info(querySql);

        Pagination pageList = new Pagination(querySql.toString(), currPage, pageSize, jdbcTemplate);
        return pageList;
    }

    def convertBeanList(List<Map<String, Object>> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<PkgVo> resultList = new ArrayList<PkgVo>();
        list.each { info ->
            resultList.add(this.convertBean(info));
        }
        return resultList;
    }

    def convertBean(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        PkgVo vo = new PkgVo();
        vo.id = map.get("id") as Long;
        vo.insertTime = DateUtil.parseDate2String(map.get("insert_time") as Long, DateUtil.DATE_TIME_FORMAT);
        vo.updateTime = DateUtil.parseDate2String(map.get("update_time") as Long, DateUtil.DATE_TIME_FORMAT);
        vo.packageName = map.get("package_name") as String;
        return vo;
    }

    def toVo(PackageNameManagerInfo info) {
        PkgVo vo = new PkgVo();
        vo.id = info.id;
        vo.packageName = info.packageName;
        vo.insertTime = info.insertTime;
        vo.updateTime = info.updateTime;
        return vo;
    }

    def addSubmit(GrailsParameterMap params) {
        try {
            PackageNameManagerInfo packageNameManagerInfo = new PackageNameManagerInfo();
            packageNameManagerInfo.properties = params;
            packageNameManagerInfo.insertTime = DateUtil.parseDate2Long(new Date());
            packageNameManagerInfo.updateTime = DateUtil.parseDate2Long(new Date());
            webConnectionService.save(packageNameManagerInfo);
        } catch (Exception e) {
            log.error("新建内容出错",e)
            throw new RuntimeException(e);
        }
    }

    def editSubmit(PackageNameManagerInfo pkgInfo, GrailsParameterMap params) {
        try {
            pkgInfo.properties = params;
            pkgInfo.updateTime = DateUtil.parseDate2Long(new Date());
            webConnectionService.save(pkgInfo);
        } catch (Exception e) {
            log.error("编辑内容出错",e)
            throw new RuntimeException(e);
        }
    }

    def deleteMemoryByIds(String pkgIdsStr) {
        try {
            StringBuffer updateSql = new StringBuffer();
            updateSql.append("delete from t_package_name_manager  where id in (")
                    .append(pkgIdsStr)
                    .append(")");
            jdbcTemplate.execute(updateSql.toString());
        } catch (Exception e) {
            log.error("编辑内容出错",e)
            throw new RuntimeException(e);
        }
    }
}
