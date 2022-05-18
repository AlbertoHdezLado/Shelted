package com.example.android.shelted.Classes

import android.media.Image
import android.provider.ContactsContract
import android.security.identity.AccessControlProfile

data class User(
    var username: String ?= null,
    var email: String ?= null,
    var pass:String ?= null,
    var name: String ?= null,
    var birth: String ?= null,
    var shelter: Boolean ?= null) {
}
