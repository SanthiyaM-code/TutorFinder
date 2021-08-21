package com.codewithsandy.tutorfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.codewithsandy.tutorfinder.databinding.ActivityTutordetailsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
            //Adding data
            TutorDetails tutorDetails=new TutorDetails(userEmail,name,qualifications,Experience,amount,Bio,location,contactNumber,state,country);
            daoTutorDetails.add(tutorDetails,FirebaseAuth.getInstance().getCurrentUser().getUid()).addOnSuccessListener(suc->
            {
                Toast.makeText(getApplicationContext(),"Account created successfully!\\nverification link is sent to your id\\nplease verify and login again!\"",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ActivityTutordetails.this,MainActivity.class));
            }).addOnFailureListener(er->{
                Toast.makeText(getApplicationContext(),""+er.getMessage(),Toast.LENGTH_SHORT).show();

            });

        });

    }
}