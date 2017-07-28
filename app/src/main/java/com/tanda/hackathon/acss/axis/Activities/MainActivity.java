package com.tanda.hackathon.acss.axis.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.estimote.sdk.SystemRequirementsChecker;
import com.tanda.hackathon.acss.axis.Models.User;
import com.tanda.hackathon.acss.axis.R;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static User currentUser = null;

    private TextView dateTitle;
    private TextView timeTitle;
    private TextView userTitle;
    private TextView mTextMessage;
    private FloatingActionButton fab;
    public static BottomNavigationView navigation;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            String nameString = "Hi, " + (MainActivity.currentUser == null? "placeholder":
                    MainActivity.currentUser.getUsername());
            String dateString = DateFormat.getDateInstance().format(new Date());
            String timeString = DateFormat.getTimeInstance().format(new Date());
            switch (item.getItemId()) {
                case R.id.navigation_clockin:
                    userTitle.setVisibility(View.VISIBLE);
                    dateTitle.setVisibility(View.INVISIBLE);
                    timeTitle.setVisibility(View.INVISIBLE);

                    mTextMessage.setText(R.string.title_clock_in);
                    userTitle.setText(nameString);
                    fab.setImageResource(android.R.drawable.ic_menu_manage);
                    return true;
                case R.id.navigation_rooms:
                    userTitle.setVisibility(View.INVISIBLE);
                    dateTitle.setVisibility(View.VISIBLE);
                    timeTitle.setVisibility(View.VISIBLE);

                    mTextMessage.setText(R.string.title_meeting_rooms);
                    dateTitle.setText(dateString);
                    timeTitle.setText(timeString);
                    fab.setImageResource(android.R.drawable.ic_menu_my_calendar);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        dateTitle = (TextView) findViewById(R.id.dateTitle);
        timeTitle = (TextView) findViewById(R.id.timeTitle);
        userTitle = (TextView) findViewById(R.id.nameTitle);
        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Add persistence here
        if (currentUser == null) {
            Intent intent = new Intent(getApplicationContext(),
                    LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SystemRequirementsChecker.checkWithDefaultDialogs(this);
    }

}
