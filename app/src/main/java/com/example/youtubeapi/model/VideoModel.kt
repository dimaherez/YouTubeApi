package com.example.youtubeapi.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class VideoModel(
    @SerializedName("items")
    val items: List<Item>
) {
    @Keep
    data class Item(
        @SerializedName("snippet")
        val snippet: Snippet
    ) {
        @Keep
        data class Snippet(
            @SerializedName("channelTitle")
            val channelTitle: String,
            @SerializedName("thumbnails")
            val thumbnails: Thumbnails,
            @SerializedName("title")
            val title: String
        ) {
            @Keep
            data class Thumbnails(
                @SerializedName("high")
                val high: High,
                @SerializedName("medium")
                val medium: Medium
            ) {
                @Keep
                data class High(
                    @SerializedName("height")
                    val height: Int,
                    @SerializedName("url")
                    val url: String,
                    @SerializedName("width")
                    val width: Int
                )

                @Keep
                data class Medium(
                    @SerializedName("height")
                    val height: Int,
                    @SerializedName("url")
                    val url: String,
                    @SerializedName("width")
                    val width: Int
                )
            }
        }
    }
}