package com.hegunhee.maplemfinder.feature.detail

import com.hegunhee.maplefinder.core.model.mapleM.Character

sealed interface DetailUiState {

    object Loading : DetailUiState

    data class Success(
        val character : Character
    ) : DetailUiState

    object Error : DetailUiState
}