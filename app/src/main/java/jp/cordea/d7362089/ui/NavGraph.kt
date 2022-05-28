package jp.cordea.d7362089.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import jp.cordea.d7362089.ui.details.Details
import jp.cordea.d7362089.ui.home.Home

object Destination {
    const val HOME = "home"
    const val DETAILS = "details"
}

@Composable
@ExperimentalCoilApi
@ExperimentalMaterial3Api
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Destination.HOME) {
        composable(route = Destination.HOME) {
            Home(hiltViewModel(), navController)
        }
        composable(route = Destination.DETAILS) {
            Details(hiltViewModel())
        }
    }
}
