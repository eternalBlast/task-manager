package com.professional.andri.taskmanager;

import android.app.Application;
import android.util.Log;

import com.professional.andri.taskmanager.migration.TaskMigration;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TaskManagerApp extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    Realm.init(this);
      RealmConfiguration myConfig = new RealmConfiguration.Builder()
              .name("task_db.realm")
              .schemaVersion(3)
              .migration(new TaskMigration())
              .build();
      Realm.setDefaultConfiguration(myConfig);
//      Realm.deleteRealm(myConfig);
//      RealmConfiguration config = new RealmConfiguration
//              .Builder()
//              .deleteRealmIfMigrationNeeded()
//              .build();
//
//      Realm.setDefaultConfiguration(config);
//    Realm realm = Realm.getDefaultInstance();
//      Log.d("TAGGG", "PATHH" + realm.getPath());
//      realm.close();
  }
}