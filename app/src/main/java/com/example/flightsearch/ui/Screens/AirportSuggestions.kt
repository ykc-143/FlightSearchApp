package com.example.flightsearch.ui.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flightsearch.data.Airport



@Composable
fun AirportSuggestions(
    airports: List<Airport>,
    onAirportSelected: (Airport) -> Unit
) {
    LazyColumn {
        items(airports) { airport ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onAirportSelected(airport) }
                    .padding(16.dp)
            ) {
                Text(text = airport.name)
                Text(
                    text = airport.iata_code,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Divider()
        }
    }
}


