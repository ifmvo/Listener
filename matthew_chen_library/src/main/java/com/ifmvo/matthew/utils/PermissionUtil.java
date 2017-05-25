package com.ifmvo.matthew.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZongfaHe on 16/4/16.
 */
public class PermissionUtil {

    public static void checkPermission(Activity activity, int requestCode, CheckObj... permission) {
        List<String> checkPermission = new ArrayList<>();
        for (CheckObj checkObj : permission) {
            int checkWriteExternalPermission = ContextCompat.checkSelfPermission(activity, checkObj.permission);
            if (checkWriteExternalPermission == PackageManager.PERMISSION_GRANTED) {
                checkObj.checkCallBack.onCheck(true);
            } else {
                checkPermission.add(checkObj.permission);
                checkObj.checkCallBack.onCheck(false);
            }
        }

        if (checkPermission.size() > 0) {
            String[] arr = checkPermission.toArray(new String[checkPermission.size()]);
            ActivityCompat.requestPermissions(activity, arr, requestCode);
        }
    }

    public static class CheckObj {

        public String permission;
        public CheckCallBack checkCallBack;

        public CheckObj(String permission, CheckCallBack checkCallBack) {
            this.permission = permission;
            this.checkCallBack = checkCallBack;
        }
    }

    public interface CheckCallBack {
        void onCheck(boolean isGranted);
    }
}
