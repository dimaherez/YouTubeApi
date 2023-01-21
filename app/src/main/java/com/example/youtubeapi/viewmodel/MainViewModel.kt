package com.example.youtubeapi.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapi.model.PlaylistData
import com.example.youtubeapi.model.PlaylistTitleModel
import com.example.youtubeapi.model.VideoIdModel
import com.example.youtubeapi.model.VideoModel
import com.example.youtubeapi.repository.Repository
import kotlinx.coroutines.*
import retrofit2.Response


class MainViewModel : ViewModel() {

    val playlistTitle = MutableLiveData<PlaylistTitleModel>()
    var videos: MutableList<VideoModel> = mutableListOf()
    val videosLiveData = MutableLiveData<MutableList<VideoModel>>()

    val loading = MutableLiveData<Boolean>()

    private lateinit var job: Job

    init {
        makeApiCall("PL8ihJxcgXC2Bexo6RFfrysJXrC059jvTr")
    }

    fun makeApiCall(id: String) {
        Repository.PLAYLIST_ID = id
        getPlaylistTitle()
        getVideoId()
    }

    private fun getPlaylistTitle() {
        loading.postValue(true)
        job = CoroutineScope(Dispatchers.IO).launch {
            coroutineScope {
                val response = Repository.getPlaylistTitleApi()

                if (response.isSuccessful) {
                    playlistTitle.postValue(response.body())
                }
            }

            loading.postValue(false)
        }

    }

    private fun getVideoId() {
        loading.postValue(true)

        job = CoroutineScope(Dispatchers.IO).launch {
            var pageToken: String? = ""
            do {
                coroutineScope {
                    val response: Response<VideoIdModel> = Repository.getVideoIdApi(pageToken!!)
                    val responseBody = response.body()!!

                    if (response.isSuccessful) {
                        for (i in 0 until responseBody.items.size) {
                            getVideo(responseBody.items[i].contentDetails.videoId)
                        }

                    }

                    pageToken = responseBody.nextPageToken

                }
            } while (pageToken != null)

            loading.postValue(false)


        }
    }

    private suspend fun getVideo(id: String) {
        loading.postValue(true)
        job = CoroutineScope(Dispatchers.IO).launch {
            coroutineScope {
                val response = Repository.getVideoApi(id)
                val responseBody = response.body()!!

                if (response.isSuccessful) {
                    videos.add(responseBody)
                }
            }
            videosLiveData.postValue(videos)

            loading.postValue(false)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}