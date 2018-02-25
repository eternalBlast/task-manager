package com.professional.andri.taskmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.professional.andri.taskmanager.model.Task;
import com.professional.andri.taskmanager.realm.TaskRealm;

import org.parceler.Parcels;

/**
 * Created by Andri on 18/11/2017.
 */

public class TaskDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

//        TaskRealm task = Parcels.unwrap(getIntent().getParcelableExtra("task"));
        String task_id = getIntent().getStringExtra("task_id");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, TaskDetailFragment.newInstance(task_id))
                .commit();
    }
}
