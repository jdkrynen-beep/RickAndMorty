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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Arrangement

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
            image = characterImage,
            status = characterStatus,
            species = characterSpecies,
            type = characterType,
            gender = characterGender,
            origin = Origin(name = characterOrigin),
            location = Location(name = characterLocation)
        )

        setContent {
            RickAndMortyTheme {
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
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image du personnage dans une Card avec ombre
        Card(
            modifier = Modifier
                .size(250.dp)
                .padding(bottom = 24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = "Image de ${character.name}",
                modifier = Modifier.fillMaxSize()
            )
        }

        // Section Informations de base
        Text(
            text = "Informations",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Disposition en grille 2x2 avec Row et Column
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                DetailCard(label = "Statut", value = character.status)
                DetailCard(label = "Genre", value = character.gender)
            }
            Column(modifier = Modifier.weight(1f)) {
                DetailCard(label = "Espèce", value = character.species)
                if (character.type.isNotEmpty()) {
                    DetailCard(label = "Type", value = character.type)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Section Localisation
        Text(
            text = "Localisation",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        DetailCard(label = "Origine", value = character.origin.name, fullWidth = true)
        DetailCard(label = "Localisation actuelle", value = character.location.name, fullWidth = true)
    }
}

@Composable
fun DetailCard(label: String, value: String, fullWidth: Boolean = false) {
    Card(
        modifier = Modifier
            .then(if (fullWidth) Modifier.fillMaxWidth() else Modifier)
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}
