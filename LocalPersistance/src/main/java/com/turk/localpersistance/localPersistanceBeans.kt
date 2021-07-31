package com.turk.localpersistance

import org.koin.dsl.module


val localPersistenceBeans= module {

    // Room
    single { RoomSupport(get()) }
    factory { get<RoomSupport>().getCitiesDao() }

}