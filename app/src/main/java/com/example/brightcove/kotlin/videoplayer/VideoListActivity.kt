package com.example.brightcove.kotlin.videoplayer

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.brightcove.player.model.Video
import com.example.brightcove.kotlin.videoplayer.viewmodels.PlayerListViewModel
import com.example.brightcove.kotlin.videoplayer.viewmodels.ViewModelFactory

class VideoListActivity : AppCompatActivity(), VideoListActions {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_list)
        setupFragment()

        val viewModel = obtainViewModel(this)
        viewModel.videoToLoad.observe(this, Observer { openVideoPlayer(it) })
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

    override fun openVideoPlayer(video: Video) {
        val intent = Intent(this, VideoPlayerActivity::class.java)
        intent.putExtra(VideoPlayerActivity.VIDEO_INTENT_DATA, video as Parcelable)
        startActivity(intent)
    }
}

interface VideoListActions {
    fun openVideoPlayer(video: Video)
}