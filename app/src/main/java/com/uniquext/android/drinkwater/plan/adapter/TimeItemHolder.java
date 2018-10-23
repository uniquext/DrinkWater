package com.uniquext.android.drinkwater.plan.adapter;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.uniquext.android.drinkwater.R;

/**
 * @author penghaitao
 * @version 1.0
 * @date 2018/10/23 16:59
 * @description
 */
public class TimeItemHolder extends RecyclerView.ViewHolder {

    AppCompatEditText editText;
    AppCompatImageView imageView;

    public TimeItemHolder(View itemView) {
        super(itemView);
        editText = itemView.findViewById(R.id.item_remind_time);
        imageView = itemView.findViewById(R.id.iv_time_operate);
    }

}
