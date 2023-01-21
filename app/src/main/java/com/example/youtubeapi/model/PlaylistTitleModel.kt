package com.example.youtubeapi.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class PlaylistTitleModel(
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
            @SerializedName("title")
            val title: String
        )
    }
}