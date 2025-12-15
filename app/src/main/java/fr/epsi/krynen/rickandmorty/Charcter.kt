package fr.epsi.krynen.rickandmorty

data class Character(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val status: String = "Unknown",
    val species: String = "Unknown",
    val type: String = "",
    val gender: String = "Unknown",
    val origin: String = "Unknown",
    val location: String = "Unknown"
)