package com.example.youtubeapi

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.adapter.ParentRvAdapter
import com.example.youtubeapi.databinding.FragmentVideosBinding
import com.example.youtubeapi.enum.RecyclerViewType
import com.example.youtubeapi.model.PlaylistData
import com.example.youtubeapi.viewmodel.MainViewModel

class VideosFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentVideosBinding

    private val firstItem = PlaylistData(recyclerViewType = RecyclerViewType.HORIZONTAL, id = "PL8ihJxcgXC2Bexo6RFfrysJXrC059jvTr")
    private val secondItem = PlaylistData(recyclerViewType = RecyclerViewType.VERTICAL, id = "PL8ihJxcgXC2Bexo6RFfrysJXrC059jvTr")
    private var playlistData = mutableListOf<PlaylistData>(firstItem, secondItem)
    private val playlistLiveData = MutableLiveData<MutableList<PlaylistData>>()

    private lateinit var parentRvAdapter: ParentRvAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideosBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        initVM()

    }


    @SuppressLint("NotifyDataSetChanged")
    private fun initVM() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

//        viewModel.playlistTitle.observe(viewLifecycleOwner) {
//
//            playlistData.last().playlistTitle =
//                it.items.first().snippet.title
//
//        }

        viewModel.videosLiveData.observe(viewLifecycleOwner) {

            firstItem.playlistTitle =viewModel.playlistTitle.value!!.items.first().snippet.title
            secondItem.playlistTitle = viewModel.playlistTitle.value!!.items.first().snippet.title

            firstItem.playlist = it
            secondItem.playlist = it


            parentRvAdapter.setPlaylistsData(
                playlistData
            )
            parentRvAdapter.notifyDataSetChanged()
        }



        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.pbLoading.visibility = View.VISIBLE
            } else {
                binding.pbLoading.visibility = View.GONE
            }
        }


    }

    private fun initRV() {
        binding.rvParent.layoutManager = LinearLayoutManager(context)
        parentRvAdapter = ParentRvAdapter()
        binding.rvParent.adapter = parentRvAdapter

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addPlaylist(id: String) {
        playlistData.add(PlaylistData(recyclerViewType = RecyclerViewType.VERTICAL, id = id))
    }

}