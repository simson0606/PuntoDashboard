package com.example.simon.puntodashboard

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
  class PuntoDashboardAppWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {
         internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int)
         {
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.punto_dashboard_app_widget)
             views.setOnClickPendingIntent(R.id.buttonWidgetPowerSteering, getPendingIntent(context, Constants.POWER_STEERING_INDEX))
             views.setOnClickPendingIntent(R.id.buttonWidgetLock, getPendingIntent(context, Constants.LOCK_INDEX))
             views.setOnClickPendingIntent(R.id.buttonWidgetStartStop, getPendingIntent(context, Constants.START_STOP_INDEX))
             views.setOnClickPendingIntent(R.id.buttonWidgetHazard, getPendingIntent(context, Constants.HAZARD_INDEX))
             views.setOnClickPendingIntent(R.id.buttonWidgetASR, getPendingIntent(context, Constants.ASR_INDEX))
             views.setOnClickPendingIntent(R.id.buttonWidgetECO, getPendingIntent(context, Constants.ECO_INDEX))

//             val io_device = IODevice(context, Constants.BASE_URL)
//             io_device.read("ASR_IN", fun(on: Boolean) {
//                 val colorString = if (on) {Constants.BUTTON_TEXT_YELLOW }else {Constants.BUTTON_TEXT_WHITE}
//                 views.setTextColor(R.id.buttonWidgetASR, Color.parseColor(colorString))
//                 appWidgetManager.updateAppWidget(appWidgetId, views)
//             })
//
//             io_device.read("ECO_IN", fun(on: Boolean) {
//                 val colorString = if (on) {Constants.BUTTON_TEXT_GREEN }else {Constants.BUTTON_TEXT_WHITE}
//                 views.setTextColor(R.id.buttonWidgetECO, Color.parseColor(colorString))
//                 appWidgetManager.updateAppWidget(appWidgetId, views)
//             })
//
//             io_device.read("LOCK_IN", fun(on: Boolean) {
//                 val colorString = if (on) {Constants.BUTTON_TEXT_RED }else {Constants.BUTTON_TEXT_WHITE}
//                 views.setTextColor(R.id.buttonWidgetLock, Color.parseColor(colorString))
//                 appWidgetManager.updateAppWidget(appWidgetId, views)
//             })
//
//             io_device.read("START_STOP_IN", fun(on: Boolean) {
//                 val colorString = if (on) {Constants.BUTTON_TEXT_YELLOW }else {Constants.BUTTON_TEXT_WHITE}
//                 views.setTextColor(R.id.buttonWidgetStartStop, Color.parseColor(colorString))
//                 appWidgetManager.updateAppWidget(appWidgetId, views)
//             })
//
//             io_device.read("AIRBAG_IN", fun(on: Boolean) {
//                 val colorString = if (on) {Constants.BUTTON_TEXT_YELLOW }else {Constants.BUTTON_TEXT_WHITE}
//                 views.setTextColor(R.id.indicatorWidgetAirbag, Color.parseColor(colorString))
//                 appWidgetManager.updateAppWidget(appWidgetId, views)
//             })
             appWidgetManager.updateAppWidget(appWidgetId, views)
        }

        private fun getPendingIntent(context: Context, value: Int): PendingIntent {
            val intent = Intent(context, MainActivity::class.java)
            intent.action = Constants.WIDGET_BUTTON_PRESSED
            intent.putExtra(Constants.WIDGET_BUTTON, value)
            return PendingIntent.getActivity(context, value, intent, 0)
        }
    }
}

