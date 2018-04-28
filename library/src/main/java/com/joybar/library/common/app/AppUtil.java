package com.joybar.library.common.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * Created by joybar on 2018/1/9.
 */

public class AppUtil {
	private static Boolean isDebug = null;

	/**
	 * form Trinea
	 * http://www.trinea.cn/android/android-whether-debug-mode-why-buildconfig-debug-always-false/
	 * @return
	 */
	public static boolean isDebug() {
		return isDebug == null ? false : isDebug.booleanValue();
	}

	/**
	 * Sync lib debug with app's debug value. Should be called in module Application
	 *
	 * @param context
	 */
	public static void syncIsDebug(Context context) {
		if (isDebug == null) {
			isDebug = context.getApplicationInfo() != null &&
					(context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
		}
	}
}
