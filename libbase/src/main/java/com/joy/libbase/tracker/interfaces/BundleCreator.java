package com.joy.libbase.tracker.interfaces;

import android.os.Bundle;

/**
 * Created by joybar on 2019/2/22.
 */

public class BundleCreator {

	public static Builder create() {
		return new Builder();
	}

	public static class Builder {

		Bundle bundle = new Bundle();

		public Builder put(String key, String value) {
			bundle.putString(key, value);
			return this;
		}

		public Builder put(String key, int value) {
			bundle.putInt(key, value);
			return this;
		}

		public Builder put(String key, long value) {
			bundle.putLong(key, value);
			return this;
		}

		public Builder put(String key, float value) {
			bundle.putFloat(key, value);
			return this;
		}

		public Builder put(String key, double value) {
			bundle.putDouble(key, value);
			return this;
		}

		public Bundle getBundle() {
			return bundle;
		}

	}

}
