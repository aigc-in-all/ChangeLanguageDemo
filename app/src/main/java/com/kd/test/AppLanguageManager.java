package com.kd.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Locale;

/**
 * Created by heqingbao on 2017/2/23.
 */
public class AppLanguageManager {
    private static final String TAG = "LanguageHelper";

    private static final String SP_NAME = "app";
    private static final String KEY_LANGUAGE = "language";

    public static void init() {
        AppLanguage language = readLanguage();
        change(language);
    }

    public static boolean change(AppLanguage language) {
        AppLanguage preLanguage = null;
        try {
            Locale locale = AppLanguage.parse(language);
            preLanguage = writeLanguage(language);
            if (preLanguage == null) {
                Log.w(TAG, "保存Language失败！language = " + language);
                return false;
            }

            Resources res = MainApplication.getContext().getResources();
            Configuration config = res.getConfiguration();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                config.setLocale(locale);
            } else {
                config.locale = locale;
            }
            res.updateConfiguration(config, res.getDisplayMetrics());
            Resources.getSystem().updateConfiguration(config, res.getDisplayMetrics());
            return true;
        } catch (Exception e) {
            Log.w(TAG, "切换语言失败！", e);
            if (preLanguage != null) {
                try {
                    writeLanguage(preLanguage); // revert
                } catch (Exception ignore) {
                }
            }
            return false;
        }
    }

    public static AppLanguage[] getSupportedLanguages() {
        return AppLanguage.values();
    }

    public static AppLanguage getCurrentLanguage() {
        return readLanguage();
    }

    public static void onConfigurationChanged(Configuration newConfig) {
        Configuration config = new Configuration(newConfig);
        config.locale = AppLanguage.parse(AppLanguageManager.getCurrentLanguage());

        Resources res = MainApplication.getContext().getResources();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    @NonNull
    private static AppLanguage readLanguage() {
        try {
            SharedPreferences spf = MainApplication.getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
            String language = spf.getString(KEY_LANGUAGE, "");
            return AppLanguage.parse(language);
        } catch (Exception e) {
            return AppLanguage.AUTO;
        }
    }

    /**
     * @return the previous value, or {@code null} if write failed
     */
    @Nullable
    private static AppLanguage writeLanguage(AppLanguage language) {
        if (language == null) {
            return null;
        }

        AppLanguage l = readLanguage();
        SharedPreferences spf = MainApplication.getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.putString(KEY_LANGUAGE, AppLanguage.toPersistenceString(language));
        boolean result = editor.commit();
        return result ? l : null;
    }
}
