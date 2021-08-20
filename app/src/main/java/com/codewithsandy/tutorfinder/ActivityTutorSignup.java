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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTutorSignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DAOTutorDetails daoTutorDetails=new DAOTutorDetails();

        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();

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

        binding.infoSave.setOnClickListener(v -> {

            if(!strPassword.equals(cnfrmPassword))
            {
                Toast.makeText(ActivityTutorSignup.this,"Password should be same",Toast.LENGTH_SHORT).show();
            }
            else {

                if (!TextUtils.isEmpty(strEmail) && !TextUtils.isEmpty(strPassword) && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(qualifications)  && !TextUtils.isEmpty(Experience)  && !TextUtils.isEmpty(amount)  && !TextUtils.isEmpty(Bio)  && !TextUtils.isEmpty(location)  && !TextUtils.isEmpty(contactNumber)  && !TextUtils.isEmpty(state)  && !TextUtils.isEmpty(country)) {


            progressDialog.setMessage("Registering...");
                    progressDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(ActivityTutorSignup.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ActivityTutorSignup.this, "Account Created sucessfully!", Toast.LENGTH_SHORT).show();

                                firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {

                                            //To save Details
                                            TutorDetails tutorDetails=new TutorDetails(strEmail,name,qualifications,Experience,amount,Bio,location,contactNumber,state,country);
                                            daoTutorDetails.add(tutorDetails,strEmail).addOnSuccessListener(suc->
                                            {
                                                Toast.makeText(ActivityTutorSignup.this,"Account created successfully!\\nverification link is sent to your id\\nplease verify and login again!\"",Toast.LENGTH_SHORT).show();
                                            }).addOnFailureListener(er->{
                                                Toast.makeText(ActivityTutorSignup.this,""+er.getMessage(),Toast.LENGTH_SHORT).show();

                                            });

                                            //To move to next Activity

                                            Intent intent = new Intent(ActivityTutorSignup.this, LoginActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(ActivityTutorSignup.this, "Error in sending link\nTry again", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                firebaseAuth.signOut();
                            } else {
                                Toast.makeText(ActivityTutorSignup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }
                    });


                }
                else {
                    Toast.makeText(ActivityTutorSignup.this, "Each field should be filled", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }

}