package com.jmperezra.bluey.feature.characters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmperezra.bluey.core.domain.ErrorApp
import com.jmperezra.bluey.feature.characters.domain.GetCharacterDetailUseCase
import kotlinx.coroutines.launch

class CharacterDetailViewModel(private val getCharacterUseCase: GetCharacterDetailUseCase) :
    ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    fun loadCharacter(characterId: String) {
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true)
            val result = try {
                getCharacterUseCase(characterId)
            } catch (e: Exception) {
                Result.failure(ErrorApp.UnknownError)
            }
            result.fold(
                { onSuccessGetCharacters(it) },
                { onError(it as ErrorApp) }
            )
        }
    }

    private fun onSuccessGetCharacters(character: GetCharacterDetailUseCase.Output) {
        _uiState.value = UiState(character = character)
        //onError(ErrorApp.UnknownError)
    }

    private fun onError(errorApp: ErrorApp) {
        _uiState.value = UiState(errorApp = errorApp)
    }

    data class UiState(
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val character: GetCharacterDetailUseCase.Output? = null
    )
}