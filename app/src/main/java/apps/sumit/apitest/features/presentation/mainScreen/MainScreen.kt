package apps.sumit.apitest.features.presentation.mainScreen

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import apps.sumit.apitest.features.presentation.mainScreen.components.BottomMenu
import apps.sumit.apitest.features.presentation.mainScreen.components.NewsScreenTopSection
import apps.sumit.apitest.features.presentation.newsComponents.breakingNews.BreakingNewsScreen
import apps.sumit.apitest.features.presentation.newsComponents.customNews.CustomNewsScreen
import apps.sumit.apitest.features.presentation.ui.theme.DeepBlue
import apps.sumit.apitest.features.presentation.util.Constants.bottomMenuContents
import apps.sumit.apitest.features.presentation.util.Constants.screenList
import apps.sumit.apitest.features.presentation.util.Screen

@Composable
fun MainScreen() {

    var screen by remember {
        mutableStateOf("Breaking News")
    }

    val navController = rememberNavController()

//    screen = when (navController.currentDestination?.route) {
//        Screen.BreakingNewsScreen.route -> "Breaking News"
//        Screen.CustomNewsScreen.route -> "Tesla"
//        else -> {
//            "Breaking News"
//        }
//    }

//    BackHandler {
//        screen = "Breaking News"
//        navController.popBackStack()
//        navController.navigate(Screen.BreakingNewsScreen.route)
//    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            NewsScreenTopSection(text = screen)
        },
        bottomBar = {
            BottomMenu(
                items = bottomMenuContents,
                modifier = Modifier.height(65.dp),
            ) {
                screen = screenList[it]
                navController.popBackStack()
                when (it) {
                    0 -> {
                        navController.navigate(Screen.BreakingNewsScreen.route)
                    }

                    1 -> {
                        navController.navigate(Screen.CustomNewsScreen.route + "query=${"finance"}")
                    }

                    2 -> {
                        navController.navigate(Screen.CustomNewsScreen.route + "query=${"science"}")
                    }

                    3 -> {
                        navController.navigate(Screen.CustomNewsScreen.route + "query=${"technology"}")
                    }

                    4 -> {
                        navController.navigate(Screen.CustomNewsScreen.route + "query=${"game"}")
                    }

                    5 -> {
                        navController.navigate(Screen.CustomNewsScreen.route + "query=${"entertainment"}")
                    }
                }

            }
        }
    ) {
        Greeting(
            Modifier
                .background(DeepBlue)
                .padding(it),
            navController = navController
        )
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.BreakingNewsScreen.route) {
        composable(
            route = Screen.BreakingNewsScreen.route,
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
            BreakingNewsScreen(modifier)
        }
        composable(
            route = Screen.CustomNewsScreen.route + "query={query}",
            arguments = listOf(
                navArgument(name = "query") {
                    type = NavType.StringType
                    defaultValue = "trending"
                }
            ),
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
            val query = it.arguments?.getString("query") ?: "trending"
            CustomNewsScreen(query = query, modifier = modifier)
        }
    }
}