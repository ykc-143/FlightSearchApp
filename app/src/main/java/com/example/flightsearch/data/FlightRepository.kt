package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow

class FlightRepository(private val flightDao: FlightDao) {

    fun searchAirports(query: String) =
        flightDao.searchAirports("%$query%")

    fun getAllFavorites() =
        flightDao.getAllFavorites()

    suspend fun insertFavorite(favorite: Favorite) {
        flightDao.insertFavorite(favorite)
    }
    fun getAllAirports(): Flow<List<Airport>> =
        flightDao.getAllAirports()

}
