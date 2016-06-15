package com.dgrlucky.log;

import com.dgrlucky.log.internal.Printer;
import com.dgrlucky.log.parser.Parser;
import com.dgrlucky.log.util.CommonUtil;

/**
 * @author dgrlucky
 * @date 2016/3/24 12:00
 * @company dgrlucky
 * @desc LogX core class
 */
public final class LogX {

    private static final Printer printer = new LogPrinter();

    /**
     * no instance
     */
    private LogX() {
    }

    /**
     * It is used to initialize
     *
     * @return the setting
     */
    public static void init(boolean isDebug) {
        printer.init().setDebug(isDebug);
    }

    /**
     * It is used to initialize
     *
     * @param isDebug      isDebug
     * @param isShowThread show thread info
     */
    public static void init(boolean isDebug, boolean isShowThread) {
        printer.init().setDebug(isDebug).setShowThread(isShowThread);
    }

    /**
     * It is used to initialize
     *
     * @param isDebug    isDebug
     * @param parseClass add parse class
     */
    public static void init(boolean isDebug, Class<? extends Parser>... parseClass) {
        printer.init().setDebug(isDebug).setParseClass(parseClass);
    }

    /**
     * Formats the verbose content and print it
     *
     * @param object the verbose content
     */
    public static void v(Object object) {
        printer.v(CommonUtil.getStackTrace(), object);
    }

    /**
     * Formats the verbose content and print it
     *
     * @param message the verbose tag
     * @param args    the verbose content
     */
    public static void v(String message, Object... args) {
        printer.v(CommonUtil.getStackTrace(), message, args);
    }

    /**
     * Formats the debug content and print it
     *
     * @param object the debug content
     */
    public static void d(Object object) {
        printer.d(CommonUtil.getStackTrace(), object);
    }

    /**
     * Formats the debug content and print it
     *
     * @param message the debug tag
     * @param args    the debug content
     */
    public static void d(String message, Object... args) {
        printer.d(CommonUtil.getStackTrace(), message, args);
    }

    /**
     * Formats the info content and print it
     *
     * @param object the info content
     */
    public static void i(Object object) {
        printer.i(CommonUtil.getStackTrace(), object);
    }

    /**
     * Formats the info content and print it
     *
     * @param message the info tag
     * @param args    the info content
     */
    public static void i(String message, Object... args) {
        printer.i(CommonUtil.getStackTrace(), message, args);
    }

    /**
     * Formats the warn content and print it
     *
     * @param object the warn content
     */
    public static void w(Object object) {
        printer.w(CommonUtil.getStackTrace(), object);
    }

    /**
     * Formats the warn content and print it
     *
     * @param message the warn tag
     * @param args    the warn content
     */
    public static void w(String message, Object... args) {
        printer.w(CommonUtil.getStackTrace(), message, args);
    }

    /**
     * Formats the error content and print it
     *
     * @param object the error content
     */
    public static void e(Object object) {
        printer.e(CommonUtil.getStackTrace(), object);
    }

    /**
     * Formats the error content and print it
     *
     * @param message the err tag
     * @param args    the error content
     */
    public static void e(String message, Object... args) {
        printer.e(CommonUtil.getStackTrace(), message, args);
    }

    /**
     * Formats the error content and print it
     *
     * @param throwable the error exception
     * @param message   the err tag
     * @param args      the error content
     */
    public static void e(Throwable throwable, String message, Object... args) {
        printer.e(CommonUtil.getStackTrace(), throwable, message, args);
    }

    /**
     * Formats the system output content and print it
     *
     * @param object the output content
     */
    public static void s(Object object) {
        printer.s(CommonUtil.getStackTrace(), object);
    }

    /**
     * Formats the system out content and print it
     *
     * @param message the output tag
     * @param args    the output content
     */
    public static void s(String message, Object... args) {
        printer.s(CommonUtil.getStackTrace(), message, args);
    }

    /**
     * Formats the assert content and print it
     *
     * @param object the assert content
     */
    public static void wtf(Object object) {
        printer.wtf(CommonUtil.getStackTrace(), object);
    }

    /**
     * Formats the assert content and print it
     *
     * @param message the assert tag
     * @param args    the assert content
     */
    public static void wtf(String message, Object... args) {
        printer.wtf(CommonUtil.getStackTrace(), message, args);
    }

    /**
     * Formats the object content and print it
     *
     * @param object the object content
     */
    public static void object(Object object) {
        printer.object(CommonUtil.getStackTrace(), object);
    }

    /**
     * Formats the json content and print it
     *
     * @param json the json content
     */
    public static void json(String json) {
        printer.json(CommonUtil.getStackTrace(), json);
    }

    /**
     * Formats the xml content and print it
     *
     * @param xml the xml content
     */
    public static void xml(String xml) {
        printer.xml(CommonUtil.getStackTrace(), xml);
    }
}
