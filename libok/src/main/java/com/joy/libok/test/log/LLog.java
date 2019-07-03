package com.joy.libok.test.log;

import android.util.Log;

public class LLog {
	private static boolean ENABLE = true;
	private static boolean ENABLE_THREAD_NAME = true;
	private static final String TAG = "libok_";

	public static void setENABLE(boolean ENABLE) {
		LLog.ENABLE = ENABLE;
	}

	public static void setEnableThreadName(boolean enableThreadName) {
		ENABLE_THREAD_NAME = enableThreadName;
	}

	public static void v(String tag, String msg) {
		if (ENABLE) {
			msg = getThreadName() + msg;
			Log.v(TAG + tag, msg);
		}

	}

	public static void d(String tag, String msg) {
		if (ENABLE) {
			msg = getThreadName() + msg;
			Log.d(TAG + tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (ENABLE) {
			msg = getThreadName() + msg;
			Log.i(TAG + tag, msg);
		}
	}


	public static void w(String tag, String msg) {
		if (ENABLE) {
			msg = getThreadName() + msg;
			Log.w(TAG + tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (ENABLE) {
			msg = getThreadName() + msg;
			Log.e(TAG + tag, msg);
		}
	}

	private static String getThreadName() {
		if (ENABLE_THREAD_NAME) {
			return Thread.currentThread().getName() + "___";
		}
		return "";
	}

}
