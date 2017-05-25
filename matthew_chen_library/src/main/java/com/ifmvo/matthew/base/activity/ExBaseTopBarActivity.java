package com.ifmvo.matthew.base.activity;

import android.view.View;

import com.ifmvo.matthew.R;



/**
 * Created by Matthew_Chen on 16/9/20.
 */
public abstract class ExBaseTopBarActivity extends BaseTopBarActivity {

    @Override
    public void onPreLoad() {
        setLeftBtn("", R.mipmap.ic_return, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideInput();
                finish();
            }
        });
    }

}
