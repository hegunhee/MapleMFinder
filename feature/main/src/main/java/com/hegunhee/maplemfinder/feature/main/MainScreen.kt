package com.hegunhee.maplemfinder.feature.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hegunhee.maplemfinder.core.ui.card.MainLikeButton
import com.hegunhee.maplemfinder.core.ui.card.MainSearchButton

@Composable
fun MainScreenRoot() {
    MainScreen()
}

@Composable
private fun MainScreen() {
    Column(modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)) {
        MainSearchButton()
        MainLikeButton()
    }
}