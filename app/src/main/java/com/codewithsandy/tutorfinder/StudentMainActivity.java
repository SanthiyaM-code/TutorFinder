package com.codewithsandy.tutorfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        ArrayList<Tutor> tutors = new ArrayList<>();
//        tutors.add(new Tutor("Tutor 1", 1.1f, 10));
//        tutors.add(new Tutor("Tutor 2", 2.2f, 11));
//        tutors.add(new Tutor("Tutor 3", 3.3f, 12));
//        tutors.add(new Tutor("Tutor 4", 4.4f, 13));
//        tutors.add(new Tutor("Tutor 5", 5.5f, 14));

        StudentMainRecyclerAdapter adapter = new StudentMainRecyclerAdapter(tutors);

        recyclerView = findViewById(R.id.main_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_favorites) {
            startActivity(new Intent(StudentMainActivity.this, FavoritesActivity.class));
        } else if (item.getItemId() == R.id.item_notification) {
//            startActivity(new Intent(StudentMainActivity.this,NotificationActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}