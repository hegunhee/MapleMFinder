package com.hegunhee.maplemfinder.feature.favorite

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


fun NavController.navigateFavorite() {
    navigate(FavoriteNavGraph.favoriteRoute)
}

fun NavGraphBuilder.favoriteNavGraph(
    onDetailClick : (String) -> Unit
) {
    composable(route = FavoriteNavGraph.favoriteRoute) {
        FavoriteScreenRoot(onItemClick = onDetailClick)
    }
}
object FavoriteNavGraph {
    const val favoriteRoute = "favorite"
}