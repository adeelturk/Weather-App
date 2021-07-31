package com.turk.common.location

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import androidx.core.app.ActivityCompat

class LocationTrack(private val mContext: Context) : LocationListener {
    protected var locationManager: LocationManager? = null
    var checkGPS = false
    var checkNetwork = false
    var canGetLocation = false
    var locationEnableInterface: LocationEnableInterface? = null
    var loc: Location? = null
    var latitude = 0.0
    var longitude = 0.0
    var accuracy = 0.0
    fun setInterfaceListener(locationEnableInterface: LocationEnableInterface?) {
        this.locationEnableInterface = locationEnableInterface
    }// if GPS Enabled get lat/long using GPS Services

    //  return loc;
//Toast.makeText(mContext, "No Service Provider is available", Toast.LENGTH_SHORT).showProgressDialog();
    // TODO: Consider calling
    //    ActivityCompat#requestPermissions
    // here to request the missing permissions, and then overriding
    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
    //                                          int[] grantResults)
    // to handle the case where the user grants the permission. See the documentation
    // for ActivityCompat#requestPermissions for more details.

    // get GPS status

    // get network provider status
    val location: Unit
        get() {
            try {
                if (ActivityCompat.checkSelfPermission(
                        mContext,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        mContext, Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                }
                locationManager = mContext
                    .getSystemService(Context.LOCATION_SERVICE) as LocationManager
                locationManager!!.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    MIN_TIME_BW_UPDATES,
                    MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this
                )

                // get GPS status
                checkGPS = locationManager!!
                    .isProviderEnabled(LocationManager.GPS_PROVIDER)

                // get network provider status
                checkNetwork = locationManager!!
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                if (!checkGPS && !checkNetwork) {
                    //Toast.makeText(mContext, "No Service Provider is available", Toast.LENGTH_SHORT).showProgressDialog();
                } else {
                    canGetLocation = true

                    // if GPS Enabled get lat/long using GPS Services
                    if (checkGPS) {
                        if (locationManager != null) {
                            loc =
                                locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                            if (loc != null) {
                                latitude = loc!!.latitude
                                longitude = loc!!.longitude
                            } else {
                                if (checkNetwork) {
                                    loc =
                                        locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                                    if (loc != null) {
                                        latitude = loc!!.latitude
                                        longitude = loc!!.longitude
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            //  return loc;
        }



    fun canGetLocation(): Boolean {
        return canGetLocation
    }

    fun showSettingsAlert() {
        val alertDialog = AlertDialog.Builder(
            mContext
        )
        alertDialog.setTitle("GPS is not Enabled!")
        alertDialog.setMessage("Do you want to turn on GPS?")
        alertDialog.setPositiveButton("Yes") { dialog, which ->
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            mContext.startActivity(intent)
        }
        alertDialog.setNegativeButton("No") { dialog, which -> dialog.cancel() }
        alertDialog.show()
    }

    fun stopListener() {
        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(
                    mContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    mContext,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            locationManager!!.removeUpdates(this)
        }
    }

    override fun onLocationChanged(location: Location) {
        location
        if (locationEnableInterface != null) {
            locationEnableInterface!!.onLocationChanged(location)
        }
    }

    override fun onStatusChanged(s: String, i: Int, bundle: Bundle) {}
    override fun onProviderEnabled(s: String) {
        val handler = Handler()
        handler.postDelayed({ //Do something after 100ms
            location
            if (locationEnableInterface != null) locationEnableInterface!!.onLocationEnabled()
        }, 5000)
    }

    override fun onProviderDisabled(s: String) {
        try {
            locationEnableInterface!!.onLocationDisabled()
        } catch (e: Exception) {
            println(s)
        }
    }

    companion object {
        private const val MIN_DISTANCE_CHANGE_FOR_UPDATES: Long = 1
        private const val MIN_TIME_BW_UPDATES: Long = 3000
    }

    init {
        location
    }
}