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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import artan.lavdim_connect_4_group_4.R
import artan.lavdim_connect_4_group_4.screens.AvenirRoundedFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(navController: NavController) {
    val username = remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) // Add padding to the entire column if needed
    ) {
        Image(
            painter = painterResource(id = R.drawable.coinnectfour),
            contentDescription = "Logo",
            modifier = Modifier.graphicsLayer(scaleX = 0.7f, scaleY = 0.7f)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Enter your username",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = AvenirRoundedFontFamily,
            color = Color(0xFFD9D9D9)
        )

        Spacer(modifier = Modifier.height(27.dp))

        Box(
            modifier = Modifier
                .border(width = 3.dp, color = Color(0xFFBAA153), shape = RoundedCornerShape(15.dp))
        ) {
            TextField(
                value = username.value,
                onValueChange = { username.value = it },
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
                fontWeight = FontWeight.Bold,
                fontFamily = AvenirRoundedFontFamily,
                fontSize = 24.sp,
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

    }
}
