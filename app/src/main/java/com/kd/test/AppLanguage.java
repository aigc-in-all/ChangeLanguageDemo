package com.kd.test;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Locale;
import java.util.StringTokenizer;

/**
 * Created by heqingbao on 2017/2/22.
 */
public enum AppLanguage {

    AUTO("跟随系统", "", ""),
    CHINESE("简体中文", "zh", "CN"),
    CHINESE_TW("繁體中文（香港）", "zh", "HK"),
    CHINESE_HK("繁體中文（台灣）", "zh", "TW"),
    ENGLISH("English", "en", ""),
    JAPANESE("日本語", "ja", "");

    String desc;
    String language;
    String country;

    private AppLanguage(String desc, String language, String country) {
        this.desc = desc;
        this.language = language;
        this.country = country;
    }

    public String desc() {
        return desc;
    }

    public String language() {
        return language;
    }

    public String country() {
        return country;
    }

    @NonNull
    public static AppLanguage parse(@Nullable String locale) {
        if (locale == null || locale.isEmpty()) {
            return AppLanguage.AUTO;
        }

        StringTokenizer token = new StringTokenizer(locale, ",");
        String language = "";
        String country = "";
        if (token.hasMoreTokens()) {
            language = (String) token.nextElement();
        }

        if (token.hasMoreTokens()) {
            country = (String) token.nextElement();
        }

        for (AppLanguage l : AppLanguage.values()) {
            if (l.language().equals(language) && l.country.equals(country)) {
                return l;
            }
        }

        return AppLanguage.AUTO;
    }

    @NonNull
    public static String toPersistenceString(@NonNull AppLanguage language) {
        return language.language() + "," + language.country();
    }

    @NonNull
    public static Locale parse(AppLanguage l) {
        if (l == null || l == AppLanguage.AUTO) {
            return Locale.getDefault();
        }

        return new Locale(l.language(), l.country());
    }
}
