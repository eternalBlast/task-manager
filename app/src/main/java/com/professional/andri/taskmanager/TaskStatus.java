package com.professional.andri.taskmanager;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class TaskStatus {

  /**
   * @hide
   */
  @IntDef({WAITING_APPROVAL, ON_PROGRESS, COMPLETED, NOT_COMPLETED})
  @Retention(RetentionPolicy.SOURCE)
  public @interface Status {
  }

  public static final int WAITING_APPROVAL = 0;
  public static final int ON_PROGRESS = 1;
  public static final int COMPLETED = 2;
  public static final int NOT_COMPLETED = 3;

  /**
   * @param status {@link #WAITING_APPROVAL}, {@link #ON_PROGRESS}, {@link #COMPLETED}, {@link #NOT_COMPLETED}
   */
  public static TextAccent getTextAccent(Context context, @Status int status) {
    switch (status) {
      case WAITING_APPROVAL:
        return new TextAccent(context.getString(R.string.waiting_approval),
          ContextCompat.getColor(context, R.color.colorPrimary));
      case ON_PROGRESS:
        return new TextAccent(context.getString(R.string.on_progress),
          ContextCompat.getColor(context, R.color.colorYellow));
      case COMPLETED:
        return new TextAccent(context.getString(R.string.completed),
                ContextCompat.getColor(context, R.color.colorGreen));
      case NOT_COMPLETED:
        return new TextAccent(context.getString(R.string.not_completed),
                ContextCompat.getColor(context, R.color.colorRed));
    }
    return new TextAccent(context.getString(R.string.need_discuss),
      ContextCompat.getColor(context, R.color.colorMagenta));
  }

}
