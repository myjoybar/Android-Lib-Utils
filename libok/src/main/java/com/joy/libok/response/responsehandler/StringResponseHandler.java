package com.joy.libok.response.responsehandler;

import com.joy.libok.handler.OKGlobalHandler;
import com.joy.libok.response.callback.IResponseCallBackHandler;
import com.joy.libok.test.log.LLog;

import java.io.IOException;

import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class StringResponseHandler implements IResponseCallBackHandler {
	private static String TAG = "StringResponseHandler";


	public StringResponseHandler() {

	}

	@Override
	public void onStart() {

	}

	@Override
	public void onCancel() {

	}

	@Override
	public void onSuccess(final Response response) {
		ResponseBody responseBody = response.body();
		if (null == responseBody) {
			OKGlobalHandler.getInstance().post(new Runnable() {
				@Override
				public void run() {
					onFailure(0, "response.body is empty");

				}
			});
			return;
		}

		String responseBodyStr = "";
		try {
			responseBodyStr = responseBody.string();
		} catch (final IOException e) {
			e.printStackTrace();
			OKGlobalHandler.getInstance().post(new Runnable() {
				@Override
				public void run() {
					onFailure(0, String.format("responseBody.string() occurs error, error msg = %s", e.getMessage()));

				}
			});
			return;

		} finally {
			responseBody.close();
		}
		LLog.d(TAG, " responseBody.string =   " + responseBodyStr);

		final String finalResponseBodyStr = responseBodyStr;
		OKGlobalHandler.getInstance().post(new Runnable() {
			@Override
			public void run() {
				onSuccess(response.code(), finalResponseBodyStr);
			}
		});


	}

	@Override
	public void onFailure(int errorCode, String errorMsg) {
		LLog.d(TAG, String.format("errorCode= %s , errorMsg = %s", errorCode, errorMsg));
	}


	public abstract void onSuccess(int statusCode, String response);


	@Override
	public void onProgress(long currentBytes, long totalBytes) {

	}

	@Override
	public void onFinish() {

	}
}
