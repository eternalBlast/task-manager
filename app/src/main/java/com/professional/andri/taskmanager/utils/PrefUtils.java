package com.professional.andri.taskmanager.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefUtils {

  private static final String PREF_USER_ID = "pref_user_id";
  private static final String PREF_ALLOW_NOTIF = "pref_allow_notif";

  public static boolean getPrefAllowNotif(final Context context) {
    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
    return sp.getBoolean(PREF_ALLOW_NOTIF, true);
  }

  public static void setPrefAllowNotif(final Context context, final boolean allowNotif) {
    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
    sp.edit().putBoolean(PREF_ALLOW_NOTIF, allowNotif).apply();
  }

  public static void setPrefUserId(final Context context, final long id) {
    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
    sp.edit().putLong(PREF_USER_ID, id).apply();
  }

  public static long getPrefUserId(final Context context) {
    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
    return sp.getLong(PREF_USER_ID, 1);
  }
}
