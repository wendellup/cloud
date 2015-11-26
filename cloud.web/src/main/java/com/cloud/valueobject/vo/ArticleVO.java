package com.cloud.valueobject.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cloud.valueobject.entity.Article;
import com.cloud.valueobject.entity.ParameterTag;

public class ArticleVO implements Serializable{

	private static final long serialVersionUID = 5830852472820598910L;

	private Article article;
//	private List<String> tagIds = new ArrayList<String>();
	private List<ParameterTag> allTagInfos = new ArrayList<ParameterTag>();
	private List<String> articleTagIds = new ArrayList<String>();
	
	public ArticleVO(){
	}
	
	public ArticleVO(Article article, List<ParameterTag> allTagInfos,
			List<Integer> articleTagIds) {
		super();
		this.article = article;
		this.allTagInfos = allTagInfos;
		if(articleTagIds!=null){
			for(Integer tagId : articleTagIds){
				this.articleTagIds.add(tagId+"");
			}
		}
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
//	public List<String> getTagIds() {
//		return tagIds;
//	}
//	public void setTagIds(List<String> tagIds) {
//		this.tagIds = tagIds;
//	}
	public List<ParameterTag> getAllTagInfos() {
		return allTagInfos;
	}
	public void setAllTagInfos(List<ParameterTag> allTagInfos) {
		this.allTagInfos = allTagInfos;
	}
	public List<String> getArticleTagIds() {
		return articleTagIds;
	}
	public void setArticleTagIds(List<String> articleTagIds) {
		this.articleTagIds = articleTagIds;
	}
}

