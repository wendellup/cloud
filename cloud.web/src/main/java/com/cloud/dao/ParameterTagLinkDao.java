package com.cloud.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cloud.valueobject.entity.ParameterTagLink;

@Repository
public interface ParameterTagLinkDao {
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
		// ///// 查询 ////////
		// ///////////////////////////////
	List<ParameterTagLink> listParameterTagLinkByTagId(int tagId);
	
}