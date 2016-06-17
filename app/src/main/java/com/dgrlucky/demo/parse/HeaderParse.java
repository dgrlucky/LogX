package com.dgrlucky.demo.parse;

import com.dgrlucky.log.parser.Parser;

import okhttp3.Headers;

/**
 * @author dgrlucky
 * @date 2016/3/25 15:00
 * @company dgrlucky
 * @desc
 */
public class HeaderParse implements Parser<Headers> {
    @Override
    public Class<Headers> parseType() {
        return Headers.class;
    }

    @Override
    public String parseResult(Headers headers) {
        StringBuilder builder = new StringBuilder(headers.getClass().getSimpleName() + " [" +
                SEPARATOR);
        for (String name : headers.names()) {
            builder.append(String.format("%s = %s" + SEPARATOR,
                    name, headers.get(name)));
        }
        return builder.append("]").toString();
    }
}
