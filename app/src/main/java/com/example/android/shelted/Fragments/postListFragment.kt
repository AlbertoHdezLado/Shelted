package com.example.android.shelted.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shelted.Classes.Post
import com.example.android.shelted.R
import com.example.android.shelted.RecyclerAdapter
import kotlinx.android.synthetic.main.fragment_post_list.*

class postListFragment : Fragment() {

    private lateinit var postRecyclerView: RecyclerView
    private lateinit var postArrayList: ArrayList<Post>
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment




        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        postListRecyclerView.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            //layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            //adapter = RecyclerAdapter()

            layoutManager = LinearLayoutManager(requireContext())
            postListRecyclerView.layoutManager
            adapter = RecyclerAdapter()
            postListRecyclerView.adapter = adapter
        }
    }


}