package com.professional.andri.taskmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.professional.andri.taskmanager.utils.PrefUtils;

/**
 * Created by Andri on 18/11/2017.
 */

public class TaskListActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        setTitle(PrefUtils.getPrefUserName(this) + " (" + PrefUtils.getPrefUserLevel(this) + " )");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, TaskListFragment.newInstance())
                .commit();
    }
}
