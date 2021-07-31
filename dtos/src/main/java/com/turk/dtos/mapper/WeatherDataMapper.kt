package com.turk.dtos.mapper

import com.turk.dtos.serverObjects.WeatherEntity
import com.turk.dtos.weather.Weather
import com.turk.dtos.weather.WeatherDbDto

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


}