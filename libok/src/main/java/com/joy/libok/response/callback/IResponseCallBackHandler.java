package com.joy.libok.response.callback;

import okhttp3.Headers;
import okhttp3.Response;

public interface IResponseCallBackHandler {

	void onStart();

	void onSuccess(Response response);

	void onFailure(int errorCode, String errorMsg);

	void onGetHeaders(Headers headers);

	void onProgress(long currentBytes, long totalBytes);

	void onEnd();

}
