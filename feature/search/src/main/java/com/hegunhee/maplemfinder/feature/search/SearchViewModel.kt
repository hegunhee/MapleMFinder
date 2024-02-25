package com.hegunhee.maplemfinder.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hegunhee.maplefinder.core.model.MapleMWorld
import com.hegunhee.maplemfinder.core.domain.usecase.ToggleFavoriteOcidUseCase
import com.hegunhee.maplemfinder.core.domain.usecase.GetCharacterUseCase
import com.hegunhee.maplemfinder.core.domain.usecase.GetHistoryCharacterListUseCase
import com.hegunhee.maplemfinder.core.domain.usecase.GetWorldListUseCase
import com.hegunhee.maplemfinder.core.domain.usecase.SetMainOcidUseCase
import com.hegunhee.maplemfinder.core.domain.usecase.ToggleHistoryOcidUseCase
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
    private val toggleFavoriteOcidUseCase: ToggleFavoriteOcidUseCase,
    private val getHistoryCharacterListUseCase: GetHistoryCharacterListUseCase,
    private val toggleHistoryOcidUseCase : ToggleHistoryOcidUseCase
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
        viewModelScope.launch {
            getHistoryCharacterListUseCase()
                .onSuccess {
                    _uiState.value = SearchUiState.Success(searchState = SearchState.History(it))
                }.onFailure {

                }
        }
    }

    fun searchCharacter(name : String,world : MapleMWorld) {
        viewModelScope.launch {
            getCharacterUseCase(name = name, worldName = world.name)
                .onSuccess {
                    _uiState.value = SearchUiState.Success(searchState = SearchState.Success(it))
                    toggleHistoryOcidUseCase(it.ocid)
                }.onFailure {
                    _uiState.value = SearchUiState.Success(searchState = SearchState.Failure)
                }
        }
    }

    fun onCharacterLikeClick(ocid : String) {
        viewModelScope.launch {
            setMainOcidUseCase(ocid)
            getCharacterUseCase(ocid = ocid)
                .onSuccess {
                    _uiState.value = SearchUiState.Success(searchState = SearchState.Success(it))
                }.onFailure {
                    _uiState.value = SearchUiState.Success(searchState = SearchState.Failure)
                }
        }
    }

    fun onCharacterFavoriteClick(ocid : String) {
        viewModelScope.launch {
            toggleFavoriteOcidUseCase(ocid)
            getCharacterUseCase(ocid = ocid)
                .onSuccess {
                    _uiState.value = SearchUiState.Success(searchState = SearchState.Success(it))
                }.onFailure {
                    _uiState.value = SearchUiState.Success(searchState = SearchState.Failure)
                }
        }
    }

    fun onHistoryCharacterDeleteClick(ocid : String) {
        viewModelScope.launch {
            toggleHistoryOcidUseCase(ocid)
//            deleteHistoryOcidUseCase(ocid)
            getHistoryCharacterListUseCase()
                .onSuccess {
                    _uiState.value = SearchUiState.Success(searchState = SearchState.History(it))
                }.onFailure {

                }
        }
    }
}