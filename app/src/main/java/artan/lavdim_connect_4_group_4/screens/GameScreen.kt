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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import artan.lavdim_connect_4_group_4.viewModels.SharedViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun GameScreen(player: Game, viewModel: SharedViewModel.GameViewModel, navController: NavController = rememberNavController()) {

//    var currentPlayerName by remember { mutableStateOf(player.player1.name) }
        var currentPlayerName = viewModel.currentPlayerName.value
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
                            text = player.player1.name,
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

                Column() {
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
                            text = player.player2.name,
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
                    text = "${currentPlayerName}'s turn",
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
                    Connect4Grid(SharedViewModel.GameViewModel())

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
fun Connect4Grid(viewModel: SharedViewModel.GameViewModel) {
    Column(
        modifier = Modifier
            .background(Color(0xFF383838), shape = RoundedCornerShape(40.dp))
            .padding(16.dp)
    ) {
        // Grid of clickable cells
        Column {
            for (row in viewModel.board) {
                Row {
                    for (cell in row) {
                        CellView(cell, onClick = {
                                val columnIndex = row.indexOf(cell)
                                viewModel.dropPiece(columnIndex)
                        })
                    }
                }
            }
        }
    }
}



@Composable
fun CellView(cell: SharedViewModel.Cell, onClick: () -> Unit) {
    val cellSize = 50.dp

    Box(
        modifier = Modifier
            .size(cellSize)
            .padding(6.dp)
            .clickable(onClick = onClick),  // Ensure that onClick is only called once
        contentAlignment = Alignment.Center
    ) {
        when (cell.state) {
            SharedViewModel.CellState.EMPTY -> {
                // For empty cells, you might want to show a gray circle or keep it empty
                Box(modifier = Modifier
                    .size(cellSize)
                    .background(Color(0xFF282828), CircleShape))
            }
            SharedViewModel.CellState.PLAYER1 -> {
                Image(
                    painter = painterResource(id = R.drawable.gold_coin),
                    contentDescription = "Gold Coin",
                    modifier = Modifier.size(cellSize)
                )
            }
            SharedViewModel.CellState.PLAYER2 -> {
                Image(
                    painter = painterResource(id = R.drawable.silver_coin),
                    contentDescription = "Silver Coin",
                    modifier = Modifier.size(cellSize)
                )
            }
        }
    }
}


