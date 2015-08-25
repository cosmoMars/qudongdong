package com.wonders.xlab.qudongdong.menu.model;


/**
 * 子菜单按钮
 * @author Administrator
 *
 */
public class CommonButton extends Button {
	
	private String key;
	private String url;
	private String type;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
		public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
