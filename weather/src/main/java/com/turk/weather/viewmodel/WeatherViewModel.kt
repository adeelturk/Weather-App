package com.turk.weather.viewmodel

import com.turk.common.base.BaseViewModel
import com.turk.weather.action.WeatherAction
import com.turk.weather.reducer.WeatherReducer
import com.turk.weather.state.WeatherState

class WeatherViewModel :BaseViewModel<WeatherState,WeatherAction>
    (WeatherState.Loading,WeatherReducer() ) {


}