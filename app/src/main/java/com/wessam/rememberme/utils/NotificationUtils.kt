package com.wessam.rememberme.utils

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import com.wessam.rememberme.receiver.AlarmReceiver
import java.util.*

class NotificationUtils {

    fun setNotification(timeInDays: Long, activity: Activity) {

        if (timeInDays > 0) {

            val alarmManager = activity.getSystemService(Activity.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(activity.applicationContext, AlarmReceiver::class.java)

            alarmIntent.putExtra("reason", "notification")
            alarmIntent.putExtra("timestamp", timeInDays)

            val calendar = Calendar.getInstance()

            calendar.timeInMillis = timeInDays

         //   calendar.set(Calendar.SECOND, 0)

            val pendingIntent = PendingIntent.getBroadcast(activity, 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT)

             alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

            //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, 20000, AlarmManager.INTERVAL_DAY, pendingIntent)
        }

    }
}