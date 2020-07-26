package com.example.brightcove.kotlin.videoplayer.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.brightcove.kotlin.videoplayer.data.source.VideoRepository
import com.example.brightcove.kotlin.videoplayer.injection.Injection
import com.example.brightcove.kotlin.videoplayer.utils.SingletonHolder

class ViewModelFactory(private val videoRepository: VideoRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object : SingletonHolder<ViewModelFactory, Context>({
        ViewModelFactory(Injection.provideVideoRepository(it))
    })

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PlayerListViewModel::class.java)) {
            PlayerListViewModel(videoRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}