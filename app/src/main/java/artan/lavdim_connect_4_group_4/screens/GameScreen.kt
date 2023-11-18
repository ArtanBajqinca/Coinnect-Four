package artan.lavdim_connect_4_group_4.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
fun GameScreen(navController: NavController) {
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
                            "Arctan",
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
                            "LavdimNation",
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
                    text = "Arctan's turn",
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
                    Image(
                        painter = painterResource(id = R.drawable.board),
                        contentDescription = "Board",
                        modifier = Modifier
                            .width(360.dp)
                            .height(360.dp)
                            .align(CenterHorizontally)
                            .padding(top = 60.dp)
                            .clickable {
                                navController.navigate(Screen.ResultScreen.route)
                            }
                    )
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

