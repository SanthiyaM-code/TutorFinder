package com.codewithsandy.tutorfinder;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class DAOTutorDetails {
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();


//    public DAOTutorDetails() {
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference(TutorDetails.class.getSimpleName());
//    }

    public Task<Void> add(Tutor tutor, String uid) {
        return firestore.collection("Tutor").document(uid).set(tutor);
    }

//    public Task<Void> update(String key, HashMap<String, Object> hashMap) {
//
//        return databaseReference.child(key).updateChildren(hashMap);
//    }

//    public Task<Void> remove(String key) {
//        return databaseReference.child(key).removeValue();
//    }
//
//    public Query get() {
//        return databaseReference.orderByKey();
//    }
}
