package com.joy.libbase.net.retrofit.interceptors;

import com.joy.libbase.net.retrofit.config.RetrofitConfig;

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
		List<String> hostList = originalRequest.headers(RetrofitConfig.BASE_URL_HEADER_TAG);
		if (hostList != null && hostList.size() > 0) {
			builder.removeHeader(RetrofitConfig.BASE_URL_HEADER_TAG);
			String hostName = hostList.get(0);
			HttpUrl baseURL=null;
			if (RetrofitConfig.BASE_URL_GOOGLE_PAY_HEADER_TAG.equals(hostName)) {
				baseURL = HttpUrl.parse(RetrofitConfig.getGooglePayUrl());
			} else if (RetrofitConfig.BASE_URL_IG_DT_HEADER_TAG.equals(hostName)) {
				baseURL = HttpUrl.parse(RetrofitConfig.getIGDtUrl());
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
