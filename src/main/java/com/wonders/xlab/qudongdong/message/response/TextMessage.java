package com.wonders.xlab.qudongdong.message.response;

import com.wonders.xlab.qudongdong.message.Message;

/**
 * 回复文本消息
 * @author bonc
 *
 */

public class TextMessage extends Message {
	//回复的文本消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
	
}
