package com.hegunhee.maplemfinder.feature.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.*

@Composable
fun TestMain(
    viewModel : TestViewModel = viewModel()
) {
    Text(viewModel.getThree().toString())
}