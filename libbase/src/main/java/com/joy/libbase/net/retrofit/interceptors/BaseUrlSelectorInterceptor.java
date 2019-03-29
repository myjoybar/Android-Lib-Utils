package com.joy.libbase.net.retrofit.interceptors;

import com.joy.libbase.config.LibConfigManager;
import com.joy.libbase.net.retrofit.config.OKConfigData;

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

		OKConfigData okConfigData = null;
		if((okConfigData = LibConfigManager.getInstance().getOkConfigData())!=null){
			List<String> hostList = originalRequest.headers(okConfigData.getUrlHeadTag());
			if (hostList != null && hostList.size() > 0) {
				builder.removeHeader(okConfigData.getUrlHeadTag());
				String hostName = hostList.get(0);
				String setUrl = okConfigData.getOkHttpBaseUrlMap().get(hostName);
				HttpUrl baseURL = HttpUrl.parse(setUrl);
				HttpUrl newHttpUrl = oldUrl.newBuilder().scheme(baseURL.scheme()).host(baseURL.host()).port(baseURL.port()).build();
				Request newRequest = builder.url(newHttpUrl).build();
				return chain.proceed(newRequest);
			}
		}

		return chain.proceed(originalRequest);

	}
}
