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
        var PlayerWon = mutableStateOf(false)


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
                                for (row in _board.indices.reversed()) {
                                        if (_board[row][column].state == CellState.EMPTY) {
                                                _board[row][column] = Cell(currentPlayer.value)

                                                // Toggle the current player for the next turn
                                                currentPlayer.value =
                                                        if (currentPlayer.value == CellState.PLAYER1) CellState.PLAYER2 else CellState.PLAYER1

                                                // Broadcast the move and change turn
                                                SupabaseService.sendTurn(column)
                                                localPlayerTurn.value = false

                                                // Check for a win condition after the move
                                                checkForWin()

                                                break
                                        }
                                }
                        }
                }
        }

        fun checkForWin() {
                for (row in 0 until 6) {
                        for (col in 0 until 7) {
                                val cell = board[row][col]
                                if (cell.state != CellState.EMPTY) {
                                        // Check for wins in horizontal, vertical, and diagonal directions
                                        if (checkHorizontalWin(row, col) || checkVerticalWin(row, col) || checkDiagonalWin(row, col)) {
                                                // Declare the current player as the winner
                                                // You can set some game state or display a winning message here
                                                println("Player ${cell.state} wins!")
                                                PlayerWon.value = true
                                                // You can also call a function to handle the win, e.g., gameWon(cell.state)
                                                return
                                        }
                                }
                        }
                }
        }

        private fun checkHorizontalWin(row: Int, col: Int): Boolean {
                // Check for horizontal win to the right
                return (col + 3 < 7) && (board[row][col].state == board[row][col + 1].state) &&
                        (board[row][col].state == board[row][col + 2].state) &&
                        (board[row][col].state == board[row][col + 3].state)
        }

        private fun checkVerticalWin(row: Int, col: Int): Boolean {
                // Check for vertical win upwards
                return (row - 3 >= 0) && (board[row][col].state == board[row - 1][col].state) &&
                        (board[row][col].state == board[row - 2][col].state) &&
                        (board[row][col].state == board[row - 3][col].state)
        }

        private fun checkDiagonalWin(row: Int, col: Int): Boolean {
                // Check for diagonal win (up-right and up-left directions)
                return (col + 3 < 7 && row - 3 >= 0 && board[row][col].state == board[row - 1][col + 1].state &&
                        board[row][col].state == board[row - 2][col + 2].state &&
                        board[row][col].state == board[row - 3][col + 3].state) ||
                        (col - 3 >= 0 && row - 3 >= 0 && board[row][col].state == board[row - 1][col - 1].state &&
                                board[row][col].state == board[row - 2][col - 2].state &&
                                board[row][col].state == board[row - 3][col - 3].state)
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
                        for (row in _board.indices.reversed()) {
                                if (_board[row][column].state == CellState.EMPTY) {
                                        _board[row][column] = Cell(currentPlayer.value)

                                        // Toggle the current player for the next turn
                                        currentPlayer.value =
                                                if (currentPlayer.value == CellState.PLAYER1) CellState.PLAYER2 else CellState.PLAYER1
                                        checkForWin()
                                        // Broadcast the move and change turn
                                        SupabaseService.releaseTurn()
                                        localPlayerTurn.value = true

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