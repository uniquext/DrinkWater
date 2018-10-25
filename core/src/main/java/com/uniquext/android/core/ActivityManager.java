package com.uniquext.android.core;

import android.app.Activity;

import java.util.LinkedList;

/**
 * @author penghaitao
 * @version 1.0
 * @date 2018/7/10 14:57
 * @description Activity管理
 */
public final class ActivityManager {

    private Activity mCurrentActivity = null;
    private volatile int mForegroundActivityCount = 0;
    private LinkedList<Activity> activities = new LinkedList<>();

    private ActivityManager() {
    }

    public static ActivityManager getInstance() {
        return SingleHolder.INSTANCE;
    }

    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    void setCurrentActivity(Activity mCurrentActivity) {
        this.mCurrentActivity = mCurrentActivity;
    }

    public boolean isOnForeground() {
        return mForegroundActivityCount > 0;
    }

    public void exit() {
        for (Activity activity : activities) {
            activity.finish();
        }
        System.exit(0);
    }

    void foregroundActivity() {
        mForegroundActivityCount++;
    }

    void backgroundActivity() {
        mForegroundActivityCount--;
    }

    void push(Activity activity) {
        activities.add(activity);
    }

    void remove(Activity activity) {
        activities.remove(activity);
    }

    /**
     * 内部类单例
     */
    private static final class SingleHolder {
        /**
         * 单例
         */
        private static final ActivityManager INSTANCE = new ActivityManager();
    }
}
