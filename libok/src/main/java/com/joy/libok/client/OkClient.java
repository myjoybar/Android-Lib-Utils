package com.joy.libok.client;

import com.joy.libok.configdata.OKConfigData;
import com.joy.libok.interceptors.BaseUrlSelectorInterceptor;
import com.joy.libok.interceptors.HeaderInterceptor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkClient {

	private OkHttpClient mOkHttpClient;
	Builder builder;
	private static class OkClientHolder {
		private static OkClient INSTANCE = new OkClient();
	}

	public static OkClient getInstance() {
		return OkClientHolder.INSTANCE;
	}


	public void init(OKConfigData okConfigData) {

		if (null != mOkHttpClient) {
			return;
		}
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

		if (okConfigData.isPrintLog()) {
			loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		}
		builder = new Builder();
		List<Interceptor> interceptors = okConfigData.getInterceptors();
		for (Interceptor interceptor : interceptors) {
			builder.addInterceptor(interceptor);
		}
		builder
			.cookieJar(okConfigData.getCookiesJar())
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
			});


		mOkHttpClient = builder.build();


	}

	public OkHttpClient getOkHttpClient() {
		if (null == mOkHttpClient) {
			throw new RuntimeException("You must init OkClient before use it");
		}
		return mOkHttpClient;
	}
}
