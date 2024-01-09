package com.hegunhee.maplemfinder.feature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.maplemfinder.core.domain.usecase.GetFavoriteCharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteCharacterListUseCase: GetFavoriteCharacterListUseCase
) : ViewModel(){

    private val _uiState : MutableStateFlow<FavoriteUiState> = MutableStateFlow(FavoriteUiState.Loading)
    val uiState : StateFlow<FavoriteUiState> = _uiState.asStateFlow()

    fun fetchData() {
        viewModelScope.launch {
            getFavoriteCharacterListUseCase()
                .onSuccess { characterList ->
                    _uiState.value = FavoriteUiState.Success(characterList)
                }.onFailure {

                }
        }
    }
}