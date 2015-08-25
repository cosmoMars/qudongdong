package com.wonders.xlab.qudongdong.message.response;

import com.wonders.xlab.qudongdong.message.Message;

/**
 * 回复音乐消息
 * @author bonc
 *
 */

public class MusicMessage extends Message {
	//回复的音乐消息
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}

	
	
}
