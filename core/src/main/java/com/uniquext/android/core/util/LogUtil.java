package com.uniquext.android.core.util;

import android.util.Log;

import com.uniquext.android.core.BuildConfig;

import java.util.Locale;

/**
 * @author penghaitao
 * @version 1.0
 * @date 2018/7/12 11:01
 * @description 日志类
 */
public final class LogUtil {

    /**
     * 默认标签
     */
    private static final String TAG = "LogUtil";
    /**
     * 日志级别
     */
    private static int level = Log.INFO;

    /**
     * 设置级别
     *
     * @param level 日志级别
     */
    public static void setLevel(int level) {
        LogUtil.level = level;
    }

    /**
     * VERBOSE
     *
     * @param msg 信息
     */
    public static void v(String msg) {
        v(TAG, msg);
    }

    /**
     * VERBOSE
     *
     * @param tag 标签
     * @param msg 信息
     */
    public static void v(String tag, String msg) {
        if (BuildConfig.DEBUG || Log.VERBOSE >= level) {
            Log.v(format(tag), msg);
        }
    }

    /**
     * DEBUG
     *
     * @param msg 信息
     */
    public static void d(String msg) {
        d(TAG, msg);
    }

    /**
     * DEBUG
     *
     * @param tag 标签
     * @param msg 信息
     */
    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG || Log.DEBUG >= level) {
            Log.d(format(tag), msg);
        }
    }

    /**
     * INFO
     *
     * @param msg 信息
     */
    public static void i(String msg) {
        i(TAG, msg);
    }

    /**
     * INFO
     *
     * @param tag 标签
     * @param msg 信息
     */
    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG || Log.INFO >= level) {
            Log.i(format(tag), msg);
        }
    }

    /**
     * WARN
     *
     * @param msg 信息
     */
    public static void w(String msg) {
        w(TAG, msg);
    }

    /**
     * WARN
     *
     * @param tag 标签
     * @param msg 信息
     */
    public static void w(String tag, String msg) {
        if (BuildConfig.DEBUG || Log.WARN >= level) {
            Log.w(format(tag), msg);
        }
    }

    /**
     * ERROR
     *
     * @param msg 信息
     */
    public static void e(String msg) {
        e(TAG, msg);
    }

    /**
     * ERROR
     *
     * @param tag 标签
     * @param msg 信息
     */
    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG || Log.ERROR >= level) {
            Log.e(format(tag), msg);
        }
    }

    /**
     * 格式化
     * 日志级别/TAG：调用类名.方法名（L：所在行数）：MSG
     *
     * @param tag 标签
     * @return 带详细信息的标签
     */
    private static String format(String tag) {
        StackTraceElement element = getStackTraceElement();
        if (element != null) {
            String className = element.getClassName();
            className = className.substring(className.lastIndexOf('.') + 1);
            tag = String.format(Locale.CHINA, "%s:%s.%s(L:%d)",
                    tag, className, element.getMethodName(), element.getLineNumber());
        }
        return tag;
    }

    /**
     * 日志输出调用所在堆栈节点
     * dalvik.system.VMStack    getThreadStackTrace
     * java.lang.Thread         getStackTrace
     * com.uniquext.core.LogUtil    {@link #format(String)}
     * com.uniquext.core.LogUtil    {@link #v(String)}
     * com.uniquext.core.LogUtil    {@link #v(String, String)}
     * who invoking
     *
     * @return invoking StackTraceElement
     */
    private static StackTraceElement getStackTraceElement() {
        boolean isTargetStackTraceElement = false;
        StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stackTraceElement) {
            boolean isLogMethod = LogUtil.class.getName().equals(element.getClassName());
            if (isTargetStackTraceElement && !isLogMethod) {
                return element;
            }
            isTargetStackTraceElement = isLogMethod;
        }
        return null;
    }

}
