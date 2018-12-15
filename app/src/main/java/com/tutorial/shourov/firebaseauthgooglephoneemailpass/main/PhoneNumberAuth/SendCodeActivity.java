package com.tutorial.shourov.firebaseauthgooglephoneemailpass.main.PhoneNumberAuth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.tutorial.shourov.firebaseauthgooglephoneemailpass.R;
import com.tutorial.shourov.firebaseauthgooglephoneemailpass.main.main.DetailsActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SendCodeActivity extends AppCompatActivity {

    @BindView(R.id.code_edittext)
    EditText codeEdittext;
    @BindView(R.id.submit_btn)
    Button submitBtn;

    private String varificaitonID;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_code);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        String number = getIntent().getStringExtra("phonenumber");
        varificationCode(number);

        submitBtn.setOnClickListener(v -> {
            String code = codeEdittext.getText().toString();
            if(code.isEmpty() || code.length() < 6){
                codeEdittext.setError("Enter code");
                return;
            }
            verifyCode(code);
        });
    }

    private void verifyCode(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(varificaitonID,code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        startActivity(new Intent(SendCodeActivity.this, DetailsActivity.class));
                        finish();
                    }else{
                        Toast.makeText(SendCodeActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void varificationCode(String number) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                callback
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            varificaitonID = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null){
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(SendCodeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
}
