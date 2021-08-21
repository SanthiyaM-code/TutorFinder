package com.codewithsandy.tutorfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class TutorProfileViewActivity extends AppCompatActivity {

    TextView sendRequest;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    Tutor tutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_profile_view);

        String tutorUid = getIntent().getStringExtra("tutorUid");
        if(tutorUid == null)
            Log.d("INNOVA", "NULLDA");
        else
            Log.d("INNOVA", tutorUid);


        firestore.collection("Tutor").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Tutor t = document.toObject(Tutor.class);
                    if (t.getContactNumber().equals(tutorUid)){
                        tutor = t;
                    }
                    Log.d("WandaVision", document.getId() + " => " + tutor);
                }
            }
            else {
                Log.d("WandaVision", "Error getting documents: ", task.getException());
            }
        });

        sendRequest = findViewById(R.id.send_request);
        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestore.collection("Tutor")
                        .document(tutor.getUid())
                        .collection("data")
                        .document("requests")
                        .set(tutor);
//
            }
        });

    }
}