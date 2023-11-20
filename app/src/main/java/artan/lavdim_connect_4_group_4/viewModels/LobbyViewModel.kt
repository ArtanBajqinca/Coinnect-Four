package artan.lavdim_connect_4_group_4.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.garrit.android.multiplayer.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch

class LobbyViewModel : ViewModel() {
    private val _players = MutableStateFlow<List<Player>>(emptyList())
    val players: StateFlow<List<Player>> = _players

    fun fetchPlayers() {
        viewModelScope.launch {
            // Call your backend or service to fetch players
//            val fetchedPlayers = ... // Fetch players from your backend
//            _players.value = fetchedPlayers
        }
    }
}
