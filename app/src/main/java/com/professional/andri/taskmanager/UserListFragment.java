package com.professional.andri.taskmanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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
import com.professional.andri.taskmanager.adapter.RealmUserAdapter;
import com.professional.andri.taskmanager.adapter.TaskAdapter;
import com.professional.andri.taskmanager.adapter.UserAdapter;
import com.professional.andri.taskmanager.realm.TaskRealm;
import com.professional.andri.taskmanager.realm.UserRealm;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Andri on 19/11/2017.
 */

public class UserListFragment extends Fragment {
    @BindView(R.id.user_rv)
    protected EndlessRecyclerView mUserRV;
    @BindView(R.id.add_user_button)
    protected AppCompatButton mAdd;
    private UserAdapter mAdapter;

    private AddUserActivity mActivity;
    private RealmResults<UserRealm> userRealms;

    public static UserListFragment newInstance() {
        return new UserListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (AddUserActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, view);

        fetchUser();
        mAdapter = new UserAdapter(getContext());
        RealmUserAdapter realmTaskAdapter = new RealmUserAdapter(userRealms);
        mAdapter.setRealmAdapter(realmTaskAdapter);
        mUserRV.setAdapter(mAdapter);
        mUserRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mUserRV.setProgressView(R.layout.item_progress);
        mAdapter.notifyDataSetChanged();

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, AddUserFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }

    private void fetchUser(){
        userRealms = mActivity.getRealm().where(UserRealm.class).findAll();
    }

    @Override
    public void onDestroyView() {
        fetchUser();
        mAdapter.notifyDataSetChanged();
        super.onDestroyView();
    }
}
