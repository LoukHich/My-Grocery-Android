package com.ensah.mygroceryapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ensah.mygroceryapp.R;
import com.ensah.mygroceryapp.adapters.ArticleAdapter;
import com.ensah.mygroceryapp.db.DatabaseHelper;
import com.ensah.mygroceryapp.models.Article;
import com.ensah.mygroceryapp.models.ArticleWithCount;
import com.ensah.mygroceryapp.models.ArticleWithInfo;
import com.ensah.mygroceryapp.models.Course;

import java.util.List;
import java.util.ListIterator;

public class ArticleActivity extends AppCompatActivity {

    private ListView articleListView;
    List<ArticleWithInfo> articleList;
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
        //    System.out.println(a.getId() + " " + a.getName() + " " + a.getUnite());
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
        articleList = databaseHelper.getAllArtclewithInfo();
    }

    private void EditTitleArticle() {
        Intent previousIntent = getIntent();
        courseNameSelected = previousIntent.getStringExtra(Article.COURSE_NAME);
        txtViewTitle.setText("Add article to " + courseNameSelected);


    }

    private void setOnClickListener() {
        articleListView.setOnItemClickListener((adapterView, view, position, l) -> {
            ArticleWithInfo articleClicked = (ArticleWithInfo) articleListView.getItemAtPosition(position);

            DatabaseHelper databaseHelper = DatabaseHelper.instanceOfDatabase(this);
            String str = String.format("ID: %d | Name: %s  | Desc: %s", articleClicked.getArticle().getId(), articleClicked.getArticle().getName(), articleClicked.getArticle().getUnite());
            System.out.println(str);
          if(  databaseHelper.CheckIsAlreadyInDB(courseNameSelected,articleClicked.getArticle().getName()) == false){
              databaseHelper.addArticleToCourse(courseNameSelected, articleClicked.getArticle().getName(), 1);
              CourseArticleActivity.articleList.add(new ArticleWithCount(articleClicked.getArticle().getId(),articleClicked.getArticle().getName(),articleClicked.getArticle().getUnite(),1));
              Toast.makeText(this, articleClicked.getArticle().getName()+" added successfully in "+courseNameSelected, Toast.LENGTH_SHORT).show();
              finish();

          }else{
              databaseHelper.incrementCountInCourse(courseNameSelected,articleClicked.getArticle().getId());
              ListIterator<ArticleWithCount> iterator = CourseArticleActivity.articleList.listIterator();
              while (iterator.hasNext()){
                  ArticleWithCount arIn = iterator.next();
                  if(arIn.getId()==articleClicked.getArticle().getId()){

                     iterator.set(new ArticleWithCount(articleClicked.getArticle().getId(), articleClicked.getArticle().getName(), articleClicked.getArticle().getUnite(),arIn.getCount()+1));
                  }
              }
              Toast.makeText(this, articleClicked.getArticle().getName()+" increment successfully in "+courseNameSelected, Toast.LENGTH_SHORT).show();
              finish();
              Log.e("ArticleActivity ","count incrimented");
          }


        });

    }

    private void setAdapter() {
        ArticleAdapter articleAdapter = new ArticleAdapter(getApplicationContext(), articleList);
        articleListView.setAdapter(articleAdapter);
    }

}