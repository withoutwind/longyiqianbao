package com.example.android.xiaolv;

import android.content.Context;
import android.content.SharedPreferences;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/9/6.
 */

public class SharedPreferencesUtil {
    private static SharedPreferencesUtil prefsUtil;
    public Context context;
    public SharedPreferences prefs;
    public SharedPreferences.Editor editor;

    public synchronized static SharedPreferencesUtil getInstance() {
        return prefsUtil;
    }

    public static void init(Context context, String prefsname, int mode) {
        prefsUtil = new SharedPreferencesUtil();
        prefsUtil.context = context;
        prefsUtil.prefs = prefsUtil.context.getSharedPreferences(prefsname, mode);
        prefsUtil.editor = prefsUtil.prefs.edit();
    }

    public static void saveRead(Context context, String bookid, int currentChapter, int curBeginPos, int curEndPos, int currentPage, int baocundiannum) {
        //获取SharedPreferences对象
        prefsUtil.prefs = context.getSharedPreferences("configs", Context.MODE_PRIVATE);
        //获取Editor对象
        prefsUtil.editor = prefsUtil.prefs.edit();
        //设置参数
        prefsUtil.editor.putInt(bookid + "currentChapter", currentChapter);
        prefsUtil.editor.putInt(bookid + "currentChapter", currentChapter);
        prefsUtil.editor.putInt(bookid + "currentChapter", currentChapter);
        prefsUtil.editor.putInt(bookid + "currentChapter", currentChapter);
        //提交
        prefsUtil.editor.commit();
    }

    public int getInt(String key, int defaultVal) {
        return this.prefs.getInt(key, defaultVal);
    }

    public long getLong(String key, long defaultVal) {
        return this.prefs.getLong(key, defaultVal);
    }

    public SharedPreferencesUtil putInt(String key, int value) {

        editor.putInt(key, value);
        editor.commit();
        return this;
    }

    public SharedPreferencesUtil putlong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
        return this;
    }

    public static void saveLogin(Context context, boolean isSanfang, String openid, String type, String token) {
        //获取SharedPreferences对象
        SharedPreferences sharedPredisan = context.getSharedPreferences("configs", Context.MODE_PRIVATE);
        //获取Editor对象
        SharedPreferences.Editor editor = sharedPredisan.edit();
        //设置参数
        editor.putBoolean("isSanfang", isSanfang);
        editor.putString("openid", openid);
        editor.putString("type", type);
        editor.putString("token", token);
        //提交
        editor.commit();
    }




    public SharedPreferencesUtil remove(String key) {
        editor.remove(key);
        editor.commit();
        return this;
    }

    public boolean getBoolean(String key, boolean defaultVal) {
        return this.prefs.getBoolean(key, defaultVal);
    }
public void setBoolean(String key, boolean defaultVal){
    editor.putBoolean(key, defaultVal);
    editor.commit();
}
    public SharedPreferencesUtil removeAll() {
        editor.clear();
        editor.commit();
        return this;
    }


    public String getString(String key, String defaultVal) {
        return this.prefs.getString(key, defaultVal);
    }

    public SharedPreferencesUtil putString(String key, String value) {

        editor.putString(key, value);
        editor.commit();
        return this;
    }
}
