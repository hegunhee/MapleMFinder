package com.hegunhee.maplemfinder.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hegunhee.maplemfinder.app.ui.theme.MapleMFinderTheme
import com.hegunhee.maplemfinder.feature.main.MainNavGraph
import com.hegunhee.maplemfinder.feature.main.mainNavGraph

@Composable
fun MapleMFinderApp(
    rememberMapleMAppState : MapleMAppState = rememberMapleMAppState()
) {
    MapleMFinderTheme {
        NavHost(navController = rememberMapleMAppState.navController, startDestination = MainNavGraph.mainRoute) {
            mainNavGraph()
        }
    }
}

@Composable
fun rememberMapleMAppState(
    navController: NavHostController = rememberNavController()
) : MapleMAppState {
    return remember(key1 = navController) {
        MapleMAppState(navController)
    }

}
class MapleMAppState(
    val navController : NavHostController
) {

    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    fun popBackStack() {
        navController.popBackStack()
    }
}