package com.example.flightsearch.ui.Screens

import FavoritesList
import android.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearch.data.Airport


@Composable
fun FlightSearchScreen(modifier: Modifier,
    viewModel: FlightSearchViewModel =
        viewModel(factory = FlightSearchViewModel.factory)
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedAirport by remember { mutableStateOf<Airport?>(null) }
    val allAirports by viewModel
        .getAllAirports()
        .collectAsState(initial = emptyList())

    val airportSuggestions by viewModel
        .searchAirports(searchQuery)
        .collectAsState(initial = emptyList())

    val favorites by viewModel
        .getAllFavorites()
        .collectAsState(initial = emptyList())

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {


        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                if (it.isEmpty()) selectedAirport = null
            },
            label = { Text("Enter airport") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Blue,

            ),
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(16.dp))

        /* ---------------- UI STATES ---------------- */

        when {
            // 🔹 State 1: No query → show favorites
            searchQuery.isEmpty() -> {
                FavoritesList(favorites)
            }

            // 🔹 State 2: Typing but not selected → suggestions
            selectedAirport == null -> {
                AirportSuggestions(
                    airports = airportSuggestions,
                    onAirportSelected = {
                        selectedAirport = it
                        searchQuery = "${it.name} (${it.iata_code})"

                    }
                )
            }

            // 🔹 State 3: Airport selected → flights
            else -> {
                FlightsFromAirport(
                    departure = selectedAirport!!,
                    destinations = allAirports,
                    onSaveFavorite = { destination ->
                        viewModel.addFavorite(
                            departureCode = selectedAirport!!.iata_code,
                            destinationCode = destination.iata_code
                        )
                    }
                )
            }
        }
    }
}
