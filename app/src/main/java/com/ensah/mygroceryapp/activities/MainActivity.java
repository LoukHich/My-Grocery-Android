package com.ensah.mygroceryapp.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ensah.mygroceryapp.R;

import com.ensah.mygroceryapp.adapters.CourseAdapter;
import com.ensah.mygroceryapp.db.DatabaseHelper;
import com.ensah.mygroceryapp.models.Article;
import com.ensah.mygroceryapp.models.ArticleWithInfo;
import com.ensah.mygroceryapp.models.Course;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView courseListView;
    List<Course> courseList;
    CourseAdapter courseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidget();
        DatabaseHelper databaseHelper = DatabaseHelper.instanceOfDatabase(this);
        List<ArticleWithInfo> articleWithInfos = databaseHelper.getAllArtclewithInfo();
        articleWithInfos.stream().forEach(a -> {
            System.out.println(a.getArticle().getId() + " " + a.getArticle().getName() + " " + a.getInfo() + " ");
        });
         courseList = databaseHelper.getAllCourses();

         courseAdapter = new CourseAdapter(getApplicationContext(), courseList);
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

    private void newArticle(View v) {
          showAlertDialogButtonClicked(v);
    }

    public void showAlertDialogButtonClicked(View view) {

        // Create an alert builder
        AlertDialog.Builder builder
                = new AlertDialog.Builder(this);
        builder.setTitle("Course Name");

        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        builder.setView(customLayout);

        // add a button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick (DialogInterface dialog,int which)
            {

                // send data from the
                // AlertDialog to the Activity
                EditText editText
                        = customLayout
                        .findViewById(
                                R.id.editText_dialog);
                sendDialogDataToActivity(
                        editText
                                .getText()
                                .toString());
            }
        });

        // create and show
        // the alert dialog
        AlertDialog dialog
                = builder.create();
        dialog.show();
    }

    // Do something with the data
    // coming from the AlertDialog
    private void sendDialogDataToActivity(String data) {
        DatabaseHelper databaseHelper = DatabaseHelper.instanceOfDatabase(this);
        databaseHelper.createCourse(new Course(data,"Marjane course"));
        courseList.add(new Course(data,"Marjane course"));
        courseAdapter.notifyDataSetChanged();
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}