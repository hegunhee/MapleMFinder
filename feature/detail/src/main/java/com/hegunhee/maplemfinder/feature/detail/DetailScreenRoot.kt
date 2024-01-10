package com.hegunhee.maplemfinder.feature.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailScreenRoot(
    ocid : String,
    popBackStack : () -> Unit
) {
    if(ocid == "") {
        popBackStack()
    }
    Text(text = ocid)
}