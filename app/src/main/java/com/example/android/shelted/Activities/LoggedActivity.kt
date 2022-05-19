package com.example.android.shelted.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.example.android.shelted.Fragments.*
import com.example.android.shelted.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_logged.*

class LoggedActivity : AppCompatActivity() {
    private val postListFragment = postListFragment()
    private val favouritesFragment = FavouritesFragment()
    private val messagesFragment = messageListFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = supportActionBar
        actionBar!!.hide()
        setContentView(R.layout.activity_logged)

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.logged_activity,postListFragment())
        transaction.addToBackStack(null)
        transaction.commit()

        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottom_navigation.background = null

        barra_inferior()
        publish()
    }

    private fun publish(){
        publicar_button.setOnClickListener {
            val intent = Intent(this, PublishActivity::class.java)
            startActivity(intent)
        }
    }

    private fun barra_inferior(){
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

        /*add_post.setOnClickListener {
            startActivity(Intent(this,PublishActivity::class.java ))
        }*/

    }
}