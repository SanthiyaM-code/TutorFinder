package com.codewithsandy.tutorfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codewithsandy.tutorfinder.databinding.ActivityTutorProfileViewBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class TutorProfileViewActivity extends AppCompatActivity {

    private ActivityTutorProfileViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTutorProfileViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        String name=getIntent().getStringExtra("name");
        String loc=getIntent().getStringExtra("location");
        String bio=getIntent().getStringExtra("bio");
        String contact=getIntent().getStringExtra("contactNumber");
        String qualification=getIntent().getStringExtra("qualification");
        String experience=getIntent().getStringExtra("experience");
        String amount=getIntent().getStringExtra("amount");
        String email=getIntent().getStringExtra("email");

        binding.tutorName.setText(name);
        binding.tutorEmail.setText(email);
        binding.tutorLocation.setText(loc);
        binding.tutorBio.setText(bio);
        binding.tutorPhoneNumber.setText(contact);
        binding.tutorEducation.setText(qualification);
        binding.tutrExperience.setText(experience);
        binding.tutorRate.setText(amount);
        binding.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(TutorProfileViewActivity.this,"Thanks for the feedback!",Toast.LENGTH_SHORT).show();

            }
        });

    }

}