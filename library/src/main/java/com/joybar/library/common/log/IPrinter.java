package com.joybar.library.common.log;

/**
 * Created by joybar on 2017/12/12.
 */

public interface IPrinter {

	void v(Object object);
	void v(String tag,Object object);

	void v(String message);
	void v(String tag,String message);

	void i(Object object);
	void i(String tag,Object object);
	void i(String message);
	void i(String tag,String message);

	void d(Object object);
	void d(String tag,Object object);
	void d(String message);
	void d(String tag,String message);


	void w(Object object);
	void w(String tag,Object object);
	void w(String message);
	void w(String tag,String message);


	void e(Object object);
	void e(String tag,Object object);
	void e(String message);
	void e(String tag,String message);

	void json(String json);
	void json(String tag,String json);

	void xml(String xml);
	void xml(String tag,String xml);
}
