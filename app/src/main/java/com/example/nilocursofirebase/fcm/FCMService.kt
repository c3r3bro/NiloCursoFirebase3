package com.example.nilocursofirebase.fcm

import com.google.firebase.messaging.FirebaseMessagingService

class FCMService:FirebaseMessagingService() {
    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
    }
}