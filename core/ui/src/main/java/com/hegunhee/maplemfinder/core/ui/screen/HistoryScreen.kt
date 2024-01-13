package com.hegunhee.maplemfinder.core.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hegunhee.maplefinder.core.model.CharacterSearch

@Composable
fun HistoryScreen(
    historyList : List<CharacterSearch>,
    onItemClick : (String) -> Unit,
    onHistoryDeleteClick : (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "최근 검색 캐릭터")
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(Color.Black))
        Spacer(modifier = Modifier.height(5.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(historyList,key = { it.ocid }) { history ->
                History(history = history, onItemClick = onItemClick,onHistoryDeleteClick = onHistoryDeleteClick)
            }
        }
    }
}


@Composable
private fun History(
    history : CharacterSearch,
    onItemClick: (String) -> Unit,
    onHistoryDeleteClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(history.ocid) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = history.world.icon),
            contentDescription = history.name,
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = history.name, fontSize = 20.sp)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { onHistoryDeleteClick(history.ocid) }) {
            Icon(imageVector = Icons.Default.Clear, contentDescription = history.name, tint = Color.Red)
        }
    }
}

@Preview
@Composable
private fun HistoryScreenPreview() {

}