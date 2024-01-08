package com.hegunhee.maplemfinder.core.ui.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hegunhee.maplemfinder.core.designsystem.R
import com.hegunhee.maplemfinder.core.designsystem.theme.DarkYellow
import com.hegunhee.maplemfinder.core.designsystem.theme.MainFontSize
import com.hegunhee.maplemfinder.core.designsystem.theme.MainTopButtonSize
import com.hegunhee.maplemfinder.core.designsystem.theme.NormalDottedStroke
import com.hegunhee.maplemfinder.core.designsystem.theme.NormalStroke

@Composable
fun MainFavoriteButton(
    onItemClick: () -> Unit = {},
    isFavoriteItemEmpty : Boolean
) {
    val stroke = if(isFavoriteItemEmpty) {
        NormalDottedStroke
    }else {
        NormalStroke
    }
    val title = if(isFavoriteItemEmpty) {
        "즐겨찾기 캐릭터 설정"
    }else {
        "즐겨찾기 캐릭터 확인"
    }
    val iconId = if(isFavoriteItemEmpty) {
        R.drawable.ic_empty_star_24
    }else {
        R.drawable.ic_star_24
    }
    Row(
        modifier = Modifier
            .heightIn(min = MainTopButtonSize)
            .fillMaxWidth()
            .clickable { onItemClick() }
            .drawBehind {
                drawRoundRect(
                    color = DarkYellow,
                    style = stroke
                )
            }
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = iconId),
            contentDescription = null,
            tint = DarkYellow
        )
        Text(text = title,fontSize = MainFontSize,color = DarkYellow)
    }
}

@Preview
@Composable
private fun MainFavoriteEmptyPreview() {
    MainFavoriteButton(
        isFavoriteItemEmpty = true
    )
}

@Preview
@Composable
private fun MainFavoriteContainPreview() {
    MainFavoriteButton(
        isFavoriteItemEmpty = false
    )
}