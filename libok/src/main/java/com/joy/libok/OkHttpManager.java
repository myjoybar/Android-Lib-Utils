package com.joy.libok;

import com.joy.libok.client.OkClient;
import com.joy.libok.configdata.OKConfigData;
import com.joy.libok.request.GetRequestBuilder;
import com.joy.libok.request.PostRequestBuilder;

import okhttp3.OkHttpClient;

public class OkHttpManager {

	private OkHttpClient mOkHttpClient;

	private static class OkHttpManagerHolder {
		private static OkHttpManager INSTANCE = new OkHttpManager();
	}

	public static OkHttpManager getInstance() {
		return OkHttpManagerHolder.INSTANCE;
	}

	public void init(OKConfigData okConfigData) {
		OkClient.getInstance().init(okConfigData);
		mOkHttpClient = OkClient.getInstance().getOkHttpClient();
	}


	public OkHttpClient getOkHttpClient() {
		return mOkHttpClient;
	}

	public GetRequestBuilder get(String url) {
		return new GetRequestBuilder(url);
	}

	public PostRequestBuilder post(String url) {
		return new PostRequestBuilder(url);
	}



}
