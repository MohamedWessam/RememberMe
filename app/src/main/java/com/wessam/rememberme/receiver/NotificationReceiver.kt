package com.wessam.rememberme.receiver

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.PendingIntent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import com.wessam.rememberme.R

class NotificationReceiver : BroadcastReceiver() {

    private lateinit var mNotification: Notification

    companion object {
        const val CHANNEL_ID = "com.rememberme.notification.CHANNEL_ID"
        const val CHANNEL_NAME = "RememberMe Notification"
    }

    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent?.action.equals("com.wessam.rememberme.ALARM")) {

            val mNotificationId = NotificationId.getId()

            val name = intent?.getStringExtra("NAME")
            val phone = intent?.getStringExtra("PHONE")

            val msg = context?.resources?.getString(R.string.notification_txt1) + " " + name + " " +
                    context?.resources?.getString(R.string.notification_txt2)

            val title = context?.resources?.getString(R.string.notification_title) + " " + name

            expandableNotification(context, title, msg, mNotificationId, phone)

        }

    }


    @SuppressLint("NewApi")
    private fun createChannel(context: Context?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notificationChannel =
                NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableVibration(true)
            notificationChannel.setShowBadge(true)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.parseColor("#e8334a")
            notificationChannel.description = "notification_channel_description"
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC

            notificationManager.createNotificationChannel(notificationChannel)
        }
    }


    private fun expandableNotification(
        context: Context?,
        title: String?,
        message: String?,
        id: Int,
        phone: String?
    ) {

        createChannel(context)

        var notificationManager: NotificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val res = context.resources
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.data = Uri.parse("tel:$phone")

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            callIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            mNotification = Notification.Builder(context, CHANNEL_ID)

                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_other)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .setContentTitle(title)
                .setStyle(Notification.BigTextStyle().bigText(message))
                .setContentText(message).build()
        } else {

            mNotification = Notification.Builder(context)

                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_other)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle(title)
                .setStyle(Notification.BigTextStyle().bigText(message))
                .setSound(uri)
                .setContentText(message).build()
        }

        notificationManager.notify(id, mNotification)
    }

}