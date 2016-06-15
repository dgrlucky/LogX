package com.dgrlucky.log.parser;

import com.dgrlucky.log.util.CommonUtil;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author dgrlucky
 * @date 2016/3/24 12:00
 * @company dgrlucky
 * @desc parse collect type
 */
public class CollectionParse implements Parser<Collection> {
    @Override
    public Class<Collection> parseType() {
        return Collection.class;
    }

    @Override
    public String parseResult(Collection collection) {
        String simpleName = collection.getClass().getName();
        String msg = "%s size = %d [" + SEPARATOR;
        msg = String.format(msg, simpleName, collection.size());
        if (!collection.isEmpty()) {
            Iterator<Object> iterator = collection.iterator();
            int flag = 0;
            while (iterator.hasNext()) {
                String itemString = "[%d]:%s%s";
                Object item = iterator.next();
                msg += String.format(itemString, flag, CommonUtil.objectToString(item),
                        flag++ < collection.size() - 1 ? "," + SEPARATOR : SEPARATOR);
            }
        }
        return msg + "]";
    }
}
