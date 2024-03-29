package com.hegunhee.maplemfinder.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.maplemfinder.core.domain.usecase.GetCharacterUseCase
import com.hegunhee.maplemfinder.core.domain.usecase.UpdateMainOcidUseCase
import com.hegunhee.maplemfinder.core.domain.usecase.ToggleFavoriteOcidUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val updateMainOcidUseCase: UpdateMainOcidUseCase,
    private val toggleFavoriteOcidUseCase: ToggleFavoriteOcidUseCase
) : ViewModel(){

    private val _uiState : MutableStateFlow<DetailUiState> = MutableStateFlow(DetailUiState.Loading)
    val uiState : StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun fetchData(ocid : String) {
        viewModelScope.launch {
            getCharacterUseCase(ocid = ocid)
                .onSuccess {
                    _uiState.value = DetailUiState.Success(it)
                }.onFailure {

                }
        }
    }

    fun setMainCharacter(ocid : String) {
        viewModelScope.launch {
            updateMainOcidUseCase(ocid)
            getCharacter(ocid)
        }
    }

    fun toggleFavoriteCharacter(ocid : String) {
        viewModelScope.launch {
            toggleFavoriteOcidUseCase(ocid)
            getCharacter(ocid)
        }
    }

    private suspend fun getCharacter(ocid : String) {
        getCharacterUseCase(ocid = ocid)
            .onSuccess {
                _uiState.value = DetailUiState.Success(it)
            }.onFailure {

            }
    }
}