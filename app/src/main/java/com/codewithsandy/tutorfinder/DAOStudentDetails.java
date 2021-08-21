package com.codewithsandy.tutorfinder;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class DAOStudentDetails {

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();


//    public DAOStudentDetails(){
//        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
//        databaseReference=firebaseDatabase.getReference(TutorDetails.class.getSimpleName());
//
//    }

    public Task<Void> add(Student student, String uid)
    {
        return firestore.collection("Student").document(uid).set(student);
    }

//    public Task<Void> update (String key, HashMap<String,Object> hashMap)
//    {
//        return databaseReference.child(key).updateChildren(hashMap);
//    }
//    public Task<Void> remove (String key)
//    {
//        return databaseReference.child(key).removeValue();
//    }
//    public Query get()
//    {
//        return databaseReference.orderByKey();
//    }
}
