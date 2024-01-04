package com.hegunhee.maplemfinder.feature.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavController.navigateSearch() {
    navigate(SearchNavGraph.searchRoute)
}

fun NavGraphBuilder.searchNavGraph() {
    composable(route = SearchNavGraph.searchRoute) {
        SearchScreenRoot()
    }
}
object SearchNavGraph {
    const val searchRoute = "Search"
}