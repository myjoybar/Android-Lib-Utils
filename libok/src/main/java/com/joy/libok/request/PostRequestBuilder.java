package com.joy.libok.request;

import android.text.TextUtils;

import com.joy.libok.OkHttpManager;
import com.joy.libok.response.callback.IResponseCallBackHandler;
import com.joy.libok.response.callback.OKCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostRequestBuilder<T extends PostRequestBuilder> extends BaseRequestBuilder<PostRequestBuilder> {

	private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");//mdiatype 这个需要和服务端保持一致

	protected String bodyContent;//和mMapParams 互斥

	public PostRequestBuilder(String url) {
		super(url);
	}


	public T addBodyContent(String bodyContent) {
		this.bodyContent = bodyContent;
		return (T) this;

	}

	@Override
	public Response execute() throws IOException {
		Request.Builder requestBuilder = new Request.Builder();
		requestBuilder = addHeaders(requestBuilder, mMapHeaders);
		requestBuilder.url(url);
		RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, getBodyContent());
		requestBuilder.post(body);
		Request request = requestBuilder.build();
		Call call = OkHttpManager.getInstance().getOkHttpClient().newCall(request);
		Response response = call.execute();
		return response;
	}

	@Override
	public void execute(IResponseCallBackHandler responseCallBackHandler) {
		Request.Builder requestBuilder = new Request.Builder();
		requestBuilder = addHeaders(requestBuilder, mMapHeaders);
		requestBuilder.url(url);
		RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, getBodyContent());
		requestBuilder.post(body);
		Request request = requestBuilder.build();
		Call call = OkHttpManager.getInstance().getOkHttpClient().newCall(request);
		call.enqueue(new OKCallback(responseCallBackHandler));

	}

	private String getBodyContent() {
		if (TextUtils.isEmpty(bodyContent)) {
			String buildParaMapUrl = getBuildParaMapUrl(mMapParams, false);
			return buildParaMapUrl;
		}
		return bodyContent;
	}

}
