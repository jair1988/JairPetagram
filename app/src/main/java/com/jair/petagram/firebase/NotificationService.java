package com.jair.petagram.firebase;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.jair.petagram.ContactoActivity;
import com.jair.petagram.MainActivity;
import com.jair.petagram.R;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class NotificationService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    private void sendNotification(RemoteMessage remoteMessage) {
        try {
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent pendingIntentProfile = PendingIntent.getActivity(this, 0, new Intent(this, ContactoActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_full_perfil, getString(R.string.perfil), pendingIntent).build();
            NotificationCompat.Action action2 = new NotificationCompat.Action.Builder(R.drawable.ic_full_follow, getString(R.string.follow), pendingIntent).build();
            NotificationCompat.Action action3 = new NotificationCompat.Action.Builder(R.drawable.ic_full_user, getString(R.string.user), pendingIntentProfile).build();
            NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender();

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
                    .extend(wearableExtender.addAction(action).addAction(action2).addAction(action3))
                    .setStyle(new NotificationCompat.BigTextStyle());
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            Objects.requireNonNull(notificationManager).notify(1, builder.build());
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