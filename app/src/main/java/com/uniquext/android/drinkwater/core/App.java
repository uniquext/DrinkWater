package com.uniquext.android.drinkwater.core;

import android.app.Application;
import android.os.Process;
import android.util.Log;

import com.uniquext.android.drinkwater.util.LogUtil;

/**
 * @author penghaitao
 * @version 1.0
 * @date 2018/7/10 16:17
 * @description Application
 */
public final class App extends Application {

    /**
     * Application
     */
    private static App app;

    public static App getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        LogUtil.setLevel(Log.ERROR);
        initServiceOnWork();
        registerActivityLifecycleCallbacks(new ActivityLifecycle());
    }

    /**
     * 在工作线程中初始化后台服务
     * 主要是一些三方服务
     * 可能会出现WorkThread尚未初始化完毕，但MainThread已开始使用的情况
     * 因此需根据使用场景判断是否应该异步初始化
     */
    private void initServiceOnWork() {
        new Thread(() -> {
            //  设置为后台线程，不予主线程抢资源
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            this.runOnWork();
        }).start();
    }

    public void runOnWork() {
    }

    /**
     * 内部类单例
     */
    private static final class SingleHolder {
        /**
         * 单例
         */
        private static final App INSTANCE = new App();
    }
}
