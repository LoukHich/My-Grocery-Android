package com.ensah.mygroceryapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ensah.mygroceryapp.R;
import com.ensah.mygroceryapp.models.ArticleWithCount;

import java.util.List;

public class CourseArticleAdapter extends ArrayAdapter<ArticleWithCount> {

    public CourseArticleAdapter(Context context, List<ArticleWithCount> articles) {
        super(context, 0, articles);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ArticleWithCount article = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.articlewithcount_item, parent, false);

        TextView name = convertView.findViewById(R.id.itemName);
        TextView desc = convertView.findViewById(R.id.itemDesc);
        TextView count = convertView.findViewById(R.id.itemcount);

        name.setText(article.getName());
        desc.setText(article.getUnite());
        count.setText(article.getCount().toString());

        return convertView;
    }
}
