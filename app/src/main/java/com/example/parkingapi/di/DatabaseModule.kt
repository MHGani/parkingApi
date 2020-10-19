package com.example.parkingapi.di

import android.app.Application
import androidx.room.Room
import com.example.parkingapi.database.ParkingDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun providersDatabase(application: Application):ParkingDatabase{
        return Room.databaseBuilder(application, ParkingDatabase::class.java, "parking.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    single { providersDatabase(androidApplication()) }
}