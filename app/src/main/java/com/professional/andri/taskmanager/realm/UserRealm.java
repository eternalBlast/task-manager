package com.professional.andri.taskmanager.realm;

import com.professional.andri.taskmanager.converter.RealmListParcelConverter;
import com.professional.andri.taskmanager.model.User;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.TaskRealmRealmProxy;
import io.realm.UserRealmRealmProxy;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Andri on 2/23/2018.
 */

//@Parcel(implementations = { UserRealmRealmProxy.class },
//        value = Parcel.Serialization.FIELD,
//        analyze = { UserRealm.class })
public class UserRealm extends RealmObject {
    @PrimaryKey
    private long id;
    private String level;
    private String name;
    private String username;
    private String password;
//    @ParcelPropertyConverter(RealmListParcelConverter.class)
    private RealmList<TaskRealm> tasks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<TaskRealm> getTasks() {
        return tasks;
    }

    public void setTasks(RealmList<TaskRealm> tasks) {
        this.tasks = tasks;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
