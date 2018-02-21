package com.professional.andri.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<Task> mTasks;
    private int count;
    private Context mContext;

    public TaskAdapter(Context context, ArrayList<Task> tasks) {
        mContext = context;
        mTasks = tasks;
    }

    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Task task = mTasks.get(position);
        holder.mText.setText(task.getTitle());
        holder.mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TaskDetailActivity.class);
                intent.putExtra("task", Parcels.wrap(task));
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public void setCount(int count) {
        this.count = count;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView)
        protected TextView mText;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
