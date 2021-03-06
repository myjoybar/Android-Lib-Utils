package com.joybar.androidlibutils.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by joybar on 2018/1/5.
 */

@Entity
public class User {

	@Id(autoincrement = true)
	private Long _id;
	private String source;
	@NotNull
	private String url;
	@Generated(hash = 1229907530)
	public User(Long _id, String source, @NotNull String url) {
		this._id = _id;
		this.source = source;
		this.url = url;
	}
	@Generated(hash = 586692638)
	public User() {
	}
	public Long get_id() {
		return this._id;
	}
	public void set_id(Long _id) {
		this._id = _id;
	}
	public String getSource() {
		return this.source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
