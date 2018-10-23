package com.uniquext.android.drinkwater.today;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.uniquext.android.drinkwater.R;
import com.uniquext.android.drinkwater.core.AbstractBaseFragment;
import com.uniquext.android.drinkwater.plan.helper.PlanManager;
import com.uniquext.android.drinkwater.widget.RingView;

import java.util.Locale;

/**
 * @author penghaitao
 * @version 1.0
 * @date 2018/10/19 17:09
 * @description
 */
public class TodayFragment extends AbstractBaseFragment {

    private float mTotalWater;

    private RingView mRingView;
    private AppCompatTextView mTvPercent;
    private AppCompatTextView mTvAlreadyDrinkWaterTotal;
    private AppCompatTextView mTvRemainderTotal;

    public static TodayFragment newInstance() {
        return new TodayFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_today;
    }

    @Override
    protected void initView(View root) {
        mRingView = root.findViewById(R.id.ring_view);
        mTvPercent = root.findViewById(R.id.tv_percent);
        mTvAlreadyDrinkWaterTotal = root.findViewById(R.id.tv_total_already_drink);
        mTvRemainderTotal = root.findViewById(R.id.tv_total_remainder);
    }

    @Override
    protected void initData() {
        mTotalWater = PlanManager.getInstance().getTotal();
        refresh(33);
    }

    @Override
    protected void initEvent() {

    }

    private void refresh(int percent) {
        mRingView.setPercent(percent);
        mTvPercent.setText(String.format(Locale.CHINA, "%d%%", percent));
        float drinkTotal = mTotalWater * percent / 100;
        mTvAlreadyDrinkWaterTotal.setText(String.format(Locale.CHINA, "%.0fmL", drinkTotal));
        mTvRemainderTotal.setText(String.format(Locale.CHINA, "%.0fmL", mTotalWater - drinkTotal));
    }
}
