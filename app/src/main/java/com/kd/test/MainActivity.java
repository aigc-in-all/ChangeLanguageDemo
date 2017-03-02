package com.kd.test;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("MainActivity------>onCreate");

        TextView copyrightView = (TextView) findViewById(R.id.copyright);
        copyrightView.setText(getString(R.string.copyright, "Demo"));
    }

    public void clickOnButton(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        System.out.println("MainActivity------>" + newConfig.locale);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("MainActivity------>onDestroy");
    }
}
