package com.cloud.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.dao.AppParameterDao;
import com.cloud.dao.ParameterTagDao;
import com.cloud.valueobject.constvar.EnumType;
import com.cloud.valueobject.constvar.EnumType.TagType;
import com.cloud.valueobject.entity.AppParameter;
import com.cloud.valueobject.entity.ParameterTag;

@Service
public class AppParameterService {

	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private AppParameterDao appParameterDao;

	@Autowired
	private ParameterTagDao parameterTagDao;

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////


	// ///////////////////////////////
	// ///// 刪除 ////////
	// ///////////////////////////////

	
	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////


	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	public AppParameter getAppParameterById(int id) throws Exception {
		AppParameter appParameter = appParameterDao.getAppParameterById(id);
		return appParameter;
	}

	/**
	 * 得到所有子目录
	 * 
	 * @param fatherId
	 */
	public List<AppParameter> listAppParameterByParentId(int parentId) {
		return appParameterDao.listAppParameterByParentId(parentId);
	}

	public List<ParameterTag> listParameterTagByType(TagType tagType){
		return parameterTagDao.listParameterTagByType(tagType.value());
	}
}
