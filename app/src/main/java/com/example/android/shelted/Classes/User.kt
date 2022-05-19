package com.example.android.shelted.Classes

data class User(
    var email: String ?= null,
    var pass:String ?= null,
    var name: String ?= null,
    var birth: String ?= null,
    var shelter: Boolean ?= null) {
}
