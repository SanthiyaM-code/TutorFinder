package com.codewithsandy.tutorfinder;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DAOTutorDetails {
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();



    public Task<Void> add(TutorDetails tutorDetails, String uid) {
        return firestore.collection("Tutor").document(uid).set(tutorDetails);
    }

}
