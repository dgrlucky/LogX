package com.dgrlucky.demo.parse;


import com.dgrlucky.log.parser.Parser;

import java.io.IOException;

import okhttp3.Response;

/**
 * @author dgrlucky
 * @date 2016/3/25 15:00
 * @company dgrlucky
 * @desc
 */
public class OkHttpResponseParse implements Parser<Response> {
    @Override
    public Class<Response> parseType() {
        return Response.class;
    }

    @Override
    public String parseResult(Response response) {
        if (response != null) {
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("code = %s" + SEPARATOR, response.code()));
            builder.append(String.format("isSuccessful = %s" + SEPARATOR, response.isSuccessful()));
            builder.append(String.format("url = %s" + SEPARATOR, response.request().url()));
            builder.append(String.format("message = %s" + SEPARATOR, response.message()));
            builder.append(String.format("protocol = %s" + SEPARATOR, response.protocol()));
            builder.append(String.format("header = %s" + SEPARATOR,
                    new HeaderParse().parseResult(response.headers())));
            try {
                builder.append(String.format("body = %s" + SEPARATOR, response.body().string()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return builder.toString();
        }
        return null;
    }
}
