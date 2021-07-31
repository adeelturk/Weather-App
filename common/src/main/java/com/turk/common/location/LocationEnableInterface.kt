package com.turk.common.location

import android.location.Location

interface LocationEnableInterface {
    fun onLocationChanged(location: Location?)
    fun onLocationDisabled()
    fun onLocationEnabled()
}