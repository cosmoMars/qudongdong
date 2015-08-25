package com.wonders.xlab.qudongdong.message.response;


import com.wonders.xlab.qudongdong.message.Message;

import java.util.List;

/**
 * 图文响应消息
 */
public class NewsMessage extends Message {

	/** 图文消息个数，限制为10条以内 */
	private int ArticleCount;
	/** 多条图文消息信息，默认第一个item为大图 */
	private List<Article> Articles;

	/**
	 * 取得图文消息个数
	 * @return 图文消息个数
	 */
	public int getArticleCount() {
		return ArticleCount;
	}

	/**
	 * 设定图文消息个数，限制为10条以内
	 * @param articleCount 图文消息个数
	 */
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	/**
	 * 取得多条图文消息信息
	 * @return 多条图文消息信息，默认第一个item为大图
	 */
	public List<Article> getArticles() {
		return Articles;
	}

	/**
	 * 设定多条图文消息信息
	 * 默认第一个item为大图
	 * @param articles 多条图文消息信息
	 */
	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
}