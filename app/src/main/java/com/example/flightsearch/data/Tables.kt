package com.example.flightsearch.data

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity("airport")
data class Airport(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val iata_code: String,
    val name:String,
    val passengers:Int
)


@Entity("favorite")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val departure_code: String,
    val destination_code: String
)