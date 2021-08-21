package com.codewithsandy.tutorfinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class StudentMainActivity extends AppCompatActivity implements StudentMainRecyclerAdapter.onProfileListener
{

    RecyclerView recyclerView;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    ArrayList<Tutor> tutors = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);


        firestore.collection("Tutor").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
//                    allTutors.add(document.getData());
                    Tutor tutor = document.toObject(Tutor.class);
                    tutors.add(tutor);
                    Log.d("WandaVision", document.getId() + " => " + tutor);
                }
                updateRecyclerView();
            }
            else {
                Log.d("WandaVision", "Error getting documents: ", task.getException());
            }
        });

        recyclerView = findViewById(R.id.main_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateRecyclerView();

    }

    void updateRecyclerView()
    {
        StudentMainRecyclerAdapter adapter = new StudentMainRecyclerAdapter(tutors,this);
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

    @Override
    public void profileOnClick(int position) {
        tutors.get(position);

        Intent intent=new Intent(StudentMainActivity.this,TutorProfileViewActivity.class);
                        intent.putExtra("name",tutors.get(position).getName());
                        intent.putExtra("bio",tutors.get(position).getBio());
                        intent.putExtra("location",tutors.get(position).getLocation());
                        intent.putExtra("email",tutors.get(position).getStrEmail());
                        intent.putExtra("contactNumber",tutors.get(position).getContactNumber());
                        intent.putExtra("experience",tutors.get(position).getExperience());
                        intent.putExtra("qualification",tutors.get(position).getQualifications());
                        intent.putExtra("amount",tutors.get(position).getAmount());
                        startActivity(intent);



    }
}