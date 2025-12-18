package fr.epsi.krynen.rickandmorty


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.epsi.krynen.rickandmorty.network.RickAndMortyApi
import kotlinx.coroutines.launch

sealed interface CharactersUiState {
    data class Success(val characters: List<Character>) : CharactersUiState
    object Error : CharactersUiState
    object Loading : CharactersUiState
}

class CharactersListViewModel : ViewModel() {
    var uiState: CharactersUiState by mutableStateOf(CharactersUiState.Loading)
        private set

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            uiState = CharactersUiState.Loading
            uiState = try {
                val response = RickAndMortyApi.retrofitService.getCharacters()
                CharactersUiState.Success(response.results)
            } catch (e: Exception) {
                CharactersUiState.Error
            }
        }
    }
}