package com.codewithsandy.tutorfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    ArrayList<Tutor> tutorsFav = new ArrayList<>();

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



        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        firestore.collection("Student").document(uid).collection("Favorites").get()
                .addOnCompleteListener(task ->
                {

                    if (task.isSuccessful())
                    {
                        Log.e("WandaVision", ""+task.getResult().isEmpty());
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult())
                        {
                            Tutor tutor = documentSnapshot.toObject(Tutor.class);
                            tutorsFav.add(tutor);
                            Log.d("WandaVision", documentSnapshot.getId() + " => " + tutor);

                        }
                        updateFavRecyclerView();
                    }

                });




//        FavoriteRecyclerAdapter adapter = new FavoriteRecyclerAdapter(tutorsFav,this);
        favrecyclerView = findViewById(R.id.fav_recyclerview);
        favrecyclerView.setHasFixedSize(true);
        favrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateFavRecyclerView();
//        favrecyclerView.setAdapter(adapter);

    }
    void updateFavRecyclerView()
    {
        FavoriteRecyclerAdapter adapter = new FavoriteRecyclerAdapter(tutorsFav,this);
        favrecyclerView.setAdapter(adapter);
    }


}