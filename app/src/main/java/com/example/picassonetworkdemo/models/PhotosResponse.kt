package com.example.picassonetworkdemo.models

data class PhotoItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)
data class PhotosResponse(
    val photos: ArrayList<PhotoItem>,
)

