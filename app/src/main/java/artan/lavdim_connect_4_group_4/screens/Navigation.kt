package artan.lavdim_connect_4_group_4.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import artan.lavdim_connect_4_group_4.viewModels.SharedViewModel

@Composable
fun Navigation() {
    val sharedViewModel = SharedViewModel()
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController )
        }
        composable(route = Screen.StartScreen.route) {
            StartScreen(
                navController = navController,
                sharedViewModel
            )
        }
        composable(route = Screen.LobbyScreen.route) {
            LobbyScreen(
                navController = navController,
                sharedViewModel
            )
        }
        composable(route = Screen.GameScreen.route) {
            GameScreen(
                navController = navController,
            )
        }
        composable(route = Screen.ResultScreen.route) {
            ResultScreen(
                navController = navController
            )
        }
    }
}




