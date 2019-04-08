package com.joybar.androidlibutils.ins.data.followingcancel;

import com.joybar.androidlibutils.ins.IGConfig;
import com.joybar.androidlibutils.ins.data.InsBasePostRequest;

public class FollowingCancelRequest extends InsBasePostRequest<FollowingCancelPayload, FollowingCancelResponseData> {

	private String userId;

	public FollowingCancelRequest(String userId, FollowingCancelPayload requestData) {
		super(requestData);
		this.userId = userId;
	}

	@Override
	protected String getActionUrl() {
		 return String.format(IGConfig.ACTION_GET_FOLLOWING_CANCEL, userId);
	}
}
