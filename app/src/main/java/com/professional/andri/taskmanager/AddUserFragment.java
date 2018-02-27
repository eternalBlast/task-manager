package com.professional.andri.taskmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.yasevich.endlessrecyclerview.EndlessRecyclerView;
import com.professional.andri.taskmanager.adapter.RealmTaskAdapter;
import com.professional.andri.taskmanager.adapter.TaskAdapter;
import com.professional.andri.taskmanager.realm.TaskRealm;
import com.professional.andri.taskmanager.realm.UserRealm;
import com.professional.andri.taskmanager.utils.PrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Andri on 19/11/2017.
 */

public class AddUserFragment extends Fragment {
    @BindView(R.id.level_spinner)
    protected Spinner mLevel;
    @BindView(R.id.username_input)
    protected TextInputEditText mUsername;
    @BindView(R.id.password_input)
    protected TextInputEditText mPassword;
    @BindView(R.id.name_input)
    protected TextInputEditText mName;
    @BindView(R.id.save_button)
    protected AppCompatButton mSave;
    private AlertDialog mAlert;

    private AddUserActivity mActivity;

    public static AddUserFragment newInstance() {
        return new AddUserFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (AddUserActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_user, container, false);
        ButterKnife.bind(this, view);

        String[] arraySpinner = new String[] {
                "Employee", "Manager", "Administrator"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(mActivity,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLevel.setAdapter(adapter);

        mAlert = new AlertDialog.Builder(mActivity).create();

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateField();
            }
        });

        return view;
    }

    private void validateField(){
        if(mUsername.getText().toString().equals("")) {
            Toast.makeText(mActivity, "Username is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if(mActivity.getRealm().where(UserRealm.class).equalTo("username", mUsername.getText().toString()).findFirst() != null){
            Toast.makeText(mActivity, "Username already existed", Toast.LENGTH_SHORT).show();
            return;
        }
        if(mPassword.getText().toString().equals("")) {
            Toast.makeText(mActivity, "Password is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if(mName.getText().toString().equals("")) {
            Toast.makeText(mActivity, "Name is required", Toast.LENGTH_SHORT).show();
            return;
        }
        createUser();
    }

    private void createUser(){
        mActivity.getRealm().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                RealmResults<UserRealm> allUsers = mActivity.getRealm().where(UserRealm.class).sort("id").findAll();
                long lastId = allUsers.last().getId();
                UserRealm userRealm = realm.createObject(UserRealm.class, lastId + 1);
                userRealm.setUsername(mUsername.getText().toString());
                userRealm.setName(mName.getText().toString());
                userRealm.setPassword(mPassword.getText().toString());
                userRealm.setLevel(mLevel.getSelectedItem().toString());
                showSuccessMessage();
                Log.d("TAGGG", mActivity.getRealm().where(UserRealm.class).findAll().size()+ " SIZEEE");
            }
        });
    }

    private void showSuccessMessage(){
        mAlert.setTitle("Success");
        mAlert.setMessage("The user has been added");
        mAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        mAlert.show();
    }
}
