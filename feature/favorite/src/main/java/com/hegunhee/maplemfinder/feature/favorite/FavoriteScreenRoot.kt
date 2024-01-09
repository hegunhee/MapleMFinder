package com.hegunhee.maplemfinder.feature.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hegunhee.maplefinder.core.model.mapleM.Character
import com.hegunhee.maplemfinder.core.ui.card.CharacterCard

@Composable
fun FavoriteScreenRoot(
    viewModel : FavoriteViewModel = hiltViewModel(),
    onItemClick : (String) -> Unit
) {
    LaunchedEffect(key1 = viewModel.uiState) {
        viewModel.fetchData()
    }
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    when(uiState) {
        FavoriteUiState.Loading -> { }
        is FavoriteUiState.Success -> {
            FavoriteScreen(characterList = uiState.favoriteCharacterList, onItemClick = onItemClick)
        }
        FavoriteUiState.Error -> { }
    }
}

@Composable
fun FavoriteScreen(
    characterList : List<Character>,
    onItemClick : (String) -> Unit
) {
    Column {
        Text(text = "즐겨찾기",fontSize = 30.sp)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = characterList,key = { it.ocid}) { character ->
                CharacterCard(
                    character = character,
                    isNormalCharacterCard = false,
                    onCardClick = onItemClick,
                    onLikeClick = { },
                    onFavoriteClick = { })
            }
        }
    }
}