package com.joy.libok.response.callback;

import com.joy.libok.handler.OKGlobalHandler;
import com.joy.libok.test.log.LLog;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class CommonOKCallback<T> implements Callback {
	private static String TAG = "CommonOKCallback";
	private final IResponseCallBackHandler mResponseHandler;

	public CommonOKCallback(IResponseCallBackHandler responseHandler) {
		mResponseHandler = responseHandler;
		OKGlobalHandler.getInstance().post(new Runnable() {
			@Override
			public void run() {
				mResponseHandler.onStart();
			}
		});
	}

	@Override
	public void onFailure(Call call, final IOException e) {
		LLog.d(TAG, String.format("request url %s fail, error msg %s", call.request().url(), e.getMessage()));
		OKGlobalHandler.getInstance().post(new Runnable() {
			@Override
			public void run() {
				mResponseHandler.onFailure(0, e.getMessage());
				mResponseHandler.onFinish();
			}
		});
	}


	@Override
	public void onResponse(final Call call, final Response response) {
		if (response.isSuccessful()) {
			LLog.d(TAG, String.format("request url %s success and start to parse the response data", call.request().url()));
			mResponseHandler.onSuccess(response);// 数据解析仍然留在子线程，然后再交给responseHandler解析
			OKGlobalHandler.getInstance().post(new Runnable() {
				@Override
				public void run() {
					mResponseHandler.onFinish();
				}
			});

		} else {
			final ResponseBody responseBody = response.body();
			try {
				final String responseStr = responseBody.string();
				OKGlobalHandler.getInstance().post(new Runnable() {
					@Override
					public void run() {
						mResponseHandler.onFailure(response.code(),responseStr);
						mResponseHandler.onFinish();
					}
				});

			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				responseBody.close();
			}

		}
	}

}
