package com.codewithsandy.tutorfinder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TutorMainActivity extends AppCompatActivity {

    RecyclerView tutrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_main);


        ArrayList<Student> students = new ArrayList<>();
//        students.add(new Student("Student 1","XI"));
//        students.add(new Student("Student 2","IX"));
//        students.add(new Student("Student 3","VII"));
//        students.add(new Student("Student 4","IV"));
//        students.add(new Student("Student 5","V"));

        TutorMainRecyclerAdapter adapter = new TutorMainRecyclerAdapter(students);
        tutrecyclerView = findViewById(R.id.tut_revcycler_view);
        tutrecyclerView.setHasFixedSize(true);
        tutrecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        tutrecyclerView.setAdapter(adapter);


    }
}