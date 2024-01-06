package com.hegunhee.maplemfinder.feature.search

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hegunhee.maplefinder.core.model.mapleM.MapleMWorld
import com.hegunhee.maplemfinder.core.ui.button.WorldSelectButton
import com.hegunhee.maplemfinder.core.ui.button.defaultWorld

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreenRoot(
    viewModel : SearchViewModel = hiltViewModel()
) {
    val worldList = viewModel.worldList.collectAsStateWithLifecycle().value
    val searchQuery = viewModel.searchQuery.collectAsStateWithLifecycle().value
    var selectedWorld by remember { mutableStateOf(defaultWorld) }
    val setWorld : (MapleMWorld) -> Unit = { world -> selectedWorld = world }

    SearchScreen(
        worldList = worldList,
        name = searchQuery, 
        selectedWorld = selectedWorld, 
        setName = viewModel::setSearchQuery, 
        setWorld = setWorld,
        onSelectedWorldButtonClick = { },
        onSearchClick = {name, world -> }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
private fun SearchScreen(
    worldList : List<MapleMWorld>,
    name : String,
    selectedWorld : MapleMWorld,
    setName : (String) -> Unit,
    setWorld : (MapleMWorld) -> Unit,
    onSelectedWorldButtonClick : () -> Unit,
    onSearchClick : (String, MapleMWorld) -> Unit,
    keyboardController : SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
    context : Context = LocalContext.current
) {
    Column(modifier = Modifier.padding(10.dp)) {
        WorldSelectButton(onButtonClick = onSelectedWorldButtonClick, selectedWorld = selectedWorld)
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
                if(selectedWorld == defaultWorld) {
                    Toast.makeText(context,"월드를 선택해주세요",Toast.LENGTH_SHORT).show()
                    onSelectedWorldButtonClick()
                } else{
                    onSearchClick(name,selectedWorld)
                }
                keyboardController?.hide()
            }),
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
private fun SearchScreenPreview() {

    SearchScreen(
        worldList = listOf(),
        name = "",
        selectedWorld = defaultWorld,
        setName = { },
        setWorld = { },
        onSelectedWorldButtonClick = { },
        onSearchClick = { name, world -> }
    )
}