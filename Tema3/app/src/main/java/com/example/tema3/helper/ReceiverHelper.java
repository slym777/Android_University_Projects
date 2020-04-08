package com.example.tema3.helper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;

import com.example.tema3.R;

import static androidx.core.content.ContextCompat.getSystemService;

public class ReceiverHelper extends BroadcastReceiver {
    private NotificationManager notificationManager;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        notificationManager = getSystemService(context,NotificationManager.class);
        String alarmTitle = intent.getStringExtra( "alarmTitle") ;
        createNotificationChannel();
        Notification notification = getNotification("Alarm Notification", context, alarmTitle);

        notificationManager.notify(1 , notification) ;
    }

    public void createNotificationChannel() {

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel (PRIMARY_CHANNEL_ID,
                    "Alarm notification", NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription
                    ("My Alarm Notification");
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private Notification getNotification (String content, Context context, String titleAlarm) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID) ;
        builder.setContentTitle(titleAlarm) ;
        builder.setContentText(content) ;
        builder.setSmallIcon(R.drawable. ic_launcher_foreground ) ;
        builder.setAutoCancel(true) ;
        return builder.build() ;
    }
}
