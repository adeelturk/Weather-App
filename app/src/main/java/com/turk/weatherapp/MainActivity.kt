package com.turk.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.turk.dtos.city.City
import com.turk.weatherapp.navigation.MainRoutHandler
import com.turk.weatherapp.navigation.Navigator
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainRoutHandler {
    private val navigator: Navigator by inject()
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController =
            (supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment).navController
        navigator.setNavigationController(this)

    }

    override fun showCities() {
        navController.navigate(R.id.action_weatherReportFragment_to_citiesListFragment)
    }

    override fun showWeatherReport(city: City) {
        val bundle =Bundle()
        bundle.putParcelable("selectedCity",city)
        navController.navigate(R.id.action_citiesListFragment_to_weatherReportFragment,bundle)
    }

    override fun onResume() {
        super.onResume()

    }
}