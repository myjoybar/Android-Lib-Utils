package com.joybar.library.common.log;

import com.joybar.library.common.log.config.LogConfig;

/**
 * Created by joybar on 2015/01/16.
 */

public class L {

	private static PrinterService printerService = new PrinterService();


	/**
	 *
	 * @param logEnable
	 */
	public static void setLogEnable(boolean logEnable) {
		LogConfig.LOG_ENABLE = logEnable;
	}

	/**
	 *
	 * @param level
	 */
	public static void setLogLevel(@LogLevel.LogLevelType int level) {
		LogConfig.LOG_LEVEL = level;
	}

	public static void setShowTopEnable(boolean logEnable) {
		LogConfig.SHOW_TOP = logEnable;
	}
	public static void setShowBottomEnable(boolean logEnable) {
		LogConfig.SHOW_BOTTOM = logEnable;
	}

	public static void v(Object object) {
		printerService.v(object);
	}

	public static void v(String tag, Object object) {
		printerService.v(tag, object);
	}

	public static void v(String message) {
		printerService.v(message);
	}

	public static void v(String tag, String message) {
		printerService.v(tag, message);
	}

	public static void i(Object object) {
		printerService.v(object);
	}

	public static void i(String tag, Object object) {
		printerService.i(tag, object);
	}

	public static void i(String message) {
		printerService.i(message);
	}

	public static void i(String tag, String message) {
		printerService.i(tag, message);
	}


	public static void d(Object object) {
		printerService.v(object);
	}

	public static void d(String tag, Object object) {
		printerService.d(tag, object);
	}

	public static void d(String message) {
		printerService.d(message);
	}

	public static void d(String tag, String message) {
		printerService.d(tag, message);
	}


	public static void w(Object object) {
		printerService.w(object);
	}

	public static void w(String tag, Object object) {
		printerService.w(tag, object);
	}

	public static void w(String message) {
		printerService.w(message);
	}

	public static void w(String tag, String message) {
		printerService.w(tag, message);
	}


	public static void e(Object object) {
		printerService.e(object);
	}

	public static void e(String tag, Object object) {
		printerService.e(object);
	}

	public static void e(String message) {
		printerService.e(message);
	}

	public static void e(String tag, String message) {
		printerService.e(tag, message);
	}


	public static void json(String json) {
		printerService.json(json);
	}

	public static void json(String tag, String json) {
		printerService.json(tag, json);
	}

	public static void xml(String xml) {
		printerService.xml(xml);
	}

	public static void xml(String tag, String xml) {
		printerService.xml(tag, xml);
	}

}
