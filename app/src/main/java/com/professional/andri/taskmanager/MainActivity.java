package com.professional.andri.taskmanager;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.professional.andri.taskmanager.model.Task;
import com.professional.andri.taskmanager.realm.TaskRealm;
import com.professional.andri.taskmanager.realm.UserRealm;
import com.professional.andri.taskmanager.utils.PrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends BaseActivity {
    @BindView(R.id.login_button)
    protected AppCompatButton mLogin;
    @BindView(R.id.username_input)
    protected TextInputEditText mUsername;
    @BindView(R.id.password_input)
    protected TextInputEditText mPassword;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        RealmResults<UserRealm> userRealms = getRealm().where(UserRealm.class).findAll();

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

//        Realm realm = Realm.getDefaultInstance();
//        realm.beginTransaction();
//        RealmResults<TaskRealm> taskRealm = getRealm().where(TaskRealm.class).findAll();
//        UserRealm userRealm = getRealm().where(UserRealm.class).equalTo("id", 5).findFirst();
//        RealmList<TaskRealm> taskRealms = new RealmList<>();
//        for(int i=0;i<taskRealm.size();i++){
//            if(i%5==0) {
//                taskRealms.add(taskRealm.get(i));
//            }
//        }
//        Log.d("TAGGG", userRealm.getTasks().get(0).getId() + "SOMOO")/*userRealm.setTasks(taskRealms)*/;
//        realm.commitTransaction();


        sendNotification();

//        Realm realm = Realm.getDefaultInstance();
//        RealmQuery taskRealmQuery = realm.where(TaskRealm.class).equalTo("id", "2");
//        Task task = taskRealmQuery.findFirst();
//        Log.d("TAG", realm.where(TaskRealm.class).equalTo("id", "23").findFirst().getImage() + " SINIIII");
//        feedData();
    }

    public void sendNotification() {

        //Get an instance of NotificationManager//

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, "1")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");


        // Gets an instance of the NotificationManager service//

        NotificationManager mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // When you issue multiple notifications about the same type of event,
        // it’s best practice for your app to try to update an existing notification
        // with this new information, rather than immediately creating a new notification.
        // If you want to update this notification at a later date, you need to assign it an ID.
        // You can then use this ID whenever you issue a subsequent notification.
        // If the previous notification is still visible, the system will update this existing notification,
        // rather than create a new one. In this example, the notification’s ID is 001//

//        mNotificationManager.notify();
//
        mNotificationManager.notify(001, mBuilder.build());
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    private void feedData() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        for (int i = 1; i <= 25; i++) {
            TaskRealm taskRealm = getRealm().where(TaskRealm.class).equalTo("id", "" + i).findFirst();
            if (i % 5 != 0)
                taskRealm.setImage("task_" + (i % 5));
            else
                taskRealm.setImage("task_" + 5);
        }
//        UserRealm userRealm = new UserRealm();
//        userRealm.setId(1);
//        userRealm.setName("Andri");
//        userRealm.setLevel("Manager");
//        final UserRealm managedUser = realm.copyToRealm(userRealm);
        realm.commitTransaction();
    }

    private void validate() {
        if(mUsername.getText().toString().equals("")) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if(mPassword.getText().toString().equals("")) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return;
        }

        UserRealm userRealm = getRealm().where(UserRealm.class)
                .equalTo("username", mUsername.getText().toString())
                .findFirst();

        if (userRealm == null) {
            Toast.makeText(this, "Username doesn't exist", Toast.LENGTH_SHORT).show();
        } else {
            if (mPassword.getText().toString().equals(userRealm.getPassword())) {
                PrefUtils.setPrefUserId(this, userRealm.getId());
                PrefUtils.setPrefUserLevel(this, userRealm.getLevel());
                if(userRealm.getLevel().equals("Administrator"))
                    startActivity(new Intent(MainActivity.this, AddUserActivity.class));
                else
                    startActivity(new Intent(MainActivity.this, TaskListActivity.class));
            }
            else
                Toast.makeText(this, "Password doesn't matched", Toast.LENGTH_SHORT).show();
        }
    }
}
