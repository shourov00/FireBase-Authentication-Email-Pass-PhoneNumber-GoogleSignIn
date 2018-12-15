package com.tutorial.shourov.firebaseauthgooglephoneemailpass.main.EmailPasswordAuth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tutorial.shourov.firebaseauthgooglephoneemailpass.R;
import com.tutorial.shourov.firebaseauthgooglephoneemailpass.main.main.DetailsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.email_edittext)
    EditText emailEdittext;
    @BindView(R.id.password_edittext)
    EditText passwordEdittext;
    @BindView(R.id.signup_btn)
    Button signupBtn;
    @BindView(R.id.login_btn)
    Button loginBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        signupBtn.setOnClickListener(v -> userRegistration());

        loginBtn.setOnClickListener(v -> finish());
    }

    private void userRegistration(){
        String userEmail = emailEdittext.getText().toString().trim();
        String userPassword = passwordEdittext.getText().toString().trim();

        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(userPassword)){
            Toast.makeText(this, "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(userPassword.length() < 6){
            Toast.makeText(this, "Password too short, enter minimum 6 character", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        startActivity(new Intent(SignupActivity.this,DetailsActivity.class));
                        finish();
                    }else{
                        Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
