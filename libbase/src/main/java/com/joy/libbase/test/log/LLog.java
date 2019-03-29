package com.joy.libbase.test.log;

import com.joy.libbase.config.LibConfigManager;
import com.joy.libbase.config.instance.log.ILogService;

public class LLog {
	private static boolean ENABLE = false;
	private static final String TAG = "lib_";

	public static void setENABLE(boolean ENABLE) {
		LLog.ENABLE = ENABLE;
	}

	public static void v(String tag, String msg) {
		if (ENABLE) {
			ILogService logService;
			if ((logService = LibConfigManager.getInstance().getLogService()) != null) {
				logService.v(TAG + tag, msg);
			}
		}

	}

	public static void d(String tag, String msg) {
		if (ENABLE) {
			ILogService logService;
			if ((logService = LibConfigManager.getInstance().getLogService()) != null) {
				logService.d(TAG + tag, msg);
			}
		}
	}

	public static void i(String tag, String msg) {
		if (ENABLE) {
			ILogService logService;
			if ((logService = LibConfigManager.getInstance().getLogService()) != null) {
				logService.i(TAG + tag, msg);
			}
		}
	}


	public static void w(String tag, String msg) {
		if (ENABLE) {
			ILogService logService;
			if ((logService = LibConfigManager.getInstance().getLogService()) != null) {
				logService.w(TAG + tag, msg);
			}
		}
	}

	public static void e(String tag, String msg) {
		if (ENABLE) {
			ILogService logService;
			if ((logService = LibConfigManager.getInstance().getLogService()) != null) {
				logService.e(TAG + tag, msg);
			}
		}
	}


}
