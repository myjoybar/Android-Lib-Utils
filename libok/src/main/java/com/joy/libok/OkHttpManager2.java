package com.joy.libok;

import android.text.TextUtils;
import android.util.Log;

import com.joy.libok.client.OkClient;
import com.joy.libok.configdata.OKConfigData;
import com.joy.libok.response.callback.IResponseCallBackHandler;
import com.joy.libok.response.callback.OKCallback;
import com.joy.libok.test.log.LLog;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpManager2 {

	private static String TAG = "libok_OkHttpManager";
	private OkHttpClient mOkHttpClient;
	private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");//mdiatype 这个需要和服务端保持一致


	private static class OkHttpManagerHolder {
		private static OkHttpManager2 INSTANCE = new OkHttpManager2();
	}

	public static OkHttpManager2 getInstance() {
		return OkHttpManagerHolder.INSTANCE;
	}

	public void init(OKConfigData okConfigData) {
		OkClient.getInstance().init(okConfigData);
		mOkHttpClient = OkClient.getInstance().getOkHttpClient();
	}


	private String getBuildGetParaMapUrl(HashMap<String, String> paramsMap) {

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
	private String getBuildPostParaMapUrl(HashMap<String, String> paramsMap) {

		StringBuffer buffer = new StringBuffer();
		if (paramsMap != null && !paramsMap.isEmpty()) {
			Iterator<Map.Entry<String, String>> iterator = paramsMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String> entry = iterator.next();
				if (!TextUtils.isEmpty(buffer.toString())) {
					buffer.append("&");
				}
				buffer.append(entry.getKey()).append("=").append(entry.getValue());
			}
		}
		return buffer.toString();
	}

	private Request.Builder addHeader(Request.Builder requestBuilder, HashMap<String, String> headerMap) {

		if (headerMap != null && !headerMap.isEmpty()) {
			Iterator<Map.Entry<String, String>> iterator = headerMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String> entry = iterator.next();
				requestBuilder.addHeader(entry.getKey(), entry.getValue());

			}
		}

		return requestBuilder;

	}


	/**
	 * @param requestUrl
	 * @param paramsMap
	 * @param headerMap
	 * @param iResponseHandler
	 */
	public void requestGet(String requestUrl, HashMap<String, String> paramsMap, HashMap<String, String> headerMap,
						   IResponseCallBackHandler iResponseHandler) {
		try {
			LLog.d(TAG, "requestGet");
			String buildParaMapUrl = getBuildGetParaMapUrl(paramsMap);
			//requestUrl = requestUrl + URLEncoder.encode(buildParaMapUrl, "utf-8");
			requestUrl = requestUrl + buildParaMapUrl;
			Request.Builder requestBuilder = new Request.Builder();
			requestBuilder = addHeader(requestBuilder, headerMap);
			requestBuilder.url(requestUrl);
			LLog.d(TAG, "requestGet,requestUrl = " + requestUrl);
			//	requestBuilder.method("GET", null);
			Request request = requestBuilder.build();
			Call call = mOkHttpClient.newCall(request);
			call.enqueue(new OKCallback(iResponseHandler));

		} catch (Exception e) {
			if (null != iResponseHandler) {
				iResponseHandler.onFailure(0, "try to build the request occurs error, error msg = " + e.getMessage());

			}

		}

	}




	public void requestPost(String requestUrl, HashMap<String, String> paramsMap, HashMap<String, String> headerMap,
							IResponseCallBackHandler iResponseHandler) {
		try {
			LLog.d(TAG, "requestPost");
			//requestUrl = requestUrl + URLEncoder.encode(buildParaMapUrl, "utf-8");
			Request.Builder requestBuilder = new Request.Builder();
			requestBuilder = addHeader(requestBuilder, headerMap);
			requestBuilder.url(requestUrl);
			String buildParaMapUrl = getBuildPostParaMapUrl(paramsMap);
			Log.d("bodyContent11", "bodyContent = " + buildParaMapUrl);
			RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, buildParaMapUrl);
			requestBuilder.post(body);
			Request request = requestBuilder.build();
			LLog.d(TAG, "requestPost,requestUrl = " + requestUrl);
			Call call = mOkHttpClient.newCall(request);
			call.enqueue(new OKCallback(iResponseHandler));

		} catch (Exception e) {
			if (null != iResponseHandler) {
				iResponseHandler.onFailure(0, "try to build the request occurs error, error msg = " + e.getMessage());

			}

		}

	}




}
