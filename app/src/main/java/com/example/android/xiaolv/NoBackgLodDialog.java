package com.example.android.xiaolv;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Android on 2017/12/8.
 */

public class NoBackgLodDialog {
    private Dialog mDialog;


    public NoBackgLodDialog(Context context, boolean isback) {
        mDialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        View view= LayoutInflater.from(context).inflate(R.layout.nobackg_loddialog, null);
        //将布局设置给Dialog
        mDialog.setContentView(view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = context.getResources().getDisplayMetrics().widthPixels;
        view.setLayoutParams(layoutParams);
        mDialog.getWindow().setGravity(Gravity.CENTER);
        mDialog.getWindow().setWindowAnimations(R.style.activityAnimation);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(isback);

    }

    public void show() {
        if (mDialog != null) {
            mDialog.show();
        }
    }
    public boolean isShowing(){
        if (mDialog!=null){
            return mDialog.isShowing();
        }
        return false;
    }
    public void  dismis(){
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
}
