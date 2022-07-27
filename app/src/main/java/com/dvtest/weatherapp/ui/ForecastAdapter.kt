package com.dvtest.weatherapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dvtest.weatherapp.R
import com.dvtest.weatherapp.model.dbmodels.ForecastEntity
import java.text.SimpleDateFormat

class ForecastAdapter(var data: List<ForecastEntity>, var context: Context) :
    RecyclerView.Adapter<ForecastAdapter.ForeCastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForeCastViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_item, parent, false)
        return ForeCastViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ForeCastViewHolder, position: Int) {
        val forecast = data[position]
        var int = forecast.main.temp.toInt()
        var image = when (forecast.weather.main.trim().toLowerCase()) {
            "rain" -> {
                R.drawable.rain
            }
            "clouds" -> {
                R.drawable.partlysunny
            }
            else -> {
                R.drawable.clear
            }
        }
        holder.tvTemp.text = int.toString() + " \u00B0"
        holder.ivIcon.setImageDrawable(context.resources.getDrawable(image))
        var dateAsText = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(forecast.dt_txt)
        var dateFormatted = SimpleDateFormat("E, hh ").format(dateAsText)
        holder.tvDay.text = dateFormatted

    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ForeCastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvDay: TextView
        var ivIcon: ImageView
        var tvTemp: TextView

        init {
            tvDay = view.findViewById(R.id.tvDay)
            ivIcon = view.findViewById(R.id.ivForecastIcon)
            tvTemp = view.findViewById(R.id.tvTemp)
        }
    }

}