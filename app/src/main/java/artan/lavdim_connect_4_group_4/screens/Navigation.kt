package artan.lavdim_connect_4_group_4.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController )
        }
        composable(route = Screen.StartScreen.route) {
            StartScreen(
                navController = navController
            )
        }
        composable(route = "LobbyScreen/{username}") { backStackEntry ->
            LobbyScreen(
                navController = navController,
                username = backStackEntry.arguments?.getString("username") ?: ""
            )
        }
    }
}




