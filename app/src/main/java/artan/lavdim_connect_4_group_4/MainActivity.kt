package artan.lavdim_connect_4_group_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import artan.lavdim_connect_4_group_4.ui.theme.Artanlavdim_Connect_4_Group_4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Artanlavdim_Connect_4_Group_4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // comment
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Artanlavdim_Connect_4_Group_4Theme {
        Greeting("Android")
    }
}

@Composable
fun Pog() {
    Text(text = "Lavdim c")
}

@Composable
fun pog2() {
    Text(text = "Lavdim Changed that shit")
}

@Composable
fun Pog3() {
    Text(text = "Howdy Artan!")
}