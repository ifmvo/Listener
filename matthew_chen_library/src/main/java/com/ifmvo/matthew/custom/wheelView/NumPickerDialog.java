package com.ifmvo.matthew.custom.wheelView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.ifmvo.matthew.R;
import com.ifmvo.matthew.utils.ViewUtil;


/**
 * Created by ZongfaHe on 16/3/15.
 */
public class NumPickerDialog extends Dialog {
    View view;
    WheelView numWheel;
    int num = 0;

    public NumPickerDialog(Context context, int startNum, int endNum, int base) {
        super(context, R.style.dialog_picker);
        init(context, startNum, endNum, base);
    }

    /**
     *
     * @param context
     * @param startNum
     * @param endNum
     * @param base 倍增基数
     */
    private void init(Context context, int startNum, int endNum, final int base) {
        num = base;
        view = LayoutInflater.from(context).inflate(R.layout.dialog_num_picker, null);
        view.setMinimumWidth(ViewUtil.getScreenDM((Activity) context).widthPixels);
        Window window = getWindow();
        window.setContentView(view);
        window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
        numWheel = (WheelView) view.findViewById(R.id.num);
        numWheel.setAdapter(new NumericWheelAdapter(startNum, endNum, base));
        numWheel.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                num = newValue + base;
            }
        });

        view.findViewById(R.id.btnCancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        view.findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectListener != null) {
                    mSelectListener.onSelect(num);
                }
                dismiss();
            }
        });
    }

    SelectListener mSelectListener;

    public void setSelectListener(SelectListener selectListener) {
        mSelectListener = selectListener;
    }

    public interface SelectListener {
        void onSelect(int num);
    }
}
