package fr.epsi.krynen.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.Box

class CharactersListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CharactersScreen(
                        characters = getSampleCharacters(),
                        isError = false,
                        errorMessage = "Impossible de charger les personnages. Vérifiez votre connexion internet."
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    characters: List<Character>,
    isError: Boolean,
    errorMessage: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Rick and Morty")
                    }
                  },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (isError) {
                ErrorScreen(errorMessage)
            } else {
                CharactersList(characters)
            }
        }
    }
}

@Composable
fun ErrorScreen(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "⚠️",
                style = MaterialTheme.typography.displayLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

fun getSampleCharacters(): List<Character> {
    return listOf(
        Character(
            id = 1,
            name = "Rick Sanchez",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            status = "Alive",
            species = "Human",
            type = "Unstable",
            gender = "Male",
            origin = "Earth (C-137)",
            location = "Citadel of Ricks"
        ),
        Character(
            id = 2,
            name = "Morty Smith",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            status = "Alive",
            species = "Human",
            type = "Victim",
            gender = "Male",
            origin = "Earth (C-137)",
            location = "Citadel of Ricks"
        ),
        Character(
            id = 3,
            name = "Summer Smith",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
            status = "Alive",
            species = "Human",
            type = "Teenager",
            gender = "Female",
            origin = "Earth (Replacement Dimension)",
            location = "Earth (Replacement Dimension)"
        ),
        Character(
            id = 4,
            name = "Beth Smith",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/4.jpeg",
            status = "Alive",
            species = "Human",
            type = "Warrior",
            gender = "Female",
            origin = "Earth (Replacement Dimension)",
            location = "Earth (Replacement Dimension)"
        ),
        Character(
            id = 5,
            name = "Jerry Smith",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/5.jpeg",
            status = "Alive",
            species = "Human",
            type = "Unworthy",
            gender = "Male",
            origin = "Earth (Replacement Dimension)",
            location = "Earth (Replacement Dimension)"
        )
    )
}

@Composable
fun CharactersList(characters: List<Character>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(characters) { character ->
            CharacterItem(character)
        }
    }
}

@Composable
fun CharacterItem(character: Character) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { navigateToDetails(context, character) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image du personnage
            AsyncImage(
                model = character.imageUrl,
                contentDescription = "Image de ${character.name}",
                modifier = Modifier
                    .size(60.dp)
                    .padding(end = 16.dp)
            )

            // Nom du personnage
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

fun navigateToDetails(context: Context, character: Character) {
    val intent = Intent(context, CharacterDetailsActivity::class.java).apply {
        putExtra("CHARACTER_ID", character.id)
        putExtra("CHARACTER_NAME", character.name)
        putExtra("CHARACTER_IMAGE", character.imageUrl)
        putExtra("CHARACTER_STATUS", character.status)
        putExtra("CHARACTER_SPECIES", character.species)
        putExtra("CHARACTER_TYPE", character.type)
        putExtra("CHARACTER_GENDER", character.gender)
        putExtra("CHARACTER_ORIGIN", character.origin)
        putExtra("CHARACTER_LOCATION", character.location)
    }
    context.startActivity(intent)
}