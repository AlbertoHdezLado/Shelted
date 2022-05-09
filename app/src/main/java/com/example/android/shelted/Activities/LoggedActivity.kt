package com.example.android.shelted.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.android.shelted.Fragments.*
import com.example.android.shelted.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoggedActivity : AppCompatActivity() {
    private val postListFragment = postListFragment()
    private val favouritesFragment = FavouritesFragment()
    private val messagesFragment = MessagesFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged)

        replaceFragment(postListFragment())

        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottom_navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.buttonMain -> {
                    replaceFragment(postListFragment)
                    true}
                R.id.buttonFavourites -> {
                    replaceFragment(favouritesFragment)
                    true}
                R.id.buttonMessages -> {
                    replaceFragment(messagesFragment)
                    true}
                R.id.buttonProfile -> {
                    replaceFragment(profileFragment)
                    true}
                else -> false
            }
        }

    }
}


// Extension function to replace fragment
fun AppCompatActivity.replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.logged_activity, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
}