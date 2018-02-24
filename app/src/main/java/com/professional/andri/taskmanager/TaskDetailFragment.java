package com.professional.andri.taskmanager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.professional.andri.taskmanager.model.Task;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Andri on 19/11/2017.
 */

public class TaskDetailFragment extends Fragment {

    @BindView(R.id.task_title_tv)
    protected TextView mTitle;
    @BindView(R.id.task_detail_tv)
    protected TextView mDetail;
    @BindView(R.id.task_iv)
    protected ImageView mImage;
    private Unbinder unbinder;

    public static final String ARG_TASK = "ARG_TASK";
    private Task task;

    public static TaskDetailFragment newInstance(Task task) {
        final Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_TASK, Parcels.wrap(task));
        final TaskDetailFragment fragment = new TaskDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_task_detail, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (getArguments() != null)
            task = Parcels.unwrap(getArguments().getParcelable(ARG_TASK));

        setTaskData();

        return view;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    public void setTaskData() {
        mTitle.setText(task.title);
        mDetail.setText(task.detail);

        int id = mImage.getContext().getResources().getIdentifier(task.image, "drawable", mImage.getContext().getPackageName());

        Glide.with(this)
                .load(id)
                .apply(new RequestOptions()
                        .centerCrop()
                        .useAnimationPool(true)
                        .dontTransform())
                .into(mImage);

    }
}
