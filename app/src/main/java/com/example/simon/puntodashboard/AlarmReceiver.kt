package com.example.simon.puntodashboard

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
//        val background = Intent(context, PuntoDashboardService::class.java)
//        context.startService(background)

        val man = AppWidgetManager.getInstance(context)
        val ids = man.getAppWidgetIds(ComponentName(context, PuntoDashboardAppWidget::class.java))
        val updateIntent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
        updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
        context.sendBroadcast(updateIntent)


        val myIntent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent, 0)

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.add(Calendar.SECOND, 1) // first time
        val frequency = (1000).toLong() // in ms
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, frequency, pendingIntent)
    }

}