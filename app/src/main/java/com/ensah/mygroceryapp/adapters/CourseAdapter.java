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

public class CourseAdapter extends ArrayAdapter<Course> {

    public CourseAdapter(@NonNull Context context, List<Course> courses) {
        super(context,0, courses);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Course course = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.course_item, parent, false);

        TextView name = convertView.findViewById(R.id.courseItemName);
        TextView desc = convertView.findViewById(R.id.courseItemDesc);

        name.setText(course.getName());
        desc.setText(course.getDescription());

        return convertView;
    }
}
