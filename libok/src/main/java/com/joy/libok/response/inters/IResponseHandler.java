package com.joy.libok.response.inters;

import okhttp3.Response;

public interface IResponseHandler {

	void onSuccess(Response response);

	void onFailure(int errorCode, String errorMsg);

	void onProgress(long currentBytes, long totalBytes);
}
