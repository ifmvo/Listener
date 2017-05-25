package com.ifmvo.matthew.base.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifmvo.matthew.base.activity.BaseActivity;
import com.ifmvo.matthew.utils.ViewUtil;

/**
 * Created by Matthew_Chen on 16/9/7.
 */
public abstract class BaseFragment extends Fragment {

    private static BaseActivity baseActivity;
    View contentView;

    boolean isInit = false;//是否初始化了
    boolean isViewCreated;//View是否创建了

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EventBus.getDefault().register(this);
//        Log.e("ceres", "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//        Log.e("ceres", "onDestroy");

    }

//    @Subscribe
//    public void onEventMainThread(Object event) {
//    }

    /**
     * 给 view 设置上边距为
     */
    public void setViewTopPadding(View view, int height) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            view.setPadding(0, height, 0, 0);
        }
    }

    /**
     * 给 View 设置上边距为状态栏的高度
     */
    public void setViewTopPadding(View view){
        setViewTopPadding(view, ViewUtil.getStatusHeight(getContext()));
    }

    public static <T> T newInstance(Context context, Class<T> clazz) {
        return newInstance(context, clazz, null);
    }

    public static <T> T newInstance(Context context, Class<T> clazz, Bundle args) {
        return (T) Fragment.instantiate(context, clazz.getName(), args);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(getLayout(), null);
        baseActivity = (BaseActivity) getActivity();
        findView();
        isViewCreated = true;
//        Log.e("ceres", "onCreateView");

        return contentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint()) {
            isInit = true;
            inited();
        }
//        Log.e("ceres", "getUserVisibleHint" + getUserVisibleHint() + "isInit"+isInit);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreated && !isInit) {
            isInit = true;
            inited();
        }
//        else if (!isVisibleToUser){
//            isInit = false;
//        }

//        Log.e("ceres", "isVisibleToUser" + isVisibleToUser + "isViewCreated"+isViewCreated+"isInit"+isInit);
    }

    public BaseActivity getContext(){
        if (baseActivity == null)
            baseActivity = (BaseActivity) getActivity();
        return baseActivity;
    }

    public View findViewById(int resId){
        return contentView.findViewById(resId);
    }

    public void replaceFragment(int resId, Fragment fragment) {
        replaceFragment(resId, fragment, fragment.getClass().getName());
    }

    public void replaceFragment(int resId, Fragment fragment, String tag) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(resId, fragment, tag);
        transaction.commit();
    }

    public abstract int getLayout();
    public abstract void findView();
    public abstract void inited();
}