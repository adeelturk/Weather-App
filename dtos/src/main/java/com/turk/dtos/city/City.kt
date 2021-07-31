package com.turk.dtos.city

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(val id:Int,val countryCode:String,val name:String,var isFavourite:Boolean=false):Parcelable{

    companion object {

        val DIFF_CALLBACK: DiffUtil.ItemCallback<City> =
            object : DiffUtil.ItemCallback<City>() {
                override fun areItemsTheSame(
                    @NonNull oldItem: City,
                    @NonNull newItem: City
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    @NonNull oldItem: City,
                    @NonNull newItem: City
                ): Boolean {
                    return oldItem == newItem
                }
            }


    }
}
