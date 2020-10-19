package com.example.parkingapi.network

import com.example.parkingapi.database.DatabaseParking
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Parking_APIServices {

    @GET(API_Calls.API_PARKING_LIST)
    fun getParkingList (): Deferred<List<DatabaseParking>>
}