package cn.egame.common

import cn.egame.global.constant.AppConstants
import org.apache.log4j.Logger

class WebConnectionService {

    static Logger log = Logger.getLogger(WebConnectionService.class);

    static transactional = true;

//    @MessageDrive
//    def save(def obj) {
//        if (obj == null)
//            return false;
//        if (!obj.save(flush: true)) {
//            obj.errors.each {
//                log.error("保存对象失败:" + it);
//            }
//            return false;
//        }
//        return true;
//    }


    def save(def obj) {
        if (obj == null)
            throw new IllegalStateException("object can not be null");
        if (!obj.save(flush: true)) {
            obj.errors.each {
                log.error("保存对象失败:" + it);
            }
            throw new IllegalStateException("save object fail");
//			return false;
        }
//		return true;
    }

    /**
     * 删除表中的对象(逻辑删除，将recordStatus字段设置为0)
     * @param obj
     * @return 0:删除失败  1:删除成功
     */
    //@MessageDrive(paramIndex = 0)
    def delete(def obj) {
        if (obj == null) {
            log.debug("删除对象为空")
            return 0
        }
        obj.recordStatus = AppConstants.RECORD_STATUS.DEL_STATUS.intValue()
        if (!obj.save(flush: true)) {
            obj.errors.each {
                log.error("删除对象失败:" + it)
            }
            return 0
        }
        return 1
    }


    /**
     * 删除表中的对象(逻辑删除，将recordStatus字段设置为0)
     * @param obj
     * @return 0:删除失败  1:删除成功
     */
    def record_statusDel(def obj) {
        if (obj == null) {
            log.debug("删除对象为空")
            return 0
        }
        obj.record_status = AppConstants.RECORD_STATUS.DEL_STATUS.intValue()
        if (!obj.save(flush: true)) {
            obj.errors.each {
                log.error("删除对象失败:" + it)
            }
            return 0
        }
        return 1
    }
}
