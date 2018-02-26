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
import com.professional.andri.taskmanager.realm.TaskRealm;
import com.professional.andri.taskmanager.realm.UserRealm;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RealmRecyclerViewAdapter<UserRealm, UserAdapter.ViewHolder> {
    //    private List<Task> mTasks;
    private int count;
    private Context mContext;

    public UserAdapter(Context context/*, ArrayList<Task> tasks*/) {
        mContext = context;
//        mTasks = tasks;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final UserRealm user = getItem(position);
        holder.mId.setText(String.valueOf(user.getId()));
        holder.mName.setText(user.getName());
        holder.mLevel.setText(user.getLevel());

    }

    @Override
    public int getItemCount() {
        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.user_id_tv)
        protected TextView mId;
        @BindView(R.id.name_tv)
        protected TextView mName;
        @BindView(R.id.level_tv)
        protected TextView mLevel;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
