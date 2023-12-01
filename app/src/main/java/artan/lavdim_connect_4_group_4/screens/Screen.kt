package artan.lavdim_connect_4_group_4.screens

sealed class Screen(val route: String){
    object SplashScreen: Screen( "splash_screen")
    object StartScreen: Screen("start_screen")
    object LobbyScreen: Screen("lobby_screen")
    object GameScreen: Screen("game_screen")
    object ResultScreen: Screen("result_screen")
}
