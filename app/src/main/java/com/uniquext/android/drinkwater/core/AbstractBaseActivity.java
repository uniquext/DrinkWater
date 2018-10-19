package com.uniquext.android.drinkwater.core;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.uniquext.android.drinkwater.R;

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
        AppManager.getInstance().push(this);
        onPostCreate();
        initToolBar();
        initView();
        initData(savedInstanceState);
        initEvent();
    }

    @Override
    protected void onDestroy() {
        AppManager.getInstance().remove(this);
        super.onDestroy();
    }

    protected void onPreCreate(){

    }

    protected void onPostCreate() {

    }

    protected void initToolBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract void initEvent();


}
