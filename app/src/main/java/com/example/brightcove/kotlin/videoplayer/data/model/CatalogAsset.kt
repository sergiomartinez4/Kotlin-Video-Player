package com.example.brightcove.kotlin.videoplayer.data.model

/**
 * Represents the Video metadata we need to know before making the request to the Brightcove Catalog.
 */
data class CatalogAsset(val type: Type, val id: String, val description: String = "") {
    enum class Type {
        VIDEO,
        PLAYLIST
    }
}