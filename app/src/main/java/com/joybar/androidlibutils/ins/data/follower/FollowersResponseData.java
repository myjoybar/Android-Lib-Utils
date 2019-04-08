package com.joybar.androidlibutils.ins.data.follower;

import com.joybar.androidlibutils.ins.data.InsBaseResponseData;

import java.util.ArrayList;
import java.util.List;

public class FollowersResponseData extends InsBaseResponseData {


	private Object sections;
	private boolean big_list;
	private String next_max_id = "";
	private int page_size;
	private List<UsersBean> users = new ArrayList<>();

	public Object getSections() {
		return sections;
	}

	public void setSections(Object sections) {
		this.sections = sections;
	}

	public boolean isBig_list() {
		return big_list;
	}

	public void setBig_list(boolean big_list) {
		this.big_list = big_list;
	}

	public String getNext_max_id() {
		return next_max_id;
	}

	public void setNext_max_id(String next_max_id) {
		this.next_max_id = next_max_id;
	}

	public int getPage_size() {
		return page_size;
	}

	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}

	public List<UsersBean> getUsers() {
		return users;
	}

	public void setUsers(List<UsersBean> users) {
		this.users = users;
	}

	public static class UsersBean {
		/**
		 * pk : 12037205369
		 * username : ngqianqianmqsyb
		 * full_name : Susanna Valentine
		 * is_private : false
		 * profile_pic_url : https://scontent-lax3-2.cdninstagram.com/vp/5c8917ccbca8c59cee9ce305a1447dc8/5D2EFB3D/t51
		 * .2885-19/s150x150/53637069_2270612172982740_7987339272938061824_n.jpg?_nc_ht=scontent-lax3-2.cdninstagram.com
		 * profile_pic_id : 2008593490594017227_12037205369
		 * is_verified : false
		 * has_anonymous_profile_picture : false
		 * latest_reel_media : 1554181226
		 */


		private long pk;
		private String username = "";
		private String full_name = "";
		private boolean is_private;
		private String profile_pic_url = "";
		private String profile_pic_id = "";
		private boolean is_verified;
		private boolean has_anonymous_profile_picture;
		private int latest_reel_media;


		public long getPk() {
			return pk;
		}

		public void setPk(long pk) {
			this.pk = pk;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getFull_name() {
			return full_name;
		}

		public void setFull_name(String full_name) {
			this.full_name = full_name;
		}

		public boolean isIs_private() {
			return is_private;
		}

		public void setIs_private(boolean is_private) {
			this.is_private = is_private;
		}

		public String getProfile_pic_url() {
			return profile_pic_url;
		}

		public void setProfile_pic_url(String profile_pic_url) {
			this.profile_pic_url = profile_pic_url;
		}

		public String getProfile_pic_id() {
			return profile_pic_id;
		}

		public void setProfile_pic_id(String profile_pic_id) {
			this.profile_pic_id = profile_pic_id;
		}

		public boolean isIs_verified() {
			return is_verified;
		}

		public void setIs_verified(boolean is_verified) {
			this.is_verified = is_verified;
		}

		public boolean isHas_anonymous_profile_picture() {
			return has_anonymous_profile_picture;
		}

		public void setHas_anonymous_profile_picture(boolean has_anonymous_profile_picture) {
			this.has_anonymous_profile_picture = has_anonymous_profile_picture;
		}

		public int getLatest_reel_media() {
			return latest_reel_media;
		}

		public void setLatest_reel_media(int latest_reel_media) {
			this.latest_reel_media = latest_reel_media;
		}
	}
}
