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

        enum class CellState { EMPTY, PLAYER1, PLAYER2 }

        data class Cell(var state: CellState = CellState.EMPTY)

        class GameViewModel: ViewModel() {
                private val _board = List(6) { MutableList(7) { Cell(CellState.EMPTY) } }
                val board: List<List<Cell>> = _board
                var currentPlayer = mutableStateOf(CellState.PLAYER1)

                fun dropPiece(column: Int) {
                        for (row in 5 downTo 0) {
                                if (_board[row][column].state == CellState.EMPTY) {
                                        _board[row][column].state = currentPlayer.value
                                        currentPlayer.value = if (currentPlayer.value == CellState.PLAYER1) CellState.PLAYER2 else CellState.PLAYER1
                                        break
                                }
                        }
                }
                // Add methods to check for win conditions...
        }


}

