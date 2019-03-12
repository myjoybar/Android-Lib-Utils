package com.joy.libbase.base.app.handler;

import android.os.Handler;
import android.os.Message;

/**
 * global handler
 */
public class GlobalHandler extends Handler {
	private HandleMsgListener mMsgListener;

	private static class GlobalHandlerHolder {
		private static GlobalHandler INSTANCE = new GlobalHandler();

	}

	public static GlobalHandler getInstance() {
		return GlobalHandlerHolder.INSTANCE;
	}

	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		if (getHandleMsgListener() != null){
			getHandleMsgListener().handleMsg(msg);
		}
	}

	public interface HandleMsgListener {
		void handleMsg(Message msg);
	}

	public void setHandleMsgListener(HandleMsgListener listener) {
		this.mMsgListener = listener;
	}

	public HandleMsgListener getHandleMsgListener() {
		return mMsgListener;
	}


}
