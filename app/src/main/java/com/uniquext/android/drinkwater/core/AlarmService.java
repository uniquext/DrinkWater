package com.uniquext.android.drinkwater.core;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Calendar;

/**
 * @author penghaitao
 * @version 1.0
 * @date 2018/10/24 17:35
 * @description
 */
public class AlarmService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        startForeground();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        test();
        return super.onStartCommand(intent, flags, startId);
    }

    private void test() {
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent1 = new Intent();
        intent1.setPackage(getPackageName());
        intent1.setAction("com.uniquext.android.receiver.WATER_REMINDER");
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
//        am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi1);
    }
}
