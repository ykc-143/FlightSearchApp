package com.example.flightsearch.ui.Screens

import androidx.lifecycle.ViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearch.FlightSearchApplication
import com.example.flightsearch.data.Favorite

import kotlinx.coroutines.flow.Flow
import androidx.lifecycle.viewModelScope
import com.example.flightsearch.data.FlightRepository
import kotlinx.coroutines.launch

class FlightSearchViewModel(
    private val flightRepository: FlightRepository
) : ViewModel() {

    /* -------- DATA -------- */

    fun searchAirports(query: String) =
        flightRepository.searchAirports(query)

    fun getAllFavorites(): Flow<List<Favorite>> =
        flightRepository.getAllFavorites()

    /* -------- ACTIONS -------- */

    fun addFavorite(
        departureCode: String,
        destinationCode: String
    ) {
        viewModelScope.launch {
            flightRepository.insertFavorite(
                Favorite(
                    departure_code = departureCode,
                    destination_code = destinationCode
                )
            )
        }
    }

    fun getAllAirports() =
        flightRepository.getAllAirports()

    /* -------- FACTORY -------- */

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[APPLICATION_KEY] as FlightSearchApplication)

                FlightSearchViewModel(
                    application.container.flightRepository
                )
            }
        }
    }
}
