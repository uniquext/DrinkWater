package com.uniquext.android.drinkwater.core;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.uniquext.android.core.ActivityManager;
import com.uniquext.android.core.util.LogUtil;

import static android.content.Context.ALARM_SERVICE;

/**
 * @author penghaitao
 * @version 1.0
 * @date 2018/10/24 13:58
 * @description
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        if ()
        LogUtil.d("AlarmReceiver foreground", String.valueOf(ActivityManager.getInstance().isOnForeground()));
        LogUtil.d("AlarmReceiver msg", intent.getStringExtra("msg"));
        LogUtil.d("AlarmReceiver action", intent.getAction());
//        test(context);
    }
    //        AlarmManager.ACTION_NEXT_ALARM_CLOCK_CHANGED

    private void test(Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        Intent intent1 = new Intent();
        intent1.setPackage(context.getPackageName());
        intent1.setAction("com.uniquext.android.receiver.WATER_REMINDER");
        intent1.putExtra("msg", "你该打酱油了1");
        PendingIntent pi1 = PendingIntent.getBroadcast(context, 1, intent1, 0);
        am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10 * 1000, pi1);
    }
}
