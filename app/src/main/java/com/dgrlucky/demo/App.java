package com.dgrlucky.demo;

import android.app.Application;

import com.dgrlucky.log.*;
import com.dgrlucky.demo.parse.OkHttpResponseParse;


/**
 * @author dgrlucky
 * @date 2016/3/25 15:00
 * @company dgrlucky
 * @desc
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //LogX.init(BuildConfig.DEBUG);
        //LogX.init(BuildConfig.DEBUG, false);//false 表示不显示线程信息
        LogX.init(BuildConfig.DEBUG, OkHttpResponseParse.class);//OkHttpResponseParse为自定义解析类型
    }
}
