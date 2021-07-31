package com.turk.cities.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.turk.business.usecase.cities.*
import com.turk.cities.action.CitiesAction
import com.turk.cities.reducer.CitiesReducer
import com.turk.cities.state.CitiesState
import com.turk.common.base.BaseViewModel
import com.turk.dtos.city.City
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CitiesViewModel(private val citiesListUrl:String,
                      private val citiesListUseCase:CitiesListUseCase,
                      private val searchCityByName: SearchCityByName,
                      private val favouriteCityUseCase: FavouriteCityUseCase,
                      ) : BaseViewModel<CitiesState,CitiesAction> (
    CitiesState.DEFAULT,
    CitiesReducer()
        ){


    override fun processAction(action: CitiesAction) {
        super.processAction(action)

        when(action){

            is CitiesAction.FetchCities->{
                fetchCitiesList()
            }
            is CitiesAction.SearchCity->{

                searchCity(action.searchQuery)
            }
            is CitiesAction.UpdateCityFavouriteStatus->{
                changeCityFavouriteStatus(action.city)
            }
        }
    }

    private val _cities=MutableStateFlow(listOf<City>())
    val cities:StateFlow<List<City>> =_cities

    private fun fetchCitiesList(){

        citiesListUseCase(viewModelScope = viewModelScope, CitiesListParams(citiesListUrl,false)){

            it.either(::handleFailure){dataList->

                dispatch(CitiesAction.PostCities(dataList))
                _cities.value=dataList
            }

        }
    }

   private fun searchCity(cityName: String) {

        searchCityByName(viewModelScope = viewModelScope, SearchCityByNameParams(cityName)){

            it.either(::handleFailure){dataList->

                dispatch(CitiesAction.PostCities(dataList))
                _cities.value=dataList
            }

        }

    }

    private fun changeCityFavouriteStatus(city:City){

        favouriteCityUseCase(viewModelScope = viewModelScope, FavouriteCityParams(city.id,city.isFavourite)){
            it.either(::handleFailure){data->

                Log.v("adeel",data.toString())

            }

        }
    }


}