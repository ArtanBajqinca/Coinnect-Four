package artan.lavdim_connect_4_group_4.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.garrit.android.multiplayer.Player


class ViewModel: ViewModel() {
    private val _player = MutableLiveData<Player>()
    val playerName: LiveData<Player> = _player

    fun joinLobbyWithUsername(username: String) {
        val newPlayer = Player(name = username)
        _player.value = newPlayer
        }
    }



