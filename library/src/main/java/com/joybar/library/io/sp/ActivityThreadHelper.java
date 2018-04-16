package com.joybar.library.io.sp;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Queue;

/**
 * Created by joybar on 2018/4/16.
 * 处理SharedPreference apply 引起的 ANR 问题
 */

public class ActivityThreadHelper {


	private static final String TAG = "ActivityThreadHelper";

	public static boolean tryHackActivityThreadH() {

		try {
			Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");

			Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
			currentActivityThreadMethod.setAccessible(true);
			Object currentActivityThread = currentActivityThreadMethod.invoke(null);

			Field handlerField = activityThreadClass.getDeclaredField("mH");
			handlerField.setAccessible(true);
			Handler handler = (Handler) handlerField.get(currentActivityThread);

			Field callbackField = Handler.class.getDeclaredField("mCallback");
			callbackField.setAccessible(true);
			callbackField.set(handler, new ActivityThreadCallBack());
			Log.d(TAG, "Hack ActivityThread Success");
			return true;

		} catch (Throwable e) {
			Log.e(TAG,"err : " + e.getMessage() + " Android version is : " + Build.VERSION.SDK_INT);
			return false;
		}
	}

	private static boolean init = false;
	private static Queue<Runnable> sFinishers;

	private static void cleanBeforeSPBlock() {
		if (!init) {
			getPendingWorkFinishers();
			init = true;
		}
		if (sFinishers != null && sFinishers.size() > 0) {
			sFinishers.clear();
		}
	}

	private static void getPendingWorkFinishers() {
		try {
			Class queuedWork = Class.forName("android.app.QueuedWork");
			Field finishers;
			if (Build.VERSION.SDK_INT > 25) {//Android Oreo开始变量名称改变
				finishers = queuedWork.getDeclaredField("sFinishers");
			} else {
				finishers = queuedWork.getDeclaredField("sPendingWorkFinishers");
			}
			finishers.setAccessible(true);
			sFinishers = (Queue<Runnable>) finishers.get(null);
		} catch (Throwable e) {
			Log.e(TAG, "getPendingWorkFinishers err: " + e.getMessage() + " Android version is : " + Build.VERSION.SDK_INT);
		}
	}

	private static class ActivityThreadCallBack implements Handler.Callback {

		private static final int SERVICE_ARGS = 115;
		private static final int STOP_SERVICE = 116;
		private static final int SLEEPING = 137;
		private static final int STOP_ACTIVITY_SHOW = 103;
		private static final int STOP_ACTIVITY_HIDE = 104;

		@Override
		public boolean handleMessage(Message msg) {
			switch (msg.what) {

				case SERVICE_ARGS:
				case STOP_SERVICE:
				case SLEEPING:
				case STOP_ACTIVITY_SHOW:
				case STOP_ACTIVITY_HIDE:
					cleanBeforeSPBlock();
					break;

				default:
					break;
			}

			return false;
		}
	}
}
