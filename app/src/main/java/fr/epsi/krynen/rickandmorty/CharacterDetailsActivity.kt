package fr.epsi.krynen.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.size
import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.foundation.layout.Box

class CharacterDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Récupération des données passées depuis la liste
        val characterId = intent.getIntExtra("CHARACTER_ID", 0)
        val characterName = intent.getStringExtra("CHARACTER_NAME") ?: ""
        val characterImage = intent.getStringExtra("CHARACTER_IMAGE") ?: ""
        val characterStatus = intent.getStringExtra("CHARACTER_STATUS") ?: ""
        val characterSpecies = intent.getStringExtra("CHARACTER_SPECIES") ?: ""
        val characterType = intent.getStringExtra("CHARACTER_TYPE") ?: ""
        val characterGender = intent.getStringExtra("CHARACTER_GENDER") ?: ""
        val characterOrigin = intent.getStringExtra("CHARACTER_ORIGIN") ?: ""
        val characterLocation = intent.getStringExtra("CHARACTER_LOCATION") ?: ""

        val character = Character(
            id = characterId,
            name = characterName,
            imageUrl = characterImage,
            status = characterStatus,
            species = characterSpecies,
            type = characterType,
            gender = characterGender,
            origin = characterOrigin,
            location = characterLocation
        )

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CharacterDetailsScreenWithToolbar(character) {
                        finish()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreenWithToolbar(
    character: Character,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(character.name)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Retour"
                        )
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
            CharacterDetailsContent(character)
        }
    }

    // Gérer le bouton retour physique
    BackHandler {
        onBackClick()
    }
}

@Composable
fun CharacterDetailsContent(character: Character) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image du personnage
        AsyncImage(
            model = character.imageUrl,
            contentDescription = "Image de ${character.name}",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp)
        )

        // On retire le nom d'ici car il est maintenant dans la toolbar

        DetailItem(label = "Statut", value = character.status)
        DetailItem(label = "Espèce", value = character.species)

        if (character.type.isNotEmpty()) {
            DetailItem(label = "Type", value = character.type)
        }

        DetailItem(label = "Genre", value = character.gender)
        DetailItem(label = "Origine", value = character.origin)
        DetailItem(label = "Localisation", value = character.location)
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "$label : ",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.weight(0.4f)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(0.6f)
            )
        }
    }
}