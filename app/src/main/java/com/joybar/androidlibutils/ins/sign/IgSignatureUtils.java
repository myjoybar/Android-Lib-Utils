package com.joybar.androidlibutils.ins.sign;

import android.text.TextUtils;

import com.joybar.androidlibutils.ins.IGConfig;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class IgSignatureUtils {





	public static String generateSignature(String payload)  {
		String parsedData = null;
		try {
			parsedData = URLEncoder.encode(payload, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String signedBody = generateHash(IGConfig.IG_SIG_KEY, payload);

		return String.format("ig_sig_key_version=%s&signed_body=%s.%s",IGConfig.SIG_KEY_VERSION,signedBody,parsedData);
		//	return "ig_sig_key_version="+IGConfig.SIG_KEY_VERSION +"&signed_body=" + signedBody + '.' + parsedData;

	}


	public static String generateHash(String key, String string) {
		SecretKeySpec object = new SecretKeySpec(key.getBytes(), "HmacSHA256");

		try {
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(object);
			byte[] byteArray = mac.doFinal(string.getBytes("UTF-8"));
			return new String((new Hex()).encode(byteArray), "ISO-8859-1");
		} catch (Exception var5) {
			var5.printStackTrace();
			return null;
		}
	}








	public static String buildBodySignContent(	String bodyContent) {

		String parsedData = null;
		try {
			parsedData = URLEncoder.encode(bodyContent, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String signedBody = SignatureUtils.hmacSha256(IGConfig.IG_SIG_KEY, bodyContent);

		return String.format("ig_sig_key_version=%s&signed_body=%s.%s",IGConfig.SIG_KEY_VERSION,signedBody,parsedData);

		//return "ig_sig_key_version=" + IGConfig.SIG_KEY_VERSION + "&signed_body=" + signedBody + '.' + parsedData;

	}


	private static String getBuildParaMapUrl(Map<String, String> paramsMap) {

		StringBuffer buffer = new StringBuffer();
		if (paramsMap != null && !paramsMap.isEmpty()) {
			Iterator<Map.Entry<String, String>> iterator = paramsMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String> entry = iterator.next();
				if (!TextUtils.isEmpty(buffer.toString())) {
					buffer.append("&");
				}
				buffer.append(entry.getKey()).append("=").append(entry.getValue());
			}
		}
		return buffer.toString();
	}

}
