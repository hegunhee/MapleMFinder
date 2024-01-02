package com.hegunhee.maplemfinder.core.ui.card

import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import com.hegunhee.maplemfinder.core.designsystem.theme.MainFontSize
import com.hegunhee.maplemfinder.core.designsystem.theme.MapleColor
import com.hegunhee.maplemfinder.core.designsystem.R
import com.hegunhee.maplemfinder.core.designsystem.theme.MainMiddleButtonSize

@Composable
fun MainSearchButton(
    onItemClick : () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .heightIn(min = MainMiddleButtonSize)
            .fillMaxWidth()
            .clickable { onItemClick() }
            .border(width = 1.dp,color = MapleColor,shape = RectangleShape)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = R.drawable.ic_add_24),
            contentDescription = null,
            tint = MapleColor)
        Text(text = "캐릭터 검색", fontSize = MainFontSize,color = MapleColor)
    }
}

@Preview
@Composable
private fun MainSearchButtonPreview() {
   MainSearchButton()
}