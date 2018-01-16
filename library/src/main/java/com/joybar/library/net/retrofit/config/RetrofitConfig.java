package com.joybar.library.net.retrofit.config;

/**
 * Created by joybar on 2017/11/2.
 */

public class RetrofitConfig {

	public static final String ON_LINE_URL = "http://api.myrightone.com/api/";
	public static final String DEBUG_URL = "http://devserver.api.myrightone.com/api/";
	public static String BASE_URL = "http://50.18.106.42:8080/billing/paytm/";
	public static int CONNECT_TIMEOUT = 30;
	public static int READ_TIMEOUT = 30;
	public static int WRITE_TIMEOUT = 30;
	public static boolean IS_DEBUG = false;

	public static void setDebug(boolean isDebug) {
		IS_DEBUG = isDebug;
	}
	public static void setBaseUrl(String baseUrl) {
		BASE_URL = baseUrl;
	}

	public static void setConnectTimeout(int connectTimeout) {
		CONNECT_TIMEOUT = connectTimeout;
	}

	public static void setReadTimeout(int readTimeout) {
		READ_TIMEOUT = readTimeout;
	}

	public static void setWriteTimeout(int writeTimeout) {
		WRITE_TIMEOUT = writeTimeout;
	}
}
