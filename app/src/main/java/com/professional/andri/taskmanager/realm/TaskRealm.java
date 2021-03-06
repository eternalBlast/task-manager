package com.professional.andri.taskmanager.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Andri on 2/23/2018.
 */

//@Parcel(implementations = { TaskRealmRealmProxy.class },
//        value = Parcel.Serialization.FIELD,
//        analyze = { TaskRealm.class })
public class TaskRealm extends RealmObject {
    @PrimaryKey
    private String id;
    private String title;
    private String detail;
    private String image;
    private int status;
    private UserRealm user;
    private String deadline;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserRealm getUser() {
        return user;
    }

    public void setUser(UserRealm user) {
        this.user = user;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
