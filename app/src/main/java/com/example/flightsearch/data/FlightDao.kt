package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightDao {

    @Query("SELECT * FROM airport WHERE name LIKE :query OR iata_code LIKE :query LIMIT 10")
    fun searchAirports(query: String): Flow<List<Airport>>

    @Query("SELECT * FROM favorite")
    fun getAllFavorites(): Flow<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(favorite: Favorite)

    @Query("SELECT * FROM airport")
    fun getAllAirports(): Flow<List<Airport>>
}