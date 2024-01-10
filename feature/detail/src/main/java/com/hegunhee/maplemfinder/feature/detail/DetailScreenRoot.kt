package com.hegunhee.maplemfinder.feature.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hegunhee.maplefinder.core.model.mapleM.Character
import com.hegunhee.maplefinder.core.model.mapleM.CharacterDate
import com.hegunhee.maplefinder.core.model.mapleM.CharacterInfo
import com.hegunhee.maplefinder.core.model.mapleM.CharacterStatus
import com.hegunhee.maplefinder.core.model.mapleM.Item
import com.hegunhee.maplefinder.core.model.mapleM.MapleMWorld
import com.hegunhee.maplemfinder.core.designsystem.R
import com.hegunhee.maplemfinder.core.designsystem.theme.DeepSkyBlue
import com.hegunhee.maplemfinder.core.designsystem.theme.MapleColor
import com.hegunhee.maplemfinder.core.designsystem.theme.Sky

@Composable
fun DetailScreenRoot(
    viewModel : DetailViewModel = hiltViewModel(),
    ocid : String,
    popBackStack : () -> Unit
) {
    if(ocid == "") {
        OcidEmptyAlertDialog(popBackStack)
    }
    LaunchedEffect(key1 = viewModel.uiState) {
        viewModel.fetchData(ocid)
    }
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    when(uiState) {
        DetailUiState.Loading -> { }
        is DetailUiState.Success -> {
            DetailScreen(
                character = uiState.character,
                onMainButtonClick = viewModel::setMainCharacter,
                onFavoriteButtonClick = viewModel::toggleFavoriteCharacter
            )
        }
        DetailUiState.Error -> { }
    }
}



@Composable
private fun OcidEmptyAlertDialog(
    popBackStack: () -> Unit
) {
    AlertDialog(
        onDismissRequest = popBackStack,
        confirmButton = {
            Button(onClick = popBackStack) {
                Text("확인")
            }
        },
        title = {
            Text(text = "해당 캐릭터를 조회할 수 없습니다.")
        }
    )
}

@Composable
private fun DetailScreen(
    character : Character,
    onMainButtonClick : (String) -> Unit,
    onFavoriteButtonClick : (String) -> Unit
) {
    Surface (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        color = Sky,
    ){
        Column {
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            DetailCharacterHeader(
                ocid = character.ocid,
                world = character.world,
                name = character.name,
                level = character.info.level,
                isMain = character.isMain,
                isFavorite = character.isFavorite,
                onLikeClick = onMainButtonClick,
                onFavoriteButtonClick = onFavoriteButtonClick
            )
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
            DetailCharacterInfo(info = character.info, date = character.date)
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
            DetailCharacterStatus(statusList = character.statusList)
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
            DetailCharacterItems(equippedItemList = character.equippedItemList)
            Spacer(modifier = Modifier.padding(vertical = 20.dp))
        }
    }
}

@Composable
private fun DetailCharacterHeader(
    ocid : String,
    world : MapleMWorld,
    name : String,
    level : Int,
    isMain : Boolean,
    isFavorite : Boolean,
    onLikeClick : (String) -> Unit,
    onFavoriteButtonClick: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Spacer(modifier = Modifier.width(10.dp))
        Icon(modifier = Modifier.size(20.dp),painter = painterResource(world.icon),contentDescription = null,tint = Color.Unspecified)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = name, fontSize = 20.sp)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "Lv. $level", fontSize = 18.sp,color = MapleColor)
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20))
                .background(color = DeepSkyBlue)
        ) {
            IconButton(
                onClick = { onLikeClick(ocid) }) {
                Icon(
                    painter = painterResource(R.drawable.ic_like_24),
                    contentDescription = world.name,
                    tint = if (isMain) {
                        Color.Red
                    } else {
                        Color.White
                    }
                )
            }
            Spacer(
                modifier = Modifier
                    .width(5.dp)
                    .height(48.dp)
                    .background(Color.White)
            )
            IconButton(onClick = { onFavoriteButtonClick(ocid) }) {
                Icon(
                    painter = painterResource(R.drawable.ic_star_24),
                    contentDescription = world.name,
                    tint = if (isFavorite) {
                        Color.Yellow
                    } else {
                        Color.White
                    }
                )
            }
        }
    }
}

@Composable
private fun DetailCharacterInfo(
    info : CharacterInfo,
    date : CharacterDate
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ){
        Text(text = "캐릭터 정보", fontSize = 13.sp)
        Spacer(modifier = Modifier.padding(bottom = 5.dp))
        Text("최근 접속 : ${date.loginStateText}", fontSize = 15.sp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text("직업 : ${info.jobName}", fontSize = 15.sp)
            Spacer(modifier = Modifier.width(10.dp))
            Text("성별 : ${info.gender}", fontSize = 15.sp)
        }
    }
}

@Composable
private fun ColumnScope.DetailCharacterStatus(
    statusList : List<CharacterStatus>
) {
    Text(text = "캐릭터 정보", fontSize = 13.sp)
    val powerLevel = statusList.find { it.name == "전투력" }?.value?.toInt() ?: 0
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "전투력 : $powerLevel",
        fontSize = 20.sp,
        textAlign = TextAlign.Center
    )
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(items = statusList.drop(1), key = {it.name}) { status ->
            Text("${status.name} : ${status.value}", fontSize = 15.sp)
        }
    }
}

@Composable
private fun DetailCharacterItems(
    equippedItemList : List<Item>
) {
    LazyColumn {
        item {
            Text(text = "장비 정보", fontSize = 13.sp)
        }
        items(items = equippedItemList, key = { it.itemName}) { item ->
            Text("${item.slotName} : ${item.itemName}", fontSize = 10.sp)
        }
    }
}