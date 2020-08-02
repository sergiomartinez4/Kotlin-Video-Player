package com.example.brightcove.kotlin.videoplayer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VideoListFragment : Fragment() {
    companion object {
        val TAG = VideoListFragment::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "VideoListFragment onCreate: $this")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        context?.let {
            val playerListViewModel = VideoListActivity.obtainViewModel(it as FragmentActivity)
            val videoListAdapter = VideoListViewAdapter(playerListViewModel, viewLifecycleOwner)
            // Set the adapter
            if (view is RecyclerView) {
                with(view) {
                    layoutManager = LinearLayoutManager(it)
                    adapter = videoListAdapter
                }
            }
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "VideoListFragment onDestroy: $this")
    }

}