package com.joy.libok.interceptors;

import android.text.TextUtils;

import com.joy.libok.OkHttpManager;
import com.joy.libok.utils.ReflectionUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Joy
 * @description 主要根据请求的时间戳判断，是否需要丢弃response，实质上是把code置为一个无效值
 * @date 2019/5/16
 */
public class ClearInvalidResponseInterceptor implements Interceptor {
	private long requestStartTimeStamp;
	private static final int INVALID_CODE = 305;

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();
		String requestTime = request.headers().get("Date");
		if (!TextUtils.isEmpty(requestTime)) {
			try {
				requestStartTimeStamp = Long.parseLong(requestTime);
			} catch (Exception e) {
				requestStartTimeStamp = System.currentTimeMillis();
			}

		} else {
			requestStartTimeStamp = System.currentTimeMillis();
		}
		Response response = chain.proceed(request);
		if (requestStartTimeStamp < OkHttpManager.getInstance().getInvalidRequestTimeStamp()) {
			try {
				//Utils.setProperty(response, "code", INVALID_CODE);
				ReflectionUtils.setPrivateField(response, "code", INVALID_CODE);
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}


		return response;

	}


}
