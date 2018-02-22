package com.module.sayem.foodculture.customs;

import android.app.Application;
import android.content.Context;

import com.module.sayem.foodculture.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MyApp extends Application {
  
  @Override
  public void onCreate() {
    super.onCreate();
    CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/Ubuntu-Regular.ttf")
            .setFontAttrId(R.attr.fontPath)
            .build()
    );
    //TypefaceUtil.overrideFont(getApplicationContext(), "serif", "fonts/Ubuntu-Regular.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf
  }



}