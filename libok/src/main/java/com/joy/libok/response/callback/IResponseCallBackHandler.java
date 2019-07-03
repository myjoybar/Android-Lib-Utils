package com.joy.libok.response.callback;

import okhttp3.Response;

public interface IResponseCallBackHandler {

	void onStart();

	void onCancel();

	void onSuccess(Response response);

	void onFailure(int errorCode, String errorMsg);

	void onProgress(long totalBytes, long currentBytes);

	void onFinish();

}
