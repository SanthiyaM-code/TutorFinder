package com.codewithsandy.tutorfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codewithsandy.tutorfinder.databinding.ActivityTutorProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class TutorProfile extends AppCompatActivity {

    private ActivityTutorProfileBinding binding;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTutorProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TutorProfile.this,TutorMainActivity.class));

            }
        });



        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        String currentUser=user.getUid();

        db=FirebaseFirestore.getInstance();
        DocumentReference reference=db.collection("Tutor").document(currentUser);
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<DocumentSnapshot> task) {
                if(task.getResult().exists())
                {
                    String name=task.getResult().getString("name");
                    String qualification=task.getResult().getString("qualifications");
                    String experience=task.getResult().getString("experience");
                    String amount=task.getResult().getString("amount");
                    String Bio=task.getResult().getString("bio");
                    String contactNumber=task.getResult().getString("contactNumber");
                    String Location=task.getResult().getString("location");
                    String email=FirebaseAuth.getInstance().getCurrentUser().getEmail();
                    binding.tutorName.setText(name);
                    binding.tutorEducation.setText(qualification);
                    binding.tutorLocation.setText(Location);
                    binding.tutorPhoneNumber.setText(contactNumber);
                    binding.tutorBio.setText(Bio);
                    binding.tutorRate.setText(amount);
                    binding.tutrExperience.setText(experience);
                    binding.tutorEmail.setText(email);

                }
                else
                {

                }
            }
        });





    }
}