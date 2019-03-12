package com.joy.libbase.tracker.testlog;

import android.util.Log;

public class TestTrackerLog {
	private static boolean ENABLE = true;
	private static final String TAG = "Tracker_";

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
