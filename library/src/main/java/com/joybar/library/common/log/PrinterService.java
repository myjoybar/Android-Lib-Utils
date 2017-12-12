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
import java.util.MissingFormatArgumentException;

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

    public final static int VERBOSE = 5; // 不过滤输出所有调试信息 包括 VERBOSE、DEBUG、INFO、WARN、ERROR

    public final static int DEBUG = 4; // debug过滤器，输出DEBUG、INFO、WARN、ERROR调试信息

    public final static int INFO = 3; // info过滤器，输出INFO、WARN、ERROR调试信息

    public final static int WARN = 2; // waring过滤器，输出WARN和ERROR调试信息

    public final static int ERROR = 1; // error过滤器，只输出ERROR调试信息


    public static void printLog(int level, String tag, String msg) {
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


    @Override
    public void v(Object object) {
        printLog(VERBOSE, null, false, ObjectUtils.objectToString(object), new Object[]{});
    }

    @Override
    public void v(String tag, Object object) {
        printLog(VERBOSE, tag, false,  ObjectUtils.objectToString(object), new Object[]{});
    }

    @Override
    public void v(String tag, String message) {
        printLog(VERBOSE, tag, false, message, new Object[]{});
    }

    @Override
    public void v(String message, Object... args) {
        printLog(VERBOSE, null, false, message, args);
    }

    @Override
    public void v(String tag, String message, Object... args) {
        printLog(VERBOSE, tag, false, message, args);

    }

    @Override
    public void i(Object object) {
        printLog(INFO, null, false, ObjectUtils.objectToString(object), new Object[]{});

    }

    @Override
    public void i(String tag, Object object) {
        printLog(INFO, tag, false, ObjectUtils.objectToString(object), new Object[]{});
    }

    @Override
    public void i(String tag, String message) {
        printLog(INFO, tag, false, message, new Object[]{});
    }

    @Override
    public void i(String message, Object... args) {
        printLog(INFO, null, false, message, new Object[]{});
    }

    @Override
    public void i(String tag, String message, Object... args) {
        printLog(INFO, tag, false, message, args);
    }

    @Override
    public void d(Object object) {
        printLog(DEBUG, null, false, ObjectUtils.objectToString(object), new Object[]{});
    }

    @Override
    public void d(String tag, Object object) {
        printLog(DEBUG, tag, false, ObjectUtils.objectToString(object), new Object[]{});
    }

    @Override
    public void d(String tag, String message) {
        printLog(DEBUG, tag, false, message, new Object[]{});
    }

    @Override
    public void d(String message, Object... args) {
        printLog(DEBUG, null, false, message, new Object[]{});
    }

    @Override
    public void d(String tag, String message, Object... args) {
        printLog(DEBUG, tag, false, message, args);
    }

    @Override
    public void w(Object object) {
        printLog(WARN, null, false,  ObjectUtils.objectToString(object), new Object[]{});
    }

    @Override
    public void w(String tag, Object object) {
        printLog(WARN, tag, false,  ObjectUtils.objectToString(object), new Object[]{});
    }

    @Override
    public void w(String tag, String message) {
        printLog(WARN, tag, false, message, new Object[]{});

    }

    @Override
    public void w(String message, Object... args) {
        printLog(WARN, null, false, message, args);
    }

    @Override
    public void w(String tag, String message, Object... args) {
        printLog(WARN, tag, false, message, args);
    }

    @Override
    public void e(Object object) {
        printLog(ERROR, null, false, ObjectUtils.objectToString(object), new Object[]{});
    }

    @Override
    public void e(String tag, Object object) {
        printLog(ERROR, tag, false, ObjectUtils.objectToString(object), new Object[]{});
    }

    @Override
    public void e(String tag, String message) {
        printLog(ERROR, tag, false, message, new Object[]{});
    }

    @Override
    public void e(String message, Object... args) {
        printLog(ERROR, null, false, message, args);
    }

    @Override
    public void e(String tag, String message, Object... args) {
        printLog(ERROR, tag, false, message, args);
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
                printLog(DEBUG, null, false, msg, new Object[]{});
            } else if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                String msg = jsonArray.toString(indent);
                printLog(DEBUG, null, false, msg, new Object[]{});
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
            d("JSON{json is empty}");
            return;
        }
        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                String msg = jsonObject.toString(indent);
                printLog(DEBUG, tag, false, msg, new Object[]{});
            } else if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                String msg = jsonArray.toString(indent);
                printLog(DEBUG, tag, false, msg, new Object[]{});
            }
        } catch (JSONException e) {
            e(e.toString() + "\n\njson = " + json);
        }
    }

    @Override
    public void xml(String xml) {
        if (TextUtils.isEmpty(xml)) {
            d("XML{xml is empty}");
            return;
        }
        try {
            Source xmlInput = new StreamSource(new StringReader(xml));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
            printLog(DEBUG, null, false, xmlOutput.getWriter().toString().replaceFirst(">", ">\n"), new Object[]{});
        } catch (TransformerException e) {
            e(e.toString() + "\n\nxml = " + xml);
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
            d("XML{xml is empty}");
            return;
        }
        try {
            Source xmlInput = new StreamSource(new StringReader(xml));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
            printLog(DEBUG, tag, false, xmlOutput.getWriter().toString().replaceFirst(">", ">\n"), new Object[]{});
        } catch (TransformerException e) {
            e(e.toString() + "\n\nxml = " + xml);
        }
    }


    public static void printLog(int level, String tag, boolean isPart, String msg, Object... args) {
        if (level > LogConfig.LOG_LEVEL) {
            return;
        }
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (!isPart) {
            if (args.length > 0) {
                try {
                    msg = String.format(msg, args);
                } catch (MissingFormatArgumentException e) {

                }
            }
        }

        if (TextUtils.isEmpty(tag)) {
            tag = LogConfig.TAG;
        }
        tag = LogConfig.TAG_PREFIX + tag;

        if (LogConfig.SHOW_TOP && !isPart) {
            printLog(level, tag, Utils.printDividingLine(Utils.DIVIDER_TOP));
        }
        if (msg.length() > LogConfig.LINE_MAX) {
            for (String subMsg : Utils.largeStringToList(msg)) {
                printLog(level, tag, true, subMsg, args);
            }
        } else {
            printLog(level, tag, createMessage(tag, msg));
        }

        if (LogConfig.SHOW_BOTTOM && !isPart) {
            printLog(level, tag, Utils.printDividingLine(Utils.DIVIDER_BOTTOM));
        }
    }

    private static String getLineIndicator() {
        StackTraceElement element = ((new Exception()).getStackTrace())[5];
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
}
