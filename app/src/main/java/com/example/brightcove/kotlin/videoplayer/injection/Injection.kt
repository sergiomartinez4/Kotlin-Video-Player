package com.example.brightcove.kotlin.videoplayer.injection

import android.content.Context
import com.brightcove.player.edge.Catalog
import com.brightcove.player.event.EventEmitterImpl
import com.example.brightcove.kotlin.videoplayer.data.source.RemoteVideoRepository
import com.example.brightcove.kotlin.videoplayer.data.source.VideoRepository
import com.example.brightcove.kotlin.videoplayer.utils.IOUtil
import com.example.brightcove.kotlin.videoplayer.utils.PlaylistParser

/**
 * File located in the assets folder
 */
const val DEFAULT_PLAYER_PLAYLIST_FILE__PATH = "player_plalist.json"

class Injection {
    companion object {
        fun provideVideoRepository(context: Context): VideoRepository {
            val playlistString =
                IOUtil.readStringFromAssets(context, DEFAULT_PLAYER_PLAYLIST_FILE__PATH)
            val playlist = PlaylistParser.parsePlaylist(playlistString)
            val catalog: Catalog = Catalog.Builder(EventEmitterImpl(), playlist.accountId)
                .setPolicy(playlist.policyKey)
                .build()
            return VideoRepository(RemoteVideoRepository(playlist, catalog))
        }
    }
}