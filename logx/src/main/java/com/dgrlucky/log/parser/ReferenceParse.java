package com.dgrlucky.log.parser;

import com.dgrlucky.log.util.CommonUtil;

import java.lang.ref.Reference;

/**
 * @author dgrlucky
 * @date 2016/3/24 12:00
 * @company dgrlucky
 * @desc parse reference type
 */
public class ReferenceParse implements Parser<Reference> {
    @Override
    public Class<Reference> parseType() {
        return Reference.class;
    }

    @Override
    public String parseResult(Reference reference) {
        Object actual = reference.get();
        StringBuilder builder = new StringBuilder(reference.getClass().getSimpleName() + "<"
                + actual.getClass().getSimpleName() + "> {");
        builder.append(CommonUtil.objectToString(actual));
        return builder.toString() + "}";
    }
}
