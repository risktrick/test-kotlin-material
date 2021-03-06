package com.raywenderlich.android.travelwishlist

import android.content.Context
import android.graphics.BitmapFactory
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.AdapterView
import com.raywenderlich.android.travelwishlist.model.PlaceData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_places.view.*

class TravelListAdapter(private var context: Context) : RecyclerView.Adapter<TravelListAdapter.ViewHolder>() {

    lateinit var itemClickListener: OnItemClickListener

    override fun getItemCount() = PlaceData.placeList().size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.row_places, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val place = PlaceData.placeList()[position]
        holder?.itemView?.placeName?.text = place.name
        val imageId = place.getImageResourceId(context)

        Picasso.with(context).load(imageId).into(holder?.itemView?.placeImage)

        val bitmap = BitmapFactory.decodeResource(context.resources, imageId)
        Palette.from(bitmap).generate { palette ->
            val bgColor = palette.getDarkMutedColor(ContextCompat.getColor(context, android.R.color.black))
            holder?.itemView?.placeNameHolder?.setBackgroundColor(bgColor)
        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            itemView.placeHolder.setOnClickListener(this)
        }

        override fun onClick(view: View) = itemClickListener.onItemClick(itemView, adapterPosition)
    }


    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }


    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}