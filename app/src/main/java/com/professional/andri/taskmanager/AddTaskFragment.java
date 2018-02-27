package com.professional.andri.taskmanager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.professional.andri.taskmanager.realm.TaskRealm;
import com.professional.andri.taskmanager.realm.UserRealm;
import com.professional.andri.taskmanager.utils.PrefUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Andri on 19/11/2017.
 */

public class AddTaskFragment extends Fragment {
    @BindView(R.id.title_input)
    protected TextInputEditText mTitle;
    @BindView(R.id.detail_input)
    protected TextInputEditText mDetail;
    @BindView(R.id.assign_spinner)
    protected Spinner mAssignTo;
    @BindView(R.id.task_deadline_input)
    protected TextInputEditText mDeadLine;
    @BindView(R.id.save_button)
    protected AppCompatButton mSave;
    private Locale locale = new Locale("in", "ID");
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", locale);

    private TaskListActivity mActivity;
    private DatePickerDialog datePickerDialog;
    private AlertDialog mAlert;
    private RealmResults<UserRealm> userRealms;
    private String deadlineDate;

    public static AddTaskFragment newInstance() {
        return new AddTaskFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (TaskListActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        ButterKnife.bind(this, view);

        mAlert = new AlertDialog.Builder(mActivity).create();

        if (PrefUtils.getPrefUserLevel(mActivity).equals("Employee"))
            mAssignTo.setVisibility(View.GONE);

        fetchUser();

        ArrayList<String> users = new ArrayList<>();
        for (UserRealm userRealm : userRealms) {
            if (userRealm.getLevel().equals("Employee"))
                users.add(userRealm.getUsername());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(mActivity,
                android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAssignTo.setAdapter(adapter);

        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                final Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                deadlineDate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", locale).format(new Date(calendar.getTimeInMillis()));
                mDeadLine.setText(formatter.format(calendar.getTime()));
                Log.d("TAGG", new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", locale).format(new Date(calendar.getTimeInMillis())) + " SINIII");
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateField();
            }
        });

        mDeadLine.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    datePickerDialog.show();
            }
        });

        return view;
    }

    private void validateField() {
        if (mTitle.getText().toString().equals("")) {
            Toast.makeText(mActivity, "Task Title is required", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mDetail.getText().toString().equals("")) {
            Toast.makeText(mActivity, "Task Detail is required", Toast.LENGTH_SHORT).show();
            return;
        }
//        if (mAssignTo.getText().toString().equals("")) {
//            Toast.makeText(mActivity, "Assign to employee is required", Toast.LENGTH_SHORT).show();
//            return;
//        }
        if (mDeadLine.getText().toString().equals("")) {
            Toast.makeText(mActivity, "Task Deadline is required", Toast.LENGTH_SHORT).show();
            return;
        }
        createTask();
    }

    private void createTask() {
        mActivity.getRealm().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                UserRealm userRealm;
                RealmResults<TaskRealm> allTasks = mActivity.getRealm().where(TaskRealm.class).sort("id").findAll();
                String lastId = String.valueOf(allTasks.last().getId()+1);
                TaskRealm taskRealm = realm.createObject(TaskRealm.class, lastId + 1);
                taskRealm.setTitle(mTitle.getText().toString());
                taskRealm.setDeadline(deadlineDate);
                taskRealm.setDetail(mDetail.getText().toString());
                if (PrefUtils.getPrefUserLevel(mActivity).equals("Manager")) {
                    userRealm = mActivity.getRealm().where(UserRealm.class)
                            .equalTo("username", mAssignTo.getSelectedItem().toString())
                            .findFirst();
                    taskRealm.setStatus(TaskStatus.ON_PROGRESS);
                } else {
                    userRealm = mActivity.getRealm().where(UserRealm.class)
                            .equalTo("id", PrefUtils.getPrefUserId(mActivity))
                            .findFirst();
                    taskRealm.setStatus(TaskStatus.WAITING_APPROVAL);
                }

                taskRealm.setUser(userRealm);
                Random random = new Random();
                taskRealm.setImage("task_" + random.nextInt(4));
                showSuccessMessage();
                Log.d("TAGGG", mActivity.getRealm().where(TaskRealm.class).findAll().last().toString() + " SIZEEE");
            }
        });
    }

    //
    private void showSuccessMessage() {
        mAlert.setTitle("Success");
        mAlert.setMessage("The Task has been added successfuly");
        mAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        mAlert.show();
    }

    private void fetchUser() {
        userRealms = mActivity.getRealm().where(UserRealm.class).findAll();
    }
}
