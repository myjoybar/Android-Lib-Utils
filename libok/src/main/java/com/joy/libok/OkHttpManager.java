package com.joy.libok;

import com.joy.libok.client.OkClient;
import com.joy.libok.configdata.OKConfigData;
import com.joy.libok.request.GetRequestBuilder;
import com.joy.libok.request.PostRequestBuilder;

import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

public class OkHttpManager {


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

	public void cancel(String tag) {
		Dispatcher dispatcher = getOkHttpClient().dispatcher();
		for (Call call : dispatcher.queuedCalls()) {
			if (tag.equals(call.request().tag())) {
				call.cancel();
			}
		}
		for (Call call : dispatcher.runningCalls()) {
			if (tag.equals(call.request().tag())) {
				call.cancel();
			}
		}
	}

}
