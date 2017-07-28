package com.tanda.hackathon.acss.axis.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.estimote.sdk.SystemRequirementsChecker;
import com.tanda.hackathon.acss.axis.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);
    }
}
