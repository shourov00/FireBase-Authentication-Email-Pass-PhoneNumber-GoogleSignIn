package com.tutorial.shourov.firebaseauthgooglephoneemailpass.main.PhoneNumberAuth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tutorial.shourov.firebaseauthgooglephoneemailpass.R;
import com.tutorial.shourov.firebaseauthgooglephoneemailpass.main.main.DetailsActivity;
import com.tutorial.shourov.firebaseauthgooglephoneemailpass.main.util.Country;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NumberSigninActivity extends AppCompatActivity {

    @BindView(R.id.country_spinner)
    Spinner countrySpinner;
    @BindView(R.id.phone_number_edittext)
    EditText phoneNumberEdittext;
    @BindView(R.id.signin_btn)
    Button signinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_signin);
        ButterKnife.bind(this);

        countrySpinner.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                Country.countryNames));

        signinBtn.setOnClickListener(v -> userSignIn());
    }

    private void userSignIn(){

        String code = Country.countryAreaCodes[countrySpinner.getSelectedItemPosition()];
        String number = phoneNumberEdittext.getText().toString().trim();
        
        if(TextUtils.isEmpty(number) || number.length() > 11){
            phoneNumberEdittext.setError("Invalid phone number");
            phoneNumberEdittext.requestFocus();
            return;
        }
        startVerifyActivity(code,number);
    }

    private void startVerifyActivity(String code, String number){
        String phoneNumber = "+"+code+number;

        Intent varifyIntent = new Intent(NumberSigninActivity.this,SendCodeActivity.class);
        varifyIntent.putExtra("phonenumber",phoneNumber);
        startActivity(varifyIntent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(NumberSigninActivity.this,DetailsActivity.class));
            finish();
        }
    }
}
