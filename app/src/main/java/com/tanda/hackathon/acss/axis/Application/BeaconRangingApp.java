package com.tanda.hackathon.acss.axis.Application;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.tanda.hackathon.acss.axis.Activities.MainActivity;
import com.tanda.hackathon.acss.axis.Models.Routes;

import java.util.List;
import java.util.UUID;

/**
 * Created by kean on 7/28/17.
 */

public class BeaconRangingApp extends Application {
    private BeaconManager beaconManager;
    private RequestQueue queue;
    private Routes routes = new Routes();

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(getApplicationContext());
        beaconManager = new BeaconManager(getApplicationContext());

        //

        //Ranging, checkin shitssss
        beaconManager.connect(new BeaconManager.ServiceReadyCallback(){
            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(new Region(
                        "Reception Region",
                        UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"),
                        55656,
                        22263
                ));

                beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
                    @Override
                    public void onEnteredRegion(Region region, List<Beacon> list) {
                        showNotification(
                                "AXIS TANDA HACKATHON",
                                "TIME TO G BOIS THIS IS CLOCKIN BITCHES");
                        routes.testPing(queue);
                    }
                    @Override
                    public void onExitedRegion(Region region) {
                        // could add an "exit" notification too if you want (-:
                        showNotification(
                                "AXIS TANDA HACKATHON",
                                "TIME TO G BOIS THIS IS CLOCKOUT BITCHES");
                    }
                });
            }
        });

        beaconManager.setBackgroundScanPeriod(1000, 1000);
    }

    public void showNotification(String title, String message) {
        Intent notifyIntent = new Intent(this, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0,
                new Intent[] { notifyIntent }, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
}
