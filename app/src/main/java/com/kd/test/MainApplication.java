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
        LanguageHelper.init();
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


//        Resources res = MainApplication.getContext().getResources();
//        Configuration config = res.getConfiguration();
//        if (locale != null) {
//            config.locale = locale;
//            Locale.setDefault(locale);
//            getResources().updateConfiguration(config, getResources().getDisplayMetrics());
//        }

//        Configuration config = new Configuration(newConfig);
//        config.locale = Language.parse(LanguageHelper.getCurrentLanguage());
//        getResources().updateConfiguration(config, getResources().getDisplayMetrics());


        System.out.println("MainApplication------>" + newConfig.locale);
    }
}
