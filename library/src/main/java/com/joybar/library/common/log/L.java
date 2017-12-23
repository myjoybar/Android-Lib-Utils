package com.joybar.library.common.log;

import com.joybar.library.common.log.config.LogConfig;

/**
 * Created by joybar on 2015/01/16.
 */

public class L {

	private static PrinterService printerService = new PrinterService();

	/**
	 * turn on or turn off the L utils
	 * 
	 * @param logEnable  set the L utils enable if the parameter is true
	 */
	public static void setLogEnable(boolean logEnable) {
		LogConfig.LOG_ENABLE = logEnable;
	}

	/**
	 * set the log level
	 * @param level  you can set the value  {@link @IntDef}  {@link @LogLevel.LogLevelType}..
	 * if the level is TYPE_VERBOSE,you can see all logs,if is TYPE_NONE,you can see non log
	 */
	public static void setLogLevel(@LogLevel.LogLevelType int level) {
		LogConfig.LOG_LEVEL = level;
	}

	/**
	 * set the logShowTopEnable
	 * 
	 * @param logShowTopEnable if the logShowTopEnable is true will show log top tag
	 */
	public static void setShowTopEnable(boolean logShowTopEnable) {
		LogConfig.SHOW_TOP = logShowTopEnable;
	}

	/**
	 * set the logShowBottomEnable
	 *
	 * @param logShowBottomEnable if the logShowBottomEnable is true will show log top tag
	 */
	public static void setShowBottomEnable(boolean logShowBottomEnable) {
		LogConfig.SHOW_BOTTOM = logShowBottomEnable;
	}

	/**
	 *
	 * @param object the object will be printed 
	 */
	public static void v(Object object) {
		printerService.v(object);
	}

	/**
	 *
	 * @param tag  the log tag 
	 * @param object the object will be printed
	 */
	public static void v(String tag, Object object) {
		printerService.v(tag, object);
	}

	/**
	 *
	 * @param message the message will be printed the message will be printed
	 */
	public static void v(String message) {
		printerService.v(message);
	}

	/**
	 *
	 * @param tag  the log tag the log tag
	 * @param message the message will be printed 
	 */
	public static void v(String tag, String message) {
		printerService.v(tag, message);
	}

	/**
	 *
	 * @param object the object will be printed 
	 */
	public static void i(Object object) {
		printerService.v(object);
	}

	/**
	 *
	 * @param tag  the log tag
	 * @param object the object will be printed the object will be printed
	 */
	public static void i(String tag, Object object) {
		printerService.i(tag, object);
	}

	/**
	 *
	 * @param message the message will be printed
	 */
	public static void i(String message) {
		printerService.i(message);
	}

	/**
	 *
	 * @param tag  the log tag
	 * @param message the message will be printed
	 */
	public static void i(String tag, String message) {
		printerService.i(tag, message);
	}

	/**
	 *
	 * @param object the object will be printed
	 */
	public static void d(Object object) {
		printerService.v(object);
	}

	/**
	 *
	 * @param tag  the log tag
	 * @param object the object will be printed
	 */
	public static void d(String tag, Object object) {
		printerService.d(tag, object);
	}

	/**
	 *
	 * @param message the message will be printed
	 */
	public static void d(String message) {
		printerService.d(message);
	}

	/**
	 *
	 * @param tag  the log tag
	 * @param message the message will be printed
	 */
	public static void d(String tag, String message) {
		printerService.d(tag, message);
	}

	/**
	 *
	 * @param object the object will be printed
	 */
	public static void w(Object object) {
		printerService.w(object);
	}

	/**
	 *
	 * @param tag  the log tag
	 * @param object the object will be printed
	 */
	public static void w(String tag, Object object) {
		printerService.w(tag, object);
	}

	/**
	 *
	 * @param message the message will be printed
	 */
	public static void w(String message) {
		printerService.w(message);
	}

	/**
	 *
	 * @param tag  the log tag
	 * @param message the message will be printed
	 */
	public static void w(String tag, String message) {
		printerService.w(tag, message);
	}

	/**
	 *
	 * @param object the object will be printed
	 */
	public static void e(Object object) {
		printerService.e(object);
	}

	/**
	 *
	 * @param tag  the log tag
	 * @param object the object will be printed
	 */
	public static void e(String tag, Object object) {
		printerService.e(object);
	}

	/**
	 *
	 * @param message the message will be printed
	 */
	public static void e(String message) {
		printerService.e(message);
	}

	/**
	 *
	 * @param tag  the log tag
	 * @param message the message will be printed
	 */
	public static void e(String tag, String message) {
		printerService.e(tag, message);
	}

	/**
	 *
	 * @param json the json will be printed
	 */
	public static void json(String json) {
		printerService.json(json);
	}

	/**
	 *
	 * @param tag  the log tag
	 * @param json the json will be printed
	 */
	public static void json(String tag, String json) {
		printerService.json(tag, json);
	}

	/**
	 *
	 * @param xml the xml will be printed
	 */
	public static void xml(String xml) {
		printerService.xml(xml);
	}

	/**
	 *
	 * @param tag  the log tag
	 * @param xml the xml will be printed
	 */
	public static void xml(String tag, String xml) {
		printerService.xml(tag, xml);
	}

}
