package com.professional.andri.taskmanager;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.yasevich.endlessrecyclerview.EndlessRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Andri on 19/11/2017.
 */

public class TaskListFragment extends Fragment implements EndlessRecyclerView.Pager{
    @BindView(R.id.task_rv)
    protected EndlessRecyclerView mTaskRV;
    private TaskAdapter mAdapter;
    private boolean loading = false;
    private static final int ITEMS_ON_PAGE = 30;
    private static final int TOTAL_PAGES = 10;
    private static final long DELAY = 1000L;

    private final Handler handler = new Handler();

    public static TaskListFragment newInstance() {
        return new TaskListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        ButterKnife.bind(this, view);
//        ArrayList<Task> mTasks = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            mTasks.add(new Task());
//        }
        ArrayList<Task> mTasks = Task.feedTask(20);
        mAdapter = new TaskAdapter(getContext(), mTasks);
        mTaskRV.setAdapter(mAdapter);
        mTaskRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mTaskRV.setProgressView(R.layout.item_progress);

        mTaskRV.setPager(this);
        mAdapter.notifyDataSetChanged();
        addItems();

        return view;
    }

    @Override
    public boolean shouldLoad() {
        return !loading && mAdapter.getItemCount() / ITEMS_ON_PAGE < TOTAL_PAGES;
    }

    @Override
    public void loadNextPage() {
        loading = true;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTaskRV.setRefreshing(false);
                loading = false;
                addItems();
            }
        }, DELAY);
    }

    private void addItems() {
        mAdapter.setCount(mAdapter.getItemCount() + ITEMS_ON_PAGE);
    }
}
