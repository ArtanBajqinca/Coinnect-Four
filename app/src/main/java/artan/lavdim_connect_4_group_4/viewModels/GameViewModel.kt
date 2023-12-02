package artan.lavdim_connect_4_group_4.viewModels

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import artan.lavdim_connect_4_group_4.R
import io.garrit.android.multiplayer.ActionResult
import io.garrit.android.multiplayer.GameResult
import io.garrit.android.multiplayer.SupabaseCallback
import io.garrit.android.multiplayer.SupabaseService
import kotlinx.coroutines.launch

class GameViewModel : ViewModel(), SupabaseCallback {
    enum class CellState { EMPTY, PLAYER1, PLAYER2 }
    data class Cell(var state: CellState = CellState.EMPTY)

    private val _board = List(6) { mutableStateListOf<Cell>().apply { addAll(List(7) { Cell(CellState.EMPTY) }) } }
    val board: List<MutableList<Cell>> = _board
    var currentPlayer = mutableStateOf(CellState.PLAYER1)
    private var localPlayerTurn = mutableStateOf(true)
    var playerWon = mutableStateOf(false)
    var playerWinner: String? by mutableStateOf(null)
    var winningPositions = mutableStateListOf<Pair<Int, Int>>()
    private var coinSoundMediaPlayer: MediaPlayer? = null
    private var winSoundMediaPlayer: MediaPlayer? = null
    var boardIsFull = mutableStateOf(false)

    init {
        println("init")
        SupabaseService.callbackHandler = this
        val currentGame = SupabaseService.currentGame
        val player = SupabaseService.player

        if (currentGame != null && player != null) {
            localPlayerTurn.value = player.id == currentGame.player1.id
        }
    }

    fun initializeMediaPlayers(context: Context) {
        coinSoundMediaPlayer = MediaPlayer.create(context, R.raw.coin_2)
        winSoundMediaPlayer = MediaPlayer.create(context, R.raw.win_sound)
    }
    fun playCoinSound() {
        coinSoundMediaPlayer?.start()
    }
    fun playWinSound() {
        winSoundMediaPlayer?.start()
    }

    fun dropPiece(column: Int) {
        viewModelScope.launch {
            if (playerWon.value) {
                return@launch
            }
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

    private fun updateBoardFromRemote(column: Int) {
        viewModelScope.launch {
            for (row in _board.indices.reversed()) {
                if (_board[row][column].state == CellState.EMPTY) {
                    _board[row][column] = Cell(currentPlayer.value)

                    // Toggle the current player for the next turn
                    currentPlayer.value =
                        if (currentPlayer.value == CellState.PLAYER1) CellState.PLAYER2
                        else CellState.PLAYER1
                    checkForWin()
                    // Broadcast the move and change turn
                    SupabaseService.releaseTurn()
                    localPlayerTurn.value = true
                    break
                }
            }
        }
    }

    override suspend fun actionHandler(x: Int,y: Int) {
        updateBoardFromRemote(x)
        // will make a sound when opponent makes a move
        playCoinSound()
    }

    private fun checkForWin() {
        var isBoardFull = true

        for (row in 0 until 6) {
            for (col in 0 until 7) {
                val cell = board[row][col]

                // Check for a win condition
                if (cell.state != CellState.EMPTY) {
                    if (checkHorizontalWin(row, col) || checkVerticalWin(row, col) || checkDiagonalWin(row, col)) {
                        println("Player ${cell.state} wins!")
                        playerWon.value = true
                        playerWinner = if (cell.state == CellState.PLAYER1) SupabaseService.currentGame?.player1?.name
                        else SupabaseService.currentGame?.player2?.name
                        return
                    }
                } else {
                    // Check if the board is still not full
                    isBoardFull = false
                }
            }
        }

        // Check if the board is full and no win condition is met
        if (isBoardFull) {
            println("It's a draw!")
            boardIsFull.value = true
        }
    }


    private fun checkHorizontalWin(row: Int, col: Int): Boolean {
        if (col + 3 < 7 && board[row][col].state == board[row][col + 1].state &&
            board[row][col].state == board[row][col + 2].state &&
            board[row][col].state == board[row][col + 3].state) {
            winningPositions.addAll((0..3).map { Pair(row, col + it) })
            return true
        }
        return false
    }

    private fun checkVerticalWin(row: Int, col: Int): Boolean {
        if (row - 3 >= 0 && board[row][col].state == board[row - 1][col].state &&
            board[row][col].state == board[row - 2][col].state &&
            board[row][col].state == board[row - 3][col].state) {
            winningPositions.addAll((-3..0).map { Pair(row + it, col) })
            return true
        }
        return false
    }

    private fun checkDiagonalWin(row: Int, col: Int): Boolean {
        if (col + 3 < 7 && row - 3 >= 0 && board[row][col].state == board[row - 1][col + 1].state &&
            board[row][col].state == board[row - 2][col + 2].state &&
            board[row][col].state == board[row - 3][col + 3].state) {
            winningPositions.addAll((0..3).map { Pair(row - it, col + it) })
            return true
        } else if (col - 3 >= 0 && row - 3 >= 0 && board[row][col].state == board[row - 1][col - 1].state &&
            board[row][col].state == board[row - 2][col - 2].state &&
            board[row][col].state == board[row - 3][col - 3].state) {
            winningPositions.addAll((0..3).map { Pair(row - it, col - it) })
            return true
        }
        return false
    }

    fun leaveGame(){
        viewModelScope.launch {
            SupabaseService.leaveGame()
        }
    }

    override suspend fun playerReadyHandler() {
        TODO("Not yet implemented")
    }

    override suspend fun answerHandler(status: ActionResult) {
        // Do not use
    }
    override suspend fun releaseTurnHandler() {
    }
    override suspend fun finishHandler(status: GameResult) {
        // Do not use
    }
}