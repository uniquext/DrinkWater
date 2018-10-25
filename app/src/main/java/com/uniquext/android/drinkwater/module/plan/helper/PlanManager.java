package com.uniquext.android.drinkwater.module.plan.helper;

import com.uniquext.android.core.App;

import java.util.List;

/**
 * @author penghaitao
 * @version 1.0
 * @date 2018/10/23 15:47
 * @description
 */
public final class PlanManager {

    private Plan mPlan = new Plan();
    private PlanHelper mPlanHelper = new PlanHelper();

    private PlanManager() {
    }

    public static PlanManager getInstance() {
        return SingleHolder.INSTANCE;
    }

    public List<String> getTime() {
        if (mPlan.getTime() == null) {
            mPlan.setTime(mPlanHelper.getPlanTimeSettings(App.getInstance()));
        }
        return mPlan.getTime();
    }

    public void setTime(List<String> time) {
        mPlan.setTime(time);
        mPlanHelper.setPlanTimeSettings(App.getInstance(), time);
    }

    public int getTotal() {
        if (mPlan.getTotal() == 0) {
            mPlan.setTotal(mPlanHelper.getPlanTotalSettings(App.getInstance()));
        }
        return mPlan.getTotal();
    }

    public void setTotal(int total) {
        mPlan.setTotal(total);
        mPlanHelper.setPlanTotalSettings(App.getInstance(), total);
    }

    private static class SingleHolder {
        private static final PlanManager INSTANCE = new PlanManager();
    }
}
