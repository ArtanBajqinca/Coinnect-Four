package artan.lavdim_connect_4_group_4.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.garrit.android.multiplayer.Game
import io.garrit.android.multiplayer.Player
import io.garrit.android.multiplayer.SupabaseService
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

    fun joinLobby(player: Player){
        viewModelScope.launch {
            SupabaseService.joinLobby(player)
        }
    }
    fun invite(player: Player){
        viewModelScope.launch {
            SupabaseService.invite(player)
        }
    }
    fun declineInvite(game: Game){
        viewModelScope.launch {
            SupabaseService.declineInvite(game)
        }
    }
    fun acceptInvite(game: Game){
        viewModelScope.launch {
            SupabaseService.acceptInvite(game)
        }
    }

    fun playerReady(){
        viewModelScope.launch {
            SupabaseService.playerReady()
        }
    }
}