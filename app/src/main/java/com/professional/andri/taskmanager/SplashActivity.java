package com.professional.andri.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.professional.andri.taskmanager.utils.PrefUtils;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(PrefUtils.getPrefUserId(this) == 0){
            startActivity(new Intent(this, MainActivity.class));
        } else{
            startActivity(new Intent(this, TaskListActivity.class));
        }
        finish();
    }

}
