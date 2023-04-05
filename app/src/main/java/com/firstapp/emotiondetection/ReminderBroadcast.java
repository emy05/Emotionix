package com.firstapp.emotiondetection;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class ReminderBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context,Intent intent){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,"notifyMe")
                .setSmallIcon(R.drawable.alert)
                .setContentTitle("Remind me Every Hour")
                .setContentText("Are You Feeling well Today? Time to Journal..")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(200,builder.build());

    }
}
