package com.joy.libok;


import com.joy.libok.client.OkClient;
import com.joy.libok.configdata.OKConfigData;
import com.joy.libok.request.DownloadBuilder;
import com.joy.libok.request.GetRequestBuilder;
import com.joy.libok.request.PostRequestBuilder;

import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

public class OkHttpManager {


	private long invalidRequestTimeStamp;

	private static class OkHttpManagerHolder {

		private static OkHttpManager INSTANCE = new OkHttpManager();
	}

	public static OkHttpManager getInstance() {
		return OkHttpManagerHolder.INSTANCE;
	}

	public void init(OKConfigData okConfigData) {
		OkClient.getInstance().init(okConfigData);
	}

	public OkHttpClient getOkHttpClient() {
		return OkClient.getInstance().getOkHttpClient();
	}

	public GetRequestBuilder get(String url) {
		return new GetRequestBuilder(url);
	}

	public PostRequestBuilder post(String url) {
		return new PostRequestBuilder(url);
	}

	public DownloadBuilder download(String url) {
		return new DownloadBuilder(url);
	}


	/**
	 * cancel one request by a tag
	 *
	 * @param tag
	 */
	public void cancelCallByTag(String tag) {
		Dispatcher dispatcher = getOkHttpClient().dispatcher();
		for (Call call : dispatcher.queuedCalls()) {
			if (tag.equals(call.request().tag())) {


			}
		}
		for (Call call : dispatcher.runningCalls()) {
			if (tag.equals(call.request().tag())) {
				if (!call.isCanceled()) {
					call.cancel();
				}
			}
		}
	}

	/**
	 * cancel all request in the readyAsyncCalls and runningAsyncCalls
	 */
	public void cancelAllCall() {
		Dispatcher dispatcher = getOkHttpClient().dispatcher();
		dispatcher.cancelAll();

	}

	/**
	 * if the request start time  is later the than invalidRequestTimeStamp,the request should be set not success
	 *
	 * @param invalidRequestTimeStamp
	 */
	public void setInvalidRequestTimeStamp(long invalidRequestTimeStamp) {
		this.invalidRequestTimeStamp = invalidRequestTimeStamp;
	}

	public long getInvalidRequestTimeStamp() {
		return invalidRequestTimeStamp;
	}

}