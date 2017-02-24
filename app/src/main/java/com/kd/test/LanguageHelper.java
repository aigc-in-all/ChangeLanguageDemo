package com.kd.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import java.util.Locale;

/**
 * Created by heqingbao on 2017/2/23.
 */
public class LanguageHelper {

    public static void init() {
        Language language = readLanguage();
        updateLanguage(language);
    }

    public static boolean updateLanguage(Language language) {
        try {
            Locale locale = Language.parse(language);
            if (!writeLanguage(language)) {
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
//            Locale.setDefault(locale);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Language[] getSupportedLanguages() {
        return Language.values();
    }

    // // TODO: 2017/2/23 是否需要验证当前res与sp中的值
    public static Language getCurrentLanguage() {
        return readLanguage();
    }

    private static Language readLanguage() {
        SharedPreferences spf = MainApplication.getContext().getSharedPreferences("test", Context.MODE_PRIVATE);
        String language = spf.getString("language", "");
        return Language.parse(language);
    }

    private static boolean writeLanguage(Language language) {
        if (language == null) {
            return false;
        }

        SharedPreferences spf = MainApplication.getContext().getSharedPreferences("test", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.putString("language", Language.toPersistenceString(language));
        return editor.commit();
    }
}
