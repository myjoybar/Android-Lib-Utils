package com.joybar.androidlibutils.ins.data.header;

import android.util.Log;

import com.joy.libok.OkHttpManager;
import com.joybar.androidlibutils.ins.IGConfig;
import com.joybar.androidlibutils.ins.IGMKVManager;
import com.joybar.androidlibutils.ins.IGUtils;
import com.joybar.androidlibutils.ins.data.InsBaseGetRequest;
import com.joybar.androidlibutils.ins.data.InsBaseResponseData;

import java.util.Map;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

public class GetHeaderRequest extends InsBaseGetRequest<InsBaseResponseData> {

	public static final String CSRFTOKEN = "csrftoken";

	@Override
	protected String getActionUrl() {
		return String.format(IGConfig.ACTION_GET_HEADER, IGUtils.generateUuid(false));
	}


	public void getCsrfCookie() {
		HttpUrl url = HttpUrl.parse(getRequestUrl());
		for (Cookie cookie : OkHttpManager.getInstance().getOkHttpClient().cookieJar().loadForRequest(url)) {
			Log.d("GETCOOKIE", "Name: " + cookie.name());
			if (cookie.name().equalsIgnoreCase(CSRFTOKEN)) {
				String value = cookie.value();
				Log.d(TAG, String.format(CSRFTOKEN + "=%s", value));
				IGMKVManager.saveCsrftoken(value);
			}
		}

	}

	@Override
	protected Map<String, String> getMapParams() {
		return null;
	}
}
