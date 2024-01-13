package com.hegunhee.maplemfinder.feature.main

import com.hegunhee.maplefinder.core.model.Character

sealed interface MainUiState {

    object Loading : MainUiState

    data class Success(
        val mainCharacter : Character,
        val isFavoriteListEmpty : Boolean
    ) : MainUiState

    data class Error(
        val errorMessage : String
    ) : MainUiState
}