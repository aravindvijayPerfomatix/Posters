package com.perfomatix.posters.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.perfomatix.posters.R
import com.perfomatix.posters.model.Content
import com.perfomatix.posters.view.adapter.viewholder.PosterViewHolder

/**
* Adapter for populating poster list
*/
class PosterAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mContextObject: Context = context
    private var mListOfPoster = mutableListOf<Content>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PosterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.poster_item, parent, false)
        ).apply {
        }
    }

    override fun getItemCount(): Int = mListOfPoster.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val movieViewHolder = viewHolder as PosterViewHolder
        movieViewHolder.bindView(mListOfPoster[position], mContextObject)
    }

    /**
     * Method to set adapter data
     *
     * @param content
     */

    fun setPosterList(content: List<Content>) {
        this.mListOfPoster = content.toMutableList()
        notifyDataSetChanged()
    }

    /**
     * Method to set adapter data while scrolling pagination
     *
     * @param content
     */

    fun addMoreData(content: List<Content>) {
        this.mListOfPoster.addAll(content)
        notifyDataSetChanged()
    }

    /**
     * Method to get adapter data
     *
     */

    fun getPosterList(): MutableList<Content> {
        return mListOfPoster
    }

}