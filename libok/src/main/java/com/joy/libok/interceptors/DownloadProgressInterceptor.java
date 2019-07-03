package com.joy.libok.interceptors;

import com.joy.libok.response.body.ResponseProgressBody;
import com.joy.libok.response.callback.IResponseCallBackHandler;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by joybar on 2019/2/12.
 */
public class DownloadProgressInterceptor implements Interceptor {
	private IResponseCallBackHandler responseCallBackHandler;
	public DownloadProgressInterceptor(IResponseCallBackHandler responseCallBackHandler) {
		this.responseCallBackHandler = responseCallBackHandler;
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		Response originalResponse = chain.proceed(chain.request());
		return originalResponse.newBuilder()
				.body(new ResponseProgressBody(originalResponse.body(), responseCallBackHandler))
				.build();
	}
}