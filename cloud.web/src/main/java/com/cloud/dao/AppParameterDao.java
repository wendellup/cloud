package com.cloud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cloud.valueobject.entity.AppParameter;

/**
 * 
 */

@Repository
public interface AppParameterDao {

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
	/**
	 * 得到参数表实体
	 * 
	 */
	public AppParameter getAppParameterById(@Param("id") int id);

	/**
	 * 根据parentId获取参数表列表
	 * 
	 */
	public List<AppParameter> listAppParameterByParentId(
			@Param("parentId") int parentId);


}
