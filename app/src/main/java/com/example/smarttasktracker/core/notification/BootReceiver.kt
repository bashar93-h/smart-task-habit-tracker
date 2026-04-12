package com.example.smarttasktracker.core.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context, intent: Intent) {
        if(intent.action == Intent.ACTION_BOOT_COMPLETED) {
            // here we start a background job (using work manager)
            RescheduleReminderWorker.enqueue(context)
        }
    }
}