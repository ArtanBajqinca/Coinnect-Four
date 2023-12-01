package artan.lavdim_connect_4_group_4.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import artan.lavdim_connect_4_group_4.screens.GameScreen
import artan.lavdim_connect_4_group_4.screens.LobbyScreen
import artan.lavdim_connect_4_group_4.screens.ResultScreen
import artan.lavdim_connect_4_group_4.screens.Screen
import artan.lavdim_connect_4_group_4.screens.SplashScreen
import artan.lavdim_connect_4_group_4.screens.StartScreen
import artan.lavdim_connect_4_group_4.viewModels.GameViewModel
import artan.lavdim_connect_4_group_4.viewModels.SharedViewModel
import io.garrit.android.multiplayer.ServerState
import io.garrit.android.multiplayer.SupabaseService
import io.garrit.android.multiplayer.SupabaseService.currentGame

@Composable
fun Navigation() {
    val sharedViewModel = SharedViewModel()
    val gameViewModel = GameViewModel()
    val navController = rememberNavController()
    val serverState = SupabaseService.serverState.collectAsState()

    LaunchedEffect(serverState.value) {
        when (serverState.value) {
            ServerState.LOADING_GAME, ServerState.GAME -> {
                // Navigate to GameScreen only if it's not the current destination
                if (navController.currentDestination?.route != Screen.GameScreen.route) {
                    navController.navigate(Screen.GameScreen.route)
                }
            }
            else -> {}
        }
    }

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController )
        }
        composable(route = Screen.StartScreen.route) {
            StartScreen(
                navController,
                sharedViewModel
            )
        }
        composable(route = Screen.LobbyScreen.route) {
            LobbyScreen(
                navController,
                sharedViewModel,
            )
        }
        composable(route = Screen.GameScreen.route) {

            if (currentGame != null) {
                GameScreen(
                    game = currentGame!!,
                    navController = navController,
                    gameViewModel
                )
            }
        }

        composable(route = Screen.ResultScreen.route) {
            ResultScreen(
                navController = navController,
                gameViewModel
            )
        }
    }
}




