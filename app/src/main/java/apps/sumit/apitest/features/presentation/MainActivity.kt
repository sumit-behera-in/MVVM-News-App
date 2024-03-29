package apps.sumit.apitest.features.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import apps.sumit.apitest.features.presentation.newsComponents.breakingNews.BreakingNewsScreen
import apps.sumit.apitest.features.presentation.newsComponents.customNews.CustomNewsScreen
import apps.sumit.apitest.features.presentation.ui.theme.ApiTestTheme
import apps.sumit.apitest.features.presentation.ui.theme.DeepBlue
import apps.sumit.apitest.features.presentation.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiTestTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DeepBlue)
                ) {
                    Greeting(Modifier.padding(it))
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.CustomNewsScreen.route) {
        composable(Screen.BreakingNewsScreen.route) {
            BreakingNewsScreen()
        }
        composable(Screen.CustomNewsScreen.route) {
            CustomNewsScreen(query = "tesla")
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