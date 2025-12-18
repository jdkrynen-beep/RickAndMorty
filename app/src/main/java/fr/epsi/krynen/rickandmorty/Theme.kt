package fr.epsi.krynen.rickandmorty

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Couleurs inspirées de Rick and Morty
private val LightColors = lightColorScheme(
    primary = Color(0xFF00B5CC),           // Bleu portail Rick
    secondary = Color(0xFF97CE4C),         // Vert acide
    tertiary = Color(0xFFF8E64E),          // Jaune
    background = Color(0xFFF5F5F5),
    surface = Color(0xFFFFFFFF),
    error = Color(0xFFBA1A1A),
    primaryContainer = Color(0xFF97CE4C),   // Vert pour la toolbar
    onPrimaryContainer = Color(0xFF1A1A1A),
    secondaryContainer = Color(0xFFE8F5E9),
    onSecondaryContainer = Color(0xFF1B5E20),
    surfaceVariant = Color(0xFFEEEEEE),
    onSurfaceVariant = Color(0xFF1A1A1A)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF00B5CC),
    secondary = Color(0xFF97CE4C),
    tertiary = Color(0xFFF8E64E),
    background = Color(0xFF1C1C1C),
    surface = Color(0xFF2C2C2C),
    error = Color(0xFFFF5449),
    primaryContainer = Color(0xFF004D5A),
    onPrimaryContainer = Color(0xFFE0F7FA),
    secondaryContainer = Color(0xFF2E7D32),
    onSecondaryContainer = Color(0xFFE8F5E9),
    surfaceVariant = Color(0xFF3C3C3C),
    onSurfaceVariant = Color(0xFFE0E0E0)
)

@Composable
fun RickAndMortyTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}