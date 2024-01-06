package com.hegunhee.maplemfinder.core.ui.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.hegunhee.maplefinder.core.model.mapleM.MapleMWorld
import com.hegunhee.maplemfinder.core.designsystem.R

@Composable
fun WorldSelectDialog(
    worldList : List<MapleMWorld>,
    onDismissDialog : () -> Unit,
    onWorldItemClick : (MapleMWorld) -> Unit
) {
    Dialog(onDismissRequest = onDismissDialog) {
        Surface {
            LazyColumn {
                item {
                    Text(text = "월드를 선택해주세요", fontSize = 30.sp)
                }
                items(items = worldList, key = { it.name }) { world ->
                    Row(
                        modifier = Modifier
                            .width(IntrinsicSize.Max)
                            .clickable {
                                onWorldItemClick(world)
                                onDismissDialog()
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(modifier = Modifier.size(20.dp),painter = painterResource(id = world.icon), contentDescription = world.name, tint = Color.Unspecified)
                        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                        Text(text = world.name, fontSize = 20.sp)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun WorldSelectDialogPreview() {
    WorldSelectDialog(
        worldList = listOf(
            MapleMWorld(name = "서버1", icon = R.drawable.ic_like_24),
            MapleMWorld(name = "서버2", icon = R.drawable.ic_default_server_mark_24)
        ),
        onDismissDialog = {  },
        onWorldItemClick = {}
    )
}