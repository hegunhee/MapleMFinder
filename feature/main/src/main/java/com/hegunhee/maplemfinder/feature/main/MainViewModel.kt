package com.hegunhee.maplemfinder.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.maplemfinder.core.domain.usecase.GetMainCharacterUseCase
import com.hegunhee.maplemfinder.core.domain.usecase.IsFavoriteListEmptyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMainCharacterUseCase: GetMainCharacterUseCase,
    private val isFavoriteListEmptyUseCase: IsFavoriteListEmptyUseCase
) : ViewModel() {

    private val _uiState : MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState.Loading)
    val uiState : StateFlow<MainUiState> = _uiState.asStateFlow()

    fun fetchData() {
        viewModelScope.launch {
            val mainCharacterResult = getMainCharacterUseCase()
            val isFavoriteListEmptyResult = isFavoriteListEmptyUseCase()
            if(mainCharacterResult.isSuccess && isFavoriteListEmptyResult.isSuccess) {
                _uiState.value = MainUiState.Success(
                    mainCharacter = mainCharacterResult.getOrThrow(),
                    isFavoriteListEmpty = isFavoriteListEmptyResult.getOrThrow()
                )
            }
        }
    }
}