package com.dgrlucky.log;

import android.text.TextUtils;
import android.util.Log;

import com.dgrlucky.log.internal.Printer;
import com.dgrlucky.log.internal.Setting;
import com.dgrlucky.log.parser.BundleParse;
import com.dgrlucky.log.parser.CollectionParse;
import com.dgrlucky.log.parser.IntentParse;
import com.dgrlucky.log.parser.MapParse;
import com.dgrlucky.log.parser.Parser;
import com.dgrlucky.log.parser.ReferenceParse;
import com.dgrlucky.log.parser.ThrowableParse;
import com.dgrlucky.log.util.CommonUtil;

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
 * @author dgrlucky
 * @date 2016/3/24 12:00
 * @company dgrlucky
 * @desc Printer implement class
 */
public final class LogPrinter implements Printer {
    /**
     * System separator
     */
    public static final String SEPARATOR = System.getProperty("line.separator");
    /**
     * Default parse type
     */
    public static final Class<? extends Parser>[] DEFAULT_PARSE_CLASS = new Class[]{
            BundleParse.class, IntentParse.class, CollectionParse.class,
            MapParse.class, ThrowableParse.class, ReferenceParse.class
    };
    /**
     * It is used for json pretty print
     */
    private static final int JSON_INDENT = 4;
    /**
     * Print border length
     */
    private static final int BORDER_LENGTH = 200;

    /**
     * It is used to determine log setting such as thread info visibility
     */
    private static Setting setting;

    /**
     * Separator border type
     */
    private static final int DIR_TOP = 1;
    private static final int DIR_BOTTOM = 2;
    private static final int DIR_CENTER = 4;
    private static final int DIR_NORMAL = 3;
    /**
     * Drawing border
     */
    private static final char TOP_LEFT_CORNER = '╔';
    private static final char BOTTOM_LEFT_CORNER = '╚';
    private static final char MIDDLE_CORNER = '╟';
    private static final char NORMAL_CORNER = '║';
    private static final char DOUBLE_DIVIDER = '═';
    private static final char SINGLE_DIVIDER = '─';
    private static final String TOP_BORDER = TOP_LEFT_CORNER + getBorderLength(DOUBLE_DIVIDER,
            BORDER_LENGTH);
    private static final String BOTTOM_BORDER = BOTTOM_LEFT_CORNER + getBorderLength
            (DOUBLE_DIVIDER, BORDER_LENGTH);
    private static final String MIDDLE_BORDER = MIDDLE_CORNER + getBorderLength(SINGLE_DIVIDER,
            BORDER_LENGTH);

    /**
     * Append border
     *
     * @param c
     * @param len
     * @return
     */
    private static String getBorderLength(char c, int len) {
        String length = "";
        for (int i = 0; i < len; i++) {
            length += c;
        }
        return length;
    }


    protected LogPrinter() {
        setting = Setting.getInstance();
        setting.setParseClass(DEFAULT_PARSE_CLASS);
    }

    @Override
    public Setting init() {
        return setting;
    }

    @Override
    public void v(StackTraceElement element, Object object) {
        logObject(Log.VERBOSE, element, object);
    }

    @Override
    public void v(StackTraceElement element, String message, Object... args) {
        log(Log.VERBOSE, element, message, args);
    }

    @Override
    public void d(StackTraceElement element, Object object) {
        logObject(Log.DEBUG, element, object);
    }

    @Override
    public void d(StackTraceElement element, String message, Object... args) {
        log(Log.DEBUG, element, message, args);
    }

    @Override
    public void i(StackTraceElement element, Object object) {
        logObject(Log.INFO, element, object);
    }

    @Override
    public void i(StackTraceElement element, String message, Object... args) {
        log(Log.INFO, element, message, args);
    }

    @Override
    public void w(StackTraceElement element, Object object) {
        logObject(Log.WARN, element, object);
    }

    @Override
    public void w(StackTraceElement element, String message, Object... args) {
        log(Log.WARN, element, message, args);
    }

    @Override
    public void e(StackTraceElement element, Object object) {
        logObject(Log.ERROR, element, object);
    }

    @Override
    public void e(StackTraceElement element, String message, Object... args) {
        log(Log.ERROR, element, message, args);
    }

    @Override
    public void e(StackTraceElement element, Throwable throwable, String message, Object... args) {
        if (throwable != null && message != null) {
            message += " : " + throwable.toString();
        }
        if (throwable != null && message == null) {
            message = throwable.toString();
        }
        if (message == null) {
            message = "No message/exception is set";
        }
        log(Log.ERROR, element, message, args);
    }

    @Override
    public void s(StackTraceElement element, Object object) {
        System.out.println(getTag(element) + "\n => " + CommonUtil.objectToString(object));
    }

    @Override
    public void s(StackTraceElement element, String message, Object... args) {
        System.out.println(getTag(element) + "\n => " + (args.length == 1 ? message + " :" +
                CommonUtil.objectToString(args) : generateMessage(message, args)));
    }

    @Override
    public void wtf(StackTraceElement element, Object object) {
        logObject(Log.ASSERT, element, object);
    }

    @Override
    public void wtf(StackTraceElement element, String message, Object... args) {
        log(Log.ASSERT, element, message, args);
    }

    @Override
    public void object(StackTraceElement element, Object object) {
        logObject(Log.DEBUG, element, object);
    }

    @Override
    public void json(StackTraceElement element, String json) {
        if (TextUtils.isEmpty(json)) {
            d(element, "Empty/Null json content");
            return;
        }
        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                String message = jsonObject.toString(JSON_INDENT);
                d(element, message);
                return;
            }
            if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                String message = jsonArray.toString(JSON_INDENT);
                d(element, message);
            }
        } catch (JSONException e) {
            e(element, e.getCause().getMessage() + "\n" + json);
        }
    }

    @Override
    public void xml(StackTraceElement element, String xml) {
        if (TextUtils.isEmpty(xml)) {
            d(element, "Empty/Null xml content");
            return;
        }
        try {
            Source xmlInput = new StreamSource(new StringReader(xml));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
            d(element, xmlOutput.getWriter().toString().replaceFirst(">", ">\n"));
        } catch (TransformerException e) {
            e(element, e.getCause().getMessage() + "\n" + xml);
        }
    }

    /**
     * This method is synchronized in order to avoid messy of logs' order.
     */
    private synchronized void log(int logType, StackTraceElement element, String msg, Object...
            args) {
        if (!setting.isDebug()) {
            return;
        }
        String tag = getTag(element);
        String message = generateMessage(msg, args);
        byte[] bytes = message.getBytes();
        int length = bytes.length;
        if (setting.isShowBorder()) {
            logChunk(logType, tag, drawBorder(DIR_TOP));
            if (setting.isShowThread()) {
                logChunk(logType, tag, "║" + " Thread: " + Thread.currentThread()
                        .getName());
                logChunk(logType, tag, drawBorder(DIR_CENTER));
            }
            logChunk(logType, tag, drawBorder(DIR_NORMAL) + getLogInfo(element));
            logChunk(logType, tag, drawBorder(DIR_CENTER));
            if (length <= CommonUtil.LINE_MAX) {
                for (String sub : message.split(SEPARATOR)) {
                    logChunk(logType, tag, drawBorder(DIR_NORMAL) + sub);
                }
                logChunk(logType, tag, drawBorder(DIR_BOTTOM));
                return;
            }
            for (int i = 0; i < length; i += CommonUtil.LINE_MAX) {
                int count = Math.min(length - i, CommonUtil.LINE_MAX);
                String lines = new String(bytes, i, count);
                for (String sub : lines.split(SEPARATOR)) {
                    logChunk(logType, tag, drawBorder(DIR_NORMAL) + sub);
                }
            }
            logChunk(logType, tag, drawBorder(DIR_BOTTOM));
        } else {
            logChunk(logType, tag, msg);
        }
    }

    /**
     * Print object
     *
     * @param type
     * @param element
     * @param object
     */

    private void logObject(int type, StackTraceElement element, Object object) {
        if (object != null) {
            for (Parser parser : setting.getParseClass()) {
                if (parser.parseType().isAssignableFrom(object.getClass())) {
                    log(type, element, parser.parseResult(object));
                    return;
                }
            }
        }
        log(type, element, CommonUtil.objectToString(object));
    }

    /**
     * Get message
     *
     * @return
     */
    private String generateMessage(String message, Object... args) {
        return args.length > 0 ? String.format(message, args) : message;
    }

    /**
     * Log tag
     *
     * @param element
     * @return
     */
    private String getTag(StackTraceElement element) {
        String callerClazzName = element.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        int i = callerClazzName.indexOf("$");
        return i == -1 ? callerClazzName : callerClazzName.substring(0, i);
    }

    /**
     * Log header
     *
     * @param element
     * @return
     */
    private String getLogInfo(StackTraceElement element) {
        String stackTrace = element.toString();
        stackTrace = stackTrace.substring(stackTrace.lastIndexOf('('), stackTrace.length());
        String tag = "%s.%s%s";
        tag = String.format(tag, getTag(element),
                element.getMethodName(), stackTrace);
        return tag;
    }

    /**
     * Print some type log
     *
     * @param logType type
     * @param tag     tag
     * @param chunk   content
     */

    private void logChunk(int logType, String tag, String chunk) {
        switch (logType) {
            case Log.VERBOSE:
                Log.v(tag, chunk);
                break;
            case Log.DEBUG:
                Log.d(tag, chunk);
                break;
            case Log.INFO:
                Log.i(tag, chunk);
                break;
            case Log.WARN:
                Log.w(tag, chunk);
                break;
            case Log.ERROR:
                Log.e(tag, chunk);
                break;
            case Log.ASSERT:
                Log.wtf(tag, chunk);
                break;
            default:
                break;
        }
    }

    /**
     * Print devider
     *
     * @param dir
     * @return
     */
    public static String drawBorder(int dir) {
        switch (dir) {
            case DIR_TOP:
                return TOP_BORDER;
            case DIR_BOTTOM:
                return BOTTOM_BORDER;
            case DIR_NORMAL:
                return NORMAL_CORNER + " ";
            case DIR_CENTER:
                return MIDDLE_BORDER;
            default:
                break;
        }
        return "";
    }
}
