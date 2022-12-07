package com.ensah.mygroceryapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ensah.mygroceryapp.R;
import com.ensah.mygroceryapp.adapters.ArticleAdapter;
import com.ensah.mygroceryapp.db.DatabaseHelper;
import com.ensah.mygroceryapp.models.Article;
import com.ensah.mygroceryapp.models.ArticleWithCount;
import com.ensah.mygroceryapp.models.Course;

import java.util.List;

public class ArticleActivity extends AppCompatActivity {

    private ListView articleListView;
    List<Article> articleList;
    private TextView txtViewTitle;
    String courseNameSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artcle);
        widgetInit();
        EditTitleArticle();
        loadFromDBToMemory();

        articleList.stream().forEach(a -> {
            System.out.println(a.getId() + " " + a.getName() + " " + a.getUnite());
        });
        setAdapter();
        setOnClickListener();


    }


    private void widgetInit() {
        this.articleListView = findViewById(R.id.articleListView);
        this.txtViewTitle = findViewById(R.id.article_txt_view_title);
    }

    private void loadFromDBToMemory() {
        DatabaseHelper databaseHelper = DatabaseHelper.instanceOfDatabase(this);
        articleList = databaseHelper.getAllArticles();
    }

    private void EditTitleArticle() {
        Intent previousIntent = getIntent();
        courseNameSelected = previousIntent.getStringExtra(Article.COURSE_NAME);
        txtViewTitle.setText("Add article to " + courseNameSelected);


    }

    private void setOnClickListener() {
        articleListView.setOnItemClickListener((adapterView, view, position, l) -> {
            Article articleClicked = (Article) articleListView.getItemAtPosition(position);
            DatabaseHelper databaseHelper = DatabaseHelper.instanceOfDatabase(this);
            String str = String.format("ID: %d | Name: %s  | Desc: %s", articleClicked.getId(), articleClicked.getName(), articleClicked.getUnite());
            System.out.println(str);
            databaseHelper.addArticleToCourse(courseNameSelected, articleClicked.getName(), 1);
            CourseArticleActivity.articleList.add(new ArticleWithCount(articleClicked.getId(),articleClicked.getName(),articleClicked.getUnite(),1));
            finish();
        });

    }

    private void setAdapter() {
        ArticleAdapter articleAdapter = new ArticleAdapter(getApplicationContext(), articleList);
        articleListView.setAdapter(articleAdapter);
    }

}