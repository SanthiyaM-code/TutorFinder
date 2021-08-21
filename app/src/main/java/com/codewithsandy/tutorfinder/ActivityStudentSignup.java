package com.codewithsandy.tutorfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.codewithsandy.tutorfinder.databinding.ActivityStudentSignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityStudentSignup extends AppCompatActivity {
    private ActivityStudentSignupBinding binding;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    DAOStudentDetails daoStudentDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityStudentSignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();

        binding.studentSignup.setOnClickListener(v -> {
            registerUser();
        });



    }
    private void  registerUser(){

        daoStudentDetails=new DAOStudentDetails();

        String strEmail=binding.studentEmail.getText().toString();
        String strPassword=binding.studentPasswordr.getText().toString();
        String cnfrmPassword=binding.studentConfrmPassrd.getText().toString();
        String name=binding.studentname.getText().toString();
        String grade=binding.studentGrade.getText().toString();
        String clgName=binding.sclName.getText().toString();
        String location=binding.studentLocation.getText().toString();
        String contactNumber=binding.studENtContactNumber.getText().toString();
        String state=binding.stateStudent.getText().toString();
        String country=binding.stuCountry.getText().toString();

        String email=binding.studentEmail.getText().toString();
        String password=binding.studentPasswordr.getText().toString();


        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"please Enter Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"please Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering please wait...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {

                            //Adding data
                            Student student =new Student(strEmail,name,grade,clgName,location,contactNumber,state,country);
                            student.setUid(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            daoStudentDetails.add(student,FirebaseAuth.getInstance().getCurrentUser().getUid()).addOnSuccessListener(suc->
                            {

                                Toast.makeText(ActivityStudentSignup.this,"Account created successfully!",Toast.LENGTH_SHORT).show();
                            }).addOnFailureListener(er->{
                                Toast.makeText(ActivityStudentSignup.this,""+er.getMessage(),Toast.LENGTH_SHORT).show();

                            });

                            startActivity(new Intent(getApplicationContext(),StudentMainActivity.class));
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

//    To save Details
