package com.cloud.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cloud.dao.AppParameterDao;
import com.cloud.valueobject.entity.AppParameter;

@Service
public class AppParameterService {

	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private AppParameterDao appParameterDao;


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
	 * @return List<Folder>
	 */
	@Cacheable(value = "folder")
	public List<AppParameter> listAppParameterByParentId(int parentId) {
		return appParameterDao.listAppParameterByParentId(parentId);
	}

}
