package com.jmperezra.bluey.feature.characters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmperezra.bluey.core.domain.ErrorApp
import com.jmperezra.bluey.feature.characters.domain.GetCharactersUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CharactersViewModel(private val getCharactersUseCase: GetCharactersUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    fun loadCharacters() {
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true)
            val result = try {
                getCharactersUseCase()
            } catch (e: Exception) {
                Result.failure(ErrorApp.UnknownError)
            }
            result.fold(
                { onSuccessGetCharacters(it) },
                { onError(it as ErrorApp) }
            )
        }
    }

    private fun onSuccessGetCharacters(characters: List<GetCharactersUseCase.Output>) {
        _uiState.value = UiState(characters = characters)
    }

    private fun onError(errorApp: ErrorApp) {
        _uiState.value = UiState(errorApp = errorApp)
    }

    data class UiState(
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val characters: List<GetCharactersUseCase.Output>? = null,
        val onCharacterClicked: ((String) -> Unit)? = null
    )
}