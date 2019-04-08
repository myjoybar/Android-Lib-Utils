package com.joybar.androidlibutils.ins.data.mediaunlike;

import com.joybar.androidlibutils.ins.IGConfig;
import com.joybar.androidlibutils.ins.data.InsBasePostRequest;
import com.joybar.androidlibutils.ins.data.InsBaseResponseData;

public class MedialUnLikeRequest extends InsBasePostRequest<MediaUnLikePayload, InsBaseResponseData> {

	private String mediaId;

	public MedialUnLikeRequest(String mediaId, MediaUnLikePayload requestData) {
		super(requestData);
		this.mediaId = mediaId;
	}

	@Override
	protected String getActionUrl() {
		return String.format(IGConfig.ACTION_GET_MEDIA_UNLIKE, mediaId);
	}
}
