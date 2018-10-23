package com.uniquext.android.drinkwater.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * @author penghaitao
 * @version 1.0
 * @date 2018/7/16 17:09
 * @description SharedPreferences 缓存工具
 */
public final class SharedPreferencesUtil {

    /**
     * 默认SP存储文件名
     */
    private static final String FILE_NAME = "SharedPreferences";

    /**
     * SP存储文件名集合
     */
    private static Set<String> SP_SET = new HashSet<>();

    /**
     * 插入到默认存储
     *
     * @param context 上下文
     * @param key     key
     * @param value   value
     */
    public static void put(Context context, String key, Object value) {
        put(context, FILE_NAME, key, value);
    }

    /**
     * 插入指定SP
     *
     * @param context  上下文
     * @param fileName 文件名
     * @param key      key
     * @param value    value
     */
    public static void put(Context context, String fileName, String key, Object value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else {
            editor.putString(key, value.toString());
        }
        editor.apply();
        SP_SET.add(fileName);
    }

    /**
     * 从默认存储获取
     *
     * @param context       上下文
     * @param key           key
     * @param defaultObject 默认值
     * @param <T>           泛型
     * @return SP 存储值
     */
    public static <T> T get(Context context, String key, T defaultObject) {
        return get(context, FILE_NAME, key, defaultObject);
    }

    /**
     * 从指定SP获取
     *
     * @param context       上下文
     * @param fileName      文件名
     * @param key           key
     * @param defaultObject 默认值
     * @param <T>           泛型
     * @return SP 存储值
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(Context context, String fileName, String key, T defaultObject) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        if (defaultObject instanceof String) {
            return (T) sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return (T) Integer.valueOf(sp.getInt(key, (Integer) defaultObject));
        } else if (defaultObject instanceof Boolean) {
            return (T) Boolean.valueOf(sp.getBoolean(key, (Boolean) defaultObject));
        } else if (defaultObject instanceof Float) {
            return (T) Float.valueOf(sp.getFloat(key, (Float) defaultObject));
        } else if (defaultObject instanceof Long) {
            return (T) Long.valueOf(sp.getLong(key, (Long) defaultObject));
        }
        SP_SET.add(fileName);
        return null;
    }

    /**
     * 清空默认SP存储
     *
     * @param context 上下文
     */
    public static void clear(Context context) {
        clear(context, FILE_NAME);
    }

    /**
     * 清空指定SP存储的数据
     *
     * @param context  上下文
     * @param fileName 文件名
     */
    public static void clear(Context context, String fileName) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * 清空所有SP存储
     *
     * @param context 上下文
     */
    public static void clearAll(Context context) {
        for (String fileName : SP_SET) {
            clear(context, fileName);
        }
    }


}
