package com.example.youtubeapi.retrofit

import com.example.youtubeapi.model.PlaylistTitleModel
import com.example.youtubeapi.model.VideoIdModel
import com.example.youtubeapi.model.VideoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("playlists?key=AIzaSyAgQZGoC7JZtI4HyJvVM03MLUSSQ_-IQLY&fields=items(snippet(title))&part=snippet,contentDetails")
    suspend fun getPlaylistTitle(
        @Query("id") id: String
    ): Response<PlaylistTitleModel>

    @GET("playlistItems?key=AIzaSyAgQZGoC7JZtI4HyJvVM03MLUSSQ_-IQLY&fields=nextPageToken,items(contentDetails(videoId))&part=contentDetails, snippet")
    suspend fun getVideoId(
        @Query("playlistId") playlistId: String,
        @Query("pageToken") pageToken: String
    ): Response<VideoIdModel>

    @GET("videos?key=AIzaSyAgQZGoC7JZtI4HyJvVM03MLUSSQ_-IQLY&part=snippet&fields=items(snippet(title,channelTitle,thumbnails(medium, high)))")
    suspend fun getVideo(
        @Query("id") id: String
    ): Response<VideoModel>

}