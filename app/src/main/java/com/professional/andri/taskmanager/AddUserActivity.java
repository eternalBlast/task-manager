package com.professional.andri.taskmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Andri on 18/11/2017.
 */

public class AddUserActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, TaskListFragment.newInstance())
                .commit();
    }
}
