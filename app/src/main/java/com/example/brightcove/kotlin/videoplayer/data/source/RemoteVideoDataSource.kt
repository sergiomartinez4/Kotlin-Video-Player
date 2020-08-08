package com.example.brightcove.kotlin.videoplayer.data.source

import com.brightcove.player.edge.Catalog
import com.brightcove.player.edge.VideoListener
import com.brightcove.player.model.Video
import com.example.brightcove.kotlin.videoplayer.data.model.CatalogAsset
import com.example.brightcove.kotlin.videoplayer.data.model.PlayerPlaylist
import com.example.brightcove.kotlin.videoplayer.utils.title
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class RemoteVideoDataSource(val playlist: PlayerPlaylist, val catalog: Catalog) : VideoDataSource {
    override suspend fun getAllVideos(): List<Video> {
        val videoPlaylist = mutableListOf<Video>()
        withContext(Dispatchers.IO) {
            val countDownLatch = CountDownLatch(playlist.catalogAssetList.size)

            withContext(Dispatchers.Main) {
                for (catalogAsset: CatalogAsset in playlist.catalogAssetList) {
                    catalog.findVideoByID(catalogAsset.id, object : VideoListener() {
                        override fun onVideo(video: Video) {
                            video.title = catalogAsset.title
                            videoPlaylist.add(video)
                            countDownLatch.countDown();
                        }
                    })
                }
            }
            countDownLatch.await(5, TimeUnit.SECONDS)
        }
        return videoPlaylist
    }
}