package com.example.brightcove.kotlin.videoplayer.data.source

import com.brightcove.player.model.Video

class VideoRepository(private val remoteVideoDataSource: VideoDataSource) : VideoDataSource {

    override suspend fun getAllVideos(): List<Video> = remoteVideoDataSource.getAllVideos()
}