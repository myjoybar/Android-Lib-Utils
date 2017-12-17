package com.joybar.library.common.log;

import android.text.TextUtils;
import android.util.Log;

import com.joybar.library.common.log.config.LogConfig;
import com.joybar.library.common.log.utils.ObjectUtils;
import com.joybar.library.common.log.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import static com.joybar.library.common.log.LogLevel.TYPE_DEBUG;
import static com.joybar.library.common.log.LogLevel.TYPE_ERROR;
import static com.joybar.library.common.log.LogLevel.TYPE_INFO;
import static com.joybar.library.common.log.LogLevel.TYPE_VERBOSE;
import static com.joybar.library.common.log.LogLevel.TYPE_WARN;

/**
 * Created by joybar on 2017/12/12.
 */

public class PrinterService implements IPrinter {


	@Override
	public void v(Object object) {
		printObject(TYPE_VERBOSE,null,object);
	}

	@Override
	public void v(String tag, Object object) {
		printObject(TYPE_VERBOSE,tag,object);
	}

	@Override
	public void v(String message) {
		printString(TYPE_VERBOSE,null,message);
	}

	@Override
	public void v(String tag, String message) {
		printString(TYPE_VERBOSE,tag,message);
	}

	@Override
	public void i(Object object) {
		printObject(TYPE_INFO,null,object);
	}

	@Override
	public void i(String tag, Object object) {
		printObject(TYPE_INFO,tag,object);
	}

	@Override
	public void i(String message) {
		printString(TYPE_INFO,null,message);
	}

	@Override
	public void i(String tag, String message) {
		printString(TYPE_INFO,tag,message);
	}

	@Override
	public void d(Object object) {
		printObject(TYPE_DEBUG,null,object);
	}

	@Override
	public void d(String tag, Object object) {
		printObject(TYPE_DEBUG,tag,object);
	}

	@Override
	public void d(String message) {
		printString(TYPE_DEBUG,null,message);
	}

	@Override
	public void d(String tag, String message) {
		printString(TYPE_DEBUG,tag,message);
	}

	@Override
	public void w(Object object) {
		printObject(TYPE_WARN,null,object);
	}

	@Override
	public void w(String tag, Object object) {
		printObject(TYPE_WARN,tag,object);
	}

	@Override
	public void w(String message) {
		printString(TYPE_WARN,null,message);
	}

	@Override
	public void w(String tag, String message) {
		printString(TYPE_WARN,tag,message);
	}

	@Override
	public void e(Object object) {
		printObject(TYPE_ERROR,null,object);
	}

	@Override
	public void e(String tag, Object object) {
		printObject(TYPE_ERROR,tag,object);
	}

	@Override
	public void e(String message) {
		printString(TYPE_ERROR,null,message);
	}

	@Override
	public void e(String tag, String message) {
		printString(TYPE_ERROR,tag,message);
	}

	@Override
	public void json(String json) {
		int indent = 4;
		if (TextUtils.isEmpty(json)) {
			d("JSON{json is empty}");
			return;
		}
		try {
			if (json.startsWith("{")) {
				JSONObject jsonObject = new JSONObject(json);
				String msg = jsonObject.toString(indent);
				printString(TYPE_DEBUG, null,  msg);
			} else if (json.startsWith("[")) {
				JSONArray jsonArray = new JSONArray(json);
				String msg = jsonArray.toString(indent);
				printString(TYPE_DEBUG, null,msg);
			}
		} catch (JSONException e) {
			e(e.toString() + "\n\njson = " + json);
		}
	}

	/**
	 * 采用orhanobut/logger的json解析方案
	 * source:https://github.com/orhanobut/logger/blob/master/logger/src/main/java/com/orhanobut
	 * /logger/LoggerPrinter.java#L152
	 *
	 * @param tag
	 * @param json
	 */
	@Override
	public void json(String tag, String json) {
		int indent = 4;
		if (TextUtils.isEmpty(json)) {
			printString(TYPE_DEBUG, tag, "JSON{json is empty}");
			return;
		}
		try {
			if (json.startsWith("{")) {
				JSONObject jsonObject = new JSONObject(json);
				String msg = jsonObject.toString(indent);
				printString(TYPE_DEBUG, tag, msg);
			} else if (json.startsWith("[")) {
				JSONArray jsonArray = new JSONArray(json);
				String msg = jsonArray.toString(indent);
				printString(TYPE_DEBUG, tag, msg);
			}
		} catch (JSONException e) {
			printString(TYPE_DEBUG, tag, e.toString() + "\n\njson = " + json);
		}
	}

	@Override
	public void xml(String xml) {
		if (TextUtils.isEmpty(xml)) {
			printString(TYPE_DEBUG, null, "XML{xml is empty}");
			return;
		}
		try {
			Source xmlInput = new StreamSource(new StringReader(xml));
			StreamResult xmlOutput = new StreamResult(new StringWriter());
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(xmlInput, xmlOutput);
			printString(TYPE_DEBUG, null, xmlOutput.getWriter().toString().replaceFirst(">", ">\n"));
		} catch (TransformerException e) {
			e(e.toString() + "\n\nxml = " + xml);
			printString(TYPE_DEBUG, null, e.toString() + "\n\nxml = " + xml);
		}
	}

	/**
	 * 采用orhanobut/logger的xml解析方案
	 * source:https://github.com/orhanobut/logger/blob/master/logger/src/main/java/com/orhanobut
	 * /logger/LoggerPrinter.java#L180
	 *
	 * @param tag
	 * @param xml
	 */
	@Override
	public void xml(String tag, String xml) {
		if (TextUtils.isEmpty(xml)) {
			printString(TYPE_DEBUG, tag, "XML{xml is empty}");
			return;
		}
		try {
			Source xmlInput = new StreamSource(new StringReader(xml));
			StreamResult xmlOutput = new StreamResult(new StringWriter());
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(xmlInput, xmlOutput);
			printString(TYPE_DEBUG, tag, xmlOutput.getWriter().toString().replaceFirst(">", ">\n"));
		} catch (TransformerException e) {
			printString(TYPE_DEBUG, tag, e.toString() + "\n\nxml = " + xml);
		}
	}


	public void printObject(@LogLevel.LogLevelType int level, String tag, Object object) {
		if (!LogConfig.LOG_ENABLE) {
			return;
		}
		if (level > LogConfig.LOG_LEVEL) {
			return;
		}
		print(level, tag, false, ObjectUtils.objectToString(object));
	}


	public void printString(@LogLevel.LogLevelType int level, String tag, String msg) {
		if (!LogConfig.LOG_ENABLE) {
			return;
		}
		if (level > LogConfig.LOG_LEVEL) {
			return;
		}
		print(level, tag, false, msg);
	}



	public static void print(@LogLevel.LogLevelType int level, String tag, boolean isPart, String msg) {

		if (TextUtils.isEmpty(msg)) {
			print(level, tag, false, "Message is empty");
			return;
		}

		if (TextUtils.isEmpty(tag)) {
			tag = LogConfig.TAG;
		}
		tag = LogConfig.TAG_PREFIX + tag;

		if (LogConfig.SHOW_TOP && !isPart) {
			print(level, tag, Utils.printDividingLine(Utils.DIVIDER_TOP));
		}
		if (msg.length() > LogConfig.LINE_MAX) {
			for (String subMsg : Utils.largeStringToList(msg)) {
				print(level, tag, true, subMsg);
			}
		} else {
			print(level, tag, createMessage(tag, msg));
		}

		if (LogConfig.SHOW_BOTTOM && !isPart) {
			print(level, tag, Utils.printDividingLine(Utils.DIVIDER_BOTTOM));
		}
	}


	private static String getLineIndicator() {
		StackTraceElement element = ((new Exception()).getStackTrace())[6];
		StringBuffer sb = new StringBuffer("(").append(element.getFileName()).append(":").append(element.getLineNumber()).append(").").append(element.getMethodName()).append(":");
		return sb.toString();
	}

	private static String getTreadName() {
		return Thread.currentThread().getName();
	}

	private static String getClassName() {
		String result;
		StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[5];
		result = thisMethodStack.getClassName();
		int lastIndex = result.lastIndexOf(".");
		result = result.substring(lastIndex + 1, result.length());
		return result;
	}

	private static String createMessage(String tag, String msg) {
		return "[Thread： " + getTreadName() + "] " + "[" + getLineIndicator() + "] " + "[Msg： " + msg + "] ";
	}

	public static void print(int level, String tag, String msg) {
		switch (level) {
			case TYPE_VERBOSE:
				Log.v(tag, msg);
				break;
			case TYPE_DEBUG:
				Log.d(tag, msg);
				break;
			case TYPE_INFO:
				Log.i(tag, msg);
				break;
			case TYPE_WARN:
				Log.w(tag, msg);
				break;
			case TYPE_ERROR:
				Log.e(tag, msg);
				break;
			default:
				break;
		}
	}

}
