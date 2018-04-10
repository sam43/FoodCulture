package com.module.sayem.foodculture.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.module.sayem.foodculture.R;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "Acti_secnd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getting_Data();
    }

    private void getting_Data() {
        Bundle bundle = getIntent().getExtras();
        String gotIt = bundle.getString("text");
        Log.d(TAG, "getting_Data: " + gotIt);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
