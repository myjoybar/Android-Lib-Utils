package com.joy.libbase.base.app.performance;


import android.util.Log;

import java.util.HashMap;

public class MethodTimeManager {
	private static final String TAG = "MethodTimeManager";
	private static final float TIME = (1000 * 1000 * 1000.0f);
	private HashMap<String, Long> mMethodNameHashMap = new HashMap<>(16);

	private static class MethodTimeManagerHolder {
		private static MethodTimeManager INSTANCE = new MethodTimeManager();

	}

	public static MethodTimeManager getInstance() {
		return MethodTimeManagerHolder.INSTANCE;
	}


	public void startTrack(String methodName) {
		mMethodNameHashMap.put(methodName, System.nanoTime());
	}

	public void endTrack(String methodName) {
		Long duration = mMethodNameHashMap.get(methodName);
		if (null != duration) {
			long endTime = System.nanoTime();
			String threadName = Thread.currentThread().getName();
			Log.d(TAG, threadName + " :" + methodName + "_____execute time: " + ((endTime - duration) / TIME));
		} else {
			String threadName = Thread.currentThread().getName();
			Log.d(TAG, threadName + " :" + methodName + "______not call startTrack");
		}

	}

}
