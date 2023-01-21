package com.example.youtubeapi.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class VideoIdModel(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("nextPageToken")
    val nextPageToken: String
) {
    @Keep
    data class Item(
        @SerializedName("contentDetails")
        val contentDetails: ContentDetails
    ) {
        @Keep
        data class ContentDetails(
            @SerializedName("videoId")
            val videoId: String
        )
    }
}