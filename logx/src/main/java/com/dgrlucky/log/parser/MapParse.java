package com.dgrlucky.log.parser;

import com.dgrlucky.log.util.CommonUtil;

import java.util.Map;
import java.util.Set;

/**
 * @author dgrlucky
 * @date 2016/3/24 12:00
 * @company dgrlucky
 * @desc parse map type
 */
public class MapParse implements Parser<Map> {
    @Override
    public Class<Map> parseType() {
        return Map.class;
    }

    @Override
    public String parseResult(Map map) {
        String msg = map.getClass().getName() + " [" + SEPARATOR;
        Set<Object> keys = map.keySet();
        for (Object key : keys) {
            String itemString = "%s -> %s" + SEPARATOR;
            Object value = map.get(key);
            msg += String.format(itemString, CommonUtil.objectToString(key),
                    CommonUtil.objectToString(value));
        }
        return msg + "]";
    }
}
