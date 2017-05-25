package com.ifmvo.matthew.base.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.ifmvo.matthew.R;
import com.ifmvo.matthew.base.AppContextBase;
import com.ifmvo.matthew.custom.LoadingDialog;
import com.ifmvo.matthew.utils.ViewUtil;


/**
 * Created by Matthew_Chen on 16/9/2.
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * BaseActivity 的实例
     */
    private BaseActivity baseActivity;
    /**
     * 正在加载的 Dialog
     */
    private LoadingDialog loadingDialog;
    private AlertDialog alertDialog;
    /**
     * 设置启动关闭Activity的动画用
     */
    public static final int START_CLOSE_MODE_RIGHT_LEFT = 3;//右进左出
    public static final int START_CLOSE_MODE_BOTTOM = 1;//从下面冒出
    public static final int START_CLOSE_MODE_FADE = 2;//渐进渐出
    /**
     * start 和 close Activity 的模式
     */
    public int startCloseMode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseActivity = this;
        AppContextBase.addActivity(this);
        startCloseMode = getIntent().getIntExtra("startCloseMode", 0);

        initView();
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    //    @Subscribe
//    public void onEventMainThread(Object event) {
//    }

    /**
     * 4.4 以上将手机状态栏透明化
     */
    public void hideStatusBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    /**
     * 给 view 设置上边距为
     */
    public void setViewTopMargin(View view, int height){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(view.getLayoutParams());
            layoutParams.topMargin = height;
            view.setLayoutParams(layoutParams);
        }
    }

    /**
     * 给 View 设置上边距为状态栏的高度
     */
    public void setViewTopMargin(RelativeLayout view){
        setViewTopMargin(view, ViewUtil.getStatusHeight(getContext()));
    }

    /**
     * 弹出式启动 Activity
     *
     * @param intent
     * @param requestCode
     */
    public void popActForResult(Intent intent, int requestCode) {
//        setSwipeEnabled(false);
        intent.putExtra("startCloseMode", START_CLOSE_MODE_BOTTOM);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 渐变是启动 Activity
     *
     * @param intent
     * @param requestCode
     */
    public void fadeActForResult(Intent intent, int requestCode) {
        intent.putExtra("startCloseMode", START_CLOSE_MODE_FADE);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 侧滑是启动 Activity
     *
     * @param intent
     * @param requestCode
     */
    public void pushActForResult(Intent intent, int requestCode) {
        intent.putExtra("startCloseMode", START_CLOSE_MODE_RIGHT_LEFT);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 获取跳转的 Intent
     */
    public Intent getIntent(Class activityClass) {
        return new Intent(baseActivity, activityClass);
    }

    public void initData() {
        /**
         * 设置启动 Activity 的动画
         */
        if (startCloseMode == START_CLOSE_MODE_BOTTOM)
            overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.alpha_to_background);
        else if (startCloseMode == START_CLOSE_MODE_RIGHT_LEFT)
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        else if (startCloseMode == START_CLOSE_MODE_FADE)
            overridePendingTransition(R.anim.alpha_to_front, R.anim.alpha_to_background);
    }

    @Override
    public void finish() {
        super.finish();
        hideInput();
        /**
         * 设置关闭 Activity 的动画
         */
        if (startCloseMode == START_CLOSE_MODE_RIGHT_LEFT) {
            overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
        } else if (startCloseMode == START_CLOSE_MODE_BOTTOM) {
            overridePendingTransition(R.anim.alpha_to_front, R.anim.slide_out_to_bottom);
        } else if (startCloseMode == START_CLOSE_MODE_FADE) {
            overridePendingTransition(R.anim.alpha_to_front, R.anim.alpha_to_background);
        }
    }

    private void initView() {
        loadingDialog = new LoadingDialog(baseActivity);
    }

    /**
     * 获取 BaseActivity 的实例
     *
     * @return
     */
    public BaseActivity getContext() {
        return baseActivity;
    }

//    protected void replaceFragment(int contentLayoutId, Fragment fragment, boolean anim) {
//        replaceFragment(contentLayoutId, fragment);
//    }

//    public void showLoading() {
//        showLoading(true);
//    }

    /**
     * @param canceledOnTouchOutside 是否可以点击屏幕停止
     */
    public void showLoading(boolean canceledOnTouchOutside) {
        if (loadingDialog == null)
            loadingDialog = new LoadingDialog(baseActivity);
        loadingDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        loadingDialog.show();
    }

    public void closeLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }


    public void showAlert(String message, String allOk, String ok, String no, final  DialogInterface.OnClickListener allOkListener, final DialogInterface.OnClickListener okListener,
                          final DialogInterface.OnClickListener noListener) {
        alertDialog = new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(allOk,allOkListener)
                .setNeutralButton(ok, okListener)
                .setNegativeButton(no, noListener)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                dialog = null;
            }
        });
        alertDialog.show();
        WindowManager.LayoutParams params =
                alertDialog.getWindow().getAttributes();
        params.width = ViewUtil.getScreenDM(this).widthPixels / 5 * 4;
        alertDialog.getWindow().setAttributes(params);
    }

    protected void replaceFragment(int contentLayoutId, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(contentLayoutId, fragment, fragment.getClass().getName());
        transaction.commit();
    }

    /**
     * 显示键盘
     *
     * @param et 输入焦点
     */
    public void showInput(EditText et) {
        if (et != null) {
            et.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInput(et, -1);
        }
    }

    /**
     * 隐藏键盘
     */
    public void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getCurrentFocus();
        if (null != v)
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
