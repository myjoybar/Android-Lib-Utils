package com.joy.libbase.net.retrofit.config;

/**
 * Created by joybar on 2017/11/2.
 */

public class RetrofitConfig {

	// ----------------------可配置部分start----------------------
	public static String BASE_URL_GOOGLE_PAY_DEBUG = "http://54.241.17.76:8080/";
	public static String BASE_URL_GOOGLE_PAY_RELEASE = "http://lbAntiFraudService-925942997.us-west-1.elb.amazonaws.com:8080/";

	public static String BASE_URL_IG_DT_SERVER_DEBUG = "http://54.241.20.16:8080/";
	public static String BASE_URL_IG_DT_SERVER_RELEASE = "https://api.getinsta.me/";

	public static String BASE_URL_GOOGLE_PAY_HEADER_TAG = "google_pay";
	public static String BASE_URL_IG_DT_HEADER_TAG = "ig_dt";
	public static String BASE_URL_HEADER_TAG = "host";

	public static int CONNECT_TIMEOUT = 10;
	public static int READ_TIMEOUT = 10;
	public static int WRITE_TIMEOUT = 10;
	public static boolean IS_DEBUG_URL = false;
	public static boolean IS_PRINT_OK_LOG = false;

	// ----------------------可配置部分end----------------------


	public static String getGooglePayUrl() {
		if (IS_DEBUG_URL) {
			return BASE_URL_GOOGLE_PAY_DEBUG;
		}
		return BASE_URL_GOOGLE_PAY_RELEASE;
	}

	public static String getIGDtUrl() {
		if (IS_DEBUG_URL) {
			return BASE_URL_IG_DT_SERVER_DEBUG;
		}
		return BASE_URL_IG_DT_SERVER_RELEASE;
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
