package com.ifmvo.matthew.base;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;

/**
 * Created by Matthew_Chen on 16/9/5.
 */
public class AppContextBase extends Application {

    /**
     * 所有 Activity extends BaseActivity
     */
    private static ArrayList<Activity> activities = new ArrayList<>();

    private static AppContextBase appContextBase;

    public AppContextBase getAppContextBase(){
        return appContextBase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContextBase = this;
    }

    public static void addActivity(Activity activity){
        if (!activities.contains(activity)){
            activities.add(activity);
        }
    }

    public void exitActivity(Activity activity){
        if (!activity.isFinishing())
            activity.finish();

        if (activities.contains(activity))
            activities.remove(activity);
    }

    public void exitClient(){
        finishAllActivity();
    }

    public void finishAllActivity(){
        for (Activity a : activities)
            if (a != null)
                a.finish();

        activities.clear();
    }
}
