package com.codewithsandy.tutorfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.codewithsandy.tutorfinder.databinding.ActivityTutordetailsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Random;

public class ActivityTutordetails extends AppCompatActivity {
    private ActivityTutordetailsBinding binding;
    DAOTutorDetails daoTutorDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTutordetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.infoSave.setOnClickListener(v -> {
            String userEmail="";
            FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
            if(user!=null)
            {
                userEmail=""+user.getEmail();
            }
            daoTutorDetails=new DAOTutorDetails();
            String name=binding.tutorname.getText().toString();
            String qualifications=binding.tutorQualification.getText().toString();
            String Experience=binding.tutorExperience.getText().toString();
            String amount=binding.monthlyRate.getText().toString();
            String Bio=binding.tutorBio.getText().toString();
            String location=binding.etutorLocation.getText().toString();
            String contactNumber=binding.tutorContactNumber.getText().toString();
            String state=binding.stateTutor.getText().toString();
            String country=binding.tutorCountry.getText().toString();
            Random rand = new Random();
            float randomNum = (float) (rand.nextInt((5 - 1) + 1) + 1);
            int randomNum2 =  (rand.nextInt((100 - 1) + 1) + 1);
            Tutor tutor =new Tutor(userEmail,name,qualifications,Experience,amount,Bio,location,contactNumber,state,country);
            tutor.setRating(randomNum);
            tutor.setStudentsCount(randomNum2);
            tutor.setUid(FirebaseAuth.getInstance().getCurrentUser().getUid());
            daoTutorDetails.add(tutor,FirebaseAuth.getInstance().getCurrentUser().getUid()).addOnSuccessListener(suc->
            {
                Toast.makeText(getApplicationContext(),"Account created successfully!",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ActivityTutordetails.this,TutorProfile.class);
                intent.putExtra("name",name);
                intent.putExtra("qualification",qualifications);
                intent.putExtra("experience",Experience);
                intent.putExtra("amount",amount);
                intent.putExtra("Bio",Bio);
                intent.putExtra("location",location);
                intent.putExtra("contactNumber",contactNumber);
                startActivity(intent);
            }).addOnFailureListener(er->{
                Toast.makeText(getApplicationContext(),""+er.getMessage(),Toast.LENGTH_SHORT).show();

            });



        });

    }
}