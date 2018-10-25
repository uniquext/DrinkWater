package com.uniquext.android.core.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * @author penghaitao
 * @version 1.0
 * @date 2018/10/19 16:37
 * @description
 */
public abstract class AbstractBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        onPreCreate();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        onPostCreate();
        initView();
        initData(savedInstanceState);
        initEvent();
    }

    protected void onPreCreate() {

    }

    protected void onPostCreate() {

    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract void initEvent();


}
