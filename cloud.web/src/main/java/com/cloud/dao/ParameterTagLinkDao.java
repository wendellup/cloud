package com.cloud.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cloud.valueobject.entity.ParameterTagLink;

@Repository
public interface ParameterTagLinkDao {
		// ///////////////////////////////
		// ///// 增加 ////////
		// ///////////////////////////////

	void addParameterTagLinkList(List<ParameterTagLink> parameterTagLinks);
	
		// ///////////////////////////////
		// ///// 刪除 ////////
		// ///////////////////////////////

	void deleteParameterTagLinkByBusinessId(int businessId);
	
		// ///////////////////////////////
		// ///// 修改 ////////
		// ///////////////////////////////


		// ///////////////////////////////
		// ///// 查询 ////////
		// ///////////////////////////////
	List<ParameterTagLink> listParameterTagLinkByTagId(int tagId);
	
}
