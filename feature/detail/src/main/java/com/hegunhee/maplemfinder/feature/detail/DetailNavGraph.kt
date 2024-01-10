package com.hegunhee.maplemfinder.feature.detail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun NavController.navigateDetail(ocid : String) {
    navigate(DetailNavGraph.detailRoute(ocid))
}

fun NavGraphBuilder.detailNavGraph(
    popBackStack : () -> Unit
) {
    composable(
        route = DetailNavGraph.detailRoute("{ocid}"),
        arguments = listOf(
            navArgument("ocid") {
                type = NavType.StringType
            }
        )
    ) { navBackStackEntry ->  
        val ocid =  navBackStackEntry.arguments?.getString("ocid") ?: ""
        DetailScreenRoot(
            ocid = ocid,
            popBackStack = popBackStack
        )
    }


}
object DetailNavGraph {
    fun detailRoute(ocid : String) : String{
        return "detail/$ocid"
    }
}