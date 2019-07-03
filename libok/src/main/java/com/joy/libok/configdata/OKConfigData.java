package com.joy.libok.configdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cache;
import okhttp3.CookieJar;
import okhttp3.Interceptor;

public class OKConfigData {

	private String okHttpBaseUrl = "";
	private String urlHeadTag = "";
	private HashMap<String, String> okHttpBaseUrlMap = new HashMap<>();
	private HashMap<String, String> okHttpHeadersMap = new HashMap<>();
	private HashMap<String, String> mockDataMap = new HashMap<>();

	private int connectTimeout = 10;
	private int readTimeout = 10;
	private int writeTimeout = 10;
	private boolean isPrintLog = true;
	private List<Interceptor> interceptors = new ArrayList<>();
	private List<Interceptor> netInterceptors = new ArrayList<>();
	private CookieJar cookiesJar = CookieJar.NO_COOKIES;
	private Cache cache;


	public String getOkHttpBaseUrl() {
		return okHttpBaseUrl;
	}

	public void setOkHttpBaseUrl(String okHttpBaseUrl) {
		this.okHttpBaseUrl = okHttpBaseUrl;
	}

	public HashMap<String, String> getOkHttpBaseUrlMap() {
		return okHttpBaseUrlMap;
	}

	public void setOkHttpBaseUrlMap(HashMap<String, String> okHttpBaseUrlMap) {
		this.okHttpBaseUrlMap = okHttpBaseUrlMap;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public int getWriteTimeout() {
		return writeTimeout;
	}

	public void setWriteTimeout(int writeTimeout) {
		this.writeTimeout = writeTimeout;
	}

	public boolean isPrintLog() {
		return isPrintLog;
	}

	public void setPrintLog(boolean printLog) {
		isPrintLog = printLog;
	}

	public String getUrlHeadTag() {
		return urlHeadTag;
	}

	public void setUrlHeadTag(String urlHeadTag) {
		this.urlHeadTag = urlHeadTag;
	}

	public HashMap<String, String> getOkHttpHeadersMap() {
		return okHttpHeadersMap;
	}

	public void setOkHttpHeadersMap(HashMap<String, String> okHttpHeadersMap) {
		this.okHttpHeadersMap = okHttpHeadersMap;
	}

	public HashMap<String, String> getMockDataMap() {
		return mockDataMap;
	}

	public void setMockDataMap(HashMap<String, String> mockDataMap) {
		this.mockDataMap = mockDataMap;
	}

	public CookieJar getCookiesJar() {
		return cookiesJar;
	}

	public void setCookiesJar(CookieJar cookiesJar) {
		this.cookiesJar = cookiesJar;
	}

	public List<Interceptor> getInterceptors() {
		return interceptors;
	}

	public void setInterceptors(List<Interceptor> interceptors) {
		this.interceptors = interceptors;
	}

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public List<Interceptor> getNetInterceptors() {
		return netInterceptors;
	}

	public void setNetInterceptors(List<Interceptor> netInterceptors) {
		this.netInterceptors = netInterceptors;
	}
}
