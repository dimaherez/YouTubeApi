package com.example.youtubeapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.R
import com.example.youtubeapi.enum.RecyclerViewType
import com.example.youtubeapi.model.PlaylistData

class ParentRvAdapter: RecyclerView.Adapter<ParentRvAdapter.ViewHolder>() {
    private var playlistData: MutableList<PlaylistData> = mutableListOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(data: PlaylistData) {
            itemView.findViewById<TextView>(R.id.tv_playlist_title).text = data.playlistTitle

            val childMembersAdapter = ChildRvAdapter()
            val rv = itemView.findViewById<RecyclerView>(R.id.rv_child)

            when (data.recyclerViewType) {
                RecyclerViewType.VERTICAL -> rv.layoutManager = GridLayoutManager(itemView.context, 3)
                RecyclerViewType.HORIZONTAL -> rv.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            }

            rv.adapter = childMembersAdapter

            childMembersAdapter.setPlaylist(data.playlist, data.recyclerViewType)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.rv_parent_item, parent,
            false
        )

        return ViewHolder(view)
    }

    fun setPlaylistsData(playlistData: MutableList<PlaylistData>){
        this.playlistData = playlistData
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(playlistData[position])
    }

    override fun getItemCount(): Int {
        return playlistData.size
    }
}