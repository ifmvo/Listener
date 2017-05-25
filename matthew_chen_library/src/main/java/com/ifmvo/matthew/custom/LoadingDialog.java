package com.ifmvo.matthew.custom;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.ifmvo.matthew.R;


/**
 * Created by Matthew_Chen on 16/9/2.
 */
public class LoadingDialog extends Dialog {

    private GifView gifView;

    public LoadingDialog(Context context) {
        super(context, R.style.dialog_loading);

        setContentView(R.layout.custom_loading);
        Window dialogWindow = getWindow();
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);
//        dialogWindow.setWindowAnimations(R.style.anim_dialog_simpleselect);
        dialogWindow.setGravity(Gravity.CENTER);

//        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失

        gifView = (GifView) findViewById(R.id.gifView);
        gifView.setMovieResource(R.drawable.loading);
    }

//    public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside){
//        setCanceledOnTouchOutside(canceledOnTouchOutside);
//    }

}
