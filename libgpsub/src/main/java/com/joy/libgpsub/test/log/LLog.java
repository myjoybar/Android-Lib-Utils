package com.joy.libgpsub.test.log;

import android.util.Log;

public class LLog {
	private static boolean ENABLE = true;
	private static final String TAG = "libok_";

	public static void setENABLE(boolean ENABLE) {
		LLog.ENABLE = ENABLE;
	}

	public static void v(String tag, String msg) {
		msg = getThreadName() + msg;
		if (ENABLE) {
			Log.v(TAG + tag, msg);
		}

	}

	public static void d(String tag, String msg) {
		msg = getThreadName() + msg;
		if (ENABLE) {
			Log.d(TAG + tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		msg = getThreadName() + msg;
		if (ENABLE) {
			Log.i(TAG + tag, msg);
		}
	}


	public static void w(String tag, String msg) {
		msg = getThreadName() + msg;
		if (ENABLE) {
			Log.w(TAG + tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		msg = getThreadName() + msg;
		if (ENABLE) {
			Log.e(TAG + tag, msg);
		}
	}

	private static String getThreadName() {
		//return Thread.currentThread().getName() + "___";
		return "";

	}


}
