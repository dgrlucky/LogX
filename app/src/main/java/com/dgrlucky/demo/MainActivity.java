package com.dgrlucky.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.dgrlucky.log.LogX;
import com.dgrlucky.demo.bean.Person;

import java.io.IOException;
import java.lang.ref.SoftReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author dgrlucky
 * @date 2016/3/25 15:00
 * @company dgrlucky
 * @desc
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_String)
    Button mBtnString;
    @Bind(R.id.btn_Map)
    Button mBtnMap;
    @Bind(R.id.btn_Collect)
    Button mBtnCollect;
    @Bind(R.id.btn_Xml)
    Button mBtnXml;
    @Bind(R.id.btn_Json)
    Button mBtnJson;
    @Bind(R.id.btn_Object)
    Button mBtnObject;
    @Bind(R.id.btn_Intent)
    Button mBtnIntent;
    @Bind(R.id.btn_Bundle)
    Button mBtnBundle;
    @Bind(R.id.btn_Reference)
    Button mBtnReference;
    @Bind(R.id.btn_Throwable)
    Button mBtnThrowable;
    @Bind(R.id.btn_Reponse)
    Button mBtnReponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_String)
    public void printString() {
        LogX.v("hello everyone !");
        LogX.s("hello everyone !");
        LogX.i(Data.getIntArray());
        LogX.e("我是%s，我的工作是%s，我喜欢%s", "dgrlucky", "Android工程师", "萌妹纸");
    }

    @OnClick(R.id.btn_Map)
    public void printMap() {
        LogX.d(Data.getStringMap());
        LogX.i(Data.getObjectMap());
    }

    @OnClick(R.id.btn_Collect)
    public void printCollect() {
        LogX.w(Data.getStringList());
        LogX.e(Data.getObjectList());
        LogX.i(Data.getObjectArray2());
    }

    @OnClick(R.id.btn_Xml)
    public void printXml() {
        LogX.xml("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<manifest\n" +
                "    package=\"com.dgrlucky.LogXsample\"\n" +
                "    xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                "    >\n" +
                "\n" +
                "    <uses-permission android:name=\"android.permission.INTERNET\"/>\n" +
                "    <application\n" +
                "        android:allowBackup=\"true\"\n" +
                "        android:icon=\"@mipmap/ic_launcher\"\n" +
                "        android:label=\"@string/app_name\"\n" +
                "        android:supportsRtl=\"true\"\n" +
                "        android:theme=\"@style/AppTheme\"\n" +
                "        >\n" +
                "        <activity android:name=\".MainActivity\">\n" +
                "            <intent-filter>\n" +
                "                <action android:name=\"android.intent.action.MAIN\"/>\n" +
                "\n" +
                "                <category android:name=\"android.intent.category.LAUNCHER\"/>\n" +
                "            </intent-filter>\n" +
                "        </activity>\n" +
                "    </application>\n" +
                "\n" +
                "</manifest>");
    }

    @OnClick(R.id.btn_Json)
    public void printJson() {
        LogX.json("[{\"CityId\":18,\"CityName\":\"西安\",\"ProvinceId\":27,\"CityOrder\":1}," +
                "{\"CityId\":53,\"CityName\":\"广州\",\"ProvinceId\":27,\"CityOrder\":1}]'");
        LogX.json(Data.getJson());
    }

    @OnClick(R.id.btn_Object)
    public void printObject() {
        LogX.object(Data.getMan());
        LogX.object(Data.getWoMan());
        LogX.object(Data.getObject());
    }

    @OnClick(R.id.btn_Reference)
    public void printReference() {
        Person p = Data.getObject();
        SoftReference<Person> soft = new SoftReference<Person>(p);
        LogX.e(soft);
    }

    @OnClick(R.id.btn_Intent)
    public void printIntent() {
        Intent it = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media
                .EXTERNAL_CONTENT_URI);
        it.putExtra("open", "shift");
        it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        LogX.e(it);
    }

    @OnClick(R.id.btn_Bundle)
    public void printBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("int", 1);
        bundle.putString("str", "text");
        bundle.putSerializable("ser", Data.getObject());
        bundle.putStringArray("array", Data.getStringArray());
        bundle.putSerializable("array2", Data.getStringArray2());
        bundle.putSerializable("array3", Data.getStringArray3());
        bundle.putSerializable("obj", Data.getObject());
        bundle.putSerializable("objarray", Data.getObjectArray());
        bundle.putSerializable("map", Data.getStringMap());
        LogX.e(bundle);
    }

    @OnClick(R.id.btn_Reponse)
    public void printReponse() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://www.baidu.com").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogX.e(e, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogX.e(response);
            }
        });
    }

    @OnClick(R.id.btn_Throwable)
    public void printThrowable() {
        LogX.i(new ClassCastException("dgrlucky"));
        try {
            LogX.e(1 / 0);
        } catch (Exception e) {
            LogX.e(e, e.getMessage());
        }
    }
}
