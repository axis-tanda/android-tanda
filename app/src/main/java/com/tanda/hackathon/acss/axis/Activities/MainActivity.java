package com.tanda.hackathon.acss.axis.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.estimote.sdk.SystemRequirementsChecker;
import com.tanda.hackathon.acss.axis.Fragments.ClockinFragment;
import com.tanda.hackathon.acss.axis.Fragments.MeetingFragment;
import com.tanda.hackathon.acss.axis.Models.User;
import com.tanda.hackathon.acss.axis.R;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements ClockinFragment.OnClockinInteractionListener, MeetingFragment.OnMeetingListener{
    public static User currentUser = null;
    private Boolean firstLoad = true;
    private TextView dateTitle;
    private TextView timeTitle;
    private TextView userTitle;
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

                    userTitle.setText(nameString);
                    fab.setImageResource(android.R.drawable.ic_menu_manage);

                    if(firstLoad) {
                        firstLoad = false;
                    } else changeFragment(0);
                    return true;
                case R.id.navigation_rooms:
                    userTitle.setVisibility(View.INVISIBLE);
                    dateTitle.setVisibility(View.VISIBLE);
                    timeTitle.setVisibility(View.VISIBLE);

                    dateTitle.setText(dateString);
                    timeTitle.setText(timeString);
                    fab.setImageResource(android.R.drawable.ic_menu_my_calendar);
                    changeFragment(1);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateTitle = (TextView) findViewById(R.id.dateTitle);
        timeTitle = (TextView) findViewById(R.id.timeTitle);
        userTitle = (TextView) findViewById(R.id.nameTitle);
        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ClockinFragment firstFragment = new ClockinFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayout, firstFragment).commit();

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

    public void changeFragment(int fragment) {
        Fragment selectedFragment = null;
        switch(fragment) {
            case 0:
                selectedFragment = ClockinFragment.newInstance("a", "b");
                break;
            case 1:
                selectedFragment = MeetingFragment.newInstance("a", "b");
                break;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frameLayout, selectedFragment);
        transaction.commit();
    }

    public void onClockinInteraction(Uri uri){}
    public void onMeetingInteraction(Uri uri){}
}
