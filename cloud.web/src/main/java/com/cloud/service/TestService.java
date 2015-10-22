package com.cloud.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.dao.TestDao;
import com.cloud.valueobject.entity.Test;

@Service
public class TestService {

	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private TestDao testDao;


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

	public Test getTestById(int id){
		Test test = testDao.getTestById(id);
		return test;
	}


}
