package com.hegunhee.maplemfinder.feature.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavController.navigateMain() {
    navigate(MainNavGraph.mainRoute)
}

fun NavGraphBuilder.mainNavGraph(
    onSearchClick : () -> Unit,
    onFavoriteClick : () -> Unit,
    onDetailClick : (String) -> Unit
) {
    composable(route = MainNavGraph.mainRoute) {
        MainScreenRoot(
            onSearchClick = onSearchClick,
            onFavoriteClick = onFavoriteClick,
            onDetailClick = onDetailClick
        )
    }
}
object MainNavGraph {
    const val mainRoute = "Main"
}