package com.uniquext.android.drinkwater.core;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.uniquext.android.core.ActivityManager;
import com.uniquext.android.core.App;
import com.uniquext.android.core.util.LogUtil;
import com.uniquext.android.drinkwater.MainActivity;
import com.uniquext.android.drinkwater.R;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * @author penghaitao
 * @version 1.0
 * @date 2018/10/24 13:58
 * @description
 */
public class AlarmReceiver extends BroadcastReceiver {

    public static final String ALARM_TIME = "AlarmTime";
    public static final String ALARM_RECEIVER = "com.uniquext.android.receiver.WATER_REMINDER";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ALARM_RECEIVER.equals(intent.getAction())){
            if (ActivityManager.getInstance().isOnForeground()) {
                Toast.makeText(context, "前台", Toast.LENGTH_SHORT).show();
            } else {
                notification(context);
            }
        }
        LogUtil.d("AlarmReceiver foreground", String.valueOf(ActivityManager.getInstance().isOnForeground()));
        LogUtil.d("AlarmReceiver msg", intent.getStringExtra("msg"));
        LogUtil.d("AlarmReceiver action", intent.getAction());

    }

    private void showNotification(Context context) {
        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("横幅通知");
        builder.setContentText("请在设置通知管理中开启消息横幅提醒权限");
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setSmallIcon(R.mipmap.ic_launcher);
//        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.contact));
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 1, intent, 0);
        builder.setContentIntent(pIntent);
//这句是重点
        builder.setFullScreenIntent(pIntent, true);
//        builder.setPriority()
        builder.setAutoCancel(true);
        Notification notification = builder.build();
        manager.notify(1, notification);

//        Notification.Builder notificationBuilder = new Notification.Builder(context)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setPriority(Notification.PRIORITY_DEFAULT)
//                .setCategory(Notification.CATEGORY_MESSAGE)
//                .setContentTitle("Sample Notification")
//                .setContentText("This is a normal notification.");
//
//        Intent push = new Intent();
//        push.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        push.setClass(context, MainActivity.class);
//
//        PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(context, 0,
//                push, PendingIntent.FLAG_CANCEL_CURRENT);
//        notificationBuilder
//                .setContentText("Heads-Up Notification on Android L or above.")
//                .setFullScreenIntent(fullScreenPendingIntent, true);
//
//        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
//        manager.notify(1, notificationBuilder.build());

    }


    public void notification(Context context) {
//        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.lufei);
//        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        //设置小图标
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        //设置大图标
//        mBuilder.setLargeIcon(bitmap);
        //设置标题
        mBuilder.setContentTitle("这是标题");
        //设置通知正文
        mBuilder.setContentText("这是正文，当前ID是：" + id);
        //设置摘要
        mBuilder.setSubText("这是摘要");
        //设置是否点击消息后自动clean
        mBuilder.setAutoCancel(true);
        //显示指定文本
        mBuilder.setContentInfo("Info");
        //与setContentInfo类似，但如果设置了setContentInfo则无效果
        //用于当显示了多个相同ID的Notification时，显示消息总数
        mBuilder.setNumber(2);
        //通知在状态栏显示时的文本
        mBuilder.setTicker("在状态栏上显示的文本");
        //设置优先级
        mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
        //自定义消息时间，以毫秒为单位，当前设置为比系统时间少一小时
        mBuilder.setWhen(System.currentTimeMillis() - 3600000);
        //设置为一个正在进行的通知，此时用户无法清除通知
        mBuilder.setOngoing(true);
        //设置消息的提醒方式，震动提醒：DEFAULT_VIBRATE     声音提醒：NotificationCompat.DEFAULT_SOUND
        //三色灯提醒NotificationCompat.DEFAULT_LIGHTS     以上三种方式一起：DEFAULT_ALL
        mBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        //设置震动方式，延迟零秒，震动一秒，延迟一秒、震动一秒
        mBuilder.setVibrate(new long[]{0, 1000, 1000, 1000});

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
        mBuilder.setContentIntent(pIntent);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(id++, mBuilder.build());
    }
int id = 1;

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
