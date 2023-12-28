package com.hegunhee.maplemfinder.feature.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavController.navigateMain() {
    navigate(MainNavGraph.mainRoute)
}

fun NavGraphBuilder.mainNavGraph() {
    composable(route = MainNavGraph.mainRoute) {
        MainScreenRoot()
    }
}
object MainNavGraph {
    const val mainRoute = "Main"
}