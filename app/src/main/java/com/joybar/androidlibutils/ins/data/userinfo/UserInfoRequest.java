package com.joybar.androidlibutils.ins.data.userinfo;

import com.joybar.androidlibutils.ins.IGConfig;
import com.joybar.androidlibutils.ins.data.InsBaseGetRequest;
import com.joybar.androidlibutils.ins.data.InsBaseResponseData;

import java.util.Map;

public class UserInfoRequest extends InsBaseGetRequest<InsBaseResponseData> {

	private String userId;

	public UserInfoRequest(String userId) {
		this.userId = userId;
	}

	@Override
	protected Map<String, String> getMapParams() {
		return null;
	}

	@Override
	protected String getActionUrl() {
		 return String.format(IGConfig.ACTION_GET_USER_INFO, userId);
	}
}
