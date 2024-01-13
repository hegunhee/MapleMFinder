package com.hegunhee.maplemfinder.feature.favorite

import com.hegunhee.maplefinder.core.model.Character

sealed interface FavoriteUiState {

    object Loading : FavoriteUiState

    data class Success(
        val favoriteCharacterList : List<Character>
    ) : FavoriteUiState

    object Error : FavoriteUiState
}