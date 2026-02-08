package com.example.flightsearch.ui.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flightsearch.data.Airport
import androidx.compose.foundation.lazy.items

@Composable
fun FlightsFromAirport(
    departure: Airport,
    destinations: List<Airport>,
    onSaveFavorite: (Airport) -> Unit
) {
    LazyColumn {
        items(destinations) { destination ->
            if (destination.iata_code != departure.iata_code) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // This Column takes all available width except the button
                        Column(
                            modifier = Modifier
                                .weight(1f) // takes remaining space
                        ) {
                            Text("${departure.iata_code} → ${destination.iata_code}")
                            Text(
                                "${departure.name} → ${destination.name}",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        // Save button stays fixed on the right
                        TextButton(
                            onClick = { onSaveFavorite(destination) }
                        ) {
                            Text("Save")
                        }
                    }
                }

            }
        }
    }
}



