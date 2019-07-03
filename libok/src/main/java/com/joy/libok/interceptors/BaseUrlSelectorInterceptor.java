package com.joy.libok.interceptors;

import java.io.IOException;
import java.util.List;

import com.joy.libok.configdata.OKConfigData;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by joybar on 2019/2/12.
 */

public class BaseUrlSelectorInterceptor implements Interceptor {

	private OKConfigData mOKConfigData;

	public BaseUrlSelectorInterceptor(OKConfigData OKConfigData) {
		mOKConfigData = OKConfigData;
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request originalRequest = chain.request();
		HttpUrl oldUrl = originalRequest.url();
		Request.Builder builder = originalRequest.newBuilder();
		if (mOKConfigData != null) {
			List<String> hostList = originalRequest.headers(mOKConfigData.getUrlHeadTag());
			if (hostList != null && hostList.size() > 0) {
				builder.removeHeader(mOKConfigData.getUrlHeadTag());
				String hostName = hostList.get(0);
				String setUrl = mOKConfigData.getOkHttpBaseUrlMap().get(hostName);
				HttpUrl baseURL = HttpUrl.parse(setUrl);
				HttpUrl newHttpUrl = oldUrl.newBuilder().scheme(baseURL.scheme()).host(baseURL.host()).port(baseURL.port()).build();
				Request newRequest = builder.url(newHttpUrl).build();
				return chain.proceed(newRequest);
			}
		}

		return chain.proceed(originalRequest);

	}
}
