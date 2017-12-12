package com.joybar.library.common.log;

/**
 * Created by joybar on 2017/12/12.
 */

public interface IPrinter {

	void v(Object object);
	void v(String tag,Object object);

	void v(String tag,String message);
	void v(String message, Object... args);
	void v(String tag,String message, Object... args);

	void i(Object object);
	void i(String tag,Object object);
	void i(String tag,String message);
	void i(String message, Object... args);
	void i(String tag,String message, Object... args);

	void d(Object object);
	void d(String tag,Object object);
	void d(String tag,String message);
	void d(String message, Object... args);
	void d(String tag,String message, Object... args);


	void w(Object object);
	void w(String tag,Object object);
	void w(String tag,String message);
	void w(String message, Object... args);
	void w(String tag,String message, Object... args);


	void e(Object object);
	void e(String tag,Object object);
	void e(String tag,String message);
	void e(String message, Object... args);
	void e(String tag,String message, Object... args);

	void json(String json);
	void json(String tag,String json);

	void xml(String xml);
	void xml(String tag,String xml);
}
