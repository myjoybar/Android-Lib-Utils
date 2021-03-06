package com.joybar.library.net.retrofit.interceptors;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by joybar on 2019/2/12.
 */

public class HeaderInterceptor implements Interceptor {
	private Map<String, String> mHeaders = new HashMap<>();
	public HeaderInterceptor() {

	}
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
}
