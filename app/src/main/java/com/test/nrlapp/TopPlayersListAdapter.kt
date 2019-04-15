package com.test.nrlapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.test.data.model.ListItem
import com.test.data.net.URL
import kotlinx.android.synthetic.main.header_view.view.*
import kotlinx.android.synthetic.main.players_row_view.view.*

class TopPlayersListAdapter (val data: List<ListItem>, val listener: (teamId: Long, playerId: Long) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_LIST = 1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == VIEW_TYPE_HEADER) {
            val headerView = LayoutInflater.from(parent.context).inflate(R.layout.header_view, parent, false)
            HeaderViewHolder(headerView)
        } else {
            val playerView = LayoutInflater.from(parent.context).inflate(R.layout.players_row_view, parent, false)
            PlayerViewHolder(playerView)
        }

    }

    override fun getItemCount() = data.size

    override fun getItemViewType(position: Int): Int {
        val statType = data[position].statType
        return if(statType != null) VIEW_TYPE_HEADER else VIEW_TYPE_LIST
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        if(viewHolder is HeaderViewHolder) {
            viewHolder.bind(item.statType)
        } else {
            (viewHolder as PlayerViewHolder).bind(item)
        }
    }

    inner class PlayerViewHolder(view:View): RecyclerView.ViewHolder(view) {

        fun bind(item: ListItem) {
            with(itemView) {
                //Player from Team A
                item.playerA?.let {
                    Glide.with(ivPlayerAImage).load(URL.PLAYER_IMAGE_URL+it.id+".jpg")
                        .apply(RequestOptions.circleCropTransform())
                        .placeholder(R.drawable.ic_default_headshot).into(ivPlayerAImage)
                    val row1Text = "${it.short_name} (${it.jumper_number})"
                    tvPlayerARow1.text = row1Text
                    val row2Text = "${it.position} (${it.stat_value})"
                    tvPlayerARow2.text = row2Text
                    ivPlayerAImage.setOnClickListener {_ ->
                        it.teamId?.let {teamId ->
                            listener(teamId, it.id)
                        }
                    }
                }
                //Player from Team B
                item.playerB?.let {
                    Glide.with(ivPlayerBImage).load(URL.PLAYER_IMAGE_URL+it.id+".jpg")
                        .apply(RequestOptions.circleCropTransform())
                        .placeholder(R.drawable.ic_default_headshot).into(ivPlayerBImage)
                    val row1Text = "${it.short_name} (${it.jumper_number})"
                    tvPlayerBRow1.text = row1Text
                    val row2Text = "${it.position} (${it.stat_value})"
                    tvPlayerBRow2.text = row2Text
                    ivPlayerBImage.setOnClickListener {_ ->
                        it.teamId?.let {teamId ->
                            listener(teamId, it.id)
                        }
                    }
                }
            }
        }

    }

    inner class HeaderViewHolder(view:View): RecyclerView.ViewHolder(view) {

        fun bind(item: String?) {
            with(itemView) {
                tvStatType.text = item
            }
        }

    }


}