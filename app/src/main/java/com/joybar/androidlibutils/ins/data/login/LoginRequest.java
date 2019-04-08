package com.joybar.androidlibutils.ins.data.login;

import com.joybar.androidlibutils.ins.IGConfig;
import com.joybar.androidlibutils.ins.data.InsBasePostRequest;

public class LoginRequest extends InsBasePostRequest<LoginPayload,LoginResponseData> {


	public LoginRequest(LoginPayload requestData) {
		super(requestData);
	}

	@Override
	protected String getActionUrl() {
		return IGConfig.ACTION_LOGIN;
	}
}
