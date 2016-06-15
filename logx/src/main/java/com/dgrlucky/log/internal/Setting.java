package com.dgrlucky.log.internal;


import com.dgrlucky.log.parser.Parser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dgrlucky
 * @date 2016/3/24 12:00
 * @company dgrlucky
 * @desc Log setting
 */
public final class Setting {
    private boolean isDebug = true;
    private static Setting instance;
    private List<Parser> parseClass;
    private boolean isShowThread = true;
    private boolean isShowBorder = true;

    private Setting() {
        parseClass = new ArrayList<>();
    }

    public static Setting getInstance() {
        if (instance == null) {
            synchronized (Setting.class) {
                if (instance == null) {
                    instance = new Setting();
                }
            }
        }
        return instance;
    }

    public List<Parser> getParseClass() {
        return parseClass;
    }

    public Setting setParseClass(Class<? extends Parser>... parse) {
        for (Class<? extends Parser> cla : parse) {
            try {
                this.parseClass.add(0, cla.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public Setting setDebug(boolean debug) {
        isDebug = debug;
        return this;
    }

    public boolean isShowThread() {
        return isShowThread;
    }

    public Setting setShowThread(boolean isShowThread) {
        this.isShowThread = isShowThread;
        return this;
    }

    public boolean isShowBorder() {
        return isShowBorder;
    }
}
