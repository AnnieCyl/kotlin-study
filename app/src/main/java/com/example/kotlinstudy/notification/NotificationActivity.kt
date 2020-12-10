package com.example.kotlinstudy.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity() {
    private lateinit var manager: NotificationManager;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelId = "normal"
        val channel2Id = "important"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Normal", NotificationManager.IMPORTANCE_DEFAULT)
            val channel2 = NotificationChannel(channel2Id, "Important", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
            manager.createNotificationChannel(channel2)
        }

        btn_send_notification.setOnClickListener {
            val intent = Intent(this, Notification2Activity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, 0)
            val notification = NotificationCompat.Builder(this, channelId)
                .setContentTitle("This is content title")
//                .setContentText("This is content text")
                .setStyle(NotificationCompat.BigTextStyle().bigText("Learn how to build notifications, send and sync data, and use voice actions. Get the official Android IDE and developer tools to build apps for Android."))
                .setStyle(NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(resources, R.drawable.girl)))
                .setSmallIcon(R.drawable.ic_menu_gallery)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.cherry))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build()
            val id = 1;
            manager.notify(id, notification)
        }

        btn_send_important_notification.setOnClickListener {
            val intent = Intent(this, Notification2Activity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, 0)
            val notification = NotificationCompat.Builder(this, channel2Id)
                .setContentTitle("This is content title")
//                .setContentText("This is content text")
                .setStyle(NotificationCompat.BigTextStyle().bigText("Learn how to build notifications, send and sync data, and use voice actions. Get the official Android IDE and developer tools to build apps for Android."))
                .setStyle(NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(resources, R.drawable.girl)))
                .setSmallIcon(R.drawable.ic_menu_gallery)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.cherry))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build()
            val id = 2;
            manager.notify(id, notification)
        }
    }
}