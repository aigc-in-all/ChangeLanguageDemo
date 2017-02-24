package com.kd.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by heqingbao on 2017/2/24.
 */
public class SettingsGeneralActivity extends AppCompatActivity {

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, SettingsGeneralActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_settings_general);

        TextView textView = (TextView) findViewById(R.id.language);
        textView.setText(AppLanguageManager.getCurrentLanguage().desc());
    }

    public void clickOnBack(View view) {
        finish();
    }

    public void clickOnButton(View view) {
        ChangeLanguageActivity.start(this);
    }
}
