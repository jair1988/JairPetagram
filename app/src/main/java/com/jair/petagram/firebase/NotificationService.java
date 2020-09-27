package com.jair.petagram.firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.jair.petagram.MainActivity;
import com.jair.petagram.R;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class NotificationService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    private void sendNotification(RemoteMessage remoteMessage) {
        try {
            Intent i = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String CHANNEL_ID = "com.jair.petagram";
                String title = "Notificacion Petagram";
                NotificationChannel androidChannel = new NotificationChannel(CHANNEL_ID,title, NotificationManager.IMPORTANCE_DEFAULT);
                androidChannel.enableLights(true);
                androidChannel.enableVibration(true);
                androidChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.createNotificationChannel(androidChannel);
                Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Notification.Builder nb = new Notification.Builder(getApplicationContext(), CHANNEL_ID)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.img_notifications))
                        .setSmallIcon(R.drawable.img_notifications)
                        .setContentTitle(Objects.requireNonNull(Objects.requireNonNull(remoteMessage.getNotification()).getTitle()))
                        .setContentText(remoteMessage.getNotification().getBody())
                        .setColor(getColor(R.color.colorAccent))
                        .setAutoCancel(true)
                        .setOngoing(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent)
                        .setTicker(title)
                        .setShowWhen(true);

                manager.notify(101, nb.build());
            } else {
                Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "fcm_default_channel")
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.img_notifications))
                        .setSmallIcon(R.drawable.img_notifications)
                        .setContentTitle(Objects.requireNonNull(Objects.requireNonNull(remoteMessage.getNotification()).getTitle()))
                        .setContentText(remoteMessage.getNotification().getBody())
                        .setColor(getColor(R.color.colorAccent))
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent)
                        .setStyle(new NotificationCompat.BigTextStyle());
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Objects.requireNonNull(notificationManager).notify(1, builder.build());
            }
        } catch (Exception e) {
            Log.e("error;", e.getMessage());
        }
    }

    @Override
    public void onMessageReceived(@NotNull RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() != null && remoteMessage.getNotification().getBody() != null)
            sendNotification(remoteMessage);
    }

    @Override
    public void onNewToken(@NotNull String token) {
        Log.d(TAG, "Refreshed token: " + token);
    }
}