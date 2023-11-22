package artan.lavdim_connect_4_group_4.viewModels

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import artan.lavdim_connect_4_group_4.multiplayer.Player


import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import artan.lavdim_connect_4_group_4.multiplayer.Game
import artan.lavdim_connect_4_group_4.multiplayer.ServerState
import artan.lavdim_connect_4_group_4.multiplayer.SupabaseService
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


}

