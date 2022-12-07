package com.ensah.mygroceryapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.ensah.mygroceryapp.R;

import com.ensah.mygroceryapp.adapters.CourseAdapter;
import com.ensah.mygroceryapp.db.DatabaseHelper;
import com.ensah.mygroceryapp.models.Article;
import com.ensah.mygroceryapp.models.Course;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView courseListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        DatabaseHelper databaseHelper = DatabaseHelper.instanceOfDatabase(this);
        List<Course> courseList = databaseHelper.getAllCourses();

        CourseAdapter courseAdapter = new CourseAdapter(getApplicationContext(), courseList);
        courseListView.setAdapter(courseAdapter);

        courseListView.setOnItemClickListener((adapterView, view, position, l) -> {

            Course courseClicked = (Course) courseListView.getItemAtPosition(position);

            String str = String.format("ID: %d | Name: %s  | Desc: %s", courseClicked.getId(), courseClicked.getName(), courseClicked.getDescription());
            System.out.println(str);
            Intent articleIntent = new Intent(MainActivity.this, CourseArticleActivity.class);
//                System.out.println(articleClicked.getId()+" "+articleClicked.getName()+"  "+articleClicked.getDescription());
            articleIntent.putExtra(Article.COURSE_NAME, courseClicked.getName());
//
            startActivity(articleIntent);

        });
    }


    private void initWidget() {
        courseListView = findViewById(R.id.courseListView);
        Log.e("MAINACTIVITY: ", "CourseListView initialise");
    }
}