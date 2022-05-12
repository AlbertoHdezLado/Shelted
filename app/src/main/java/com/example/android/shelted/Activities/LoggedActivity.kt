package com.example.android.shelted.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.android.shelted.Fragments.*
import com.example.android.shelted.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LoggedActivity : AppCompatActivity() {
    private val postListFragment = postListFragment()
    private val favouritesFragment = FavouritesFragment()
    private val messagesFragment = messageListFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged)

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.logged_activity,postListFragment())
        transaction.addToBackStack(null)
        transaction.commit()
        val button_publicar: FloatingActionButton = findViewById<FloatingActionButton>(R.id.button_publicar)
        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)


        bottom_navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.buttonMain -> {
                    val fragmentManager = supportFragmentManager
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.logged_activity,postListFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true}
                R.id.buttonFavourites -> {
                    val fragmentManager = supportFragmentManager
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.logged_activity,favouritesFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true}
                R.id.buttonMessages -> {
                    val fragmentManager = supportFragmentManager
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.logged_activity,messagesFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true}
                R.id.buttonProfile -> {
                    val fragmentManager = supportFragmentManager
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.logged_activity,profileFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true}
                else -> false
            }

        }
        button_publicar.setOnClickListener {
            startActivity(Intent(this, Publish_Activity::class.java))
        }


    }
}