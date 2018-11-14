package com.uniquext.android.drinkwater.core;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.uniquext.android.core.util.LogUtil;
import com.uniquext.android.drinkwater.MainActivity;
import com.uniquext.android.drinkwater.module.plan.helper.PlanManager;

import java.util.Calendar;

/**
 * @author penghaitao
 * @version 1.0
 * @date 2018/10/24 17:35
 * @description
 */
public class AlarmService extends Service {

    private final long INTERVAL_MILLIS = 24 * 60 * 60 * 1000;

    private AlarmReceiver mAlarmReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d("AlarmService", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        test();
        LogUtil.d("AlarmService", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    private void setAlarmReminder() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        for (String time : PlanManager.getInstance().getTime()) {

            String[] date = time.split(":");
            if (date.length != 2) continue;

            Intent intent = new Intent(AlarmReceiver.ALARM_RECEIVER);
            intent.setPackage(getPackageName());
            intent.putExtra(AlarmReceiver.ALARM_TIME, time);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MINUTE, Integer.valueOf(date[0]));
            calendar.set(Calendar.SECOND, Integer.valueOf(date[1]));
            calendar.set(Calendar.MILLISECOND, 0);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), INTERVAL_MILLIS, pendingIntent);
        }
    }

    private void test() {
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent1 = new Intent(AlarmReceiver.ALARM_RECEIVER);
        intent1.setPackage(getPackageName());
        intent1.putExtra("msg", "你该打酱油了1");
        PendingIntent pi1 = PendingIntent.getBroadcast(this, 1, intent1, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 16);
        calendar.set(Calendar.SECOND, 10);
        calendar.set(Calendar.MILLISECOND, 0);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            am.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(), pi1);
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            am.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(), pi1);
//        } else {
//            am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(), 5*1000, pi1);
//        }
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 5 * 1000, pi1);
    }
}
