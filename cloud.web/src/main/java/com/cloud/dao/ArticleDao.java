package com.cloud.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cloud.valueobject.entity.Article;

@Repository
public interface ArticleDao {
		// ///////////////////////////////
		// ///// 增加 ////////
		// ///////////////////////////////

	int addArticle(Article article);
	
		// ///////////////////////////////
		// ///// 刪除 ////////
		// ///////////////////////////////

		// ///////////////////////////////
		// ///// 修改 ////////
		// ///////////////////////////////
	void updateArticle(Article article);

		// ///////////////////////////////
		// ///// 查询 ////////
		// ///////////////////////////////
	List<Article> listArticleByAppParameterId(int paramId);
	
	List<Article> listArticle();
	
	/*List<Article> listArticleByAppParameterIdAndType(int paramId);*/
	
	Article getArticleById(int id);

}
