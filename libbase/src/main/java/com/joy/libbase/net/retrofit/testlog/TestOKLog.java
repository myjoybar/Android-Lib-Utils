package com.joy.libbase.net.retrofit.testlog;

import android.util.Log;

public class TestOKLog {
	private static boolean ENABLE = true;
	private static final String TAG = "okhttp_";

	public static void setEnable(boolean enable) {
		ENABLE = enable;
	}

	public static void print(String msg) {
		print("", msg);
	}

	public static void print(String tag, String msg) {
		if (ENABLE) {
			Log.d(TAG + tag, msg);
		}
	}

}
