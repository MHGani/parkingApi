package com.example.mvvm_cakesapi.di

import com.example.parkingapi.network.Parking_APIServices
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    fun provideUserApi (retrofit: Retrofit):Parking_APIServices{
        return retrofit.create(Parking_APIServices::class.java)
    }
    single { provideUserApi(get()) }
}