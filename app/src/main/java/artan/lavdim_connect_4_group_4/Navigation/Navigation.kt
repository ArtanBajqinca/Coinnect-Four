package artan.lavdim_connect_4_group_4.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import artan.lavdim_connect_4_group_4.viewModels.SharedViewModel
import artan.lavdim_connect_4_group_4.screens.GameScreen
import artan.lavdim_connect_4_group_4.screens.LobbyScreen
import artan.lavdim_connect_4_group_4.screens.ResultScreen
import artan.lavdim_connect_4_group_4.screens.Screen
import artan.lavdim_connect_4_group_4.screens.SplashScreen
import artan.lavdim_connect_4_group_4.screens.StartScreen
import io.garrit.android.multiplayer.ServerState
import io.garrit.android.multiplayer.SupabaseService

@Composable
fun Navigation() {
    val sharedViewModel = SharedViewModel()
    val navController = rememberNavController()
    val serverState = SupabaseService.serverState.collectAsState()

    LaunchedEffect(serverState.value) {
        when (serverState.value) {
            ServerState.LOADING_GAME -> {
                // Navigate to the GameScreen when the serverState is LOADING_GAME
                navController.navigate(Screen.GameScreen.route)
            }
            ServerState.GAME -> {
                // Navigate to the GameScreen when the serverState is LOADING_GAME
                navController.navigate(Screen.GameScreen.route)
            }
            else -> {}
        }
    }

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
            val currentGame = SupabaseService.currentGame
            if (currentGame != null) {
                GameScreen(
                    navController = navController,
                    player = currentGame,
                    viewModel = sharedViewModel,
                )
            }
        }
        composable(route = Screen.ResultScreen.route) {
            ResultScreen(
                navController = navController
            )
        }
    }
}




