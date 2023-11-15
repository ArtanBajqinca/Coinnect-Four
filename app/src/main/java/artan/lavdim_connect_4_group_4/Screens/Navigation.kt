package artan.lavdim_connect_4_group_4.Screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.StartScreen.route) {
        composable(route = Screen.StartScreen.route) {
            StartScreen(
                navController = navController
            )
        }
        composable(
            route = Screen.LobbyScreen.route + "/{name}",
        ) {
            LobbyScreen(
                navController = navController
            )
        }
    }
}




