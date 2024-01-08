package com.hegunhee.maplemfinder.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.maplefinder.core.model.mapleM.MapleMWorld
import com.hegunhee.maplemfinder.core.domain.usecase.SetToggleFavoriteOcidUseCase
import com.hegunhee.maplemfinder.core.domain.usecase.GetCharacterUseCase
import com.hegunhee.maplemfinder.core.domain.usecase.GetWorldListUseCase
import com.hegunhee.maplemfinder.core.domain.usecase.SetMainOcidUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getWorldListUseCase: GetWorldListUseCase,
    private val getCharacterUseCase : GetCharacterUseCase,
    private val setMainOcidUseCase: SetMainOcidUseCase,
    private val setToggleFavoriteOcidUseCase: SetToggleFavoriteOcidUseCase
) : ViewModel() {

    private val _worldList : MutableStateFlow<List<MapleMWorld>> = MutableStateFlow(getWorldListUseCase())
    val worldList : StateFlow<List<MapleMWorld>> = _worldList.asStateFlow()

    private val _searchQuery : MutableStateFlow<String> = MutableStateFlow("")
    val searchQuery : StateFlow<String> = _searchQuery.asStateFlow()

    private val _uiState : MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState.Loading)
    val uiState : StateFlow<SearchUiState> = _uiState.asStateFlow()

    init {
        fetchData()
    }

    fun setSearchQuery(text : String) {
        _searchQuery.value = text
    }

    private fun fetchData() {
        _uiState.value = SearchUiState.Success(searchState = SearchState.History(listOf()))
    }

    fun searchCharacter(name : String,world :MapleMWorld) {
        viewModelScope.launch {
            getCharacterUseCase(name = name, worldName = world.name)
                .onSuccess {
                    _uiState.value = SearchUiState.Success(searchState = SearchState.Success(it))
                }.onFailure {
                    _uiState.value = SearchUiState.Success(searchState = SearchState.Failure)
                }
        }
    }

    fun onCharacterLikeClick(ocid : String,name : String,world : MapleMWorld) {
        viewModelScope.launch {
            setMainOcidUseCase(ocid)
            getCharacterUseCase(name = name, worldName = world.name)
                .onSuccess {
                    _uiState.value = SearchUiState.Success(searchState = SearchState.Success(it))
                }.onFailure {
                    _uiState.value = SearchUiState.Success(searchState = SearchState.Failure)
                }
        }
    }

    fun onCharacterFavoriteClick(ocid : String,name : String,world : MapleMWorld) {
        viewModelScope.launch {
            setToggleFavoriteOcidUseCase(ocid)
            getCharacterUseCase(name = name, worldName = world.name)
                .onSuccess {
                    _uiState.value = SearchUiState.Success(searchState = SearchState.Success(it))
                }.onFailure {
                    _uiState.value = SearchUiState.Success(searchState = SearchState.Failure)
                }
        }
    }
}