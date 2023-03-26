package com.example.nilocursofirebase.fcm

import android.util.Log
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.nilocursofirebase.Constants
import com.google.firebase.messaging.FirebaseMessagingService

class FCMService:FirebaseMessagingService() {
    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)

        registerNewTokenLocal(newToken)
    }

    private fun registerNewTokenLocal(newToken: String){
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)

        preferences.edit {
            putString(Constants.PROP_TOKEN, newToken)
                .apply()
        }

        Log.i("new token", newToken)
    }
}