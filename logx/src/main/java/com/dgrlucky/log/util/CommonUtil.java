package com.dgrlucky.log.util;

import java.lang.reflect.Field;

/**
 * @author dgrlucky
 * @date 2016/3/24 12:00
 * @company dgrlucky
 * @desc common util class
 */
public class CommonUtil {

    public static final String SEPARATOR = System.getProperty("line.separator");

    /**
     * Android's max limit for a log entry is ~4076 bytes,
     * so 4000 bytes is used as chunk size since default charset
     * is UTF-8
     */
    public static final int LINE_MAX = 4000;

    /**
     * Get StackTraceElement
     *
     * @return
     */
    public static StackTraceElement getStackTrace() {
        return Thread.currentThread().getStackTrace()[4];
    }

    /**
     * Object convert to string
     *
     * @param object
     * @return
     */
    public static String objectToString(Object object) {
        if (object == null) {
            return "Object[object is null]";
        }
        if (ArrayUtil.isArray(object)) {
            return ArrayUtil.parseArray(object);
        } else {
            if (object.toString().startsWith(object.getClass().getName() + "@")) {
                StringBuilder builder = new StringBuilder();
                getClassFields(object.getClass(), builder, object, false);
                Class superClass = object.getClass().getSuperclass();
                while (superClass != null) {
                    getClassFields(superClass, builder, object, true);
                    superClass = superClass.getSuperclass();
                }
                return builder.toString();
            } else {
                return object.toString();
            }
        }
    }

    /**
     * Append char or charsequence
     *
     * @param cla
     * @param builder
     */
    private static void getClassFields(Class cla, StringBuilder builder, Object o, boolean
            isSuper) {
        if (cla.equals(Object.class)) {
            return;
        }
        if (isSuper) {
            builder.append(SEPARATOR + "    ");
        }
        builder.append(cla.getSimpleName() + " {");
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object subObject = null;
            try {
                subObject = field.get(o);
            } catch (IllegalAccessException e) {
                subObject = e;
            } finally {
                if (subObject != null) {
                    if (subObject instanceof String) {
                        subObject = "\"" + subObject + "\"";
                    } else if (subObject instanceof Character) {
                        subObject = "\'" + subObject + "\'";
                    }
                }
                builder.append(String.format("%s = %s, ", field.getName(),
                        subObject == null ? "null" : subObject.toString()));
            }
        }
        if (builder.toString().endsWith("{")) {
            builder.append("}");
        } else {
            builder.replace(builder.length() - 2, builder.length() - 1, "}");
        }
    }
}
