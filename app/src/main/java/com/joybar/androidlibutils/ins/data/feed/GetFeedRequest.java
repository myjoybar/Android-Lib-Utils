package com.joybar.androidlibutils.ins.data.feed;

import com.joy.libbase.base.util.common.device.DeviceUtils;
import com.joybar.androidlibutils.ins.IGConfig;
import com.joybar.androidlibutils.ins.InsCommonFieldsManager;
import com.joybar.androidlibutils.ins.data.InsBaseGetRequest;

import java.util.HashMap;
import java.util.Map;

public class GetFeedRequest extends InsBaseGetRequest<FeedResponseData> {

	private boolean isFirstPage;
	private String userId;
	private String nextMaxId;

	public GetFeedRequest(boolean isFirstPage, String userId, String nextMaxId) {
		this.isFirstPage = isFirstPage;
		this.userId = userId;
		this.nextMaxId = nextMaxId;
	}

	@Override
	protected String getActionUrl() {
		return String.format(IGConfig.ACTION_GET_FEED, userId);
	}

	@Override
	protected Map<String, String> getMapParams() {

		HashMap<String, String> paramsMap = new HashMap<>();
		paramsMap.put("rank_token", String.format("%s_%s", userId, DeviceUtils.getAndroidId(InsCommonFieldsManager.getInstance().getContext())));//rank_token
		// 本地拼接的userid_deviceID （
		paramsMap.put("ranked_content", "true");
		if(!isFirstPage){
			paramsMap.put("max_id", nextMaxId);
		}

		return paramsMap;
	}
}
