package com.joy.libok.response;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.joy.libok.handler.OKGlobalHandler;
import com.joy.libok.response.inters.IResponseHandler;
import com.joy.libok.test.log.LLog;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class GsonResponseHandler<T> implements IResponseHandler {
	private static String TAG = "GsonResponseHandler";
	private Type mType;

	private Type getType() {
		return mType;
	}


	public GsonResponseHandler() {
		Type myclass = getClass().getGenericSuperclass();    //反射获取带泛型的class
		if (myclass instanceof Class) {
			throw new RuntimeException("Missing type parameter.");
		}
		ParameterizedType parameter = (ParameterizedType) myclass;      //获取所有泛型
		mType = $Gson$Types.canonicalize(parameter.getActualTypeArguments()[0]);  //将泛型转为type
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
			onFailure(0, "responseBody.string() occurs error, error msg = " + e.getMessage());
			return;
		} finally {
			responseBody.close();
		}

		LLog.e(TAG, "responseBodyStr =   " + responseBodyStr);

		try {

			Gson gson = new Gson();
			final T gsonResponse = (T) gson.fromJson(responseBodyStr, getType());
			OKGlobalHandler.getInstance().post(new Runnable() {
				@Override
				public void run() {
					onSuccess(response.code(), gsonResponse);
				}
			});


		} catch (Exception e) {
			onFailure(0, "try to parse the json response data occurs error, error msg = " + e.getMessage());
		}


	}


	public abstract void onSuccess(int statusCode, T response);


	@Override
	public void onProgress(long currentBytes, long totalBytes) {

	}
}
