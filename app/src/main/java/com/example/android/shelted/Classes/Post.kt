package com.example.android.shelted.Classes

import android.location.Location
import android.media.Image

data class Post(
    var name:String = "",
    var age:Int = 0,
    var kind:String = "",
    var country: String = "",
    var city: String = "",
    var cp: String = "",
    var desciption:String,
    var mainImg:String = "",
    var path: String = "") {
}
