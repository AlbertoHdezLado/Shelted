package com.example.android.shelted.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shelted.Classes.Post
import com.example.android.shelted.R
import com.example.android.shelted.RecyclerAdapter
import com.google.firebase.firestore.*

class postListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var postArrayList: ArrayList<Post>
    private lateinit var myAdapter: RecyclerAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreate(savedInstanceState)

        val v: View = inflater.inflate(R.layout.fragment_post_list, container, false)

        recyclerView = v.findViewById(R.id.postListRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

        postArrayList = arrayListOf()

        myAdapter = RecyclerAdapter(postArrayList)

        EventChangedListener()
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)

        recyclerView = view.findViewById(R.id.postListRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

        postArrayList = arrayListOf()

        myAdapter = RecyclerAdapter(postArrayList)

        EventChangedListener()
    }

    private fun EventChangedListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("posts").addSnapshotListener(object: EventListener<QuerySnapshot> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?
            ) {
                if (error != null) {
                    Log.e("Firestore Error", error.message.toString())
                    return
                }

                for (dc: DocumentChange in value?.documentChanges!!) {
                    if(dc.type == DocumentChange.Type.ADDED) {
                        postArrayList.add(dc.document.toObject(Post::class.java))
                    }
                }
                myAdapter.notifyDataSetChanged()
            }
        })
    }


}