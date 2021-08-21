package com.codewithsandy.tutorfinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class TutorMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    ArrayList<Student> student = new ArrayList<>();
    ImageView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_main);

        btn=findViewById(R.id.profile);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TutorMainActivity.this,TutorProfile.class));
            }
        });

//
//        ArrayList<Student> students = new ArrayList<>();
////        students.add(new Student("Student 1","XI"));
////        students.add(new Student("Student 2","IX"));
////        students.add(new Student("Student 3","VII"));
////        students.add(new Student("Student 4","IV"));
////        students.add(new Student("Student 5","V"));
//
//        TutorMainRecyclerAdapter adapter = new TutorMainRecyclerAdapter(students);
//        tutrecyclerView = findViewById(R.id.tut_revcycler_view);
//        tutrecyclerView.setHasFixedSize(true);
//        tutrecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        tutrecyclerView.setAdapter(adapter);

        firestore.collection("Student").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
//                    allTutors.add(document.getData());
                    Student stu = document.toObject(Student.class);
                    student.add(stu);

                }
                updateRecyclerView();
            }
            else {
                Log.d("WandaVision", "Error getting documents: ", task.getException());
            }
        });

        recyclerView = findViewById(R.id.tut_revcycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateRecyclerView();

    }

    void updateRecyclerView()
    {
        TutorMainRecyclerAdapter adapter = new TutorMainRecyclerAdapter(student);
        recyclerView.setAdapter(adapter);
    }



}