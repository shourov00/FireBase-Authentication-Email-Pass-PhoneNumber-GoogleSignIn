package com.tutorial.shourov.firebaseauthgooglephoneemailpass.main.EmailPasswordAuth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.tutorial.shourov.firebaseauthgooglephoneemailpass.R;
import com.tutorial.shourov.firebaseauthgooglephoneemailpass.main.main.DetailsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmailPassActivity extends AppCompatActivity {

    @BindView(R.id.email_edittext)
    EditText emailEdittext;
    @BindView(R.id.password_edittext)
    EditText passwordEdittext;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.forgot_password_btn)
    Button forgotPasswordBtn;
    @BindView(R.id.register_btn)
    Button registerBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        //auto login
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(EmailPassActivity.this, DetailsActivity.class));
            finish();
        }

        setContentView(R.layout.activity_email_pass);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();


        loginBtn.setOnClickListener(v -> userLogin());

        forgotPasswordBtn.setOnClickListener(v ->
                startActivity(new Intent(EmailPassActivity.this, ResetActivity.class)));

        registerBtn.setOnClickListener(v ->
                startActivity(new Intent(EmailPassActivity.this,SignupActivity.class)));
    }

    private void userLogin() {
        String userEmail = emailEdittext.getText().toString();
        String userPassword = passwordEdittext.getText().toString();

        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(EmailPassActivity.this, DetailsActivity.class));
                        finish();
                    } else {

                        if (userPassword.length() < 6) {
                            passwordEdittext.setError("Invalid password");
                        } else {
                            Toast.makeText(EmailPassActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}
