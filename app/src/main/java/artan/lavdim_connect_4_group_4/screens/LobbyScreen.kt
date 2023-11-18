package artan.lavdim_connect_4_group_4.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import artan.lavdim_connect_4_group_4.R

@Composable
fun LobbyScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.coinnectfour),
            contentDescription = "Logo",
            modifier = Modifier
                .graphicsLayer(scaleX = 0.4f, scaleY = 0.4f)
        )
        Text(
            text = "LOBBY",
            color = Color(0xFFD9D9D9),
            fontWeight = FontWeight.Bold,
            fontFamily = AvenirRoundedFontFamily,
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "Online players",
            color = Color(0xFFD9D9D9),
            fontWeight = FontWeight.Bold,
            fontFamily = AvenirRoundedFontFamily,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(15.dp))
        Column(
          modifier = Modifier
              .width(350.dp)
              .height(250.dp)
              .border(width = 4.dp, color = Color(0xFFBAA153), shape = RoundedCornerShape(15.dp))
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 20.dp)
            ) {
                Text(
                    text = "Arctanium",
                    color = Color(0xFFD9D9D9),
                    fontWeight = FontWeight.Bold,
                    fontFamily = AvenirRoundedFontFamily,
                    fontSize = 20.sp,
                    modifier = Modifier.width(140.dp)
                )
                Icon(
                    painter = painterResource(id =  R.drawable.signal_solid),
                    contentDescription = "Online Status",
                    tint = Color(0xFF42A54A),
                    modifier = Modifier
                        .size(23.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 15.dp)
            ) {
                Text(
                    text = "PunaniSlayer",
                    color = Color(0xFFD9D9D9),
                    fontWeight = FontWeight.Bold,
                    fontFamily = AvenirRoundedFontFamily,
                    fontSize = 20.sp,
                    modifier = Modifier.width(140.dp)
                )
                Icon(
                    painter = painterResource(id =  R.drawable.signal_solid),
                    contentDescription = "Online Status",
                    tint = Color(0xFF42A54A),
                    modifier = Modifier
                        .size(23.dp)
                )
                Button(
                    onClick = {

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
                        fontWeight = FontWeight.Bold,
                        fontFamily = AvenirRoundedFontFamily,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Incoming challenges",
            color = Color(0xFFD9D9D9),
            fontWeight = FontWeight.Bold,
            fontFamily = AvenirRoundedFontFamily,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(15.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .width(350.dp)
                .height(250.dp)
                .border(width = 4.dp, color = Color(0xFFBAA153), shape = RoundedCornerShape(15.dp))
        ){
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = AvenirRoundedFontFamily, color = Color(0xFFD9D9D9))) {
                        append("PunaniSlayer ")
                    }
                    append("\n") // Line break
                    withStyle(style = SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = AvenirRoundedFontFamily, color = Color(0xFFD9D9D9))) {
                        append("challenges you!")
                    }
                },
                modifier = Modifier
                    .padding(start = 20.dp, top = 20.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(start = 20.dp)
            ) {

                Button(
                    onClick = {
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
                        "Accept",
                        color = Color(0xFFD9D9D9),
                        fontWeight = FontWeight.Bold,
                        fontFamily = AvenirRoundedFontFamily,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                }

                Button(
                    onClick = {

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
                        "Decline",
                        color = Color(0xFFD9D9D9),
                        fontWeight = FontWeight.Bold,
                        fontFamily = AvenirRoundedFontFamily,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }



        }
    }

}