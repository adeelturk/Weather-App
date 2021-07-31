package com.turk.cities.state

import com.turk.common.error.ErrorEntity
import com.turk.common.mvi.ViewState
import com.turk.dtos.city.City

sealed class CitiesState:ViewState{

     object DEFAULT : CitiesState()
     object Loading : CitiesState()
     data class Cities(val data:List<City>): CitiesState()
     data class Error(val error: ErrorEntity) : CitiesState()
}
