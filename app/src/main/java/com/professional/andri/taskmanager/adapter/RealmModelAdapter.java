package com.professional.andri.taskmanager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import io.realm.RealmBaseAdapter;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Andri on 2/24/2018.
 */

public class RealmModelAdapter <T extends RealmObject> extends RealmBaseAdapter<T> {
    public RealmModelAdapter(RealmResults<T> realmResults) {
        super(realmResults);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
