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
import io.garrit.android.multiplayer.ActionResult
import io.garrit.android.multiplayer.Game
import io.garrit.android.multiplayer.GameResult
import io.garrit.android.multiplayer.Player
import io.garrit.android.multiplayer.SupabaseCallback
import io.garrit.android.multiplayer.SupabaseService
import io.garrit.android.multiplayer.SupabaseService.currentGame
import io.garrit.android.multiplayer.SupabaseService.player
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


        enum class CellState { EMPTY, PLAYER1, PLAYER2 }

        data class Cell(var state: CellState = CellState.EMPTY)

        class GameViewModel : ViewModel(), SupabaseCallback {
                private val _board = List(6) { mutableStateListOf<Cell>().apply { addAll(List(7) { Cell(CellState.EMPTY) }) } }
                val board: List<MutableList<Cell>> = _board
                var currentPlayer = mutableStateOf(CellState.PLAYER1)
                var localPlayerTurn by mutableStateOf(true)
                private val _currentPlayerName = MutableStateFlow(currentGame?.player1?.name ?: "")
                val currentPlayerName: StateFlow<String> = _currentPlayerName

                init {
                        SupabaseService.callbackHandler = this
                }

                fun dropPiece(column: Int) {
                        viewModelScope.launch {
                                if (localPlayerTurn) {
                                        for (row in 5 downTo 0) {
                                                if (_board[row][column].state == CellState.EMPTY) {
                                                        _board[row][column] = Cell(currentPlayer.value)

                                                        // Toggle the current player for the next turn
                                                        currentPlayer.value = if (currentPlayer.value == CellState.PLAYER1) CellState.PLAYER2 else CellState.PLAYER1

                                                        // Update the current player's name
                                                        _currentPlayerName.value = if (currentPlayer.value == CellState.PLAYER1) currentGame?.player1?.name ?: "" else currentGame?.player2?.name ?: ""

                                                        // Broadcast the move and change turn
                                                        SupabaseService.sendTurn(column)
                                                        localPlayerTurn = false

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
                                                SupabaseService.releaseTurn()
                                                localPlayerTurn = true

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


}

