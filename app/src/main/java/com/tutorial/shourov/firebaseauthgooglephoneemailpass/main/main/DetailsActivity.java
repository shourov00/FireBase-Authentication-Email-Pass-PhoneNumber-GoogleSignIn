package com.tutorial.shourov.firebaseauthgooglephoneemailpass.main.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.tutorial.shourov.firebaseauthgooglephoneemailpass.R;
import com.tutorial.shourov.firebaseauthgooglephoneemailpass.main.EmailPasswordAuth.EmailPassActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.logout_btn)
    Button logoutBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        logoutBtn.setOnClickListener(v -> {
            mAuth.signOut();
            Intent intent = new Intent(DetailsActivity.this,OptionsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
