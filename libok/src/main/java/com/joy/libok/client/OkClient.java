package com.joy.libok.client;

import com.joy.libok.config.OKConfigData;
import com.joy.libok.interceptors.BaseUrlSelectorInterceptor;
import com.joy.libok.interceptors.HeaderInterceptor;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkClient {

	private OkHttpClient mOkHttpClient;

	private static class OkClientHolder {
		private static OkClient INSTANCE = new OkClient();
	}

	public static OkClient getInstance() {
		return OkClientHolder.INSTANCE;
	}


	public void init(OKConfigData okConfigData) {
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

		if (okConfigData.isPrintLog()) {
			loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		}
		mOkHttpClient = new OkHttpClient.Builder()
				.addInterceptor(new HeaderInterceptor(okConfigData.getOkHttpHeadersMap()))
				.addInterceptor(new BaseUrlSelectorInterceptor(okConfigData))
				.addInterceptor(loggingInterceptor)
				.connectTimeout(okConfigData.getConnectTimeout(), TimeUnit.SECONDS)
				.readTimeout(okConfigData.getReadTimeout(), TimeUnit.SECONDS)
				.writeTimeout(okConfigData.getWriteTimeout(), TimeUnit.SECONDS)
				.hostnameVerifier(new HostnameVerifier() {
					@Override
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				})
				.build();

	}

	public OkHttpClient getOkHttpClient() {
		return mOkHttpClient;
	}
}
