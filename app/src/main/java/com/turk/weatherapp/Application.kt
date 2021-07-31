package com.turk.weatherapp

import android.app.Application
import com.turk.business.businessDependencies
import com.turk.cities.citiesDependencies
import com.turk.citiesrepository.citiesRepoDependencies
import com.turk.localpersistance.localPersistenceBeans
import com.turk.network.networkModule
import com.turk.weather.weatherDependencies
import com.turk.weatherapp.navigation.navigationModule
import com.turk.weatherrepository.weatherRepoDependencies

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        //region Koin Initializations
        startKoin {
            androidContext(this@Application)
            modules(
                listOf(
                    networkModule,
                    businessDependencies,
                    citiesRepoDependencies,
                    localPersistenceBeans,
                    citiesDependencies,
                    weatherRepoDependencies,
                    weatherDependencies,
                    navigationModule,

                )
            )
        }

        //endregion
    }

}