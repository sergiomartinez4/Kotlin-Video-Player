package com.example.brightcove.kotlin.videoplayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.brightcove.player.model.Video
import com.brightcove.player.util.StringUtil
import com.bumptech.glide.Glide
import com.example.brightcove.kotlin.videoplayer.viewmodels.PlayerListViewModel

class VideoListViewAdapter(
    private val playerListViewModel: PlayerListViewModel,
    lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<VideoListViewAdapter.ViewHolder>() {

    init {
        playerListViewModel.videoList.observe(lifecycleOwner, Observer { notifyDataSetChanged() })
        playerListViewModel.loadVideos(false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.video_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = playerListViewModel.videoList.value?.get(position) ?: Video(mapOf())
        holder.videoNameView.text = video.name
        holder.videoDurationView.text = StringUtil.stringForTime(video.duration.toLong())
        holder.videoThumbnailImageView.setOnClickListener { playerListViewModel.openVideo(video) }
        val poster = video.posterImage
        Glide.with(holder.videoThumbnailImageView)
            .load(poster.toString())
            .centerCrop()
            .placeholder(R.drawable.ic_play)
            .into(holder.videoThumbnailImageView)
    }

    override fun getItemCount(): Int = playerListViewModel.videoList.value?.size ?: 0

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val videoNameView: TextView = view.findViewById(R.id.video_name)
        val videoDurationView: TextView = view.findViewById(R.id.video_duration)
        val videoThumbnailImageView: ImageView = view.findViewById(R.id.video_thumbnail)
    }
}