package com.hegunhee.maplemfinder.feature.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavController.navigateSearch() {
    navigate(SearchNavGraph.searchRoute)
}

fun NavGraphBuilder.searchNavGraph(
    onDetailClick : (String) -> Unit
) {
    composable(route = SearchNavGraph.searchRoute) {
        SearchScreenRoot(
            onDetailClick = onDetailClick
        )
    }
}
object SearchNavGraph {
    const val searchRoute = "Search"
}