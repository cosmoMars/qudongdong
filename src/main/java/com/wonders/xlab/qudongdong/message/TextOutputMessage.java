package com.wonders.xlab.qudongdong.message;

/**
 * Created by mars on 15/8/25.
 */
public class TextOutputMessage extends OutputMessage {

    /**
     * 消息类型:文本消息
     */
    private String MsgType = "text";
    /**
     * 文本消息
     */
    private String Content;

    public TextOutputMessage() {
    }

    public TextOutputMessage(String content) {
        Content = content;
    }

    @Override
    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
