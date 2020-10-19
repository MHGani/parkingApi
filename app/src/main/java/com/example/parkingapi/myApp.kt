package com.example.parkingapi

import android.app.Application
import com.example.mvvm_cakesapi.di.apiModule
import com.example.mvvm_cakesapi.di.viewModelModule
import com.example.parkingapi.di.databaseModule
import com.example.parkingapi.di.netModule
import com.example.parkingapi.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class myApp : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@myApp)
            androidLogger(Level.DEBUG)
            modules(viewModelModule, repositoryModule, netModule, apiModule, databaseModule)
        }
    }
}

