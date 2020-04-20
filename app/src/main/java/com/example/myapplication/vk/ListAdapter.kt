package com.example.myapplication.vk

import `class`.getPrettyTime
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import audio.Audio
import audio.Track
import com.bumptech.glide.Glide
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_mini_player.view.*


class ListAdapter(var items: List<Track>, val callback: Callback) : RecyclerView.Adapter<ListAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_vk_track, parent, false))

    override fun getItemCount() = items.size
    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position])
    }
    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val firstName = itemView.findViewById<TextView>(R.id.playerTitleTrack)
        private val lastName = itemView.findViewById<TextView>(R.id.playerArtistTrack)
        private val duration = itemView.findViewById<TextView>(R.id.playerDurationTrack)
        private val ico       = itemView.findViewById<ImageView>(R.id.playerIcoTrack)


        fun bind(item: Track) {
            firstName.text = item.title
            lastName.text = item.artist
            duration.text = getPrettyTime(item.duration);
            ico.background
            Glide
                .with(itemView)
                .load(item.urlImage)
                .into(ico)



            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) callback.onItemClicked(items[adapterPosition])
            }
        }
    }
    interface Callback {
        fun onItemClicked(item: Track)
    }
}