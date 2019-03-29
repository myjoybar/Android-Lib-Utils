package com.joy.libok.config;

import java.util.HashMap;

public class OKConfigData {

	private String okHttpBaseUrl = "";
	private String urlHeadTag = "";
	private HashMap<String, String> okHttpBaseUrlMap = new HashMap<>();
	private HashMap<String, String> okHttpHeadersMap = new HashMap<>();
	private int connectTimeout = 10;
	private int readTimeout = 10;
	private int writeTimeout = 10;
	private boolean isPrintLog = false;


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
}
