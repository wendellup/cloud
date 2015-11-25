package com.cloud.valueobject.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cloud.valueobject.entity.Article;

public class ArticleVO implements Serializable{

	private static final long serialVersionUID = 5830852472820598910L;

	private Article article;
	private List<String> tagIds = new ArrayList<String>();
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public List<String> getTagIds() {
		return tagIds;
	}
	public void setTagIds(List<String> tagIds) {
		this.tagIds = tagIds;
	}
	@Override
	public String toString() {
		return "ArticleVO [article=" + article + ", tagIds=" + tagIds + "]";
	}
	
}

