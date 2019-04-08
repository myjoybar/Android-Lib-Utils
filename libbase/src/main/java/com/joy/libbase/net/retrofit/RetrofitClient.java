package com.joy.libbase.net.retrofit;


import com.joy.libbase.config.LibConfigManager;
import com.joy.libok.client.OkClient;
import com.joy.libok.configdata.OKConfigData;

import okhttp3.OkHttpClient;
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


		OKConfigData okConfigData = LibConfigManager.getInstance().getOkConfigData();
		OkClient.getInstance().init(okConfigData);
		OkHttpClient client = OkClient.getInstance().getOkHttpClient();
//		OkHttpClient okHttpClient = new OkHttpClient.Builder()
//				.addInterceptor(new HeaderInterceptor(okConfigData.getOkHttpHeadersMap()))
//				.addInterceptor(new BaseUrlSelectorInterceptor(okConfigData))
//				.addInterceptor(loggingInterceptor)
//				.connectTimeout(okConfigData.getConnectTimeout(), TimeUnit.SECONDS)
//				.readTimeout(okConfigData.getReadTimeout(), TimeUnit.SECONDS)
//				.writeTimeout(okConfigData.getWriteTimeout(), TimeUnit.SECONDS)
//				.hostnameVerifier(new HostnameVerifier() {
//					@Override
//					public boolean verify(String hostname, SSLSession session) {
//						return true;
//					}
//				})
//				.build();

		mRetrofit = new Retrofit.Builder()
				.baseUrl(okConfigData.getOkHttpBaseUrl())
				.client(client)
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
