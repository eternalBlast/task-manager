package com.professional.andri.taskmanager.realm;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Andri on 2/23/2018.
 */

public class UserRealm extends RealmObject {
    @PrimaryKey
    private long id;
    private String level;
    private String name;
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
}
