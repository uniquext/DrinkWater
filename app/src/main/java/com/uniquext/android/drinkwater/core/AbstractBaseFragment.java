package com.uniquext.android.drinkwater.core;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author penghaitao
 * @version 1.0
 * @date 2018/10/19 17:27
 * @description
 */
public abstract class AbstractBaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(getLayoutId(), container, false);
        initView(root);
        initData();
        initEvent();
        return root;
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View root);

    protected abstract void initData();

    protected abstract void initEvent();

}
