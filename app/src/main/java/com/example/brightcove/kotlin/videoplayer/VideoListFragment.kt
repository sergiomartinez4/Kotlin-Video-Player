package com.example.brightcove.kotlin.videoplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brightcove.kotlin.videoplayer.viewmodels.PlayerListViewModel


class VideoListFragment(private val playerListViewModel: PlayerListViewModel) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        context?.let {
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


}