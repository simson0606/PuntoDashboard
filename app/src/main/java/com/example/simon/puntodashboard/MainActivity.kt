package com.example.simon.puntodashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.IntentFilter
import java.util.*


class MainActivity : Activity() {

    val _io_device : IODevice = IODevice(this, Constants.BASE_URL)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        enableServicePoll()
        if (intent != null && intent.action == Constants.WIDGET_BUTTON_PRESSED) {
            val buttonIndex = intent.getIntExtra(Constants.WIDGET_BUTTON, -1)
            if (buttonIndex == Constants.POWER_STEERING_INDEX) {
                _io_device.pulse("POWER_STEERING_OUT")
            }
            else if (buttonIndex == Constants.LOCK_INDEX) {
                _io_device.pulse("LOCK_OUT")
            }
            else if (buttonIndex == Constants.START_STOP_INDEX) {
                _io_device.pulse("START_STOP_OUT")
            }
            else if (buttonIndex == Constants.HAZARD_INDEX) {
                _io_device.pulse("HAZARD_OUT")
            }
            else if (buttonIndex == Constants.ASR_INDEX) {
                _io_device.pulse("ASR_OUT")
            }
            else if (buttonIndex == Constants.ECO_INDEX) {
                _io_device.pulse("ECO_OUT")
            }

//            val man = AppWidgetManager.getInstance(this)
//            val ids = man.getAppWidgetIds(ComponentName(this, PuntoDashboardAppWidget::class.java))
//            val updateIntent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
//            updateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
//            this.sendBroadcast(updateIntent)

            val startMain = Intent(Intent.ACTION_MAIN)
            startMain.addCategory(Intent.CATEGORY_HOME)
            startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(startMain)
            finish()
        }
    }

//    fun enableServicePoll(){
//        registerReceiver(AlarmReceiver(), IntentFilter())
//        val myIntent = Intent(this, AlarmReceiver::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0)
//
//        val alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        val calendar = Calendar.getInstance()
//        calendar.timeInMillis = System.currentTimeMillis()
//        calendar.add(Calendar.SECOND, 1) // first time
//        val frequency = (1000).toLong() // in ms
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, frequency, pendingIntent)
//    }



    fun buttonPowerSteeringClicked(view: View) {
        _io_device.pulse("POWER_STEERING_OUT")
    }

    fun buttonLockClicked(view: View) {
        _io_device.pulse("LOCK_OUT")
    }

    fun buttonStartStopClicked(view: View) {
        _io_device.pulse("START_STOP_OUT")
    }

    fun buttonHazardClicked(view: View) {
        _io_device.pulse("HAZARD_OUT")
    }

    fun buttonASRClicked(view: View) {
        _io_device.pulse("ASR_OUT")
    }

    fun buttonECOClicked(view: View) {
        _io_device.pulse("ECO_OUT")
    }

}
