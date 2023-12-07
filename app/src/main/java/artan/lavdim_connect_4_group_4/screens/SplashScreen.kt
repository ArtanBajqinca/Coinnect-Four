package artan.lavdim_connect_4_group_4.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import artan.lavdim_connect_4_group_4.R
import artan.lavdim_connect_4_group_4.viewModels.SharedViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController = rememberNavController(), viewModel: SharedViewModel) {
    LaunchedEffect(key1 = true) {
        viewModel.scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(1500L)
        navController.navigate(Screen.StartScreen.route)
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.coinnectfour),
            contentDescription = "Logo",
            modifier = Modifier.scale(viewModel.scale.value)
        )
    }
}
