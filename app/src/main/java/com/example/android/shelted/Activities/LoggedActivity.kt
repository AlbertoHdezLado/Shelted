package com.example.android.shelted.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.ui.setupWithNavController
import com.example.android.shelted.Fragments.*
import com.example.android.shelted.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_logged.*

class LoggedActivity : AppCompatActivity() {
    private val postListFragment = postListFragment()
    private val favouritesFragment = FavouritesFragment()
    private val messageListFragment = messageListFragment()
    private val profileFragment = ProfileFragment()

    @SuppressLint("CutPasteId")
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

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottom_navigation.background = null
        bottom_navigation.menu.getItem(2).isEnabled = false


        bottom_navigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.buttonMain -> {
                    fab.show()
                    val fragmentManager = supportFragmentManager
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.logged_activity,postListFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true}
                R.id.buttonFavourites -> {
                    fab.show()
                    val fragmentManager = supportFragmentManager
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.logged_activity,favouritesFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true}
                R.id.buttonMessages -> {
                    fab.hide()
                    val fragmentManager = supportFragmentManager
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.logged_activity,messageListFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true}
                R.id.buttonProfile -> {
                    fab.show()
                    val fragmentManager = supportFragmentManager
                    val transaction = fragmentManager.beginTransaction()
                    transaction.replace(R.id.logged_activity,profileFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true}
                else -> false
            }
        }

        fab.setOnClickListener {
            startActivity(Intent(this,PublishActivity::class.java ))
        }

        val states = arrayOf(
            intArrayOf(android.R.attr.state_checked),
            intArrayOf(-android.R.attr.state_checked))

        val colors = intArrayOf(
            Color.parseColor("#116B0A"),
            Color.parseColor("#D3D3D3"))

        bottom_navigation.itemIconTintList = ColorStateList(states, colors)

    }

    override fun onBackPressed() {
        var f: Fragment? = supportFragmentManager.findFragmentById(R.id.logged_activity)
        if (f !is postFragment && f !is ShelterProfileFragment)
            moveTaskToBack(true)
        else
            super.onBackPressed()
    }
}