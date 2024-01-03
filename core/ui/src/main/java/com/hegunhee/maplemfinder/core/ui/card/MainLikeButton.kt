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
import com.hegunhee.maplemfinder.core.designsystem.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hegunhee.maplemfinder.core.designsystem.theme.LikeColor
import com.hegunhee.maplemfinder.core.designsystem.theme.MainFontSize
import com.hegunhee.maplemfinder.core.designsystem.theme.MainTopButtonSize
import com.hegunhee.maplemfinder.core.designsystem.theme.NormalStroke

@Composable
fun MainLikeButton(
    onItemClick : () -> Unit = { }
) {
    Row(
        modifier = Modifier
            .heightIn(min = MainTopButtonSize)
            .fillMaxWidth()
            .clickable { onItemClick() }
            .drawBehind {
                drawRoundRect(
                    color = LikeColor,
                    style = NormalStroke)
            }
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = R.drawable.ic_like_24),
            contentDescription = null,
            tint = LikeColor
        )
        Text(text = "대표 캐릭터 설정하기", fontSize = MainFontSize,color = LikeColor)
    }
}

@Preview
@Composable
private fun MainLikeButtonPreview() {
    MainLikeButton()
}