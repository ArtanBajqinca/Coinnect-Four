package artan.lavdim_connect_4_group_4.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import artan.lavdim_connect_4_group_4.Font.AvenirRoundedFontFamily
import artan.lavdim_connect_4_group_4.Font.AvenirTypography
import artan.lavdim_connect_4_group_4.R
import artan.lavdim_connect_4_group_4.viewModels.SharedViewModel
import artan.lavdim_connect_4_group_4.multiplayer.Game
import artan.lavdim_connect_4_group_4.multiplayer.Player
import artan.lavdim_connect_4_group_4.multiplayer.SupabaseService

@Composable
fun LobbyScreen(navController: NavController, viewModel: SharedViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Image(
            painter = painterResource(id = R.drawable.coinnectfour),
            contentDescription = "Logo",
            modifier = Modifier.width(100.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "LOBBY",
            style = AvenirTypography.displayLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Online players",
            style = AvenirTypography.displayMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(15.dp))

        LazyColumn(
            modifier = Modifier
                .width(320.dp)
                .height(200.dp)
                .border(width = 4.dp, color = Color(0xFFBAA153), shape = RoundedCornerShape(15.dp))
        ) {
            items(SupabaseService.users) { player ->
                userCard(player = player, viewModel)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Incoming challenges",
            style = AvenirTypography.displayMedium,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .width(320.dp)
                .height(300.dp)
                .border(width = 4.dp, color = Color(0xFFBAA153), shape = RoundedCornerShape(15.dp))
        ) {
            items(SupabaseService.games) { player ->
                challangeCard(navController,player, viewModel)
            }
        }
    }
}

@Composable
fun userCard(player: Player, viewModel: SharedViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 20.dp)
    ) {
        Text(
            text = player.name,
            style = AvenirTypography.displayMedium,
            modifier = Modifier
                .width(150.dp)
                .padding(end = 10.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.signal_solid),
            contentDescription = "Online Status",
            tint = Color(0xFF42A54A),
            modifier = Modifier
                .size(23.dp)
        )
        Button(
            onClick = {
                viewModel.invite(player)
            },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD9D9D9)
            ),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .width(100.dp)
                .height(25.dp)
                .padding(start = 20.dp)
        ) {
            Text(
                "Challenge",
                color = Color(0xFF282828),
                style = AvenirTypography.titleSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun challangeCard(navController: NavController, player: Game, viewModel: SharedViewModel) {

    Column() {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = AvenirRoundedFontFamily,
                        color = Color(0xFFD9D9D9)
                    )
                ) {
                    append(player.player1.name)
                }
                append("\n") // Line break
                withStyle(
                    style = SpanStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = AvenirRoundedFontFamily,
                        color = Color(0xFFD9D9D9)
                    )
                ) {
                    append("challenges you!")
                }
            },
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(start = 20.dp)
        ) {
            Button(
                onClick = {
                    viewModel.acceptInvite(player)
                    navController.navigate(Screen.GameScreen.route)
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF42A54A)
                ),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .width(75.dp)
                    .height(25.dp)
            ) {
                Text(
                    text = "Accept",
                    style = AvenirTypography.titleSmall,
                    textAlign = TextAlign.Center
                )
            }

            Button(
                onClick = {
                          viewModel.declineInvite(player)
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC84E4E)
                ),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .width(75.dp)
                    .height(25.dp)
            ) {
                Text(
                    text = "Decline",
                    style = AvenirTypography.titleSmall,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
