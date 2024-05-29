package com.example.vihang;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class ScheduledNotificationReceiver extends BroadcastReceiver {

    // The implementation for Scheduled Notifications was taken from the following link:
    // https://youtu.be/nl-dheVpt8o?si=yveTK3pN5OrGiIgZ&t=2m21s

    // Store the channel ID for the notification
    private static final String CHANNEL_ID = "daily_facts";
    NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        DBHelper dbHelper = new DBHelper(context);

        if (dbHelper.getFactsCount() == 0) {
            dbHelper.addTestingFacts();
        }

        String notificationTitle = "Daily Sustainability Fact";
        String notificationText = dbHelper.getRandomFact();

        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1,
                notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,
                CHANNEL_ID);

        builder.setContentTitle(notificationTitle)
                .setContentText(notificationText)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Daily Facts",
                NotificationManager.IMPORTANCE_HIGH);
        notificationManager.createNotificationChannel(notificationChannel);

        notificationManager.notify(1, builder.build());
    }
}