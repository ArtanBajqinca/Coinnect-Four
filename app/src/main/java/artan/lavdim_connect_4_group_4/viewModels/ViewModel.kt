package artan.lavdim_connect_4_group_4.viewModels

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import artan.lavdim_connect_4_group_4.multiplayer.ActionResult
import artan.lavdim_connect_4_group_4.multiplayer.Game
import artan.lavdim_connect_4_group_4.multiplayer.GameResult
import artan.lavdim_connect_4_group_4.multiplayer.Player
import artan.lavdim_connect_4_group_4.multiplayer.SupabaseCallback
import artan.lavdim_connect_4_group_4.multiplayer.SupabaseService
import artan.lavdim_connect_4_group_4.multiplayer.SupabaseService.currentGame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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



class GameViewModel : ViewModel(), SupabaseCallback {
        enum class CellState { EMPTY, PLAYER1, PLAYER2 }

        data class Cell(var state: CellState = CellState.EMPTY)

        private val _board = List(6) { mutableStateListOf<Cell>().apply { addAll(List(7) { Cell(CellState.EMPTY) }) } }
        val board: List<MutableList<Cell>> = _board
        var currentPlayer = mutableStateOf(CellState.PLAYER1)
        var localPlayerTurn = mutableStateOf(true)
        var _currentPlayerName = MutableStateFlow(currentGame?.player1?.name ?: "")
        var currentPlayerName: StateFlow<String> = _currentPlayerName

        init {
                println("init")
                SupabaseService.callbackHandler = this
                val currentGame = SupabaseService.currentGame
                val player = SupabaseService.player
                if (currentGame != null && player != null) {
                        localPlayerTurn.value = player.id == currentGame.player1.id
                }
        }

        fun dropPiece(column: Int) {
                viewModelScope.launch {
                        if (localPlayerTurn.value) {
                                for (row in 5 downTo 0) {
                                        if (_board[row][column].state == CellState.EMPTY) {
                                                _board[row][column] = Cell(currentPlayer.value)

                                                // Toggle the current player for the next turn
                                                currentPlayer.value = if (currentPlayer.value == CellState.PLAYER1) CellState.PLAYER2 else CellState.PLAYER1

                                                // Update the current player's name
                                                _currentPlayerName.value = if (currentPlayer.value == CellState.PLAYER1) currentGame?.player1?.name ?: "" else currentGame?.player2?.name ?: ""

                                                // Broadcast the move and change turn
                                                SupabaseService.sendTurn(column)
                                                SupabaseService.releaseTurn()
                                                localPlayerTurn.value = false

                                                // Make sure to break the loop after placing the coin
                                                break
                                        }
                                }
                        }
                }
        }





        override suspend fun playerReadyHandler() {
                TODO("Not yet implemented")
        }

        override suspend fun releaseTurnHandler() {
                localPlayerTurn.value = true
        }

        override suspend fun actionHandler(x: Int,y: Int) {
                updateBoardFromRemote(x)
        }

        private fun updateBoardFromRemote(column: Int) {
                viewModelScope.launch {
                        for (row in 5 downTo 0) {
                                if (_board[row][column].state == CellState.EMPTY) {
                                        _board[row][column] = Cell(currentPlayer.value)

                                        // Toggle the current player for the next turn
                                        currentPlayer.value = if (currentPlayer.value == CellState.PLAYER1) CellState.PLAYER2 else CellState.PLAYER1

                                        // Broadcast the move and change turn
                                        //SupabaseService.releaseTurn()

                                        break
                                }
                        }
                }
        }












        override suspend fun answerHandler(status: ActionResult) {
                // Do not use
        }

        override suspend fun finishHandler(status: GameResult) {
                // Do not use
        }
}