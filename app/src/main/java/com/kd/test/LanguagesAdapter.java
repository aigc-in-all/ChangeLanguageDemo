package com.kd.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

/**
 * Created by heqingbao on 2017/2/23.
 */
public class LanguagesAdapter extends BaseAdapter {

    private Context context;
    private Language[] languages;
    private Language current;

    public LanguagesAdapter(Context context, Language[] languages, Language current) {
        this.context = context;
        this.languages = languages;
        this.current = current;
    }

    public void setCurrentLanguage(Language language) {
        current = language;
        notifyDataSetChanged();
    }

    public Language getCurrentLanguage() {
        return current;
    }

    @Override
    public int getCount() {
        return languages.length;
    }

    @Override
    public Language getItem(int position) {
        return languages[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_language, parent, false);
            CheckedTextView textView = (CheckedTextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(textView);
        }

        CheckedTextView textView = (CheckedTextView) convertView.getTag();
        Language language = getItem(position);
        textView.setText(getItem(position).desc());
        textView.setChecked(language == current);

        return convertView;
    }
}
