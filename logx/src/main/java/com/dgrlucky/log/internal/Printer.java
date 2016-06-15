package com.dgrlucky.log.internal;

/**
 * @author dgrlucky
 * @date 2016/3/24 12:00
 * @company dgrlucky
 * @desc Define log print interface
 */
public interface Printer {

    Setting init();

    void v(StackTraceElement element, Object object);

    void v(StackTraceElement element, String message, Object... args);

    void d(StackTraceElement element, Object object);

    void d(StackTraceElement element, String message, Object... args);

    void i(StackTraceElement element, Object object);

    void i(StackTraceElement element, String message, Object... args);

    void w(StackTraceElement element, Object object);

    void w(StackTraceElement element, String message, Object... args);

    void e(StackTraceElement element, Object object);

    void e(StackTraceElement element, String message, Object... args);

    void e(StackTraceElement element, Throwable throwable, String message, Object... args);

    void s(StackTraceElement element, Object object);

    void s(StackTraceElement element, String message, Object... args);

    void wtf(StackTraceElement element, Object object);

    void wtf(StackTraceElement element, String message, Object... args);

    void object(StackTraceElement element, Object object);

    void json(StackTraceElement element, String json);

    void xml(StackTraceElement element, String xml);
}
