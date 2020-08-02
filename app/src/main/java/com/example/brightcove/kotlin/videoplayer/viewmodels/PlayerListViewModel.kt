package com.example.brightcove.kotlin.videoplayer.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brightcove.player.model.Video
import com.example.brightcove.kotlin.videoplayer.data.source.VideoRepository
import kotlinx.coroutines.launch

class PlayerListViewModel(val videoRepository: VideoRepository) : ViewModel() {

    private val _videoList = MutableLiveData<List<Video>>()
    val videoList: LiveData<List<Video>> = _videoList

    fun loadVideos(forceUpdate: Boolean) {
        val mustUpdate = forceUpdate || _videoList.value?.isEmpty() ?: true
        if (mustUpdate) {
            viewModelScope.launch {
                _videoList.postValue(videoRepository.getAllVideos())
            }
        }
    }
}