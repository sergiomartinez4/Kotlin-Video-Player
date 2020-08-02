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
import com.bumptech.glide.Glide
import com.example.brightcove.kotlin.videoplayer.viewmodels.PlayerListViewModel

class VideoListViewAdapter(
    playerListViewModel: PlayerListViewModel,
    lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<VideoListViewAdapter.ViewHolder>() {

    init {
        playerListViewModel.videoList.observe(lifecycleOwner, Observer { videoList = it })
        playerListViewModel.loadVideos()
    }

    private val _videoList = mutableListOf<Video>()
    private var videoList: List<Video>
        get() = _videoList
        private set(value) {
            _videoList.clear()
            _videoList.addAll(value)
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.video_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = videoList[position]
        holder.videoNameView.text = video.name
        holder.videoDescriptionView.text = video.longDescription ?: video.description
        val poster = video.posterImage
        Glide.with(holder.videoThumbnailImageView)
            .load(poster.toString())
            .centerCrop()
            .placeholder(R.drawable.ic_play)
            .into(holder.videoThumbnailImageView)
    }

    override fun getItemCount(): Int = videoList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val videoNameView: TextView = view.findViewById(R.id.video_name)
        val videoDescriptionView: TextView = view.findViewById(R.id.video_description)
        val videoThumbnailImageView: ImageView = view.findViewById(R.id.video_thumbnail)
    }
}