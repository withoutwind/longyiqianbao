package com.example.android.xiaolv;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import android.util.Log;


import com.lzy.okhttputils.OkHttpUtils;

import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;



import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/7/4.
 */

public class MyApication extends Application {
    // public static RequestQueue volleyQueue;
    private static Context context;
    private List<Activity> oList;//用于存放所有启动的Activity的集合
    // public static RefWatcher mRefWatcher;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

       /* if (isMainProcess()) {
            OpenInstall.init(this);
        }*/
        oList = new ArrayList<Activity>();
        refresh();

        // sInstance = this;


        context = getApplicationContext();

        //volleyQueue = Volley.newRequestQueue(context);
        OkHttpUtils.init(this);



       /* //检测app
        if (LeakCanary.isInAnalyzerProcess(this)) {//1
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        mRefWatcher = LeakCanary.install(this);*/

    }

    /* public boolean isMainProcess() {
         int pid = android.os.Process.myPid();
         ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
         for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
             if (appProcess.pid == pid) {
                 return getApplicationInfo().packageName.equals(appProcess.processName);
             }
         }
         return false;
     }*/
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        //initHotfix();

    }



    //static 代码段可以防止内存泄露
    private void refresh() {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new WaterDropHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
           /* //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
                @Override
                public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                    //指定为经典Footer，默认是 BallPulseFooter
                    return new ClassicsFooter(context).setDrawableSize(20);
                }
            });*/
    }

    /**
     * 添加Activity
     */
    public void addActivity_(Activity activity) {
        Log.d("rexiufu", "addActivity_");
// 判断当前集合中不存在该Activity
        if (!oList.contains(activity)) {
            oList.add(activity);//把当前Activity添加到集合中
        }
    }

    /**
     * 销毁单个Activity
     */
    public void removeActivity_(Activity activity) {
//判断当前集合中存在该Activity
        if (oList.contains(activity)) {
            oList.remove(activity);//从集合中移除
            activity.finish();//销毁当前Activity
        }
    }

    /**
     * 销毁所有的Activity
     */
    public void removeALLActivity_() {
        //通过循环，把集合中的所有Activity销毁
        for (Activity activity : oList) {
            activity.finish();
        }
    }

}
