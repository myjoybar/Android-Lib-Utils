package com.joybar.androidlibutils.ins;

import android.content.Context;

public class InsCommonFieldsManager {

	private Context mContext;

	private static class InsCommonFieldsManagerHolder {
		private static InsCommonFieldsManager INSTANCE = new InsCommonFieldsManager();
	}

	public static InsCommonFieldsManager getInstance() {
		return InsCommonFieldsManagerHolder.INSTANCE;
	}



	public void init(Context context){
		mContext = context;
	}

	public Context getContext() {
		return mContext;
	}
}
