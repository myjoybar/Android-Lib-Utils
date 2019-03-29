package com.joy.libok;

import android.text.TextUtils;
import android.util.Log;

import com.joy.libok.client.OkClient;
import com.joy.libok.config.OKConfigData;
import com.joy.libok.response.GsonResponseHandler;
import com.joy.libok.response.inters.IOKCallback;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpManager {

	private static String TAG = "OkHttpManager";
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


	private String getBuildParaMapUrl(HashMap<String, String> paramsMap) {

		StringBuffer buffer = new StringBuffer();
		if (paramsMap != null && !paramsMap.isEmpty()) {
			Iterator<Map.Entry<String, String>> iterator = paramsMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String> entry = iterator.next();
				if (TextUtils.isEmpty(buffer.toString())) {
					buffer.append("?");
				} else {
					buffer.append("&");
				}

				buffer.append(entry.getKey()).append("=").append(entry.getValue());
			}
		}
		return buffer.toString();
	}


	private void addHeader(Request.Builder requestBuilder, HashMap<String, String> headerMap) {

		if (headerMap != null && !headerMap.isEmpty()) {
			Iterator<Map.Entry<String, String>> iterator = headerMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String> entry = iterator.next();
				requestBuilder.addHeader(entry.getKey(), entry.getValue());

			}
		}

	}


	/**
	 * @param requestUrl
	 * @param paramsMap
	 * @param headerMap
	 * @param gsonResponseHandler
	 */
	public void requestGet(String requestUrl, HashMap<String, String> paramsMap, HashMap<String, String> headerMap,
						   GsonResponseHandler gsonResponseHandler) {
		try {
			Log.d(TAG,"requestGet");
			String buildParaMapUrl = getBuildParaMapUrl(paramsMap);
			//requestUrl = requestUrl + URLEncoder.encode(buildParaMapUrl, "utf-8");
			requestUrl = requestUrl +buildParaMapUrl;
			Request.Builder requestBuilder = new Request.Builder();
			addHeader(requestBuilder, headerMap);
			requestBuilder.url(requestUrl);
			Log.d(TAG,"requestGet,requestUrl = "+requestUrl);
			//	requestBuilder.method("GET", null);
			Request request = requestBuilder.build();
			Call call = mOkHttpClient.newCall(request);
			call.enqueue(new IOKCallback(gsonResponseHandler));

		} catch (Exception e) {
			if (null != gsonResponseHandler) {
				gsonResponseHandler.onFailure(0, "try to build the request occurs error, error msg = " + e.getMessage());

			}

		}

	}

}
