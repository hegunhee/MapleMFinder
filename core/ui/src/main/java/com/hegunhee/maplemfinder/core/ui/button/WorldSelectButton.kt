package com.hegunhee.maplemfinder.core.ui.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hegunhee.maplefinder.core.model.mapleM.MapleMWorld
import com.hegunhee.maplemfinder.core.designsystem.R

@Composable
fun WorldSelectButton(
    onButtonClick : () -> Unit,
    selectedWorld : MapleMWorld 
) {
    Card(
        modifier = Modifier.clickable { onButtonClick() }
    ) {
        Row(
            modifier = Modifier
                .sizeIn(minHeight = 40.dp, minWidth = 60.dp)
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = selectedWorld.icon),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(text = selectedWorld.name)
        }
    }

}

@Preview
@Composable
private fun WorldSelectButtonPreview() {
    WorldSelectButton(onButtonClick = {  },selectedWorld = MapleMWorld(name = "월드 선택",R.drawable.ic_default_server_mark_24))
}

val defaultWorld =  MapleMWorld(name = "월드 선택", R.drawable.ic_default_server_mark_24)