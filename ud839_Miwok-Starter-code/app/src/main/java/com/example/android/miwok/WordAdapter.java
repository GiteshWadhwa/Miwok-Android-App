package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 3/3/2018.
 */
//this is a consstructor...
public class WordAdapter extends ArrayAdapter {

    private int mColorResourceId;
    public WordAdapter(Context context, ArrayList pWords,int colorResourceId) {
        super(context,0, pWords);
        mColorResourceId=colorResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Word my_word= (Word) getItem(position);
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(my_word.getMiwokTranslation());
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(my_word.getDefaultTranslation());
        ImageView imageView=(ImageView) listItemView.findViewById(R.id.image);
        if(my_word.hasImage()) {
            imageView.setImageResource(my_word.getmImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        }
        else
        {
            imageView.setVisibility(View.GONE);
        }
        View textContainer=listItemView.findViewById(R.id.text_container);
        int color= ContextCompat.getColor(getContext(),mColorResourceId);
        textContainer.setBackgroundColor(color);
          return listItemView;
    }
}