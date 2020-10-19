package com.example.parkingapi.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ParkingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(parking:List<DatabaseParking>)

    @Query("SELECT * FROM DATABASEPARKING")
    fun getLocalDBParking (): LiveData<List<DatabaseParking>>
}

@Database(entities = [DatabaseParking::class], version = 1, exportSchema = false)
abstract class ParkingDatabase : RoomDatabase(){
    abstract val parkingDao : ParkingDao
}