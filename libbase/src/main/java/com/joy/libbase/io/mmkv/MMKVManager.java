package com.joy.libbase.io.mmkv;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.tencent.mmkv.MMKV;
import com.tencent.mmkv.MMKVLogLevel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by joybar on 2019/03/12.
 * https://github.com/Tencent/MMKV/wiki/android_tutorial_cn
 */
public class MMKVManager {
	private static final String TAG = "MMKVManager";
	private MMKV mDefaulMMKV;

	private static class MMKVManagerHolder {
		private static MMKVManager INSTANCE = new MMKVManager();

	}

	public static MMKVManager getInstance() {
		return MMKVManagerHolder.INSTANCE;
	}

	public void init(Context context) {
		String rootDir = MMKV.initialize(context);
		Log.d(TAG, "mmkv root: " + rootDir);
	}
	public void disableLog(){
		MMKV.setLogLevel(MMKVLogLevel.LevelNone);
	}

	public void checkMMKV() {
		if (null == mDefaulMMKV) {
			mDefaulMMKV = MMKV.defaultMMKV();
		}
	}

	public MMKV getDeFaultMMKV() {
		if (null == mDefaulMMKV) {
			mDefaulMMKV = MMKV.defaultMMKV();
		}
		return mDefaulMMKV;
	}

	public MMKV getMMKV(String id) {
		if (TextUtils.isEmpty(id)) {
			return getDeFaultMMKV();
		} else {
			return MMKV.mmkvWithID(id);
		}
	}

	public void put(String key, Object value) {
		put(null, key, value);
	}

	public void put(String id, String key, Object value) {
		if (null != value) {
			MMKV mmkv = getMMKV(id);
			if (value instanceof Boolean) {
				mmkv.encode(key, (Boolean) value);
			} else if (value instanceof Integer) {
				mmkv.encode(key, (Integer) value);
			} else if (value instanceof Long) {
				mmkv.encode(key, (Long) value);
			} else if (value instanceof Float) {
				mmkv.encode(key, (Float) value);
			} else if (value instanceof Double) {
				mDefaulMMKV.encode(key, (Double) value);
			} else if (value instanceof byte[]) {
				mmkv.encode(key, (byte[]) value);
			} else if (value instanceof String) {
				mmkv.encode(key, (String) value);
			} else {
				Log.e(TAG, "the type of this value is not supported in MMKV,object: " + value.toString());
			}
		}

	}

	public boolean getBoolean(String key, boolean defaultValue) {
		return getBoolean(null, key, defaultValue);
	}

	public boolean getBoolean(String id, String key, boolean defaultValue) {
		if (!TextUtils.isEmpty(key)) {
			return getMMKV(id).decodeBool(key);
		}
		return defaultValue;
	}

	public int getInt(String key, int defaultValue) {
		return getInt(null, key, defaultValue);
	}


	public int getInt(String id, String key, int defaultValue) {
		if (!TextUtils.isEmpty(key)) {
			return getMMKV(id).decodeInt(key);
		}
		return defaultValue;
	}


	public long getLong(String key, long defaultValue) {
		return getLong(null, key, defaultValue);
	}

	public long getLong(String id, String key, long defaultValue) {
		if (!TextUtils.isEmpty(key)) {
			return getMMKV(id).decodeLong(key);
		}
		return defaultValue;
	}

	public float getFloat(String key, float defaultValue) {
		return getFloat(null, key, defaultValue);
	}

	public float getFloat(String id, String key, float defaultValue) {
		if (!TextUtils.isEmpty(key)) {
			return getMMKV(id).decodeFloat(key);
		}
		return defaultValue;
	}


	public double getDouble(String key, double defaultValue) {
		return getDouble(null, key, defaultValue);
	}

	public double getDouble(String id, String key, double defaultValue) {
		if (!TextUtils.isEmpty(key)) {
			return getMMKV(id).decodeDouble(key);
		}
		return defaultValue;
	}

	public String getString(String key, String defaultValue) {
		return getString(null, key, defaultValue);
	}


	public String getString(String id, String key, String defaultValue) {
		if (!TextUtils.isEmpty(key)) {
			return getMMKV(id).decodeString(key);
		}
		return defaultValue;
	}

	public byte[] getByte(String key, byte[] defaultValue) {
		return getByte(null, key, defaultValue);
	}

	public byte[] getByte(String id, String key, byte[] defaultValue) {
		if (!TextUtils.isEmpty(key)) {
			return getMMKV(id).decodeBytes(key);
		}
		return defaultValue;
	}


	/**
	 * 存储对象
	 *
	 * @param key
	 * @param value
	 */
	public void saveObj(String key, Object value) {
		saveObj(null, key, value);
	}

	/**
	 * 存储对象
	 * @param id
	 * @param key
	 * @param object
	 */
	public void saveObj(String id, String key, Object object) {
		if (!TextUtils.isEmpty(key) && null != object) {
			ByteArrayOutputStream baos = null;
			ObjectOutputStream oos = null;
			try {
				baos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(baos);
				oos.writeObject(object);
				String newsListBase64 = new String(Base64.encode(baos.toByteArray(), 1));
				getMMKV(id).encode(key, newsListBase64);
			} catch (Exception exception) {
				Log.e(TAG, "save obj occurs error, msg: " + exception.getMessage());
			} finally {
				if (oos != null) {
					try {
						oos.close();
						oos = null;
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}

				if (baos != null) {
					try {
						baos.close();
						baos = null;
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}
				}
			}
		}

	}

	/**
	 * 获取对象
	 *
	 * @param key
	 */
	public Object getObj(String key) {
		return getObj(null, key);
	}

	/**
	 * @param id
	 * @param key
	 * @return Object
	 */
	public Object getObj(String id, String key) {
		Object object = null;
		if (!TextUtils.isEmpty(key)) {
			ByteArrayInputStream bais = null;
			ObjectInputStream ois = null;
			try {
				String newsListBase64 = getString(id, key, null);
				if (newsListBase64 != null) {
					byte[] base64Bytes = Base64.decode(newsListBase64.getBytes(), 1);
					bais = new ByteArrayInputStream(base64Bytes);
					ois = new ObjectInputStream(bais);
					object = ois.readObject();
					return object;
				}
			} catch (Exception e) {
				Log.e(TAG, "get obj occurs error, msg: " + e.getMessage());
			} finally {
				if (ois != null) {
					try {
						ois.close();
						ois = null;
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}
				}
				if (bais != null) {
					try {
						bais.close();
						bais = null;
					} catch (IOException var22) {
						var22.printStackTrace();
					}
				}
			}
		}
		return object;
	}


	public void remove(String key) {
		if (!TextUtils.isEmpty(key)) {
			checkMMKV();
			mDefaulMMKV.removeValueForKey(key);
		}
	}

	public void removeAll() {
		checkMMKV();
		mDefaulMMKV.removeValuesForKeys(new String[]{"bool", "int", "long", "float", "double", "string", "byte[]"});
	}

}
