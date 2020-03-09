package com.icynets.reminder2020

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.room.Room
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast

class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        //Retrieve data from intent
        val uid = intent.getIntExtra("uid", 0)
        val text = intent.getStringExtra("message")

        //context.toast(text!!)
        //Pass the notication to the MainActivity where show notification function is
        MainActivity.showNotification(context,text!!)

        //Remove reminder from db after it is shown
        doAsync {
            val db = Room.databaseBuilder(context, AppDatabase::class.java,"reminders")
                .build()
            db.reminderDao().delete(uid)
            db.close()
        }



    }





}