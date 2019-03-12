package com.joybar.library.net.retrofit;

import com.joybar.library.net.retrofit.config.RetrofitConfig;
import com.joybar.library.net.retrofit.interceptors.BaseUrlSelectorInterceptor;
import com.joybar.library.net.retrofit.interceptors.HeaderInterceptor;
import com.joybar.library.net.retrofit.jsonconvert.ConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

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
		if(RetrofitConfig.IS_DEBUG){
			loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		}
		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.addInterceptor(new HeaderInterceptor())
				.addInterceptor(new BaseUrlSelectorInterceptor())
				.addInterceptor(loggingInterceptor)
				.connectTimeout(RetrofitConfig.CONNECT_TIMEOUT,TimeUnit.SECONDS)
				.readTimeout(RetrofitConfig.READ_TIMEOUT, TimeUnit.SECONDS)
				.writeTimeout(RetrofitConfig.WRITE_TIMEOUT, TimeUnit.SECONDS)
				.build();

		mRetrofit = new Retrofit.Builder()
				.baseUrl(RetrofitConfig.getGooglePayUrl())
				.client(okHttpClient)
//				.addConverterFactory(GsonConverterFactory.create())
				.addConverterFactory(ConverterFactory.create())
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

