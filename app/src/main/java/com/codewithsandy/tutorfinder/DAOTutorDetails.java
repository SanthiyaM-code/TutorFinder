package com.codewithsandy.tutorfinder;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class DAOTutorDetails {
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();



    public Task<Void> add(Tutor tutor, String uid) {
        return firestore.collection("Tutor").document(uid).set(tutor);
    }

}
