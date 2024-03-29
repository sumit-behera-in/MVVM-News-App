package apps.sumit.apitest.features.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import apps.sumit.apitest.features.presentation.mainScreen.MainScreen
import apps.sumit.apitest.features.presentation.splashScreen.SplashScreen
import apps.sumit.apitest.features.presentation.ui.theme.ApiTestTheme
import apps.sumit.apitest.features.presentation.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiTestTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.SplashScreen.route
                ) {
                    composable(Screen.SplashScreen.route) {
                        SplashScreen(navController)
                    }
                    composable(Screen.MainScreen.route) {
                        MainScreen()
                    }
                }
            }
        }
    }
}



