package artan.lavdim_connect_4_group_4.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import artan.lavdim_connect_4_group_4.Font.AvenirRoundedFontFamily
import artan.lavdim_connect_4_group_4.R
import artan.lavdim_connect_4_group_4.multiplayer.Game
import artan.lavdim_connect_4_group_4.multiplayer.SupabaseService.currentGame
import artan.lavdim_connect_4_group_4.multiplayer.SupabaseService.player
import artan.lavdim_connect_4_group_4.viewModels.GameViewModel
import artan.lavdim_connect_4_group_4.viewModels.SharedViewModel
import kotlinx.coroutines.delay

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun GameScreen(game: Game, navController: NavController = rememberNavController(), gameViewModel: GameViewModel) {
    val currentPlayerName = if (gameViewModel.currentPlayer.value == GameViewModel.CellState.PLAYER1) {
        "${game.player1.name}'s turn"
    } else {
        "${game.player2.name}'s turn"
    }

    LaunchedEffect(gameViewModel.PlayerWon.value) {
        if (gameViewModel.PlayerWon.value) {
            delay(2000)
            navController.navigate(Screen.ResultScreen.route)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Column(
            verticalArrangement = Center,
            horizontalAlignment = CenterHorizontally

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        color = Color(0xFF383838),
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 30.dp,
                            bottomEnd = 30.dp
                        )
                    ),
                horizontalArrangement = Center
            ) {
                Column(
                    horizontalAlignment = CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .padding(start = 0.dp, top = 29.dp)
                            .width(130.dp)
                            .height(40.dp)
                            .border(
                                width = 2.dp,
                                color = Color(0xFFD9D9D9),
                                shape = RoundedCornerShape(20.dp),
                            )
                    ) {
                        Text(
                            text = game.player1.name,
                            color = Color(0xFFD9D9D9),
                            fontWeight = FontWeight.Bold,
                            fontFamily = AvenirRoundedFontFamily,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .align(CenterHorizontally)
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.gold_coin),
                            contentDescription = "Gold Coin",
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp)
                        )
                    }
                }

                Text(
                    "VS",
                    color = Color(0xFFD9D9D9),
                    fontWeight = FontWeight.Bold,
                    fontFamily = AvenirRoundedFontFamily,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 30.dp, top = 38.dp, end = 30.dp)
                )

                Column {
                    Box(
                        modifier = Modifier
                            .padding(start = 0.dp, top = 29.dp)
                            .width(130.dp)
                            .height(40.dp)
                            .background(Color.Transparent)
                            .border(
                                width = 2.dp,
                                color = Color(0xFFD9D9D9),
                                shape = RoundedCornerShape(20.dp),
                            )
                    ) {
                        Text(
                            text = game.player2.name,
                            color = Color(0xFFD9D9D9),
                            fontWeight = FontWeight.Bold,
                            fontFamily = AvenirRoundedFontFamily,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .align(CenterHorizontally)
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.silver_coin),
                            contentDescription = "Silver Coin",
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp)
                        )
                    }
                }
            }
            Column(
                verticalArrangement = Center,
                horizontalAlignment = CenterHorizontally,

                ) {
                    Text(
                        text = currentPlayerName,
                        color = Color(0xFFD9D9D9),
                        fontWeight = FontWeight.Bold,
                        fontFamily = AvenirRoundedFontFamily,
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 60.dp)
                    )
                Column(
                    modifier = Modifier
                ) {

                    Spacer(modifier = Modifier.height(30.dp))
                    Connect4Grid(gameViewModel)

                }
                Column(
                    modifier = Modifier
                        .padding(top = 80.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.coinnectfour),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .width(100.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Connect4Grid(gameViewModel: GameViewModel) {
    Column(
        modifier = Modifier
            .background(Color(0xFF383838), shape = RoundedCornerShape(40.dp))
            .padding(16.dp)
    ) {
        // Grid of clickable cells
        Column {
            for ((rowIndex, row) in gameViewModel.board.withIndex()) {
                Row {
                    for ((columnIndex, cell) in row.withIndex()) {
                        val isWinningCell = Pair(rowIndex, columnIndex) in gameViewModel.winningPositions
                        CellView(cell, isWinningCell, onClick = {
                            gameViewModel.dropPiece(columnIndex)
                        })
                    }
                }
            }
        }
    }
}




@Composable
fun CellView(cell: GameViewModel.Cell, isWinningCell: Boolean, onClick: () -> Unit) {
    val cellSize = 50.dp

    Box(
        modifier = Modifier
            .size(cellSize)
            .padding(6.dp)
            .clickable(onClick = onClick)
            .border(
                width = if (isWinningCell) 3.dp else 0.dp,
                color = if (isWinningCell) Color.Green else Color.Transparent,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        when (cell.state) {
            GameViewModel.CellState.EMPTY -> {

                Box(modifier = Modifier
                    .size(cellSize)
                    .background(Color(0xFF282828), CircleShape))
            }
            GameViewModel.CellState.PLAYER1 -> {
                Image(
                    painter = painterResource(id = R.drawable.gold_coin),
                    contentDescription = "Gold Coin",
                    modifier = Modifier.size(cellSize)
                )
            }
            GameViewModel.CellState.PLAYER2 -> {
                Image(
                    painter = painterResource(id = R.drawable.silver_coin),
                    contentDescription = "Silver Coin",
                    modifier = Modifier.size(cellSize)
                )
            }
        }
    }
}


