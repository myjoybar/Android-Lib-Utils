package com.joy.libok.request;

import android.text.TextUtils;

import com.joy.libok.response.callback.IResponseCallBackHandler;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

public abstract class BaseRequestBuilder<T extends BaseRequestBuilder> {

	protected final String url;
	protected  String tag;
	protected Map<String, String> mMapHeaders;
	protected Map<String, String> mMapParams;


	public BaseRequestBuilder(String url) {
		this.url = url;
	}


	public T addHeaders(Map<String, String> mapHeaders) {
		this.mMapHeaders = mapHeaders;
		return (T) this;

	}

	public T addParams(Map<String, String> mapParams) {
		this.mMapParams = mapParams;
		return (T) this;

	}

	public T tag(String tag) {
		this.tag = tag;
		return (T) this;

	}


	public abstract Response execute() throws IOException;
	public abstract void execute(IResponseCallBackHandler responseCallBackHandler);


	public Request.Builder addHeaders(Request.Builder requestBuilder, Map<String, String> headerMap) {

		if (headerMap != null && !headerMap.isEmpty()) {
			Iterator<Map.Entry<String, String>> iterator = headerMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String> entry = iterator.next();
				requestBuilder.addHeader(entry.getKey(), entry.getValue());

			}
		}

		return requestBuilder;

	}


	protected String getBuildParaMapUrl(Map<String, String> paramsMap, boolean addQuestionMark) {

		StringBuffer buffer = new StringBuffer();
		if (paramsMap != null && !paramsMap.isEmpty()) {
			Iterator<Map.Entry<String, String>> iterator = paramsMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String> entry = iterator.next();
				if (TextUtils.isEmpty(buffer.toString())) {
					if(addQuestionMark){
						buffer.append("?");
					}
				} else {
					buffer.append("&");
				}

				buffer.append(entry.getKey()).append("=").append(entry.getValue());
			}
		}
		return buffer.toString();
	}


}
