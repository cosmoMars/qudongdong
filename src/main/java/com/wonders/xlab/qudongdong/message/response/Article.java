package com.wonders.xlab.qudongdong.message.response;

/**
 * 图文信息
 */
public class Article {

	/** 图文消息名称 */
	private String Title;

	/** 图文消息描述 */
	private String Description;

	/** 图片链接 */
	private String PicUrl;

	/** 点击图文消息跳转链接 */
	private String Url;

	/**
	 * 取得图文消息名称
	 * @return 图文消息名称
	 */
	public String getTitle() {
		return Title;
	}

	/**
	 * 设定图文消息名称
	 * @param title 图文消息名称
	 */
	public void setTitle(String title) {
		Title = title;
	}

	/**
	 * 取得图文消息描述
	 * @return 图文消息描述
	 */
	public String getDescription() {
		return null == Description ? "" : Description;
	}

	/**
	 * 设定图文消息描述
	 * @param description 图文消息描述
	 */
	public void setDescription(String description) {
		Description = description;
	}

	/**
	 * 取得图片链接
	 * @return 图片链接
	 */
	public String getPicUrl() {
		return null == PicUrl ? "" : PicUrl;
	}

	/**
	 * 设定图片链接
	 * 支持JPG、PNG格式，
	 * 较好的效果为大图360*200，小图200*200，
	 * 限制图片链接的域名需要与开发者填写的基本资料中的Url一致
	 * @param picUrl 图片链接
	 */
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	/**
	 * 取得点击图文消息跳转链接
	 * @return 点击图文消息跳转链接
	 */
	public String getUrl() {
		return null == Url ? "" : Url;
	}

	/**
	 * 设定点击图文消息跳转链接
	 * @param url 点击图文消息跳转链接
	 */
	public void setUrl(String url) {
		Url = url;
	}

}
