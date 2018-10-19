package com.uniquext.android.drinkwater;

import android.os.Bundle;
import android.util.Log;

import com.uniquext.android.drinkwater.core.AbstractBaseActivity;
import com.uniquext.android.drinkwater.core.LogUtil;

public class MainActivity extends AbstractBaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        LogUtil.d("initView");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        LogUtil.e("MainActivity", "initData");
    }

    @Override
    protected void initEvent() {
    }
}
