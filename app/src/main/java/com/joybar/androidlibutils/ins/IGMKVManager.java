package com.joybar.androidlibutils.ins;

import com.joy.libbase.io.mmkv.MMKVManager;

public class IGMKVManager {

	public static final String CSRFTOKEN = "csrftoken";
	public static final String PK_ID = "pk_id";

	public static void saveCsrftoken(String csrftoken) {
		MMKVManager.getInstance().put(IGMKVManager.CSRFTOKEN, csrftoken);
	}

	public static String getCsrftoken() {
		return MMKVManager.getInstance().getString(CSRFTOKEN, "");
	}

	public static void savePKID(String PK_ID) {
		MMKVManager.getInstance().put(IGMKVManager.PK_ID, PK_ID);
	}

	public static String getPKID() {
		return MMKVManager.getInstance().getString(PK_ID, "");
	}

}
