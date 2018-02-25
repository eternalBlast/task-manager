package com.professional.andri.taskmanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.professional.andri.taskmanager.R;
import com.professional.andri.taskmanager.TaskDetailActivity;
import com.professional.andri.taskmanager.TaskStatus;
import com.professional.andri.taskmanager.TextAccent;
import com.professional.andri.taskmanager.model.Task;
import com.professional.andri.taskmanager.realm.TaskRealm;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskAdapter extends RealmRecyclerViewAdapter<TaskRealm, TaskAdapter.ViewHolder> {
//    private List<Task> mTasks;
    private int count;
    private Context mContext;

    public TaskAdapter(Context context/*, ArrayList<Task> tasks*/) {
        mContext = context;
//        mTasks = tasks;
    }

    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        final Task task = mTasks.get(position);
        final TaskRealm task = getItem(position);
//        final Task task = new Task();
//        task.id = taskRealm.getId();
//        task.title = taskRealm.getTitle();
//        task.detail = taskRealm.getDetail();
//        task.image = taskRealm.getImage();
//        task.status = taskRealm.getStatus();
        holder.mTitle.setText(task.getTitle());
        holder.mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TaskDetailActivity.class);
//                intent.putExtra("task", Parcels.wrap(task));
                intent.putExtra("task_id", task.getId());
                view.getContext().startActivity(intent);
            }
        });
        final TextAccent taskAccent = TaskStatus.getTextAccent(mContext, task.getStatus());
        holder.mStatus.setText(taskAccent.getText());
        holder.mStatus.setTextColor(taskAccent.getColor());
    }

    @Override
    public int getItemCount() {
        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }

    public void setCount(int count) {
        this.count = count;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.task_title)
        protected TextView mTitle;
        @BindView(R.id.task_status)
        protected TextView mStatus;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
