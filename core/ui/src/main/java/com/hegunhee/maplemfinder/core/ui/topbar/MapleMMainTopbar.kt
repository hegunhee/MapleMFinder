package com.hegunhee.maplemfinder.core.ui.topbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hegunhee.maplemfinder.core.designsystem.theme.DarkYellow

@Composable
fun MapleMMainTopbar(
    
) {
    Text(modifier = Modifier.fillMaxWidth().padding(10.dp),text = "메이플M 검색기", fontSize = 30.sp,color = DarkYellow, textAlign =  TextAlign.Center)
}