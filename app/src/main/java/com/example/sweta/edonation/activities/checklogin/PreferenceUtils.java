package com.example.sweta.edonation.activities.checklogin;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;

public class PreferenceUtils {

    public static void startLogInActivity(Context context, Boolean isLoggedIn) {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("IsLoggedIn", isLoggedIn);
        editor.apply();
    }
}

