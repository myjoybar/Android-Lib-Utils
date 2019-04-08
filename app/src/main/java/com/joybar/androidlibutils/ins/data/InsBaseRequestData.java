package com.joybar.androidlibutils.ins.data;

import com.joy.libbase.json.GsonUtil;

public class InsBaseRequestData {

	public String getPayLoad(){
		return  GsonUtil.parseBeanToStr(this);
	}

}
