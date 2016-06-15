package com.dgrlucky.log.parser;

import android.content.Intent;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dgrlucky
 * @date 2016/3/24 12:00
 * @company dgrlucky
 * @desc parse intent type
 */
public class IntentParse implements Parser<Intent> {

    private static Map<Integer, String> flagMap = new HashMap<>();

    static {
        Class cla = Intent.class;
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().startsWith("FLAG_")) {
                int value = 0;
                try {
                    Object object = field.get(cla);
                    if (object instanceof Integer || object.getClass().getSimpleName().equals
                            ("int")) {
                        value = (int) object;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (flagMap.get(value) == null) {
                    flagMap.put(value, field.getName());
                }
            }
        }
    }

    @Override
    public Class<Intent> parseType() {
        return Intent.class;
    }

    @Override
    public String parseResult(Intent intent) {
        StringBuilder builder = new StringBuilder(parseType().getSimpleName() + " [" + SEPARATOR);
        builder.append(String.format("%s = %s" + SEPARATOR, "Scheme", intent.getScheme()));
        builder.append(String.format("%s = %s" + SEPARATOR, "Action", intent.getAction()));
        builder.append(String.format("%s = %s" + SEPARATOR, "DataString", intent.getDataString()));
        builder.append(String.format("%s = %s" + SEPARATOR, "Type", intent.getType()));
        builder.append(String.format("%s = %s" + SEPARATOR, "Package", intent.getPackage()));
        builder.append(String.format("%s = %s" + SEPARATOR, "ComponentInfo", intent.getComponent
                ()));
        builder.append(String.format("%s = %s" + SEPARATOR, "Flags", getFlags(intent.getFlags())));
        builder.append(String.format("%s = %s" + SEPARATOR, "Categories", intent.getCategories()));
        builder.append(String.format("%s = %s" + SEPARATOR, "Extras",
                new BundleParse().parseResult(intent.getExtras())));
        return builder.toString() + "]";
    }

    /**
     * Get flag value
     *
     * @param flags intent type
     * @return value
     */
    private String getFlags(int flags) {
        StringBuilder builder = new StringBuilder();
        for (int flagKey : flagMap.keySet()) {
            if ((flagKey & flags) == flagKey) {
                builder.append(flagMap.get(flagKey));
                builder.append(" | ");
            }
        }
        if (TextUtils.isEmpty(builder.toString())) {
            builder.append(flags);
        } else if (builder.indexOf("|") != -1) {
            builder.delete(builder.length() - 2, builder.length());
        }
        return builder.toString();
    }
}
