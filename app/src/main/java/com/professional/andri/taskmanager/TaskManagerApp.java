package com.professional.andri.taskmanager;

import android.app.Application;
import android.util.Log;

import io.realm.Realm;

public class TaskManagerApp extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    Realm.init(this);
    Realm realm = Realm.getDefaultInstance();
      Log.d("TAGGG", "PATHH" + realm.getPath());
  }
}