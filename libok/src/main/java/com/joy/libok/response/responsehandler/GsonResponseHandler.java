package com.joy.libok.response.responsehandler;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.joy.libok.handler.OKGlobalHandler;
import com.joy.libok.response.callback.IResponseCallBackHandler;
import com.joy.libok.test.log.LLog;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class GsonResponseHandler<T> implements IResponseCallBackHandler {
	private static String TAG = "GsonResponseHandler";
	private Type mType;

	private Type getType() {
		return mType;
	}

	public GsonResponseHandler() {
		Type type = getClass().getGenericSuperclass();    //反射获取带泛型的class
		if (type instanceof Class) {
			throw new RuntimeException("Missing type parameter.");
		}
		ParameterizedType parameter = (ParameterizedType) type;      //获取所有泛型
		mType = $Gson$Types.canonicalize(parameter.getActualTypeArguments()[0]);  //将泛型转为type
	}


	@Override
	public void onStart() {

	}

	@Override
	public void onSuccess(final Response response) {
		ResponseBody responseBody = response.body();
		if (null == responseBody) {
			onFailure(0, "response.body is empty");
			return;
		}
		String responseBodyStr = "";
		try {
			responseBodyStr = responseBody.string();
		} catch (IOException e) {
			e.printStackTrace();
			onFailure(0, String.format("responseBody.string() occurs error, error msg = %s", e.getMessage()));
			return;
		} finally {
			responseBody.close();
		}
		LLog.d(TAG, " responseBody.string =   " + responseBodyStr);
		try {
			Gson gson = new Gson();
			final T gsonResponse = (T) gson.fromJson(responseBodyStr, getType());
			OKGlobalHandler.getInstance().post(new Runnable() {
				@Override
				public void run() {
					LLog.d(TAG, "parse the json response data success");
					onSuccess(response.code(), gsonResponse);
				}
			});

		} catch (final Exception e) {
			OKGlobalHandler.getInstance().post(new Runnable() {
				@Override
				public void run() {
					LLog.d(TAG, "parse the json response data  occurs error");
					onFailure(0, String.format("parse the json response data occurs error, error msg = %s", e.getMessage()));

				}
			});

		}
	}

	@Override
	public void onFailure(int errorCode, String errorMsg) {
		LLog.d(TAG, String.format("errorCode= %s , errorMsg = %s", errorCode, errorMsg));
	}

	@Override
	public void onGetHeaders(Headers headers) {

	}

	public abstract void onSuccess(int statusCode, T response);


	@Override
	public void onProgress(long currentBytes, long totalBytes) {

	}

	@Override
	public void onEnd() {

	}
}
