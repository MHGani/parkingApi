package com.example.parkingapi.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class DatabaseParking (
    @PrimaryKey
    var id : Int,
    var lat : Double,
    var lng : Double,
    var is_reserved: Boolean

):Parcelable
