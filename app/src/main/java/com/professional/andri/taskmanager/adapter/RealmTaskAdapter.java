package com.professional.andri.taskmanager.adapter;

import com.professional.andri.taskmanager.realm.TaskRealm;

import io.realm.RealmResults;

/**
 * Created by User on 2/24/2018.
 */

public class RealmTaskAdapter extends RealmModelAdapter<TaskRealm> {
    public RealmTaskAdapter(RealmResults<TaskRealm> realmResults) {
        super(realmResults);
    }

}
