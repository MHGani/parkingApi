package com.example.parkingapi.repository

import androidx.lifecycle.LiveData
import com.example.parkingapi.database.DatabaseParking
import com.example.parkingapi.database.ParkingDatabase
import com.example.parkingapi.network.Parking_APIServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ParkingRepository(private val parkingApiservices: Parking_APIServices, private val database: ParkingDatabase) {
    suspend fun refreshParking(){
        withContext(Dispatchers.IO){
            val parkingList = parkingApiservices.getParkingList().await()
            database.parkingDao.insertAll(parkingList)
        }
    }

    val results : LiveData<List<DatabaseParking>> = database.parkingDao.getLocalDBParking()
}