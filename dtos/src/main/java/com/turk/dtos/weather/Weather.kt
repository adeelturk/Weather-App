package com.turk.dtos.weather

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import com.turk.dtos.city.City
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather( var id: Int = 0,
                    val visibility: Long,
                    val cityName: String,
                    val countryCode: String,
                    val windDeg: Int,
                    val windSpeed: Double,
                    val feels_like: Double,
                    val humidity: Int,
                    val pressure: Int,
                    val temp: Double,
                    val temp_max: Double,
                    val temp_min: Double,
                    var dateTime:String=""
): Parcelable{

    companion object {

        val DIFF_CALLBACK: DiffUtil.ItemCallback<Weather> =
            object : DiffUtil.ItemCallback<Weather>() {
                override fun areItemsTheSame(
                    @NonNull oldItem: Weather,
                    @NonNull newItem: Weather
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    @NonNull oldItem: Weather,
                    @NonNull newItem: Weather
                ): Boolean {
                    return oldItem == newItem
                }
            }


    }
}
