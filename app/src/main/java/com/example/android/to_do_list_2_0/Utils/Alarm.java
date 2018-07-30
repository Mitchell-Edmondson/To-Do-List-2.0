package com.example.android.to_do_list_2_0.Utils;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.android.to_do_list_2_0.Activities.MainActivity;
import com.example.android.to_do_list_2_0.R;
import com.example.android.to_do_list_2_0.Room.Task;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Alarm extends BroadcastReceiver {

    String CHANNEL_ID = "My_Channel_Id";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("alarm", "in onrecieve");
        Toast.makeText(context, "AYYYYY BOY", Toast.LENGTH_LONG).show();


        Bundle bundle = intent.getExtras();
        int taskId = bundle.getInt("notification_startup");
        Log.d("alarm", "bundle = " + taskId);
        Intent intent1 = new Intent(context, MainActivity.class);
        intent1.putExtra("notification_startup", taskId);

        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.heineken_logo)
                .setContentTitle("To-Do-List")
                .setContentText(String.valueOf(taskId))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        // notificationId is a unique int for each notification that you must define
        int notificationId = taskId;//task.getId();
        notificationManager.notify(notificationId, mBuilder.build());

    }
}
