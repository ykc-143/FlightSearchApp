import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flightsearch.data.Favorite

@Composable
fun FavoritesList(favorites: List<Favorite>) {
    if (favorites.isEmpty()) {
        Text("No favorite destinations saved.")
    } else {
        LazyColumn {
            items(favorites) { favorite ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        text = "${favorite.departure_code} → ${favorite.destination_code}",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
