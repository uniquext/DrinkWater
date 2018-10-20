package com.uniquext.android.drinkwater;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.uniquext.android.drinkwater.core.AbstractBaseActivity;
import com.uniquext.android.drinkwater.core.AbstractBaseFragment;
import com.uniquext.android.drinkwater.today.TodayFragment;
import com.uniquext.android.drinkwater.util.LogUtil;

public class MainActivity extends AbstractBaseActivity {

    private ViewPager mViewPager;

    private AbstractBaseFragment[] mFragments;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mViewPager = findViewById(R.id.view_pager);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mFragments = new AbstractBaseFragment[]{
                TodayFragment.newInstance(),
                TodayFragment.newInstance(),
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
}
