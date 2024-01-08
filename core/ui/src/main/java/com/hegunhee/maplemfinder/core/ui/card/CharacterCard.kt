package com.hegunhee.maplemfinder.core.ui.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hegunhee.maplefinder.core.model.mapleM.Character
import com.hegunhee.maplefinder.core.model.mapleM.CharacterDate
import com.hegunhee.maplefinder.core.model.mapleM.CharacterStatus
import com.hegunhee.maplefinder.core.model.mapleM.MapleMWorld
import com.hegunhee.maplemfinder.core.designsystem.R
import com.hegunhee.maplemfinder.core.designsystem.theme.DeepSkyBlue
import com.hegunhee.maplemfinder.core.designsystem.theme.MapleColor
import com.hegunhee.maplemfinder.core.designsystem.theme.Sky

@Composable
fun CharacterCard(
    character : Character,
    onCardClick : (String) -> Unit,
    onLikeClick : (Boolean) -> Unit,
    onFavoriteClick : (Boolean) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClick(character.ocid) },
        shape = RoundedCornerShape(20),
        color = Sky,
    ) {
        Column {
            CharacterCardHeader(
                world = character.world,
                name = character.name,
                level = character.info.level,
                isMain = character.isMain,
                isFavorite = character.isFavorite,
                onLikeClick = onLikeClick,
                onFavoriteClick = onFavoriteClick
            )
            CharacterCardContents(
                date = character.date,
                jobName = character.info.jobName,
                gender = character.info.gender,
                statusList = character.statusList
            )
        }
    }
}

@Composable
private fun CharacterCardHeader(
    world : MapleMWorld,
    name : String,
    level : Int,
    isMain : Boolean = false,
    isFavorite : Boolean = false,
    onLikeClick: (Boolean) -> Unit,
    onFavoriteClick: (Boolean) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(10.dp))
        Icon(modifier = Modifier.size(20.dp),painter = painterResource(world.icon),contentDescription = null,tint = Color.Unspecified)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = name, fontSize = 20.sp)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "Lv. $level", fontSize = 18.sp,color = MapleColor)
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(20))
            .background(color = DeepSkyBlue)) {
            IconButton(
                onClick = { onLikeClick(isMain) }) {
                Icon(
                    painter = painterResource(R.drawable.ic_like_24),
                    contentDescription = world.name,
                    tint = if(isMain) {
                        Color.Red
                    }else {
                        Color.White
                    }
                )
            }
            Spacer(modifier = Modifier
                .width(5.dp)
                .height(48.dp)
                .background(Color.White))
            IconButton(onClick = { onFavoriteClick(isFavorite) }) {
                Icon(
                    painter = painterResource(R.drawable.ic_star_24),
                    contentDescription = world.name,
                    tint = if(isFavorite) {
                        Color.Yellow
                    }else {
                        Color.White
                    }
                )
            }
        }
    }
}

@Composable
private fun CharacterCardContents(
    date : CharacterDate,
    jobName : String,
    gender : String,
    statusList : List<CharacterStatus>,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp, horizontal = 10.dp),
        shape = RoundedCornerShape(20),
        color = DeepSkyBlue,
    ) {
        Column {
            val powerLevel = statusList.find { it.name == "전투력" }?.value?.toInt() ?: 0
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "전투력 : $powerLevel",
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
            // 추후 loginState를 구체화할예정
            val loginState = if(date.lastLoginDate > date.lastLogoutDate) {
                "최근 접속중"
            }else {
                date.lastLoginDate
            }
            Text("최근 접속 : $loginState", fontSize = 15.sp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text("직업 : $jobName", fontSize = 15.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Text("성별 : $gender", fontSize = 15.sp)
            }
        }
    }
}

@Preview
@Composable
private fun CharacterCardPreview() {
    CharacterCard(
        character = Character.EMPTY,
        onCardClick = { },
        onLikeClick = { },
        onFavoriteClick = { }
    )
}

