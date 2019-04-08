package com.joybar.androidlibutils.ins.data.medialike;

import com.joybar.androidlibutils.ins.IGConfig;
import com.joybar.androidlibutils.ins.data.InsBasePostRequest;
import com.joybar.androidlibutils.ins.data.InsBaseResponseData;

public class MedialLikeRequest extends InsBasePostRequest<MediaLikePayload, InsBaseResponseData> {

	private String mediaId;

	public MedialLikeRequest(String mediaId,MediaLikePayload requestData) {
		super(requestData);
		this.mediaId = mediaId;
	}

	@Override
	protected String getActionUrl() {
		return String.format(IGConfig.ACTION_GET_MEDIA_LIKE, mediaId);
	}
}
