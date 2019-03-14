package com.iisysgroup.utils;

public class EventMsg {
	public int msg;
	public Object data;

	public EventMsg(int msg, Object data) {
		this.data = data;
		this.msg = msg;
	}

	public EventMsg(int msg) {
		this.msg = msg;
	}
}
