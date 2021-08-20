package com.codewithsandy.tutorfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.codewithsandy.tutorfinder.databinding.ActivityTutorSignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityTutorSignup extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private ActivityTutorSignupBinding binding;
    private ProgressDialog progressDialog;
    DAOTutorDetails daoTutorDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTutorSignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();



        binding.infoSave.setOnClickListener(v -> {


        });
    }
    private void  registerUser(){

        daoTutorDetails=new DAOTutorDetails();

        String strEmail=binding.tutorEmail.getText().toString();
        String strPassword=binding.tutorPasswordr.getText().toString();
        String cnfrmPassword=binding.tutorConfrmPassrd.getText().toString();
        String name=binding.tutorname.getText().toString();
        String qualifications=binding.tutorQualification.getText().toString();
        String Experience=binding.tutorExperience.getText().toString();
        String amount=binding.monthlyRate.getText().toString();
        String Bio=binding.tutorBio.getText().toString();
        String location=binding.etutorLocation.getText().toString();
        String contactNumber=binding.tutorContactNumber.getText().toString();
        String state=binding.stateTutor.getText().toString();
        String country=binding.tutorCountry.getText().toString();


        if(TextUtils.isEmpty(strEmail))
        {
            Toast.makeText(this,"please Enter Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(strPassword))
        {
            Toast.makeText(this,"please Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering please wait...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(strEmail,strPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            finish();
                            //Adding data
                            TutorDetails tutorDetails=new TutorDetails(strEmail,name,qualifications,Experience,amount,Bio,location,contactNumber,state,country);
                            daoTutorDetails.add(tutorDetails,strEmail).addOnSuccessListener(suc->
                            {
                                Toast.makeText(getApplicationContext(),"Account created successfully!\\nverification link is sent to your id\\nplease verify and login again!\"",Toast.LENGTH_SHORT).show();
                            }).addOnFailureListener(er->{
                                Toast.makeText(getApplicationContext(),""+er.getMessage(),Toast.LENGTH_SHORT).show();

                            });

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Registration error",Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

}