package com.module.sayem.foodculture.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by A.S.M Sayem on 2/20/2018.
 */

public class CustomEditText extends AppCompatEditText {

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Ubuntu-Regular.ttf");
            setTypeface(tf);
        }
    }
}

/*
* MyEditText editText = (MyEditText) findViewById(R.id.editText);

editText.setText("Hello");
Or in Your xml File

   <MyEditText
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:textColor="#fff"
    android:textSize="16dp"
    android:id="@+id/editText"
    />
*
*
* */
