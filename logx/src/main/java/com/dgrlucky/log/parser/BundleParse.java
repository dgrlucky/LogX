package com.dgrlucky.log.parser;

import android.os.Bundle;

import com.dgrlucky.log.util.CommonUtil;

/**
 * @author dgrlucky
 * @date 2016/3/24 12:00
 * @company dgrlucky
 * @desc parse bundle type
 */
public class BundleParse implements Parser<Bundle> {

    @Override
    public Class<Bundle> parseType() {
        return Bundle.class;
    }

    @Override
    public String parseResult(Bundle bundle) {
        if (bundle != null) {
            StringBuilder builder = new StringBuilder(bundle.getClass().getName() + " [" +
                    SEPARATOR);
            for (String key : bundle.keySet()) {
                builder.append(String.format("'%s' => %s " + SEPARATOR,
                        key, CommonUtil.objectToString(bundle.get(key))));
            }
            builder.append("]");
            return builder.toString();
        }
        return null;
    }
}
