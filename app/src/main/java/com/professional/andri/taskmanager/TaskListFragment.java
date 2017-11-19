package com.professional.andri.taskmanager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Andri on 19/11/2017.
 */

public class TaskListFragment extends Fragment{
    @BindView(R.id.task_rv)
    protected RecyclerView mTaskRV;
    private TaskAdapter mAdapter;
    public static TaskListFragment newInstance() {
        return new TaskListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        ButterKnife.bind(this, view);
        ArrayList<Task> mTasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mTasks.add(new Task());
        }
        mAdapter = new TaskAdapter(mTasks);
        mTaskRV.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        return view;
    }
}
