package com.uniquext.android.drinkwater.core;

import android.app.Activity;

import java.util.LinkedList;

/**
 * @author penghaitao
 * @version 1.0
 * @date 2018/7/10 14:57
 * @description App管理
 */
public final class AppManager {
    /**
     * Log标签
     */
    private static final String TAG = "AppManager";

    /**
     * activity集合
     */
    private LinkedList<Activity> activities = new LinkedList<>();

    private AppManager() {
    }

    /**
     * 获取管理器单例
     *
     * @return 单例
     */
    public static AppManager getInstance() {
        return SingleHolder.INSTANCE;
    }

    /**
     * 插入
     *
     * @param activity activity
     */
    protected void push(Activity activity) {
        activities.add(activity);
        LogUtil.d(TAG, "Push:" + activity.getLocalClassName());
    }

    /**
     * 移除
     *
     * @param activity activity
     */
    protected void remove(Activity activity) {
        activities.remove(activity);
        LogUtil.d(TAG, "Remove  :" + activity.getLocalClassName());
    }

    /**
     * 获取当前Activity
     *
     * @return 最上层Activity
     */
    public Activity getCurrentActivity() {
        if (!activities.isEmpty()) {
            return activities.getLast();
        }
        return null;
    }

    /**
     * 退出App
     */
    public void exit() {
        for (Activity activity : activities) {
            LogUtil.d(TAG, "finish :" + activity.getLocalClassName());
            activity.finish();
        }
        LogUtil.d(TAG, "App was exited.");
        System.exit(0);
    }


    /**
     * 内部类单例
     */
    private static final class SingleHolder {
        /**
         * 单例
         */
        private static final AppManager INSTANCE = new AppManager();
    }
}
