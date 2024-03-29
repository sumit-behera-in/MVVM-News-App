package apps.sumit.apitest.features.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import apps.sumit.apitest.features.presentation.newsComponents.BreakingNewsScreen
import apps.sumit.apitest.features.presentation.ui.theme.ApiTestTheme
import apps.sumit.apitest.features.presentation.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.BreakingNewsScreen.route) {
        composable(Screen.BreakingNewsScreen.route) {
            BreakingNewsScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ApiTestTheme {
        Greeting()
    }
}