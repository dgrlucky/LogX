package com.dgrlucky.log.parser;

import android.util.Log;

/**
 * @author dgrlucky
 * @date 2016/3/24 12:00
 * @company dgrlucky
 * @desc parse throwable type
 */
public class ThrowableParse implements Parser<Throwable> {
    @Override
    public Class<Throwable> parseType() {
        return Throwable.class;
    }

    @Override
    public String parseResult(Throwable throwable) {
        return Log.getStackTraceString(throwable);
    }
}
