package com.hegunhee.maplemfinder.feature.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hegunhee.maplefinder.core.model.Character
import com.hegunhee.maplemfinder.core.ui.card.CharacterCard
import com.hegunhee.maplemfinder.core.ui.card.MainFavoriteButton
import com.hegunhee.maplemfinder.core.ui.card.MainLikeButton
import com.hegunhee.maplemfinder.core.ui.card.MainSearchButton
import com.hegunhee.maplemfinder.core.ui.topbar.MapleMMainTopbar

@Composable
fun MainScreenRoot(
    viewModel : MainViewModel = hiltViewModel(),
    onSearchClick : () -> Unit,
    onFavoriteClick : () -> Unit,
    onDetailClick : (String) -> Unit
) {
    LaunchedEffect(key1 = viewModel.uiState) {
        viewModel.fetchData()
    }
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    when(uiState) {
        is MainUiState.Loading -> { }
        is MainUiState.Success -> {
            MainScreen(
                mainCharacter = uiState.mainCharacter,
                isFavoriteListEmpty = uiState.isFavoriteListEmpty,
                onSearchClick = onSearchClick,
                onFavoriteClick = onFavoriteClick,
                onDetailClick = onDetailClick
            )
        }
        is MainUiState.Error -> { }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreen(
    mainCharacter : Character,
    isFavoriteListEmpty : Boolean,
    onSearchClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onDetailClick : (String) -> Unit
) {
    Scaffold(
        topBar = {
            MapleMMainTopbar()
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(vertical = 10.dp, horizontal = 10.dp)) {
            MainSearchButton(
                onItemClick = onSearchClick
            )
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            if(mainCharacter == Character.EMPTY) {
                MainLikeButton(
                    onItemClick = onSearchClick
                )
            }else {
                Text(text = "메인 캐릭터")
                CharacterCard(character = mainCharacter,isNormalCharacterCard = false, onCardClick = onDetailClick, onLikeClick = { }, onFavoriteClick = { })
            }
            Spacer(modifier = Modifier.padding(vertical = 10.dp))

            val favoriteClick = if(isFavoriteListEmpty) {
                onSearchClick
            } else {
                onFavoriteClick
            }
            MainFavoriteButton(
                onItemClick = favoriteClick,
                isFavoriteItemEmpty = isFavoriteListEmpty
            )
            Text(modifier = Modifier.fillMaxWidth(),text = "Data based on NEXON Open API", textAlign = TextAlign.End)
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen(
        mainCharacter = Character.EMPTY,
        isFavoriteListEmpty = false,
        onSearchClick = { },
        onFavoriteClick = { },
        onDetailClick = { }
    )
}