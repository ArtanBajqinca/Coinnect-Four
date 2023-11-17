package artan.lavdim_connect_4_group_4.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun GameScreen(navController: NavController) {

    Text(
        "GameScreen",
        color = Color(0xFFD9D9D9),
        fontWeight = FontWeight.Bold,
        fontFamily = AvenirRoundedFontFamily,
        fontSize = 40.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(50.dp)
    )

}