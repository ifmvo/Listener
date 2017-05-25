package com.ifmvo.matthew.utils.print;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ifmvo.matthew.R;


public class CustomToast extends Toast {
    public TextView tvText;

    public CustomToast(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
        setView(view);
        setGravity(Gravity.CENTER, 0, 0);
        tvText = (TextView) view.findViewById(R.id.tvText);
    }

    public static CustomToast makeText(Context context, CharSequence text, int duration) {
        CustomToast result = new CustomToast(context);
        result.tvText.setText(text);
        result.setDuration(duration);
        return result;
    }

    public static CustomToast makeText(Context context, int resId, int duration) {
        CustomToast result = new CustomToast(context);
        result.tvText.setText(resId);
        result.setDuration(duration);
        return result;
    }

    @Override
    public void setText(int resId) {
        tvText.setText(resId);

    }

    @Override
    public void setText(CharSequence s) {
        tvText.setText(s);
    }
}
