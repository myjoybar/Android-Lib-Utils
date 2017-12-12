package com.joybar.library.common.log;

/**
 * Created by joybar on 2015/01/16.
 */

public class Logger {

	static PrinterService printerService = new PrinterService();

	public static void v(Object object) {
		printerService.v(object);
	}

	public static void v(String tag, Object object) {
		printerService.v(tag, object);
	}

	public static void v(String tag, String message) {
		printerService.v(tag, message);
	}

	public static void v(String message, Object... args) {
		printerService.v(message, args);
	}

	public static void v(String tag, String message, Object... args) {
		printerService.v(tag, message, args);
	}

	public static void i(Object object) {
		printerService.v(object);
	}

	public static void i(String tag, Object object) {
		printerService.i(tag, object);
	}

	public static void i(String tag, String message) {
		printerService.i(tag, message);
	}


	public static void i(String message, Object... args) {
		printerService.i(message, args);
	}

	public static void i(String tag, String message, Object... args) {
		printerService.i(tag, message, args);
	}

	public static void d(Object object) {
		printerService.v(object);
	}

	public static void d(String tag, Object object) {
		printerService.d(tag, object);
	}
	public static void d(String tag, String message) {
		printerService.d(tag, message);
	}

	public static void d(String message, Object... args) {
		printerService.d(message, args);
	}

	public static void d(String tag, String message, Object... args) {
		printerService.d(tag, message, args);
	}


	public static void w(Object object) {
		printerService.w(object);
	}

	public static void w(String tag, Object object) {
		printerService.w(tag, object);
	}

	public static void w(String tag, String message) {
		printerService.w(tag, message);
	}

	public static void w(String message, Object... args) {
		printerService.w(message, args);
	}

	public static void w(String tag, String message, Object... args) {
		printerService.w(tag, message, args);
	}


	public static void e(Object object) {
		printerService.e(object);
	}

	public static void e(String tag, Object object) {
		printerService.e(object);
	}
	public static void e(String tag, String message) {
		printerService.e(tag, message);
	}

	public static void e(String message, Object... args) {
		printerService.e(message, args);
	}

	public static void e(String tag, String message, Object... args) {
		printerService.e(tag, message, args);
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
