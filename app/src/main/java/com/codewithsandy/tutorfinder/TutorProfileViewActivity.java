package com.codewithsandy.tutorfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class TutorProfileViewActivity extends AppCompatActivity {

    TextView sendRequest;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_profile_view);

        String tutorUid = getIntent().getStringExtra("tutorUid");
        sendRequest = findViewById(R.id.send_request);
        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestore.collection("Tutor")
                        .document(tutorUid)
                        .collection("data")
                        .document("requests");
//
            }
        });

    }
}