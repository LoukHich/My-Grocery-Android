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
import com.ensah.mygroceryapp.models.Article;
import com.ensah.mygroceryapp.models.Course;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(@NonNull Context context, List<Article> articles) {
        super(context,0, articles);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Article article = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.article_item, parent, false);

        TextView name = convertView.findViewById(R.id.article_itemName);
        TextView unite = convertView.findViewById(R.id.article_itemUnite);

        name.setText(article.getName());
        unite.setText(article.getUnite());

        return convertView;
    }
}
