package artan.lavdim_connect_4_group_4.viewModels

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import artan.lavdim_connect_4_group_4.multiplayer.Player
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import artan.lavdim_connect_4_group_4.multiplayer.ActionResult
import artan.lavdim_connect_4_group_4.multiplayer.Game
import artan.lavdim_connect_4_group_4.multiplayer.GameResult
import artan.lavdim_connect_4_group_4.multiplayer.ServerState
import artan.lavdim_connect_4_group_4.multiplayer.SupabaseCallback
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

        class GameViewModel: ViewModel(), SupabaseCallback {
                private val _board = List(6) { mutableStateListOf<Cell>().apply { addAll(List(7) { Cell(CellState.EMPTY) }) } }
                val board: List<MutableList<Cell>> = _board
                var currentPlayer = mutableStateOf(CellState.PLAYER1)

                init {
                    SupabaseService.callbackHandler = this
                }

                fun dropPiece(column: Int) {
                        for (row in 5 downTo 0) {
                                if (_board[row][column].state == CellState.EMPTY) {
                                        _board[row][column] = Cell(currentPlayer.value)
                                        currentPlayer.value = if (currentPlayer.value == CellState.PLAYER1) CellState.PLAYER2 else CellState.PLAYER1

                                        // Broadcast the move
                                        viewModelScope.launch {
                                                SupabaseService.sendTurn(column)
                                        }
                                        break
                                }
                        }
                }

                override suspend fun playerReadyHandler() {
                        TODO("Not yet implemented")
                }

                override suspend fun releaseTurnHandler() {
                        TODO("Not yet implemented")
                }

                override suspend fun actionHandler(x: Int, y: Int) {
                        dropPiece(x)
                }

                override suspend fun answerHandler(status: ActionResult) {
                        TODO("Not yet implemented")
                }

                override suspend fun finishHandler(status: GameResult) {
                        TODO("Not yet implemented")
                }
                // win conditions...

        }


}

