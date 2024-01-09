package com.hegunhee.maplemfinder.core.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hegunhee.maplemfinder.core.designsystem.R
import com.hegunhee.maplemfinder.core.designsystem.theme.DarkYellow

@Composable
fun SearchFailureCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(bottom = 100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(50.dp),
            painter = painterResource(id = R.drawable.ic_default_server_mark_24),
            contentDescription = null,
            tint = DarkYellow
        )
        Text(text = "검색 결과가 존재하지 않습니다.", fontSize = 20.sp)
    }
}

@Preview
@Composable
private fun SearchFailureCardPreview() {
    SearchFailureCard()
}