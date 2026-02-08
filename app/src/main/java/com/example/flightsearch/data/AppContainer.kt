package com.example.flightsearch.data

import android.content.Context

interface AppContainer{

//    private val database = FlightDatabase.getDatabase(context)
//
//    val flightDao = database.flightDao()
    val flightRepository : FlightRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val flightRepository: FlightRepository by lazy {
        FlightRepository(FlightDatabase.getDatabase(context).flightDao())
    }
}