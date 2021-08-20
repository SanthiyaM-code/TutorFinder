package com.codewithsandy.tutorfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codewithsandy.tutorfinder.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private ActivityLoginBinding binding;
    final Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth=FirebaseAuth.getInstance();

        binding.buttonLogin.setOnClickListener(v -> {
            String stremail=binding.loginEmail.getText().toString();
            String strpassword=binding.loginPassword.getText().toString();
            Boolean isStudent=binding.radioButtonstudent.isChecked();
            Boolean isTutor=binding.radioButtontutor.isChecked();

            if(!TextUtils.isEmpty(stremail) && !TextUtils.isEmpty(strpassword)){
                firebaseAuth.signInWithEmailAndPassword(stremail,strpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            if(firebaseAuth.getCurrentUser().isEmailVerified())
                            {
                                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                binding.resend.setVisibility(View.VISIBLE);
                                binding.resend.setClickable(true);
                                Toast.makeText(LoginActivity.this,"Please verify your email\n and continue!",Toast.LENGTH_SHORT).show();
                                firebaseAuth.signOut();

                            }
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
            else{
                Toast.makeText(LoginActivity.this,"Email and Password cannot be empty!",Toast.LENGTH_SHORT).show();
            }

            binding.resend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(firebaseAuth!=null)
                    {
                        if(firebaseAuth.getCurrentUser()!=null)
                        {
                            firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(LoginActivity.this,"Link has been sent to your Email\nplease verify and continue!",Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
                        }
                    }
                }
            });
        });




        binding.signupinLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.signup_dialog);
                dialog.setTitle("Confirm");
                Button stu=dialog.findViewById(R.id.buttonStudent);
                stu.setOnClickListener(v12 -> {
                    startActivity(new Intent(getApplicationContext(),ActivityStudentSignup.class));
                    dialog.dismiss();
                    finish();
                });
                Button tutor=dialog.findViewById(R.id.buttontutor);
                Button student=dialog.findViewById(R.id.buttonStudent);
                tutor.setOnClickListener(v1 -> {
                    startActivity(new Intent(getApplicationContext(),ActivityTutorSignup.class));
                    dialog.dismiss();
                    finish();
                });
                student.setOnClickListener(v13 -> {
                    startActivity(new Intent(getApplicationContext(),ActivityStudentSignup.class));
                    dialog.dismiss();
                    finish();

                });

                dialog.show();
            }
        });
    }
}