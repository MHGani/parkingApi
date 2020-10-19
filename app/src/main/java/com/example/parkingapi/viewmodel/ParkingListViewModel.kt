package com.example.parkingapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.parkingapi.database.DatabaseParking
import com.example.parkingapi.repository.ParkingRepository
import kotlinx.coroutines.*
import java.lang.Exception

class ParkingListViewModel(private val parkingRepository:ParkingRepository): ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val parkingListResults = parkingRepository.results

    init {
        refreshFromRepository()
    }

    fun refreshFromRepository() {
        viewModelScope.launch {
            try {
                parkingRepository.refreshParking()
            } catch (networkError: Exception) {

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
