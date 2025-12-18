package fr.epsi.krynen.rickandmorty

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("species")
    val species: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("origin")
    val origin: Origin,

    @SerializedName("location")
    val location: Location,

    @SerializedName("image")
    val image: String
)

data class Origin(
    @SerializedName("name")
    val name: String
)

data class Location(
    @SerializedName("name")
    val name: String
)

data class CharacterResponse(
    @SerializedName("info")
    val info: Info,

    @SerializedName("results")
    val results: List<Character>
)

data class Info(
    @SerializedName("count")
    val count: Int,

    @SerializedName("pages")
    val pages: Int,

    @SerializedName("next")
    val next: String?,

    @SerializedName("prev")
    val prev: String?
)