package com.example.mvvm_cakesapi.di


import com.example.parkingapi.viewmodel.ParkingListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ParkingListViewModel(get()) }

}