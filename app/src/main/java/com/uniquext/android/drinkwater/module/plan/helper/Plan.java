package com.uniquext.android.drinkwater.module.plan.helper;

import java.util.List;

/**
 * @author penghaitao
 * @version 1.0
 * @date 2018/10/23 14:48
 * @description
 */
final class Plan {

    private int total;
    private List<String> time;

    public Plan() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }
}
