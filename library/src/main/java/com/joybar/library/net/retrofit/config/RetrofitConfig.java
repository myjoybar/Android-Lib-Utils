package com.joybar.library.net.retrofit.config;

/**
 * Created by joybar on 2017/11/2.
 */

public class RetrofitConfig {


	public static String BASE_URL_GOOGLE_PAY_DEBUG = "http://54.241.20.16:8080/";
	public static String BASE_URL_GOOGLE_PAY_RELEASE = "http://54.241.20.16:8080/";

	public static String getGooglePayUrl() {
		if (IS_DEBUG) {
			return BASE_URL_GOOGLE_PAY_DEBUG;
		}
		return BASE_URL_GOOGLE_PAY_RELEASE;
	}

	public static String BASE_URL_GOOGLE_PAY_HEADER_TAG = "google_pay";
	public static String BASE_URL_HEADER_TAG = "host";

	public static int CONNECT_TIMEOUT = 10;
	public static int READ_TIMEOUT = 10;
	public static int WRITE_TIMEOUT = 10;
	public static boolean IS_DEBUG = true;

	public static void setDebug(boolean isDebug) {
		IS_DEBUG = isDebug;
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
