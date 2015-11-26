package com.cloud.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.egame.common.model.PageData;
import cn.egame.common.util.Utils;

import com.cloud.dao.ArticleDao;
import com.cloud.dao.ParameterTagLinkDao;
import com.cloud.dao.UserDao;
import com.cloud.valueobject.entity.Article;
import com.cloud.valueobject.entity.ParameterTagLink;
import com.cloud.valueobject.entity.User;

@Service
public class UserService {

	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private UserDao userDao;
	
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

	public User getUserByUserNameAndPwd(User user){
		return userDao.getUserByUserNameAndPwd(user);
	}
}
