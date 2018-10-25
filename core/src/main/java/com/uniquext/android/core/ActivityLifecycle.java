package com.uniquext.android.core;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.uniquext.android.core.util.LogUtil;


/**
 * @author penghaitao
 * @version 1.0
 * @date 2018/10/25 15:36
 * @description
 */
class ActivityLifecycle implements Application.ActivityLifecycleCallbacks {

    /**
     * Log标签
     */
    private static final String TAG = "ActivityLifecycle";

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        ActivityManager.getInstance().push(activity);
        LogUtil.i(TAG, "onActivityCreated:" + activity.getLocalClassName());
    }

    @Override
    public void onActivityStarted(Activity activity) {
        ActivityManager.getInstance().foregroundActivity();
        LogUtil.i(TAG, "onActivityStarted:" + activity.getLocalClassName());
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ActivityManager.getInstance().setCurrentActivity(activity);
        LogUtil.i(TAG, "onActivityResumed:" + activity.getLocalClassName());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        LogUtil.i(TAG, "onActivityPaused:" + activity.getLocalClassName());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        ActivityManager.getInstance().backgroundActivity();
        LogUtil.i(TAG, "onActivityStopped:" + activity.getLocalClassName());
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        LogUtil.i(TAG, "onActivitySaveInstanceState:" + activity.getLocalClassName());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        ActivityManager.getInstance().remove(activity);
        LogUtil.i(TAG, "onActivityDestroyed:" + activity.getLocalClassName());
    }
}
