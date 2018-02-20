package com.module.sayem.foodculture.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by A.S.M Sayem on 2/20/2018.
 */

public class Utility {

    public static void FontViewBold(Context cxt, TextView textView){
        Typeface typeface = Typeface.createFromAsset(cxt.getAssets(), "fonts/Ubuntu-Bold.ttf");
        textView.setTypeface(typeface);
    }


}
