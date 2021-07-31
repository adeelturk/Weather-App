package com.turk.cities.action

import com.turk.common.error.ErrorEntity
import com.turk.common.mvi.ViewAction
import com.turk.dtos.city.City

sealed class CitiesAction:ViewAction{

     object DEFAULT : CitiesAction()
     object FetchCities : CitiesAction()
     data class SearchCity(val searchQuery:String) : CitiesAction()
     data class UpdateCityFavouriteStatus(val city:City) : CitiesAction()
     data class Error(val error:ErrorEntity) : CitiesAction()
     data class PostCities(val data:List<City>) : CitiesAction()

}
