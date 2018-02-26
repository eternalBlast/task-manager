package com.professional.andri.taskmanager.adapter;

import com.professional.andri.taskmanager.realm.TaskRealm;
import com.professional.andri.taskmanager.realm.UserRealm;

import io.realm.RealmResults;

/**
 * Created by User on 2/24/2018.
 */

public class RealmUserAdapter extends RealmModelAdapter<UserRealm> {
    public RealmUserAdapter(RealmResults<UserRealm> realmResults) {
        super(realmResults);
    }

}
