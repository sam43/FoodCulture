package com.module.sayem.foodculture.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.module.sayem.foodculture.R;

import java.lang.reflect.Field;

import es.dmoral.toasty.Toasty;

/**
 * Created by A.S.M Sayem on 2/20/2018.
 */

public class Utility {

    public static void FontViewBold(Context cxt, TextView textView){
        Typeface typeface = Typeface.createFromAsset(cxt.getAssets(), "fonts/Ubuntu-Bold.ttf");
        textView.setTypeface(typeface);
    }
    public static void FontViewRegular(Context cxt, TextView textView){
        Typeface typeface = Typeface.createFromAsset(cxt.getAssets(), "fonts/Ubuntu-Regular.ttf");
        textView.setTypeface(typeface);
    }
    public static void FontViewMedium(Context cxt, TextView textView){
        Typeface typeface = Typeface.createFromAsset(cxt.getAssets(), "fonts/Ubuntu-Medium.ttf");
        textView.setTypeface(typeface);
    }

    public static void ToastyConfig() {
        Toasty.Config.getInstance().setErrorColor(hexToInt("ff3333"))
                .setInfoColor(hexToInt("22313F"))
                .setSuccessColor(hexToInt("1E824C"))
                .apply();
    }

    private static int hexToInt(String hex) {
        return (Integer.parseInt( hex.substring( 0,2 ), 16) << 24) + Integer.parseInt( hex.substring( 2 ), 16);
    }

}
