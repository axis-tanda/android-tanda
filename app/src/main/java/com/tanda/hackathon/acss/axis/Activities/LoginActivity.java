package com.tanda.hackathon.acss.axis.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tanda.hackathon.acss.axis.Models.User;
import com.tanda.hackathon.acss.axis.R;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameForm;
    private EditText passwordForm;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameForm = (EditText) findViewById(R.id.emailEditText);
        passwordForm = (EditText) findViewById(R.id.passwordEditText);
        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.currentUser = new User();
                finish();

                MainActivity.navigation.findViewById(R.id.navigation_clockin).performClick();
                overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
