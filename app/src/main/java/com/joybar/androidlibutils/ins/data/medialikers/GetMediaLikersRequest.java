package com.joybar.androidlibutils.ins.data.medialikers;

import com.joybar.androidlibutils.ins.IGConfig;
import com.joybar.androidlibutils.ins.data.InsBaseGetRequest;

import java.util.Map;

public class GetMediaLikersRequest extends InsBaseGetRequest<MediaLikersResponseData> {

	private String itemID;

	public GetMediaLikersRequest(String itemID) {
		this.itemID = itemID;
	}

	@Override
	protected Map<String, String> getMapParams() {
		return null;
	}

	@Override
	protected String getActionUrl() {
		return String.format(IGConfig.ACTION_GET_MEDIA_LIKERS, itemID);
	}
}
