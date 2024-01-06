package com.hegunhee.maplemfinder.feature.search

import androidx.lifecycle.ViewModel
import com.hegunhee.maplefinder.core.model.mapleM.MapleMWorld
import com.hegunhee.maplemfinder.core.domain.usecase.GetWorldListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getWorldListUseCase: GetWorldListUseCase
) : ViewModel() {

    init {
        fetchData()
    }
    private val _worldList : MutableStateFlow<List<MapleMWorld>> = MutableStateFlow(getWorldListUseCase())
    val worldList : StateFlow<List<MapleMWorld>> = _worldList.asStateFlow()

    private val _searchQuery : MutableStateFlow<String> = MutableStateFlow("")
    val searchQuery : StateFlow<String> = _searchQuery.asStateFlow()

    private val _uiState : MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState.Loading)
    val uiState : StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun setSearchQuery(text : String) {
        _searchQuery.value = text
    }

    private fun fetchData() {

    }

    fun searchCharacter(name : String,world :MapleMWorld) {

    }
}