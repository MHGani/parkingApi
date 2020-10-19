package com.example.parkingapi.di


import com.example.parkingapi.database.ParkingDatabase
import com.example.parkingapi.network.Parking_APIServices
import com.example.parkingapi.repository.ParkingRepository
import org.koin.dsl.module

val repositoryModule = module {
    fun provideRepository(api:Parking_APIServices, dao: ParkingDatabase): ParkingRepository{
        return ParkingRepository(api, dao)
    }

    single { provideRepository(get(), get()) }
}