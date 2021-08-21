package com.codewithsandy.tutorfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codewithsandy.tutorfinder.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private ActivityLoginBinding binding;
    final Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

        binding.buttonLogin.setOnClickListener(v -> {
            String stremail=binding.loginEmail.getText().toString();
            String strpassword=binding.loginPassword.getText().toString();
            Boolean isStudent=binding.radioButtonstudent.isChecked();
            Boolean isTutor=binding.radioButtontutor.isChecked();

            if(TextUtils.isEmpty(stremail))
            {
                Toast.makeText(this,"please Enter Email",Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(strpassword))
            {
                Toast.makeText(this,"please Enter Password",Toast.LENGTH_SHORT).show();
                return;
            }
            progressDialog.setMessage("Loging Please wait");

            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(stremail,strpassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull  Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {

                                if(isStudent) {
                                    startActivity(new Intent(LoginActivity.this, StudentMainActivity.class));
                                    progressDialog.dismiss();
                                }
                                else if(isTutor)
                                {
                                    startActivity(new Intent(LoginActivity.this,TutorMainActivity.class));
                                    progressDialog.dismiss();
                                }
                                else
                                {
                                    progressDialog.dismiss();
                                    Toast.makeText(LoginActivity.this,"Please select student or Tutor",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this,"No such User found!",Toast.LENGTH_SHORT).show();

                            }
                        }
                    }).addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull  Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        });




        binding.signupinLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.signup_dialog);
                dialog.setTitle("Confirm");
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.getWindow().setLayout(600,600);

                Button tutor=dialog.findViewById(R.id.buttontutor);
                Button student=dialog.findViewById(R.id.buttonStudent);
                tutor.setOnClickListener(v1 -> {
                    startActivity(new Intent(getApplicationContext(),ActivityTutorSignup.class));
                    dialog.dismiss();

                });
                student.setOnClickListener(v13 -> {
                    startActivity(new Intent(getApplicationContext(),ActivityStudentSignup.class));
                    dialog.dismiss();


                });

                dialog.show();
            }
        });
    }
}