@file:Suppress("DEPRECATION")

package artan.lavdim_connect_4_group_4.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import artan.lavdim_connect_4_group_4.Font.AvenirRoundedFontFamily
import artan.lavdim_connect_4_group_4.Font.AvenirTypography
import artan.lavdim_connect_4_group_4.R
import artan.lavdim_connect_4_group_4.viewModels.SharedViewModel
import io.garrit.android.multiplayer.Player

@OptIn(ExperimentalMaterial3Api::class) // for fixing textFieldColors
@Composable
fun StartScreen(navController: NavController = rememberNavController(), viewModel: SharedViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.coinnectfour),
            contentDescription = "Logo",
            modifier = Modifier.graphicsLayer(scaleX = 0.7f, scaleY = 0.7f)
        )
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Enter your username",
            style = AvenirTypography.titleMedium
        )
        Spacer(modifier = Modifier.height(27.dp))

        Box(
            modifier = Modifier
                .border(width = 3.dp, color = Color(0xFFBAA153), shape = RoundedCornerShape(15.dp))
        ) {
            TextField(
                value = viewModel.username.value,
                onValueChange = { viewModel.username.value = it },
                singleLine = true,
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    color = Color(0xFFD9D9D9),
                    fontWeight = FontWeight.Bold,
                    fontFamily = AvenirRoundedFontFamily,
                    fontSize = 24.sp,
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    autoCorrect = false
                ),
            )
        }
        Spacer(modifier = Modifier.height(27.dp))

        Button(
            onClick = {
                viewModel.joinLobby(Player(name = viewModel.username.value))
                navController.navigate(Screen.LobbyScreen.route)
            },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD9D9D9)
            )
        ) {
            Text(
                "JOIN LOBBY",
                color = Color(0xFF282828),
                style = AvenirTypography.titleMedium
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}
