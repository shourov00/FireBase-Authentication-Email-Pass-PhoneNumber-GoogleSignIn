package com.tutorial.shourov.firebaseauthgooglephoneemailpass.main.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tutorial.shourov.firebaseauthgooglephoneemailpass.R;
import com.tutorial.shourov.firebaseauthgooglephoneemailpass.main.EmailPasswordAuth.EmailPassActivity;
import com.tutorial.shourov.firebaseauthgooglephoneemailpass.main.GoogleAuth.GoogleSigninActivity;
import com.tutorial.shourov.firebaseauthgooglephoneemailpass.main.PhoneNumberAuth.NumberSigninActivity;

import java.security.cert.PKIXRevocationChecker;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OptionsActivity extends AppCompatActivity {

    @BindView(R.id.email_password_btn)
    Button emailPasswordBtn;

    @BindView(R.id.phone_number_btn)
    Button phoneNumberBtn;

    @BindView(R.id.google_btn)
    Button googleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        ButterKnife.bind(this);

        emailPasswordBtn.setOnClickListener(v ->
                startActivity(new Intent(OptionsActivity.this,EmailPassActivity.class)));

        phoneNumberBtn.setOnClickListener(v ->
                startActivity(new Intent(OptionsActivity.this,NumberSigninActivity.class)));

        googleBtn.setOnClickListener(v ->
                startActivity(new Intent(OptionsActivity.this,GoogleSigninActivity.class)));
    }
}
