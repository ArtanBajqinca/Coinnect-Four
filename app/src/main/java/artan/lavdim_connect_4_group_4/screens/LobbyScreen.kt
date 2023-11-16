package artan.lavdim_connect_4_group_4.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import artan.lavdim_connect_4_group_4.R

@Composable
fun LobbyScreen(navController: NavController, username: String) {



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.coinnectfour),
            contentDescription = "Logo",
            modifier = Modifier.graphicsLayer(scaleX = 0.3f, scaleY = 0.3f)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Hello $username \nare you ready to play Coinnect Four?",
            color = Color(0xFFD9D9D9),
            fontWeight = FontWeight.Bold,
            fontFamily = AvenirRoundedFontFamily,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(40.dp)

        )
    }

}