package com.joybar.androidlibutils.ins.data.followingcreate;

import com.joybar.androidlibutils.ins.IGConfig;
import com.joybar.androidlibutils.ins.data.InsBasePostRequest;

public class FollowingCreateRequest extends InsBasePostRequest<FollowingCreatePayload, FollowingCreateResponseData> {

	private String userId;

	public FollowingCreateRequest(String userId,FollowingCreatePayload requestData) {
		super(requestData);
		this.userId = userId;
	}

	@Override
	protected String getActionUrl() {
		 return String.format(IGConfig.ACTION_GET_FOLLOWING_CREATE, userId);
	}
}
