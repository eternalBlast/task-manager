package com.professional.andri.taskmanager;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.yasevich.endlessrecyclerview.EndlessRecyclerView;
import com.professional.andri.taskmanager.adapter.RealmTaskAdapter;
import com.professional.andri.taskmanager.adapter.TaskAdapter;
import com.professional.andri.taskmanager.model.Task;
import com.professional.andri.taskmanager.realm.TaskRealm;
import com.professional.andri.taskmanager.realm.UserRealm;
import com.professional.andri.taskmanager.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by Andri on 19/11/2017.
 */

public class TaskListFragment extends Fragment implements EndlessRecyclerView.Pager {
    @BindView(R.id.task_rv)
    protected EndlessRecyclerView mTaskRV;
    @BindView(R.id.add_task_button)
    protected AppCompatButton mAddTask;
    private TaskAdapter mAdapter;
    private boolean loading = false;
    private static final int ITEMS_ON_PAGE = 30;
    private static final int TOTAL_PAGES = 10;
    private static final long DELAY = 1000L;

    private final Handler handler = new Handler();

    private TaskListActivity mActivity;
    private RealmResults<TaskRealm> taskRealms;

    public static TaskListFragment newInstance() {
        return new TaskListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (TaskListActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        ButterKnife.bind(this, view);

        mAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, AddTaskFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });
        fetchTaskRealm();
        RealmTaskAdapter realmTaskAdapter = new RealmTaskAdapter(taskRealms);
//        ArrayList<Task> mTasks = Task.feedTask(20);
        mAdapter = new TaskAdapter(getContext());
        mAdapter.setRealmAdapter(realmTaskAdapter);
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

    @Override
    public void onResume() {
        super.onResume();
        fetchTaskRealm();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        fetchTaskRealm();
        mAdapter.notifyDataSetChanged();
        super.onDestroyView();
    }

    private void addItems() {
        mAdapter.setCount(mAdapter.getItemCount() + ITEMS_ON_PAGE);
    }

    private void fetchTaskRealm() {
        UserRealm userRealm = mActivity.getRealm().where(UserRealm.class)
                .equalTo("id", PrefUtils.getPrefUserId(mActivity))
                .findFirst();
        if (userRealm != null) {
            if (userRealm.getLevel().equals("Manager") || userRealm.getLevel().equals("Administrator"))
                taskRealms = mActivity.getRealm().where(TaskRealm.class).findAll();
            else
                taskRealms = mActivity.getRealm().where(TaskRealm.class).equalTo("user.id", userRealm.getId()).findAll();
        }
    }
}
