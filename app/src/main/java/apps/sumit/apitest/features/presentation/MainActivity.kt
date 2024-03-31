package apps.sumit.apitest.features.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import apps.sumit.apitest.features.presentation.common.WebViewScreen
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
                        MainScreen(navController)
                    }

                    composable(
                        route = Screen.WebViewScreen.route + "/url={url}",
                        enterTransition = {
                            slideIn(
                                animationSpec = tween(200),
                                initialOffset = {
                                    IntOffset(-it.width, 0)
                                }
                            )
                        },
                        exitTransition = {
                            slideOut(
                                animationSpec = tween(200),
                                targetOffset = {
                                    IntOffset(it.width, 0)
                                }
                            )
                        }

                    ) {

//                        navArgument(name = "url") {
//                            type = NavType.StringType
//                            defaultValue = "https://github.com/sumitbehera1508"
//                        }

                        val url =
                            it.arguments?.getString("url") ?: "https://github.com/sumitbehera1508"

                        WebViewScreen(
                            modifier = Modifier.fillMaxSize(),
                            mUrl = url
                        )

                    }

                }
            }
        }
    }
}



