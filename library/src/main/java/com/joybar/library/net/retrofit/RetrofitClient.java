package com.joybar.library.net.retrofit;

import com.joybar.library.net.retrofit.config.RetrofitConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by joybar on 5/23/16.
 */
public class RetrofitClient {

	private static RetrofitClient mClient;
	private Retrofit mRetrofit;
	private Map<String, String> mHeaders;
	private RetrofitClient() {
		init();
	}

	public static RetrofitClient getClient() {
		if (null == mClient) mClient = new RetrofitClient();
		return mClient;
	}

	private Interceptor mInterceptor = new Interceptor() {
		@Override
		public Response intercept(Chain chain) throws IOException {
			Request oldRequest = chain.request();
			Request.Builder build = oldRequest.newBuilder()
					.method(oldRequest.method(), oldRequest.body())
					.url(oldRequest.url());
			for (String k : mHeaders.keySet()) {
				build.addHeader(k, mHeaders.get(k));
			}
			return chain.proceed(build.build());
		}
	};

	public void init() {
		mHeaders = new HashMap<>();
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.addInterceptor(mInterceptor)
				.addInterceptor(loggingInterceptor)
				.connectTimeout(RetrofitConfig.CONNECT_TIMEOUT,TimeUnit.SECONDS)
				.readTimeout(RetrofitConfig.READ_TIMEOUT, TimeUnit.SECONDS)
				.writeTimeout(RetrofitConfig.WRITE_TIMEOUT, TimeUnit.SECONDS)
				.build();

		mRetrofit = new Retrofit.Builder()
				.baseUrl(RetrofitConfig.BASE_URL)
				.client(okHttpClient)
				.addConverterFactory(GsonConverterFactory.create())
				//.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build();
	}

	private boolean isInit() {
		return null != mRetrofit;
	}

	public <T> T create(final Class<T> service) {
		if (!isInit()){
			throw new NullPointerException("RetrofitClient is not be initialized");
		}
		return mRetrofit.create(service);
	}

}
