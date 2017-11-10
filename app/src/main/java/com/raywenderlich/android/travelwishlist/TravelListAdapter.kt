package com.raywenderlich.android.travelwishlist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import com.raywenderlich.android.travelwishlist.model.PlaceData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_places.view.*

class TravelListAdapter(private var context: Context) : RecyclerView.Adapter<TravelListAdapter.ViewHolder>() {

    override fun getItemCount() = PlaceData.placeList().size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.row_places, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val place = PlaceData.placeList()[position]
        holder?.itemView?.placeName?.text = place.name
        Picasso.with(context).load(place.getImageResourceId(context)).into(holder?.itemView?.placeImage)
    }

    // 2
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}