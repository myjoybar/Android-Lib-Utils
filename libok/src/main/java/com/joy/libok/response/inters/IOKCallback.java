package com.joy.libok.response.inters;

import com.joy.libok.handler.OKGlobalHandler;
import com.joy.libok.test.log.LLog;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class IOKCallback<T> implements Callback {
	private static String TAG = "IOKCallback";
	private IResponseHandler mResponseHandler;

	public IOKCallback(IResponseHandler responseHandler) {
		mResponseHandler = responseHandler;
	}

	@Override
	public void onFailure(Call call, final IOException e) {
		LLog.e(TAG, "request  " + call.request().url() + " fail, error msg = " + e.getMessage());
		OKGlobalHandler.getInstance().post(new Runnable() {
			@Override
			public void run() {
				if (null != mResponseHandler) {
					mResponseHandler.onFailure(0, e.getMessage());
				}
			}
		});

	}

	@Override
	public void onResponse(Call call, final Response response) throws IOException {
		if (response.isSuccessful()) {
			if (null != mResponseHandler) {
				mResponseHandler.onSuccess(response);
			}
		} else {
			OKGlobalHandler.getInstance().post(new Runnable() {
				@Override
				public void run() {
					if (null != mResponseHandler) {
						mResponseHandler.onFailure(response.code(), "fail status=" + response.code());
					}
				}
			});
		}
	}
}
