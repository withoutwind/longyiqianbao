package com.example.android.xiaolv;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;



/**
 * @author zjc
 */
@SuppressLint("NewApi")
public class BaseActivity extends AppCompatActivity {
    private MyApication application;
    private BaseActivity oContext;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        if (AndroidWorkaround.checkDeviceHasNavigationBar(this)) {
            AndroidWorkaround.assistActivity(findViewById(android.R.id.content));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        if (application == null) {
            // 得到Application对象
            application = (MyApication) getApplication();
        }
        oContext = this;// 把当前的上下文对象赋值给BaseActivity
        addActivity();// 调用添加方法
    }


    public void showToast(String title) {
        ToolUtils.showToast(title,this);
    }
    // 添加Activity方法
    public void addActivity() {
        application.addActivity_(oContext);// 调用myApplication的添加Activity方法
    }

    //销毁当个Activity方法
    public void removeActivity() {
        application.removeActivity_(oContext);// 调用myApplication的销毁单个Activity方法
    }

    //销毁所有Activity方法
    public void removeALLActivity() {
        application.removeALLActivity_();// 调用myApplication的销毁单个Activity方法
    }



    public void addFragment(FragmentTransaction transaction, int id, BaseFragment frament, boolean b) {
        if (b)
            transaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out, R.anim.back_left_in, R.anim.back_right_out);
        transaction.add(id, frament);
        transaction.commit();
    }

    public void replaceFragment(FragmentTransaction transaction, int id, BaseFragment frament) {
        transaction.replace(id, frament);
    }

    public void removeFragment(FragmentTransaction transaction, BaseFragment frament) {
        transaction.remove(frament);
    }

    public void showFragment(FragmentTransaction transaction, int id, BaseFragment frament, boolean b) {
        if (b)
            transaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out, R.anim.back_left_in, R.anim.back_right_out);
        transaction.show(frament);
        transaction.commit();
    }

    public void hideFragment(FragmentTransaction transaction, BaseFragment frament) {
        if (frament != null) {
            transaction.hide(frament);
        }
    }

    public void goBack() {
        removeActivity();
    }

    //获取状态栏高度
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public static void todaohanglanHeight(LinearLayout linearLayout, BaseActivity baseActivity) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
//获取当前控件的布局对象
        params.height = getStatusBarHeight(baseActivity);//设置当前控件布局的高度
        linearLayout.setLayoutParams(params);//将设置好的布局参数应用到控件中
    }



/*@Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("访问次数"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息。"SplashScreen"为页面名称，可自定义
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("访问次数"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);          //统计时长
    }*/

}
