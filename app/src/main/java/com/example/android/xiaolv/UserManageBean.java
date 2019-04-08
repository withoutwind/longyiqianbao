package com.example.android.xiaolv;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;
import com.lzy.okhttputils.request.BaseRequest;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/29.
 */

public class UserManageBean {
    private static boolean isLogin = false;//是否登录
    public static UserBean userBean = new UserBean();
    //public static Context context;
    private static String writerId = "";




    /**
     * 退出登录
     */

    public static void exit(Context context) {
        PreferencesTool.saveValue(context, "isCache", "");
        isLogin = false;//是否登录
        userBean = new UserBean();
    }





    /**
     * 判断是否登录
     * @param context
     * @return
     */

    /*public static boolean isLogin(BaseActivity context) {
        if (isLogin){
            if(userBean==null||userBean.get()==null||userBean.getNickname().equals("")){
                goAddInfo(context);
                return false;
            }else {
                return true;
            }
        }else {
            goLogin(context);
        }
        return isLogin;
    }*/

    /**
     * 判断是否登录
     *
     * @param
     * @return
     */

    public static boolean isLogin() {
        return isLogin;
    }

    /**
     * 判断是否登录
     * @param context
     * @return
     */
    public static boolean isLogin(Context context) {
        if (isLogin){
            if(userBean==null||userBean.getPhone()==null||userBean.getPhone().equals("")){
                isLogin=false;
                goLogin(context);
            }else {
                return true;
            }
        }else {
            goLogin(context);
        }
        return isLogin;
    }


    /**
     * 提示信息不全
     * @param context
     */

    /*public static void goAddInfo(final BaseActivity context) {
        OkDialog okDialog=new OkDialog(context,"信息不全,不能操作,是否补全信息?");
        okDialog.setListener(new OkDialog.Listener() {
            @Override
            public void okClick() {
               *//* new AddInfoDialog(context, new AddInfoDialog.Listener() {
                    @Override
                    public void ok() {
                    }
                    @Override
                    public void close() {
                    }
                });*//*
            }
            @Override
            public void closeClick() {
            }
        }, null, null);
        okDialog.setBigTitle("提示");
        okDialog.show();
    }*/

    /**
     * 提示不是作者
     *
     * @param context
     */

    public static void goAddAuthor(final Context context) {

    }

    /**
     * 跳转登录页面
     *
     * @param context
     */

    public static void goLogin(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public static void setIsLogin(boolean isLogin) {
        UserManageBean.isLogin = isLogin;
    }


    /**
     * 查看缓存
     *
     * @param context
     * @return
     */

    public static String getCache(Context context) {
        String isCache = PreferencesTool.getValue(context, "isCache");
        if (!"".equals(isCache)) {
            return isCache;
        } else {
            return null;
        }
    }


    /**
     * 后台登录
     *
     * @param context
     */

    public static void backgroundLogin(Context context) {
        OkHttpUtils.post(CachUrl.baseurl+"customer/login")
                .params("phone",PreferencesTool.getUserName(context))
                .params("passwd",PreferencesTool.getPassWord(context))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        Log.v("logion",s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if ("200".equals(jsonObject.getString("code"))) {
                                UserManageBean.userBean = gson.fromJson(jsonObject.getString("data"), UserBean.class);
                                isLogin=true;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

    }




}
