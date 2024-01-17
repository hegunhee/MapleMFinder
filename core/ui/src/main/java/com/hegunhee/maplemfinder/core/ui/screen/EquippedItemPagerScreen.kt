package com.hegunhee.maplemfinder.core.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hegunhee.maplefinder.core.model.Item
import com.hegunhee.maplefinder.core.model.ItemGrade

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EquippedItemPagerScreen(
    equippedItemList : List<Item>,
    pagerState : PagerState
) {
    HorizontalPager(pageCount = EQUIPPED_ITEM_PAGING_SIZE,state = pagerState) { page ->
        val currentItemList = if(page == 0) {
            equippedItemList.filter { it.grade != ItemGrade.Cash }
        }else {
            equippedItemList.filter { it.grade == ItemGrade.Cash }
        }
        val itemDescription = if(page == 0) {
            "아이템 정보"
        }else {
            "캐쉬 아이템 정보"
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                Text(modifier = Modifier.fillMaxWidth(),text = itemDescription, fontSize = 17.sp, textAlign = TextAlign.Center)
            }
            items(items = currentItemList, key = { it.itemName }) { item ->
                Text("${item.slotName} : ${item.itemName}", fontSize = 12.sp)
            }
        }
    }
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        repeat(EQUIPPED_ITEM_PAGING_SIZE) { iteration ->
            val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(16.dp)
            )
        }
    }
}

private const val EQUIPPED_ITEM_PAGING_SIZE = 2