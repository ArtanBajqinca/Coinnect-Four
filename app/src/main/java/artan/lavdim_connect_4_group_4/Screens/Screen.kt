package artan.lavdim_connect_4_group_4.Screens

sealed class Screen(val route: String){
    object StartScreen: Screen("start_screen")
    object LobbyScreen: Screen("lobby_screen")
}
