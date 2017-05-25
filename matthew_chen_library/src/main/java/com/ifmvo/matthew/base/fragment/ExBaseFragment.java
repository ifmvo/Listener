package com.ifmvo.matthew.base.fragment;

/**
 * Created by Matthew_Chen on 16/9/7.
 */
public abstract class ExBaseFragment extends BaseFragment {
    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd(getClass().getName());
//        JPushInterface.onFragmentPause(getContext(), getClass().getName());
    }

    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart(getClass().getName());
//        JPushInterface.onFragmentResume(getContext(), getClass().getName());
    }
}