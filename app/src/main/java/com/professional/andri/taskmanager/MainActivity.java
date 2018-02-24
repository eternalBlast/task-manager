package com.professional.andri.taskmanager;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;

import com.professional.andri.taskmanager.model.Task;
import com.professional.andri.taskmanager.realm.TaskRealm;
import com.professional.andri.taskmanager.realm.UserRealm;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends BaseActivity {
    @BindView(R.id.login_button)
    protected AppCompatButton mLogin;
    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TaskListActivity.class));
            }
        });

        sendNotification();

//        Realm realm = Realm.getDefaultInstance();
//        RealmQuery taskRealmQuery = realm.where(TaskRealm.class).equalTo("id", "2");
//        Task task = taskRealmQuery.findFirst();
//        Log.d("TAG", taskRealmQuery.findFirst() + " SINIIII");
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

//    private void feedData(){
//        Realm realm = Realm.getDefaultInstance();
//        realm.beginTransaction();
//        UserRealm userRealm = new UserRealm();
//        userRealm.setId(1);
//        userRealm.setName("Andri");
//        userRealm.setLevel("Manager");
//        final UserRealm managedUser = realm.copyToRealm(userRealm);
//        realm.commitTransaction();
//    }
}
