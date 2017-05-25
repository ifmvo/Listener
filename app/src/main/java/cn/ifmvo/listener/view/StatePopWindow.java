package cn.ifmvo.listener.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import cn.ifmvo.listener.R;

/**
 * Created by 陈序员 on 2017/5/23.
 * Email: Matthew_Chen_1994@163.com
 * Blog: https://blog.ifmvo.cn
 */

public class StatePopWindow extends PopupWindow {

    TextView tvAll;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;

    public StatePopWindow(Context context) {
        super(LayoutInflater.from(context).inflate(R.layout.layout_pop_window, null), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
    }


    @Override
    public void setContentView(View contentView) {
        super.setContentView(contentView);

        tvAll = (TextView) contentView.findViewById(R.id.tvAll);
        tv1 = (TextView) contentView.findViewById(R.id.tv1);
        tv2 = (TextView) contentView.findViewById(R.id.tv2);
        tv3 = (TextView) contentView.findViewById(R.id.tv3);
        tv4 = (TextView) contentView.findViewById(R.id.tv4);

//        contentView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss();
//            }
//        });

        tvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectStateListener.onSelectState(0);
                dismiss();
            }
        });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectStateListener.onSelectState(1);
                dismiss();
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectStateListener.onSelectState(2);
                dismiss();

            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectStateListener.onSelectState(3);
                dismiss();

            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectStateListener.onSelectState(4);
                dismiss();

            }
        });


    }

    OnSelectStateListener onSelectStateListener;

    public interface OnSelectStateListener{
        void onSelectState(int position);
    }

    public void setOnSelectStateListener(OnSelectStateListener onSelectStateListener){
        this.onSelectStateListener = onSelectStateListener;
    }
}
