package com.dgrlucky.log.parser;

/**
 * @author dgrlucky
 * @date 2016/3/24 12:00
 * @company dgrlucky
 * @desc parse interface
 */
public interface Parser<T> {

    String SEPARATOR = System.getProperty("line.separator");

    Class<T> parseType();

    String parseResult(T t);
}
