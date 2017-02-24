package com.kd.test;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by heqingbao on 2017/2/23.
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_settings);
        System.out.println("SettingsActivity------>onCreate");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        System.out.println("SettingsActivity------>onConfigurationChanged");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("SettingsActivity------>onDestroy");
    }

    public void clickOnBack(View view) {
        finish();
    }

    public void clickOnButton(View view) {
        SettingsGeneralActivity.start(this);
    }
}
