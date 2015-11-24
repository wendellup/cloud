package com.cloud.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cloud.valueobject.entity.ParameterTag;

@Repository
public interface ParameterTagDao {
		// ///////////////////////////////
		// ///// 增加 ////////
		// ///////////////////////////////
	void addParameterTag(ParameterTag parameterTag);
		
		// ///////////////////////////////
		// ///// 刪除 ////////
		// ///////////////////////////////

		// ///////////////////////////////
		// ///// 修改 ////////
		// ///////////////////////////////
	void updateParameterTag(ParameterTag parameterTag);

		// ///////////////////////////////
		// ///// 查询 ////////
		// ///////////////////////////////
	ParameterTag getParameterTagById(int id);

	List<ParameterTag> listParameterTagByType(int type);
	
}
