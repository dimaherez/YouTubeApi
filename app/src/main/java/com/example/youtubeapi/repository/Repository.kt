package com.example.youtubeapi.repository

import com.example.youtubeapi.retrofit.RetrofitInstance

object Repository {

    var PLAYLIST_ID = "" //"PL8ihJxcgXC2Bexo6RFfrysJXrC059jvTr" //"PLpcn8VknuwDRNj_4JDEH7oBFPpZ_Tr2mF"

    suspend fun getPlaylistTitleApi() = RetrofitInstance.api.getPlaylistTitle(PLAYLIST_ID)

    suspend fun getVideoIdApi(pageToken: String) = RetrofitInstance.api.getVideoId(PLAYLIST_ID, pageToken)

    suspend fun getVideoApi(id: String) = RetrofitInstance.api.getVideo(id)

}