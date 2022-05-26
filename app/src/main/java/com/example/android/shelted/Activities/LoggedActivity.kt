package com.example.android.shelted.Activities

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.ui.setupWithNavController
import com.example.android.shelted.Fragments.*
import com.example.android.shelted.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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

        var shelter = false

        val auth = FirebaseAuth.getInstance()
        val email = auth.currentUser?.email
        val db = FirebaseFirestore.getInstance()
        val usersRef = db.collection("users")
        usersRef.document(email!!).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document.exists()) {
                    shelter = document.getBoolean("shelter")!!
                } else {
                    Log.d(ContentValues.TAG, "The document doesn't exist.")
                }
            } else {
                task.exception?.message?.let {
                    Log.d(ContentValues.TAG, "Error in task")
                }
            }
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottom_navigation.background = null
        bottom_navigation.menu.getItem(2).isEnabled = false


        bottom_navigation.setOnItemSelectedListener { item ->
            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            when (item.itemId) {
                R.id.buttonMain -> {
                    fab.show()
                    transaction.replace(R.id.logged_activity,postListFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true}
                R.id.buttonFavourites -> {
                    fab.show()
                    transaction.replace(R.id.logged_activity,favouritesFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true}
                R.id.buttonMessages -> {
                    fab.hide()
                    transaction.replace(R.id.logged_activity,messageListFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true}
                R.id.buttonProfile -> {
                    fab.show()
                    transaction.replace(R.id.logged_activity,profileFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                    true}
                else -> false
            }
        }

        fab.setOnClickListener {
            if (shelter)
                startActivity(Intent(this,PublishActivity::class.java ))
            else
                Toast.makeText(this, "You need to be a shelter to publish a post!", Toast.LENGTH_SHORT).show()
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