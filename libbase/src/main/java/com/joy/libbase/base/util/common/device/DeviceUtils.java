package com.joy.libbase.base.util.common.device;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.LocaleList;
import android.provider.Settings;
import android.text.TextUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;
import java.util.TimeZone;



/**
 * Created by joybar on 2019/2/12.
 */

public class DeviceUtils {

	public static final String TAG_PHONE = "Phone";
	public static final String TAG_PAD = "Pad";


	/**
	 * ANDROID_ID是Android系统第一次启动时产生的一个64bit（16BYTES）数，如果设备被wipe还原后，该ID将被重置（变化）。
	 *
	 * @param context
	 * @return
	 */
	public static String getAndroidId(Context context) {
		return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
	}


	/**
	 * 获取设备唯一ID
	 * 需要请求权限
	 *
	 * @param context
	 * @return
	 */
	public static String getDeviceUniqID(Context context) {
		android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String unique_id;
		unique_id = tm.getDeviceId();
		if (TextUtils.isEmpty(unique_id)) {
			unique_id = Build.SERIAL;
		}
		return unique_id;
	}


	/**
	 * Android系统2.3版本以上可以通过下面的方法得到Serial Number，且非手机设备也可以通过该接口获取。
	 *
	 * @return
	 */
	public static String getSerial() {
		return Build.SERIAL;
	}

	/**
	 * 正确获取IP
	 *
	 * @return
	 */
	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces(); networkInterfaces.hasMoreElements(); ) {
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				for (Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses(); inetAddresses.hasMoreElements(); ) {
					InetAddress inetAddress = inetAddresses.nextElement();
					//过滤Loopback address, Link-local address
					if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
						return inetAddress.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
		}
		return null;
	}

	/**
	 * 获取正确MAC姿势
	 *
	 * @return
	 */
	public static String getMac() {
		try {
			for (Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces(); networkInterfaces.hasMoreElements(); ) {
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				if ("wlan0".equals(networkInterface.getName())) {
					byte[] hardwareAddress = networkInterface.getHardwareAddress();
					if (hardwareAddress == null || hardwareAddress.length == 0) {
						continue;
					}
					StringBuilder buf = new StringBuilder();
					for (byte b : hardwareAddress) {
						buf.append(String.format("%02X:", b));
					}
					if (buf.length() > 0) {
						buf.deleteCharAt(buf.length() - 1);
					}
					String mac = buf.toString();
					return mac;
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return null;
	}



	public static String getDeviceName() {
		return String.format("%s_%s", Build.MANUFACTURER, Build.MODEL);
	}

	public static String getDeviceModel(Context context) {
		return String.format("%s_%s_%s", isPad(context), Build.MANUFACTURER, Build.MODEL);
	}


	public static boolean isPad(Context context) {
		return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration
				.SCREENLAYOUT_SIZE_LARGE;
	}

	public static boolean isRunningOnEmulator() {

		boolean result = Build.FINGERPRINT.startsWith("generic") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build
				.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion");

		if (result) return true;
		result |= Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic");
		if (result) return true;
		result |= "google_sdk".equals(Build.PRODUCT);
		return result;
	}


	public static boolean checkVPNConnectionByNetworkInterface() {
		try {
			Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
			if (networkInterfaceEnumeration != null) {
				for (NetworkInterface intf : Collections.list(networkInterfaceEnumeration)) {
					if (!intf.isUp() || intf.getInterfaceAddresses().size() == 0) continue;
					if ("tun0".equals(intf.getName()) || "ppp0".equals(intf.getName())) {
						// The VPN is up
						return true;
					}
				}
			}

		} catch (Throwable e) {
			e.printStackTrace();
		}

		return false;
	}



	public static String getDeviceOS() {
		return "Android";
	}

	public static String getWifiName(Context context) {
		WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		int wifiState = wifiMgr.getWifiState();
		WifiInfo info = wifiMgr.getConnectionInfo();
		String wifiName = info != null ? info.getSSID() : null;
		if (TextUtils.isEmpty(wifiName)) {
			ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			wifiName = wifiInfo.getExtraInfo();
		}
		return wifiName;
	}

	public static String getLanguage() {
		String language = "";
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			LocaleList localeList = LocaleList.getDefault();
			if (null != localeList && null != localeList.get(0)) {
				language = localeList.get(0).getLanguage();
			}

		} else {
			Locale locale = Locale.getDefault();
			language = locale.getLanguage();
		}
		return language;
	}


	public static String getCountry() {
		String country = "";
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			LocaleList localeList = LocaleList.getDefault();
			if (null != localeList && null != localeList.get(0)) {
				country = localeList.get(0).getCountry();
			}

		} else {
			Locale locale = Locale.getDefault();
			country = locale.getCountry();
		}
		return country;
	}


	public static String getTimeZone() {
		return TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT);
	}

	public static String getTimeZoneFormat() {
		return TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT);
	}


}
