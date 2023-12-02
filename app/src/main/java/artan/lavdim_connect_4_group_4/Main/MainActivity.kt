package artan.lavdim_connect_4_group_4.Main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import artan.lavdim_connect_4_group_4.Navigation.Navigation
import artan.lavdim_connect_4_group_4.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF282828)
                ) {
                    backgroundImage()
                    Navigation()
                }
        }
    }
}

@Composable
fun backgroundImage() {
    val image = painterResource(id = R.drawable.background);
    Image(
        painter = image,
        contentDescription = "background",
        modifier = Modifier
            .fillMaxHeight()
            .aspectRatio(image.intrinsicSize.width / image.intrinsicSize.height, matchHeightConstraintsFirst = true)
    )
}