package artan.lavdim_connect_4_group_4.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import artan.lavdim_connect_4_group_4.Font.AvenirRoundedFontFamily
import artan.lavdim_connect_4_group_4.R
import artan.lavdim_connect_4_group_4.viewModels.GameViewModel
import io.garrit.android.multiplayer.SupabaseService

@Composable
fun ResultScreen(navController: NavController = rememberNavController(), gameViewModel: GameViewModel) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier

    ) {
        Image(
            painter = if (gameViewModel.playerWinner == SupabaseService.currentGame?.player1?.name ) painterResource(id = R.drawable.gold_coin)
            else painterResource(id = R.drawable.silver_coin),
            contentDescription = "Gold Coin",
            modifier = Modifier
                .width(110.dp)
                .height(110.dp)
        )
        Text(
            text = "${gameViewModel.playerWinner} Won!",
            color = Color(0xFFD9D9D9),
            fontWeight = FontWeight.Bold,
            fontFamily = AvenirRoundedFontFamily,
            fontSize = 35.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 30.dp)
        )

        Button(
            onClick = {
                gameViewModel.leaveGame()
                navController.navigate(Screen.StartScreen.route)
            },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (gameViewModel.playerWinner == SupabaseService.currentGame?.player1?.name ) Color(0xFFBAA153)
                else Color(0xFFD9D9D9)

            ),
            modifier = Modifier
                .padding(top = 30.dp)
        ) {
            Text(
                "BACK TO START",
                color = Color(0xFF282828),
                fontWeight = FontWeight.Bold,
                fontFamily = AvenirRoundedFontFamily,
                fontSize = 24.sp,
            )
        }
    }
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(bottom = 56.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.coinnectfour),
            contentDescription = "Logo",
            modifier = Modifier
                .width(100.dp)
        )
    }
}