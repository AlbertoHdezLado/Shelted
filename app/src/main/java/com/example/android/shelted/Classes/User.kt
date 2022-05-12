package com.example.android.shelted.Classes

import android.media.Image
import android.provider.ContactsContract
import android.security.identity.AccessControlProfile

class User(
    var username: String,
    var email: String,
    var pass:String,
    var name: String,
    var birth: String,
    var shelter: Boolean) {
}
