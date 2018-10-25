package com.uniquext.android.drinkwater.module.plan.helper;

import android.content.Context;

import com.uniquext.android.drinkwater.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author penghaitao
 * @version 1.0
 * @date 2018/10/20 16:05
 * @description
 */
final class PlanHelper {

    private static final String PLAN_SETTINGS_FILE = "PlanSettings";
    private static final String WATER_TOTAL_KEY = "WaterTotal";
    private static final String[] REMINDER_TIME_KEY = new String[]{
            "ReminderTime1", "ReminderTime2", "ReminderTime3", "ReminderTime4",
            "ReminderTime5", "ReminderTime6", "ReminderTime7", "ReminderTime8"
    };

    private static final int DEFAULT_TOTAL = 2000;
    private static final String[] DEFAULT_REMINDER_TIME = new String[]{
            "06:30", "08:30", "11:00", "13:30", "16:00", "17:30", "19:00", "21:00"
    };

    public void setPlanTotalSettings(Context context, int total) {
        SharedPreferencesUtil.put(context, PLAN_SETTINGS_FILE, WATER_TOTAL_KEY, total);
    }

    public int getPlanTotalSettings(Context context) {
        Integer total = SharedPreferencesUtil.get(context, PLAN_SETTINGS_FILE, WATER_TOTAL_KEY, DEFAULT_TOTAL);
        return total == null ? DEFAULT_TOTAL : total;
    }

    public void setPlanTimeSettings(Context context, List<String> times) {
        for (int i = 0; i < times.size(); ++i) {
            SharedPreferencesUtil.put(context, PLAN_SETTINGS_FILE, REMINDER_TIME_KEY[i], times.get(i));
        }
    }

    public List<String> getPlanTimeSettings(Context context) {
        List<String> time = new ArrayList<>();
        for (int i = 0; i < REMINDER_TIME_KEY.length; ++i) {
            time.add(SharedPreferencesUtil.get(
                    context, PLAN_SETTINGS_FILE, REMINDER_TIME_KEY[i], DEFAULT_REMINDER_TIME[i])
            );
        }
        return time;
    }
}
