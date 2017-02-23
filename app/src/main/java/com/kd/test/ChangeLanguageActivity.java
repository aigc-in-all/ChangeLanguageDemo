package com.kd.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by heqingbao on 2017/2/22.
 */
public class ChangeLanguageActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private LanguagesAdapter adapter;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, ChangeLanguageActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_change_language);

        adapter = new LanguagesAdapter(this, LanguageHelper.getSupportedLanguages(), LanguageHelper.getCurrentLanguage());
        listView = (ListView) findViewById(R.id.list);
        listView.addHeaderView(createDividerView());
        listView.addFooterView(createDividerView());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        position = position - listView.getHeaderViewsCount();
        if (position < 0 || position >= adapter.getCount()) {
            return;
        }

        Language language = adapter.getItem(position);

        adapter.setCurrentLanguage(language);
    }

    public void clickOnBack(View view) {
        finish();
    }

    public void clickOnSave(View view) {
        Language language = adapter.getCurrentLanguage();
        boolean b = LanguageHelper.updateLanguage(language);
        if (b) {
            Intent intent = new Intent(this, MainActivity.class);
            //  | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_MULTIPLE_TASK | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    private View createDividerView() {
        View view = new View(this);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 44);
        view.setLayoutParams(params);
        view.setBackgroundColor(getResources().getColor(R.color.background));
        return view;
    }
}
