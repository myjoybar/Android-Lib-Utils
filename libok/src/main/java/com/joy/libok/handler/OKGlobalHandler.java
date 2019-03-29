package com.joy.libok.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * global handler
 */
public class OKGlobalHandler extends Handler {
	private HandleMsgListener mMsgListener;

	public OKGlobalHandler() {
		super(Looper.getMainLooper());
	}

	private static class OKGlobalHandlerHolder {
		private static OKGlobalHandler INSTANCE = new OKGlobalHandler();

	}

	public static OKGlobalHandler getInstance() {
		return OKGlobalHandlerHolder.INSTANCE;
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
