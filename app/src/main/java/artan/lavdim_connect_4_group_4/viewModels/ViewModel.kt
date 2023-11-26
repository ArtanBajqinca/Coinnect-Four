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
                var isMyTurn = mutableStateOf(true) // Indicates if it's the local player's turn

                init {
                    SupabaseService.callbackHandler = this
                }

                fun dropPiece(column: Int) {

                                for (row in 5 downTo 0) {
                                        if (_board[row][column].state == CellState.EMPTY) {
                                                _board[row][column] = Cell(currentPlayer.value)
                                                currentPlayer.value = if (currentPlayer.value == CellState.PLAYER1) CellState.PLAYER2 else CellState.PLAYER1

                                                if (isMyTurn.value){
                                                        // Broadcast the move and change turn
                                                        viewModelScope.launch {
                                                                SupabaseService.sendTurn(column)
                                                                SupabaseService.releaseTurn()
                                                        }
                                                        isMyTurn.value = false // It's now the remote player's turn
                                                }

                                                break
                                        }
                                }

                }

                override suspend fun playerReadyHandler() {
                        TODO("Not yet implemented")
                }

                override suspend fun releaseTurnHandler() {
                        isMyTurn.value = true
                }

                override suspend fun actionHandler(x: Int,y: Int) {
                        updateBoardFromRemote(x)
                }

                private fun updateBoardFromRemote(column: Int) {
                        dropPiece(column)
//                        if (_board[row][column].state == CellState.EMPTY) {
//                                val remotePlayerState = if (currentPlayer.value == CellState.PLAYER1) CellState.PLAYER2 else CellState.PLAYER1
//                                _board[row][column] = Cell(remotePlayerState)
//                                dropPiece(column)
//                        }
                }








                override suspend fun answerHandler(status: ActionResult) {
                        // Do not use
                }

                override suspend fun finishHandler(status: GameResult) {
                        // Do not use
                }
        }


}

