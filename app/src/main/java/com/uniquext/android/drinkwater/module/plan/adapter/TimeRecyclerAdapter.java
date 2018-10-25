package com.uniquext.android.drinkwater.module.plan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniquext.android.drinkwater.R;
import com.uniquext.android.drinkwater.module.plan.helper.PlanManager;

import java.util.List;

/**
 * @author penghaitao
 * @version 1.0
 * @date 2018/10/23 16:59
 * @description
 */
public class TimeRecyclerAdapter extends RecyclerView.Adapter<TimeItemHolder> {

    private Context mContext;
    private List<String> mDataTime = PlanManager.getInstance().getTime();

    public TimeRecyclerAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public TimeItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_remind_time, parent, false);
        return new TimeItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeItemHolder holder, int position) {
        holder.editText.setText(mDataTime.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataTime.size();
    }
}
