package com.example.android.shelted.Classes

import com.google.firebase.firestore.IgnoreExtraProperties


@IgnoreExtraProperties
class Post(
    var id:String ?= null,
    var name:String ?= null,
    var age:Int ?= null,
    var kind:String ?= null,
    var country: String ?= null,
    var city: String ?= null,
    var cp: String ?= null,
    var description:String ?= null,
    var mainImg:String ?= null,
    var path: String ?= null,
    var shelter: String ?= null
)



