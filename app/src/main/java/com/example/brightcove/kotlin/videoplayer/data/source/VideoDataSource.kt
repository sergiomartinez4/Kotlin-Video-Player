package com.example.brightcove.kotlin.videoplayer.data.source

import com.brightcove.player.model.Video

interface VideoDataSource {
    suspend fun getAllVideos():List<Video>
}