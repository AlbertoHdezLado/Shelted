package com.example.android.shelted.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.shelted.Classes.Post
import com.example.android.shelted.R
import com.example.android.shelted.PostListAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.fragment_post_list.*

class postListFragment : Fragment() {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val collectionReference : CollectionReference = db.collection("posts")
    var postListAdapter: PostListAdapter ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreate(savedInstanceState)
        val v: View = inflater.inflate(R.layout.fragment_post_list, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val query : Query = collectionReference
        val firestoreRecyclerOptions: FirestoreRecyclerOptions<Post> = FirestoreRecyclerOptions.Builder<Post>()
            .setQuery(query, Post::class.java)
            .build()

        postListAdapter = PostListAdapter(firestoreRecyclerOptions)

        favouritesRecyclerView.layoutManager = LinearLayoutManager(activity)
        favouritesRecyclerView.adapter = postListAdapter
    }

    override fun onStart() {
        super.onStart()
        postListAdapter!!.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        postListAdapter!!.stopListening()
    }
}