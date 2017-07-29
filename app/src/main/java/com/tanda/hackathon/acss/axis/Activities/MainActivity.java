package com.tanda.hackathon.acss.axis.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.estimote.sdk.SystemRequirementsChecker;
import com.tanda.hackathon.acss.axis.Fragments.ClockinFragment;
import com.tanda.hackathon.acss.axis.Models.User;
import com.tanda.hackathon.acss.axis.R;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements ClockinFragment.OnClockinInteractionListener{
    public static User currentUser = null;

    private TextView dateTitle;
    private TextView timeTitle;
    private TextView userTitle;
    private FloatingActionButton fab;
    public static BottomNavigationView navigation;
    private FrameLayout fragmentContainer;
    private ClockinFragment clockIn;

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

                    return true;
                case R.id.navigation_rooms:
                    userTitle.setVisibility(View.INVISIBLE);
                    dateTitle.setVisibility(View.VISIBLE);
                    timeTitle.setVisibility(View.VISIBLE);

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

        dateTitle = (TextView) findViewById(R.id.dateTitle);
        timeTitle = (TextView) findViewById(R.id.timeTitle);
        userTitle = (TextView) findViewById(R.id.nameTitle);
        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (findViewById(R.id.frameLayout) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            ClockinFragment firstFragment = new ClockinFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frameLayout, firstFragment).commit();
        }

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

    public void onClockinInteraction(Uri uri){

    }
}
