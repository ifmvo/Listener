package com.ifmvo.matthew.utils.print;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

public class Toaster {
    // Toast
    private static Toast toast;

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
//        show(context, message, CustomToast.LENGTH_SHORT);
        if (null == toast) {
            toast = CustomToast.makeText(context, message, CustomToast.LENGTH_SHORT);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message) {
//        show(context, message, CustomToast.LENGTH_SHORT);
        if (null == toast) {
            toast = CustomToast.makeText(context, message, CustomToast.LENGTH_SHORT);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
//        show(context, message, CustomToast.LENGTH_LONG);

        if (null == toast) {
            toast = CustomToast.makeText(context, message, CustomToast.LENGTH_LONG);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message) {
//        show(context, message, CustomToast.LENGTH_LONG);
        if (null == toast) {
            toast = CustomToast.makeText(context, message, CustomToast.LENGTH_LONG);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, CharSequence message, int duration) {
//        CustomToast.makeText(context, message, duration).show();
        if (null == toast) {
            toast = CustomToast.makeText(context, message, CustomToast.LENGTH_LONG);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(Context context, int message, int duration) {
        CustomToast.makeText(context, message, duration).show();
    }
}
