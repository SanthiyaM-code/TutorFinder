package com.codewithsandy.tutorfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    RecyclerView favrecyclerView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        toolbar = findViewById(R.id.toolbar_favorites);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(arrow -> onBackPressed());

        ArrayList<Tutor> tutors = new ArrayList<>();
//        tutors.add(new Tutor("FavTut 1", 1.1f, 10));
//        tutors.add(new Tutor("Tutor 2", 2.2f, 11));
//        tutors.add(new Tutor("Tutor 3", 3.3f, 12));


        FavoriteRecyclerAdapter adapter = new FavoriteRecyclerAdapter(tutors);

        favrecyclerView = findViewById(R.id.fav_recyclerview);
        favrecyclerView.setHasFixedSize(true);
        favrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        favrecyclerView.setAdapter(adapter);

    }

}