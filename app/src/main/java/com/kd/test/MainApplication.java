package com.kd.test;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by heqingbao on 2017/2/22.
 */
public class MainApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        AppLanguageManager.init();
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        AppLanguageManager.onConfigurationChanged(newConfig);
    }
}
