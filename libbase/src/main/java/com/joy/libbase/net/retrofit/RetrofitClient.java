package com.joy.libbase.net.retrofit;


import com.joy.libbase.config.LibConfigManager;
import com.joy.libbase.net.retrofit.config.OKConfigData;
import com.joy.libbase.net.retrofit.interceptors.BaseUrlSelectorInterceptor;
import com.joy.libbase.net.retrofit.interceptors.HeaderInterceptor;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by joybar on 5/23/16.
 */
public class RetrofitClient {


	private static RetrofitClient mClient;
	private Retrofit mRetrofit;

	private RetrofitClient() {
		init();
	}

	public static RetrofitClient getClient() {
		if (null == mClient) {
			mClient = new RetrofitClient();
		}
		return mClient;
	}


	public void init() {
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();


		OKConfigData okConfigData = LibConfigManager.getInstance().getOkConfigData();

		if (okConfigData.isPrintLog()) {
			loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		}


		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.addInterceptor(new HeaderInterceptor(okConfigData.getOkHttpHeadersMap()))
				.addInterceptor(new BaseUrlSelectorInterceptor())
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


		mRetrofit = new Retrofit.Builder()
				.baseUrl(okConfigData.getOkHttpBaseUrl())
				.client(okHttpClient)
				.addConverterFactory(GsonConverterFactory.create())
				//.addConverterFactory(ConverterFactory.create())
				//.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build();
	}

	private boolean isInit() {
		return null != mRetrofit;
	}

	public <T> T create(final Class<T> service) {
		if (!isInit()) {
			throw new NullPointerException("RetrofitClient is not be initialized");
		}
		return mRetrofit.create(service);
	}

}
