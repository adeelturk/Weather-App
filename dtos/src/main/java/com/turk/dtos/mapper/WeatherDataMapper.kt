package com.turk.dtos.mapper

import com.turk.dtos.serverObjects.RequestedCityData
import com.turk.dtos.serverObjects.WeatherEntity
import com.turk.dtos.serverObjects.WeatherForecastEntity
import com.turk.dtos.weather.Weather
import com.turk.dtos.weather.WeatherDbDto
import com.turk.dtos.weather.WeatherReport
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class WeatherDataMapper : DataMapper<WeatherEntity, WeatherDbDto, Weather>() {

    override fun mapToDbModel(entity: WeatherEntity): WeatherDbDto {
        with(entity) {


            return WeatherDbDto(
                id = id,
                visibility = visibility,
                name,
                cod,
                base,
                timezone,
                clouds.all,
                sys.id,
                sys.type,
                sys.country,
                sys.sunrise,
                sys.sunset,
                wind.deg,
                wind.speed,
                main.feels_like,
                main.humidity,
                main.pressure,
                main.temp,
                main.temp_max,
                main.temp_min
            )

        }
    }

    override fun mapToDomainModel(dbModel: WeatherDbDto): Weather {

        with(dbModel) {

            return Weather(
                id,
                visibility,
                cityName,
                country,
                windDeg,
                windSpeed,
                feels_like,
                humidity,
                pressure,
                temp,
                temp_max,
                temp_min
            )
        }
    }


    private fun mapToDomainModel(city: RequestedCityData, data: WeatherForecastEntity): Weather {

        return Weather(
            city.id, data.visibility, city.name, city.country,
            data.wind.deg, data.wind.speed, data.main.feels_like,
            data.main.humidity, data.main.pressure,
            data.main.temp, data.main.temp_max,
            data.main.temp_min,
            getDate(data.dt_txt)
        )
    }

    fun mapToDomainModel(city: RequestedCityData, data: List<WeatherForecastEntity>):List<Weather> {

        return data.map {

            mapToDomainModel(city,it)
        }
    }
    //"2021-07-31 15:00:00"
    private fun getDate(dateTime:String):String{

        val outputPattern = "EEE dd MMM"
        val inputPattern = "yyyy-mm-dd HH:mm:ss"
        val inputFormat = SimpleDateFormat(inputPattern)
        val outputFormat = SimpleDateFormat(outputPattern)

        var date: Date? = null
        var str: String = dateTime

        try {
            date = inputFormat.parse(dateTime)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return str

    }


    fun toCelcis(data: WeatherReport):WeatherReport {

        val currentWeatherData=data.currentCityWeather.copy(temp = data.currentCityWeather.temp.toCelsius().toTwoDecimalPoints(),
            temp_min = data.currentCityWeather.temp_min.toCelsius().toTwoDecimalPoints(),
            temp_max = data.currentCityWeather.temp_max.toCelsius().toTwoDecimalPoints(),)

       val foreCastList= data.foreCast.map {

            it.copy(temp = data.currentCityWeather.temp.toCelsius().toTwoDecimalPoints(),
                temp_min = data.currentCityWeather.temp_min.toCelsius().toTwoDecimalPoints(),
                temp_max = data.currentCityWeather.temp_max.toCelsius().toTwoDecimalPoints())
        }

        return WeatherReport(currentWeatherData,foreCastList)
    }

    fun toKelvin(data: WeatherReport):WeatherReport{

        val currentWeatherData=data.currentCityWeather.copy(temp = data.currentCityWeather.temp.toKelvin().toTwoDecimalPoints(),
            temp_min = data.currentCityWeather.temp_min.toKelvin().toTwoDecimalPoints(),
            temp_max = data.currentCityWeather.temp_max.toKelvin().toTwoDecimalPoints())

        val foreCastList= data.foreCast.map {

            it.copy(temp = data.currentCityWeather.temp.toKelvin().toTwoDecimalPoints(),
                temp_min = data.currentCityWeather.temp_min.toKelvin().toTwoDecimalPoints(),
                temp_max = data.currentCityWeather.temp_max.toKelvin().toTwoDecimalPoints())
        }

        return WeatherReport(currentWeatherData,foreCastList)
    }


    private fun Double.toCelsius() = this - 273.15
    private fun Double.toTwoDecimalPoints() = String.format("%.2f", this).toDouble()

    private  fun Double.toKelvin() = this + 273.15




}