package com.uniquext.android.drinkwater.plan;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.uniquext.android.drinkwater.R;
import com.uniquext.android.drinkwater.core.AbstractBaseFragment;
import com.uniquext.android.drinkwater.plan.adapter.TimeRecyclerAdapter;
import com.uniquext.android.drinkwater.plan.helper.PlanManager;

/**
 * @author penghaitao
 * @version 1.0
 * @date 2018/10/19 17:09
 * @description
 */
public class PlanFragment extends AbstractBaseFragment {

    private AppCompatEditText mEtTotal;
    private RecyclerView mRvTime;
    private FloatingActionButton mFabMenu;

    public static PlanFragment newInstance() {
        return new PlanFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_plan;
    }

    @Override
    protected void initView(View root) {
        mEtTotal = root.findViewById(R.id.et_total);
        mRvTime = root.findViewById(R.id.rv_remind_time);
        mFabMenu = root.findViewById(R.id.fab_menu_plan);
    }

    @Override
    protected void initData() {
        mEtTotal.setText(String.valueOf(PlanManager.getInstance().getTotal()));
        mRvTime.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRvTime.setAdapter(new TimeRecyclerAdapter(getContext()));
    }

    @Override
    protected void initEvent() {

    }
}
