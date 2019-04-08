package com.joybar.androidlibutils.ins.data.medialikers;

import com.joybar.androidlibutils.ins.data.InsBaseResponseData;
import com.joybar.androidlibutils.ins.data.feed.FeedResponseData;

import java.util.ArrayList;
import java.util.List;

public class MediaLikersResponseData extends InsBaseResponseData {


	private int user_count;
	private List<FeedResponseData.ItemsBean.LikersBean> users = new ArrayList<>();

	public int getUser_count() {
		return user_count;
	}

	public void setUser_count(int user_count) {
		this.user_count = user_count;
	}

	public List<FeedResponseData.ItemsBean.LikersBean> getUsers() {
		return users;
	}

	public void setUsers(List<FeedResponseData.ItemsBean.LikersBean> users) {
		this.users = users;
	}


}
