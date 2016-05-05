/**
 * 
 */
package cn.egame.filter

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

/**
 * <p>操作记录拦截器,用于记录操作信息
 * @Author houqq
 * 
 */

class OperationFilters {
	
	private static final Log logger = LogFactory.getLog(OperationFilters.class);
	
	def filters = {
//		all(controller: '*', action: '*') {
//			after = { Map model ->
//				HttpSession session = request.getSession(false);
//				User user = session.getAttribute(AppConstants.CURRENT_USER);
//				if(logger.isInfoEnabled())
//				    logger.info('user: ${user.cnName} controller: ${params.controller} action: ${params.action} url: ${reqeust.requestURI}');
//			}
//			afterView = { Exception e ->
//
//			}
//		}
	}
}
