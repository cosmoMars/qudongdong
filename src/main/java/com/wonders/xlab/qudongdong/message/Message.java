package com.wonders.xlab.qudongdong.message;

/**
 * 消息父类
 * @author xieyinglin
 *
 */
public class Message {
	//接收方帐号(公众易信号）
	private String ToUserName;

	//消息发送方（普通用户OpenID）
	private String FromUserName;

	//消息创建时间（64位整型）
	private long CreateTime;
	
	//消息类型,文本（text），图片（image）,语音（audio）,视频（video）,地理位置（location）
	private String MsgType;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}


	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	
	
}
