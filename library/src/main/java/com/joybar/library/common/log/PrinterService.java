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

/**
 * Created by joybar on 2017/12/12.
 */

public class PrinterService implements IPrinter {

	private final static int VERBOSE = 5; // 不过滤输出所有调试信息 包括 VERBOSE、DEBUG、INFO、WARN、ERROR

	private final static int DEBUG = 4; // debug过滤器，输出DEBUG、INFO、WARN、ERROR调试信息

	private final static int INFO = 3; // info过滤器，输出INFO、WARN、ERROR调试信息

	private final static int WARN = 2; // waring过滤器，输出WARN和ERROR调试信息

	private final static int ERROR = 1; // error过滤器，只输出ERROR调试信息



	@Override
	public void v(Object object) {
		printObject(VERBOSE,null,object);
	}

	@Override
	public void v(String tag, Object object) {
		printObject(VERBOSE,tag,object);
	}

	@Override
	public void v(String message) {
		printString(VERBOSE,null,message);
	}

	@Override
	public void v(String tag, String message) {
		printString(VERBOSE,tag,message);
	}

	@Override
	public void i(Object object) {
		printObject(INFO,null,object);
	}

	@Override
	public void i(String tag, Object object) {
		printObject(INFO,tag,object);
	}

	@Override
	public void i(String message) {
		printString(INFO,null,message);
	}

	@Override
	public void i(String tag, String message) {
		printString(INFO,tag,message);
	}

	@Override
	public void d(Object object) {
		printObject(DEBUG,null,object);
	}

	@Override
	public void d(String tag, Object object) {
		printObject(DEBUG,tag,object);
	}

	@Override
	public void d(String message) {
		printString(DEBUG,null,message);
	}

	@Override
	public void d(String tag, String message) {
		printString(DEBUG,tag,message);
	}

	@Override
	public void w(Object object) {
		printObject(WARN,null,object);
	}

	@Override
	public void w(String tag, Object object) {
		printObject(WARN,tag,object);
	}

	@Override
	public void w(String message) {
		printString(WARN,null,message);
	}

	@Override
	public void w(String tag, String message) {
		printString(WARN,tag,message);
	}

	@Override
	public void e(Object object) {
		printObject(ERROR,null,object);
	}

	@Override
	public void e(String tag, Object object) {
		printObject(ERROR,tag,object);
	}

	@Override
	public void e(String message) {
		printString(ERROR,null,message);
	}

	@Override
	public void e(String tag, String message) {
		printString(ERROR,tag,message);
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
				printString(DEBUG, null,  msg);
			} else if (json.startsWith("[")) {
				JSONArray jsonArray = new JSONArray(json);
				String msg = jsonArray.toString(indent);
				printString(DEBUG, null,msg);
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
			printString(DEBUG, tag, "JSON{json is empty}");
			return;
		}
		try {
			if (json.startsWith("{")) {
				JSONObject jsonObject = new JSONObject(json);
				String msg = jsonObject.toString(indent);
				printString(DEBUG, tag, msg);
			} else if (json.startsWith("[")) {
				JSONArray jsonArray = new JSONArray(json);
				String msg = jsonArray.toString(indent);
				printString(DEBUG, tag, msg);
			}
		} catch (JSONException e) {
			printString(DEBUG, tag, e.toString() + "\n\njson = " + json);
		}
	}

	@Override
	public void xml(String xml) {
		if (TextUtils.isEmpty(xml)) {
			printString(DEBUG, null, "XML{xml is empty}");
			return;
		}
		try {
			Source xmlInput = new StreamSource(new StringReader(xml));
			StreamResult xmlOutput = new StreamResult(new StringWriter());
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(xmlInput, xmlOutput);
			printString(DEBUG, null, xmlOutput.getWriter().toString().replaceFirst(">", ">\n"));
		} catch (TransformerException e) {
			e(e.toString() + "\n\nxml = " + xml);
			printString(DEBUG, null, e.toString() + "\n\nxml = " + xml);
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
			printString(DEBUG, tag, "XML{xml is empty}");
			return;
		}
		try {
			Source xmlInput = new StreamSource(new StringReader(xml));
			StreamResult xmlOutput = new StreamResult(new StringWriter());
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(xmlInput, xmlOutput);
			printString(DEBUG, tag, xmlOutput.getWriter().toString().replaceFirst(">", ">\n"));
		} catch (TransformerException e) {
			printString(DEBUG, tag, e.toString() + "\n\nxml = " + xml);
		}
	}


	public void printObject(int level, String tag, Object object) {
		if (!LogConfig.LOG_ENABLE) {
			return;
		}
		if (level > LogConfig.LOG_LEVEL) {
			return;
		}
		print(level, tag, false, ObjectUtils.objectToString(object));
	}


	public void printString(int level, String tag, String msg) {
		if (!LogConfig.LOG_ENABLE) {
			return;
		}
		if (level > LogConfig.LOG_LEVEL) {
			return;
		}
		print(level, tag, false, msg);
	}



	public static void print(int level, String tag, boolean isPart, String msg) {

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
			case VERBOSE:
				Log.v(tag, msg);
				break;
			case DEBUG:
				Log.d(tag, msg);
				break;
			case INFO:
				Log.i(tag, msg);
				break;
			case WARN:
				Log.w(tag, msg);
				break;
			case ERROR:
				Log.e(tag, msg);
				break;
			default:
				break;
		}
	}

}
