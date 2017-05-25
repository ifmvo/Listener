package com.ifmvo.matthew.base.fragment;

import android.view.View;


/**
 * Created by Matthew_Chen on 16/9/11.
 */
public abstract class ExBaseTopBarFragment extends BaseTopBarFragment {

    @Override
    public void onPreLoad() {
        btnTopL.setVisibility(View.GONE);
        btnTopR.setVisibility(View.GONE);
    }
}
