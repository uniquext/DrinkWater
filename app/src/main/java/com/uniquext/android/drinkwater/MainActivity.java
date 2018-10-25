package com.uniquext.android.drinkwater;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.uniquext.android.core.base.AbstractBaseActivity;
import com.uniquext.android.core.base.AbstractBaseFragment;
import com.uniquext.android.core.util.LogUtil;
import com.uniquext.android.drinkwater.module.plan.PlanFragment;
import com.uniquext.android.drinkwater.module.today.TodayFragment;
import com.uniquext.android.drinkwater.core.AlarmService;

public class MainActivity extends AbstractBaseActivity {

    private ViewPager mViewPager;

    private AbstractBaseFragment[] mFragments;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mViewPager = findViewById(R.id.view_pager);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mFragments = new AbstractBaseFragment[]{
                TodayFragment.newInstance(),
                PlanFragment.newInstance(),
                TodayFragment.newInstance(),
                TodayFragment.newInstance()
        };

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return mFragments.length;
            }
        });

        startService(new Intent(this, AlarmService.class));
//        test();
    }

    @Override
    protected void initEvent() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LogUtil.d("OnPageChangeListener", String.valueOf(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void test() {
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent1 = new Intent();
        intent1.setPackage(getPackageName());
        intent1.setAction("com.uniquext.android.receiver.WATER_REMINDER");
        intent1.putExtra("msg", "你该打酱油了1");
        PendingIntent pi1 = PendingIntent.getBroadcast(this, 1, intent1, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 5 * 1000, pi1);
    }
}
