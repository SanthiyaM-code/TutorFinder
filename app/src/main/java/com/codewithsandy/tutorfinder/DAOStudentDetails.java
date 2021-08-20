package com.codewithsandy.tutorfinder;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOStudentDetails {

    private DatabaseReference databaseReference;
    public DAOStudentDetails(){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference(TutorDetails.class.getSimpleName());

    }

    public Task<Void> add(StudentDetails studentDetails, String email)
    {
        return databaseReference.child(email).child("Student Details").setValue(studentDetails);
    }
    public Task<Void> update (String key, HashMap<String,Object> hashMap)
    {
        return databaseReference.child(key).updateChildren(hashMap);
    }
    public Task<Void> remove (String key)
    {
        return databaseReference.child(key).removeValue();
    }
    public Query get()
    {
        return databaseReference.orderByKey();
    }
}