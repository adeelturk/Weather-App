package com.turk.weather.ui

import android.Manifest
import android.annotation.TargetApi
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.turk.common.base.BaseFragment
import com.turk.common.base.BaseViewModel
import com.turk.common.base.GeneralAdapter
import com.turk.common.extension.fault
import com.turk.common.location.LocationTrack
import com.turk.dtos.city.City
import com.turk.dtos.weather.Weather
import com.turk.weather.BR
import com.turk.weather.R
import com.turk.weather.action.WeatherAction
import com.turk.weather.databinding.WeatherReportFragmentBinding
import com.turk.weather.rounting.WeatherNavigation
import com.turk.weather.state.WeatherState
import com.turk.weather.viewmodel.WeatherViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherReportFragment : BaseFragment<WeatherReportFragmentBinding,WeatherState, WeatherAction>() {

    private val weatherReportViewModel:WeatherViewModel by viewModel()

    override fun layoutResourceId(): Int =R.layout.weather_report_fragment

    override fun getViewModel(): BaseViewModel<WeatherState, WeatherAction> =weatherReportViewModel

    private val adapter = GeneralAdapter(BR.weatherItem, R.layout.weather_forecast_item, Weather.DIFF_CALLBACK)
    private val locationTrack: LocationTrack by inject()
    private val weatherNavigation: WeatherNavigation by inject()

    override fun initialize(savedInstanceState: Bundle?) {

       val selectedCity= arguments?.getParcelable<City>("selectedCity")
        binding.adapter=adapter

        if(selectedCity!=null){
            binding.locationBtn.visibility= View.GONE
            dispatchIntent(WeatherAction.FetchWeatherForCity(selectedCity.name))
        }else{
            binding.locationBtn.visibility= View.VISIBLE
            getCurrentLocationAndFetchWeather()
        }

        binding.tempSwitch.setOnCheckedChangeListener{_,isCelcius->

            if(isCelcius){

                dispatchIntent(WeatherAction.ChangeTempratureToKelvin)
            }else{
                dispatchIntent(WeatherAction.ChangeTempratureToCelcius)

            }
        }

        binding.swipeRefresh.setOnRefreshListener {

            dispatchIntent(WeatherAction.RefreshWeatherReport)
        }

        binding.locationBtn.setOnClickListener {
            weatherNavigation.showCities()
        }

    }

    override fun attachListeners() {

        super.attachListeners()
        fault(weatherReportViewModel.errorEntity,::handleFailure)


    }

    private fun getCurrentLocationAndFetchWeather() {
        if (checkIfHavePermissionAndRequest()) {
            locationTrack.location
        }
         if (locationTrack.latitude == 0.0 || locationTrack.longitude == 0.0) {
            Toast.makeText(context, "can't get location", Toast.LENGTH_SHORT).show()

        } else {

            dispatchIntent(WeatherAction.FetchCurrentWeather(locationTrack.longitude ,locationTrack.latitude))
        }
    }


    override fun handleState(state: WeatherState) {

        when(state){

           is WeatherState.Loading->{

               showMessage(getString(R.string.loading_message))
               binding.tempSwitch.visibility=View.GONE
               binding.progressBar.visibility=View.VISIBLE
            }

           is WeatherState.WeatherData->{

                binding.weather=state.data
               binding.tempSwitch.visibility=View.VISIBLE
               binding.progressBar.visibility=View.GONE
            }
            is WeatherState.SwipeRefreshLoading->{

                binding.swipeRefresh.isRefreshing=true
            }
            is WeatherState.WeatherForecastData->{

                binding.swipeRefresh.isRefreshing=false
                adapter.submitList(state.data)
            }

            is WeatherState.Error->{
                binding.swipeRefresh.isRefreshing=false
                binding.progressBar.visibility=View.GONE
            }
            is WeatherState.GetLocationAndRefresh->{

                getCurrentLocationAndFetchWeather()
            }
            else->{

            }

        }
    }




    private var locationPermissionFlag = false
    private val PERMISSION_REQUEST_CODE = 1000

    @TargetApi(Build.VERSION_CODES.M)
    private fun checkIfHavePermissionAndRequest(): Boolean {

        locationPermissionFlag = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) ===
                PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) ===
                PackageManager.PERMISSION_GRANTED
        if (!locationPermissionFlag) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ), PERMISSION_REQUEST_CODE
            )
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE
            -> {

                val perms: MutableMap<String, Int> =
                    java.util.HashMap()
                // Initialize the map with both permissions
                // Initialize the map with both permissions
                perms[Manifest.permission.ACCESS_COARSE_LOCATION] =
                    PackageManager.PERMISSION_GRANTED
                perms[Manifest.permission.ACCESS_FINE_LOCATION] = PackageManager.PERMISSION_GRANTED
                if (grantResults.isNotEmpty()) {

                    for (i in permissions.indices) perms[permissions[i]] = grantResults[i]
                    if (perms[Manifest.permission.ACCESS_COARSE_LOCATION] ==
                        PackageManager.PERMISSION_GRANTED
                        && perms[Manifest.permission.ACCESS_FINE_LOCATION] ==
                        PackageManager.PERMISSION_GRANTED
                    ) {
                        getCurrentLocationAndFetchWeather()
                    } else {

                        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) ||
                            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)
                        ) {

                            val alertDialog = AlertDialog.Builder(
                                context
                            )
                            alertDialog.setTitle(R.string.note)
                            alertDialog.setMessage(R.string.you_must_allow_all_permissions_to_continue)
                            alertDialog.setPositiveButton(R.string.ok_msg) { dialog, which ->
                                getCurrentLocationAndFetchWeather()
                            }
                            alertDialog.setNegativeButton(R.string.cancel) {  dialog, which ->
                                showMessage( getString(R.string.for_more_accurate_result_allow_location))
                            }
                            alertDialog.show()



                        } else {

                            showSettingsAlert()
                        }

                    }
                }
            }

        }
    }

    private fun showSettingsAlert(){
        val alertDialog = AlertDialog.Builder(
            activity
        )
        alertDialog.setTitle("GPS is not Enabled!")
        alertDialog.setMessage("Do you want to turn on GPS?")
        alertDialog.setPositiveButton("Yes") { dialog, which ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }
        alertDialog.setNegativeButton("No") { dialog, which -> dialog.cancel() }
        alertDialog.show()

    }
}