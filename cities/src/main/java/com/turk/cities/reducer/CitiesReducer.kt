package com.turk.cities.reducer

import com.turk.common.mvi.Reducer
import com.turk.cities.action.CitiesAction
import com.turk.cities.state.CitiesState

class CitiesReducer: Reducer<CitiesState, CitiesAction> {

    override fun reduce(
        currentState: CitiesState,
        action: CitiesAction
    ): CitiesState {

       return  when (action){

           is CitiesAction.DEFAULT->{

               CitiesState.DEFAULT
           }
            is CitiesAction.FetchCities->{

                stateForFetchingResults()
            }

          is  CitiesAction.PostCities->{

              stateAfterCitiesFetched(action)
           }
           is CitiesAction.Error->{
               stateOnError(action)
           }

           else -> {
               currentState
           }
        }
    }


    private fun stateForFetchingResults(): CitiesState {

        return CitiesState.Loading
    }

    private fun stateAfterCitiesFetched( action: CitiesAction.PostCities): CitiesState {

        return CitiesState.Cities(action.data)
    }

    private fun stateOnError(action: CitiesAction.Error): CitiesState {
        return CitiesState.Error(action.error)
    }



}