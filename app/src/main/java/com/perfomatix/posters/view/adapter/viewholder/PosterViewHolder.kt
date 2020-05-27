package com.perfomatix.posters.view.adapter.viewholder

import android.content.Context
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.perfomatix.posters.R
import com.perfomatix.posters.model.Content
import kotlinx.android.synthetic.main.poster_item.view.*

/**
* View holder for poster adapter
**/
class PosterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(data: Content, context: Context) {

        itemView.txtViewName.text = data.name
        val imageId: Int

        //attach corresponding image resource
        when (data.poster_image) {
            "poster1.jpg" -> {
                imageId = R.drawable.poster1
                itemView.txtViewNoPreview.visibility = INVISIBLE
                itemView.imgViewPoster.visibility = VISIBLE
            }
            "poster2.jpg" -> {
                imageId = R.drawable.poster2
                itemView.txtViewNoPreview.visibility = INVISIBLE
                itemView.imgViewPoster.visibility = VISIBLE
            }
            "poster3.jpg" -> {
                imageId = R.drawable.poster3
                itemView.txtViewNoPreview.visibility = INVISIBLE
                itemView.imgViewPoster.visibility = VISIBLE
            }
            "poster4.jpg" -> {
                imageId = R.drawable.poster4
                itemView.txtViewNoPreview.visibility = INVISIBLE
                itemView.imgViewPoster.visibility = VISIBLE
            }
            "poster5.jpg" -> {
                imageId = R.drawable.poster5
                itemView.txtViewNoPreview.visibility = INVISIBLE
                itemView.imgViewPoster.visibility = VISIBLE
            }
            "poster6.jpg" -> {
                imageId = R.drawable.poster6
                itemView.txtViewNoPreview.visibility = INVISIBLE
                itemView.imgViewPoster.visibility = VISIBLE
            }
            "poster7.jpg" -> {
                imageId = R.drawable.poster7
                itemView.txtViewNoPreview.visibility = INVISIBLE
                itemView.imgViewPoster.visibility = VISIBLE
            }
            "poster8.jpg" -> {
                imageId = R.drawable.poster8
                itemView.txtViewNoPreview.visibility = INVISIBLE
                itemView.imgViewPoster.visibility = VISIBLE
            }
            "poster9.jpg" -> {
                imageId = R.drawable.poster9
                itemView.txtViewNoPreview.visibility = INVISIBLE
                itemView.imgViewPoster.visibility = VISIBLE
            }
            else -> {
                //if no resource is available
                imageId = R.drawable.placeholder_for_missing_posters
                itemView.txtViewNoPreview.visibility = VISIBLE
                itemView.imgViewPoster.visibility = INVISIBLE
            }

        }
        itemView.imgViewPoster.setImageDrawable(ContextCompat.getDrawable(context, imageId))
    }

}