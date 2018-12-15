package com.tutorial.shourov.firebaseauthgooglephoneemailpass.main.EmailPasswordAuth;

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
import com.google.firebase.auth.FirebaseAuth;
import com.tutorial.shourov.firebaseauthgooglephoneemailpass.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResetActivity extends AppCompatActivity {

    @BindView(R.id.email_edittext)
    EditText emailEdittext;
    @BindView(R.id.reset_btn)
    Button resetBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        resetBtn.setOnClickListener(v -> resetPassword());
    }

    private void resetPassword(){
        String userEmail = emailEdittext.getText().toString().trim();

        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Enter valid email!", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.sendPasswordResetEmail(userEmail)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(ResetActivity.this, "We have sent you instractions to reset password", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ResetActivity.this, "Failed to reset email", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
