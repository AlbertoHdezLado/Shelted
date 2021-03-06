package com.example.android.shelted.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.android.shelted.R
import com.example.android.shelted.Fragments.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = supportActionBar
        actionBar!!.hide()
        setContentView(R.layout.activity_main)

        // Initially display the first fragment in main activity
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.main_activity,LoginFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onRestart() {
        super.onRestart()
        super.onDestroy()
    }
}