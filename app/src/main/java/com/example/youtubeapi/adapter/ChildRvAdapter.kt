package com.example.youtubeapi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.R
import com.example.youtubeapi.enum.RecyclerViewType
import com.example.youtubeapi.model.PlaylistData
import com.example.youtubeapi.model.VideoModel
import com.squareup.picasso.Picasso

class ChildRvAdapter(): RecyclerView.Adapter<ChildRvAdapter.ViewHolder>() {

    private var playlist: MutableList<VideoModel>? = mutableListOf()
    private var recyclerViewType: RecyclerViewType = RecyclerViewType.HORIZONTAL

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        init {
            imageView = when (recyclerViewType) {
                RecyclerViewType.HORIZONTAL -> itemView.findViewById(R.id.iv_image_horizontal)
                RecyclerViewType.VERTICAL -> itemView.findViewById(R.id.iv_image_vertical)
            }
        }

        fun bind(data: VideoModel) {
            when (recyclerViewType) {
                RecyclerViewType.VERTICAL -> {
                    itemView.findViewById<TextView>(R.id.tv_video_title_vertical).text = data.items[0].snippet.title
                    itemView.findViewById<TextView>(R.id.tv_channel_title_vertical).text = data.items[0].snippet.channelTitle.split("-").dropLast(1).joinToString()
                }
                RecyclerViewType.HORIZONTAL -> {
                    itemView.findViewById<TextView>(R.id.tv_video_title_horizontal).text = data.items[0].snippet.title
                    itemView.findViewById<TextView>(R.id.tv_channel_title_horizontal).text = data.items[0].snippet.channelTitle.split("-").dropLast(1).joinToString()
                }
            }

        }
    }

    fun setPlaylist(videos: MutableList<VideoModel>, recyclerViewType: RecyclerViewType) {
        this.recyclerViewType = recyclerViewType
        playlist = videos
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = when(recyclerViewType) {
            RecyclerViewType.HORIZONTAL -> LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.rv_horizontal_item, viewGroup, false)
            RecyclerViewType.VERTICAL -> LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.rv_vertical_item, viewGroup, false)
        }


        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val element = playlist?.get(position)
        if (element != null) {
            viewHolder.bind(element)
        }

        when(recyclerViewType){
            RecyclerViewType.HORIZONTAL -> if (element != null) {
                Picasso.get().load(element.items.first().snippet.thumbnails.high.url).into(viewHolder.imageView)
            }
            RecyclerViewType.VERTICAL -> if (element != null) {
                Picasso.get().load(element.items.first().snippet.thumbnails.medium.url).into(viewHolder.imageView)
            }
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int = playlist?.size ?: 0
}