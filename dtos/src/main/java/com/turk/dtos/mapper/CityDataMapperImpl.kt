package com.turk.dtos.mapper

import com.turk.dtos.city.City
import com.turk.dtos.city.CityDbData
import com.turk.dtos.serverObjects.CityEntity

class CityDataMapperImpl : DataMapper<CityEntity,CityDbData,City>() {

    override fun mapToDbModel(entity: CityEntity): CityDbData {

        return CityDbData(id=entity.id,country = entity.country,name = entity.name,lon = entity.coord.lon,lat=
        entity.coord.lat,population= entity.stat.population)
    }

    override fun mapToDomainModel(dbModel: CityDbData): City {

        return City(id=dbModel.id,countryCode = dbModel.country,name = dbModel.name,isFavourite = dbModel.isFavourite)
    }



}