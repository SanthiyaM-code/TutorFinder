package com.codewithsandy.tutorfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.codewithsandy.tutorfinder.databinding.ActivityLoginBinding;
import com.codewithsandy.tutorfinder.databinding.ActivityStudentSignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityStudentSignup extends AppCompatActivity {
    private ActivityStudentSignupBinding binding;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityStudentSignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DAOStudentDetails daoStudentDetails=new DAOStudentDetails();

        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();

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

        binding.studentSignup.setOnClickListener(v -> {

            if(!strPassword.equals(cnfrmPassword))
            {
                Toast.makeText(ActivityStudentSignup.this,"Password should be same",Toast.LENGTH_SHORT).show();
            }
            else {

                if (!TextUtils.isEmpty(cnfrmPassword) &&    !TextUtils.isEmpty(strEmail) && !TextUtils.isEmpty(strPassword) && !TextUtils.isEmpty(name) &&  !TextUtils.isEmpty(clgName)  && !TextUtils.isEmpty(grade)  && !TextUtils.isEmpty(location)  && !TextUtils.isEmpty(contactNumber)  && !TextUtils.isEmpty(state)  && !TextUtils.isEmpty(country))
                {
                    progressDialog.setMessage("Registering...");
                    progressDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(ActivityStudentSignup.this, task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(ActivityStudentSignup.this, "Account Created sucessfully!", Toast.LENGTH_SHORT).show();

                            firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        //To save Details
                                        StudentDetails studentDetails=new StudentDetails(strEmail,name,grade,clgName,location,contactNumber,state,country);
                                        daoStudentDetails.add(studentDetails,strEmail).addOnSuccessListener(suc->
                                        {
                                            Toast.makeText(ActivityStudentSignup.this,"Account created successfully!\\nverification link is sent to your id\\nplease verify and login again!\"",Toast.LENGTH_SHORT).show();
                                        }).addOnFailureListener(er->{
                                            Toast.makeText(ActivityStudentSignup.this,""+er.getMessage(),Toast.LENGTH_SHORT).show();

                                        });
                                        //To move to next Activity

                                        Intent intent = new Intent(ActivityStudentSignup.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(ActivityStudentSignup.this, "Error in sending link\nTry again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            firebaseAuth.signOut();
                        } else {
                            Toast.makeText(ActivityStudentSignup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    });


                }
                else
                    {
                    Toast.makeText(ActivityStudentSignup.this, "Each field should be filled", Toast.LENGTH_SHORT).show();
                    }
            }
        });



    }
}