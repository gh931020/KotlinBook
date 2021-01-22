package com.xxyl.sunnyweather15.ui.place

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.xxyl.sunnyweather15.R
import com.xxyl.sunnyweather15.logic.model.Place
import com.xxyl.sunnyweather15.ui.weather.WeatherActivity
import kotlinx.android.synthetic.main.item_place.view.*

/**
 * author: zhang
 * created on: 2021/1/11 17:42
 * description:
 */
class PlaceAdapter(private val fragment: Fragment, private val dataList: List<Place>) :
    RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    class PlaceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvPlaceName = view.findViewById<TextView>(R.id.tvPlaceName)
        val tvPlaceAddress = view.findViewById<TextView>(R.id.tvPlaceAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder{
        val holder = PlaceViewHolder(
            LayoutInflater.from(fragment.activity).inflate(
                R.layout.item_place, parent, false
            )
        )
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val place = dataList[position]
            WeatherActivity.start(parent.context, place.location.lng, place.location.lat, place.name)
        }
        return holder
    }


    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = dataList[position]
        holder.tvPlaceName.text = place.name
        holder.tvPlaceAddress.text = place.address
    }

}