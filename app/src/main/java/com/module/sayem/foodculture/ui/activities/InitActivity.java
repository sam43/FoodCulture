package com.module.sayem.foodculture.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.module.sayem.foodculture.storage.SessionManager;
import com.module.sayem.foodculture.utils.Constants;

public class InitActivity extends Activity {


    SessionManager session;
    private static final String TAG = "Act_Init";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(this.getApplicationContext());
        session.checkLogin();

        Log.d(TAG, "onCreate: going to next..." + Constants.STORAGE.LOGGED_IN);
        if(Constants.STORAGE.LOGGED_IN){
            //openNextActivity();
            Log.d(TAG, "onCreate: going to next...");
            startActivity(new Intent(this, NavigationDrawerActivity.class));
            finish();
        } else {
            Log.d(TAG, "onCreate: going to login...");
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}
