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

	public int addAppParameter(AppParameter appParameter);
	
	
	// ///////////////////////////////
	// ///// 刪除 ////////
	// ///////////////////////////////

	public void deleteAppParameter(AppParameter appParameter);
	
	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////

	public int updateAppParameter(AppParameter appParameter);

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
