package com.example.brightcove.kotlin.videoplayer

import android.os.Bundle
import android.util.Log
import com.brightcove.player.edge.Catalog
import com.brightcove.player.edge.VideoListener
import com.brightcove.player.model.Video
import com.brightcove.player.view.BrightcoveExoPlayerVideoView
import com.brightcove.player.view.BrightcovePlayer


class MainActivity : BrightcovePlayer() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // When extending the BrightcovePlayer, we must assign the brightcoveVideoView before
        // entering the superclass. This allows for some stock video player lifecycle
        // management.  Establish the video object and use it's event emitter to get important
        // notifications and to control logging.
        setContentView(R.layout.activity_main)
        brightcoveVideoView =
            findViewById<BrightcoveExoPlayerVideoView>(R.id.brightcove_video_view)

        super.onCreate(savedInstanceState)

        // Get the event emitter from the SDK and create a catalog request to fetch a video from the
        // Brightcove Edge service, given a video id, an account id and a policy key.
        // Get the event emitter from the SDK and create a catalog request to fetch a video from the
        // Brightcove Edge service, given a video id, an account id and a policy key.
        val eventEmitter = brightcoveVideoView.eventEmitter
        val catalog =
            Catalog(eventEmitter, getString(R.string.account), getString(R.string.policy))

        catalog.findVideoByID(getString(R.string.videoId), object : VideoListener() {
            // Add the video found to the queue with add().
            // Start playback of the video with start().
            override fun onVideo(video: Video) {
                Log.v(TAG, "onVideo: video = $video")
                brightcoveVideoView.add(video)
                brightcoveVideoView.start()
            }
        })
    }
}
