package com.hegunhee.maplemfinder.feature.search

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hegunhee.maplefinder.core.model.mapleM.Character
import com.hegunhee.maplefinder.core.model.mapleM.CharacterSearch
import com.hegunhee.maplefinder.core.model.mapleM.MapleMWorld
import com.hegunhee.maplemfinder.core.ui.button.WorldSelectButton
import com.hegunhee.maplemfinder.core.ui.button.defaultWorld
import com.hegunhee.maplemfinder.core.ui.card.CharacterCard
import com.hegunhee.maplemfinder.core.ui.card.SearchFailureCard
import com.hegunhee.maplemfinder.core.ui.dialog.WorldSelectDialog

@Composable
fun SearchScreenRoot(
    viewModel : SearchViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    val worldList = viewModel.worldList.collectAsStateWithLifecycle().value
    val searchQuery = viewModel.searchQuery.collectAsStateWithLifecycle().value
    var selectedWorld by remember { mutableStateOf(defaultWorld) }
    val setWorld : (MapleMWorld) -> Unit = { world -> selectedWorld = world }

    var isWorldDialogOpen by remember { mutableStateOf(false) }
    val openWorldDialog : (Boolean) -> Unit = { dialogState ->
        isWorldDialogOpen = dialogState
    }

    when(uiState) {
        SearchUiState.Loading -> { }
        is SearchUiState.Success -> {
            SearchScreen(
                worldList = worldList,
                name = searchQuery,
                selectedWorld = selectedWorld,
                searchState = uiState.searchState,
                setName = viewModel::setSearchQuery,
                setWorld = setWorld,
                isWorldDialogOpen = isWorldDialogOpen,
                onSelectedWorldButtonClick = openWorldDialog,
                onSearchClick = viewModel::searchCharacter,
                onLikeButtonClick = viewModel::onCharacterLikeClick,
                onFavoriteButtonClick = viewModel::onCharacterFavoriteClick
            )
        }
        is SearchUiState.Error -> {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
private fun SearchScreen(
    worldList : List<MapleMWorld>,
    name : String,
    selectedWorld : MapleMWorld,
    isWorldDialogOpen : Boolean,
    searchState: SearchState,
    setName : (String) -> Unit,
    setWorld : (MapleMWorld) -> Unit,
    onSelectedWorldButtonClick : (Boolean) -> Unit,
    onSearchClick : (String, MapleMWorld) -> Unit,
    onLikeButtonClick : (String) -> Unit,
    onFavoriteButtonClick : (String) -> Unit,
    context : Context = LocalContext.current
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    if(isWorldDialogOpen) {
        WorldSelectDialog(worldList = worldList, onDismissDialog = { onSelectedWorldButtonClick(false)}, onWorldItemClick = setWorld)
    }
    Column(modifier = Modifier.padding(10.dp)) {
        WorldSelectButton(onButtonClick = { onSelectedWorldButtonClick(true) }, selectedWorld = selectedWorld)
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = setName,
            label = { Text("캐릭터 이름을 입력해주세요")},
            singleLine = true,
            maxLines = 1,
            trailingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Color.Black)
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                keyboardController?.hide()
                if(selectedWorld == defaultWorld) {
                    Toast.makeText(context,"월드를 선택해주세요",Toast.LENGTH_SHORT).show()
                    onSelectedWorldButtonClick(true)
                } else{
                    onSearchClick(name,selectedWorld)
                }
            }),
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        when(searchState) {
            is SearchState.History -> {

            }
            is SearchState.Success-> {
                CharacterCard(character = searchState.character, onCardClick = { }, onLikeClick = onLikeButtonClick, onFavoriteClick = onFavoriteButtonClick)
            }
            SearchState.Failure -> {
                SearchFailureCard()
            }
        }
    }
}

sealed interface SearchState {

    data class History(val searchList : List<CharacterSearch>) : SearchState

    data class Success (val character : Character) : SearchState

    object Failure : SearchState
}

@Preview
@Composable
private fun SearchScreenPreview() {

    SearchScreen(
        worldList = listOf(),
        name = "",
        selectedWorld = defaultWorld,
        isWorldDialogOpen = false,
        searchState = SearchState.Success(Character.EMPTY),
        setName = { },
        setWorld = { },
        onSelectedWorldButtonClick = {  },
        onSearchClick = { name, world -> },
        onLikeButtonClick = {  },
        onFavoriteButtonClick = { }
    )
}