package com.hegunhee.maplemfinder.feature.search


sealed interface SearchUiState {

    object Loading : SearchUiState

    data class Success(val searchState : SearchState) : SearchUiState

    data class Error(val error : Exception) : SearchUiState
}