package com.joybar.library.net.retrofit.interceptors;

import com.joybar.library.net.retrofit.config.RetrofitConfig;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by joybar on 2019/2/12.
 */

public class BaseUrlSelectorInterceptor implements Interceptor {
	@Override
	public Response intercept(Chain chain) throws IOException {
		Request originalRequest = chain.request();
		HttpUrl oldUrl = originalRequest.url();
		Request.Builder builder = originalRequest.newBuilder();
		List<String> urlnameList = originalRequest.headers("urlname");
		if (urlnameList != null && urlnameList.size() > 0) {
			builder.removeHeader(RetrofitConfig.BASE_URL_HEADER_TAG);
			String urlname = urlnameList.get(0);
			HttpUrl baseURL=null;
			if (RetrofitConfig.BASE_URL_GOOGLE_PAY_HEADER_TAG.equals(urlname)) {
				baseURL = HttpUrl.parse(RetrofitConfig.getGooglePayUrl());
			} else{
				baseURL = HttpUrl.parse(RetrofitConfig.getGooglePayUrl());
			}
			HttpUrl newHttpUrl = oldUrl.newBuilder()
					.scheme(baseURL.scheme())
					.host(baseURL.host())
					.port(baseURL.port())
					.build();
			Request newRequest = builder.url(newHttpUrl).build();
			return  chain.proceed(newRequest);
		}else{
			return chain.proceed(originalRequest);
		}

	}
}
