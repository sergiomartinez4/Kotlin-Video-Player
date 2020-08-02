package com.example.brightcove.kotlin.videoplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.brightcove.kotlin.videoplayer.viewmodels.PlayerListViewModel
import com.example.brightcove.kotlin.videoplayer.viewmodels.ViewModelFactory

class VideoListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_list)
        setupFragment()
    }

    private fun setupFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, VideoListFragment())
            .commitNow()
    }

    companion object {
        fun obtainViewModel(fragmentActivity: FragmentActivity): PlayerListViewModel {
            val viewModelFactory = ViewModelFactory.getInstance(fragmentActivity)
            return ViewModelProvider(
                fragmentActivity,
                viewModelFactory
            ).get(PlayerListViewModel::class.java)
        }
    }
}