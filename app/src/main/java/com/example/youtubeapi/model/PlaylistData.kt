package com.example.youtubeapi.model

import com.example.youtubeapi.enum.RecyclerViewType

data class PlaylistData(var playlist: MutableList<VideoModel> = mutableListOf(), var playlistTitle: String = "", var recyclerViewType: RecyclerViewType, var id: String)
