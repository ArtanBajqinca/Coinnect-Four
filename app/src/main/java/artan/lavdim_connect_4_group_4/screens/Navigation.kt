package artan.lavdim_connect_4_group_4.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.GameScreen.route) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController )
        }
        composable(route = Screen.StartScreen.route) {
            StartScreen(
                navController = navController
            )
        }
        composable(route = Screen.LobbyScreen.route) {
            LobbyScreen(
                navController = navController
            )
        }
        composable(route = Screen.GameScreen.route) {
            GameScreen(
                navController = navController
            )
        }
    }
}




