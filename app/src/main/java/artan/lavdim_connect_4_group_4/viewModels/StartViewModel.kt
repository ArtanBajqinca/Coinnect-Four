package artan.lavdim_connect_4_group_4.viewModels

import androidx.lifecycle.ViewModel
import io.garrit.android.multiplayer.Player
import io.garrit.android.multiplayer.SupabaseService
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class StartViewModel: ViewModel() {
    fun JoinLobby(player: Player) {
       val currentPlayer = player
        viewModelScope.launch {
            SupabaseService.joinLobby(player)
        }
    }
}