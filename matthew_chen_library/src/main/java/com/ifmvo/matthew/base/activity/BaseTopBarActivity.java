package com.ifmvo.matthew.base.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ifmvo.matthew.R;



/**
 * Created by Matthew_Chen on 16/9/25.
 */
public abstract class BaseTopBarActivity extends BaseActivity {
    protected ImageView ivTopBg, ivBaseBg;
    protected Button btnTopL;
    protected Button btnTopR;
    protected RelativeLayout layTopCenter, layTopBar;
    protected LinearLayout layLoading;
    protected FrameLayout layContent;
    protected TextView tvError, tvTopTitle;
    protected ContentLoadingProgressBar progressBar;
    protected LayoutInflater layoutInflater;
    protected TextView tvRightTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_base_activity_topbar);
        ivBaseBg = (ImageView) findViewById(R.id.ivSuperBg);
        ivTopBg = (ImageView) findViewById(R.id.ivTopBg);
        btnTopL = (Button) findViewById(R.id.btnTopL);
        btnTopR = (Button) findViewById(R.id.btnTopR);
        layTopCenter = (RelativeLayout) findViewById(R.id.layTopCenter);
        progressBar = (ContentLoadingProgressBar) findViewById(R.id.progressBar);
        layLoading = (LinearLayout) findViewById(R.id.layLoading);
        tvError = (TextView) findViewById(R.id.tvError);
        layTopBar = (RelativeLayout) findViewById(R.id.layTop);
        layContent = (FrameLayout) findViewById(R.id.layContent);
        tvTopTitle = (TextView) findViewById(R.id.tvTopTitle);
        tvRightTip = (TextView) findViewById(R.id.tvRightTip);
        layoutInflater = LayoutInflater.from(this);
//        EventBus.getDefault().register(this);
        onPreLoad();
        onCreatedContentView();

//        ButterKnife.bind(this);
        onFindView();
        onPostLoad();
    }


    public ViewGroup getSupperMainView() {
        return (ViewGroup) findViewById(R.id.laySupperMain);
    }

    public abstract void onPreLoad();

    public abstract void onCreatedContentView();

    public abstract void onFindView();

    public abstract void onPostLoad();

    protected boolean onErrorRefreshBtnClick() {
        return false;
    }

    protected void setLoadError() {
        setLoadError("");
    }

    protected void setLoadError(String errorStr) {
        layContent.setVisibility(View.GONE);
        layLoading.setVisibility(View.VISIBLE);
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(errorStr);
        progressBar.setVisibility(View.GONE);
    }

//    protected void showContentLoading() {
//        layContent.setVisibility(View.GONE);
//        layLoading.setVisibility(View.VISIBLE);
//        tvError.setVisibility(View.GONE);
//        progressBar.setVisibility(View.VISIBLE);
//    }
//
//    protected void dismissContentLoading() {
//        layContent.setVisibility(View.VISIBLE);
//        layLoading.setVisibility(View.GONE);
//    }

    public void setLoadError(int drawableRes, String errorStr) {
        tvError.setCompoundDrawablesWithIntrinsicBounds(0, drawableRes, 0, 0);
        setLoadError(errorStr);
    }

    protected void setMainContentView(View view) {
        if (view == null) {
            return;
        }
        layContent.removeAllViews();
        layContent.addView(view);
    }

    protected void setMainContentView(int layoutResId) {
        if (layoutResId == 0) {
            return;
        }
        layoutInflater.inflate(layoutResId, layContent);
    }

    public void setTitle(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        tvTopTitle.setVisibility(View.VISIBLE);
        tvTopTitle.setText(text);
    }

    public void setLeftBtn(String text, int resId, View.OnClickListener clickListener) {
//        btnTopL.setText(text == null ? "" : text);
//        setLeftBtnImg(resId);
        btnTopL.setVisibility(View.VISIBLE);
        btnTopL.setOnClickListener(clickListener);
    }

//    public void setLeftBtnImg(int resId) {
//        btnTopL.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
//    }

    public void setRightBtn(String text, int resId, View.OnClickListener clickListener) {
        btnTopR.setText(text == null ? "" : text);
        setRightBtnImg(resId);
        btnTopR.setVisibility(View.VISIBLE);
        btnTopR.setOnClickListener(clickListener);
    }

    public void setRightBtnImg(int resId) {
        btnTopR.setCompoundDrawablesWithIntrinsicBounds(0, 0, resId, 0);
    }

    protected void replaceContent(Fragment fragment) {
        replaceFragment(R.id.layContent, fragment);
    }
}



