package jp.cordea.d7362089.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import jp.cordea.d7362089.ui.details.Details
import jp.cordea.d7362089.ui.home.Home

private val TAG_HOME = Destination.Home.build()
private val TAG_DETAILS = Destination.Details("{id}").build()

sealed class Destination {
    object Home : Destination() {
        fun build() = "home"
    }

    class Details(private val id: String) : Destination() {
        fun build() = "details/$id"
    }
}

@Composable
@ExperimentalCoilApi
@ExperimentalMaterial3Api
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = TAG_HOME) {
        composable(route = TAG_HOME) {
            Home(hiltViewModel(), navController)
        }
        composable(
            route = TAG_DETAILS,
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
        ) { entry ->
            Details(hiltViewModel(), entry.arguments?.getString("id").orEmpty())
        }
    }
}
