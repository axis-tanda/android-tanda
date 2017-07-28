package com.tanda.hackathon.acss.axis.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.tanda.hackathon.acss.axis.R;

public class LoginActivity extends AppCompatActivity {
    private ImageView backButton;
    private EditText usernameForm;
    private EditText passwordForm;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        backButton = (ImageView) findViewById(R.id.backBtn);
        usernameForm = (EditText) findViewById(R.id.emailEditText);
        passwordForm = (EditText) findViewById(R.id.passwordEditText);
        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SplashActivity.currentUser
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
