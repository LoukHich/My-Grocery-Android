package com.ensah.mygroceryapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ensah.mygroceryapp.R;

import com.ensah.mygroceryapp.adapters.CourseArticleAdapter;

import com.ensah.mygroceryapp.db.DatabaseHelper;
import com.ensah.mygroceryapp.models.Article;
import com.ensah.mygroceryapp.models.ArticleWithCount;

import java.util.List;

public class CourseArticleActivity extends AppCompatActivity {
    private  ListView articleListView;
    private String courseNameSelected;
    private TextView txtViewTitle;
    static  List<ArticleWithCount> articleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_course);
        initWidget();

        Intent previousIntent = getIntent();
        courseNameSelected = previousIntent.getStringExtra(Article.COURSE_NAME);
        txtViewTitle.setText(courseNameSelected);
        loadFromDBToMemory();
        articleList.stream().forEach(a -> {
            System.out.println(a.getName() + " " + a.getCount());
        });
        setArticleCourseAdapter();
    }

    private void initWidget() {
        this.txtViewTitle = findViewById(R.id.txt_view_title);
        this.articleListView = findViewById(R.id.CourseArticleListView);
        Log.e("ARTICLE_ACTIVITY: ", "Article ListView initialise");
    }

    private void loadFromDBToMemory() {
        DatabaseHelper databaseHelper = DatabaseHelper.instanceOfDatabase(this);
        articleList = databaseHelper.getArticlesOfCourseV2(courseNameSelected);

    }

    private void setArticleCourseAdapter() {
        CourseArticleAdapter articleAdapter = new CourseArticleAdapter(getApplicationContext(), articleList);
        articleListView.setAdapter(articleAdapter);
    }

    public void newArticle(View v) {
        Intent articleIntent = new Intent(CourseArticleActivity.this, ArticleActivity.class);
        articleIntent.putExtra(Article.COURSE_NAME, courseNameSelected);
        startActivity(articleIntent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        CourseArticleAdapter articleAdapter = new CourseArticleAdapter(getApplicationContext(), articleList);
        articleListView.setAdapter(articleAdapter);
    }
}