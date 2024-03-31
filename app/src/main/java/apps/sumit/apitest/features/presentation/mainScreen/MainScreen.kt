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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import apps.sumit.apitest.features.presentation.mainScreen.components.BottomMenu
import apps.sumit.apitest.features.presentation.newsComponents.breakingNews.BreakingNewsScreen
import apps.sumit.apitest.features.presentation.newsComponents.customNews.CustomNewsScreen
import apps.sumit.apitest.features.presentation.ui.theme.DeepBlue
import apps.sumit.apitest.features.presentation.util.Constants.bottomMenuContents
import apps.sumit.apitest.features.presentation.util.Constants.screenList
import apps.sumit.apitest.features.presentation.util.Screen

@Composable
fun MainScreen(parentNavHostController: NavHostController) {


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

        bottomBar = {
            BottomMenu(
                items = bottomMenuContents,
                modifier = Modifier.height(65.dp),
            ) {
                navController.popBackStack()
                when (it) {
                    0 -> {
                        navController.navigate(Screen.BreakingNewsScreen.route)
                    }

                    1 -> {
                        navController.navigate(Screen.CustomNewsScreen.route + "query=${"finance"}/screen=${screenList[it]}")
                    }

                    2 -> {
                        navController.navigate(Screen.CustomNewsScreen.route + "query=${"science"}/screen=${screenList[it]}")
                    }

                    3 -> {
                        navController.navigate(Screen.CustomNewsScreen.route + "query=${"technology"}/screen=${screenList[it]}")
                    }

                    4 -> {
                        navController.navigate(Screen.CustomNewsScreen.route + "query=${"game"}/screen=${screenList[it]}")
                    }

                    5 -> {
                        navController.navigate(Screen.CustomNewsScreen.route + "query=${"bollywood"}/screen=${screenList[it]}")
                    }
                }

            }
        }
    ) {
        Greeting(
            Modifier
                .background(DeepBlue)
                .padding(it),
            navController = navController,
            parentNavHostController
        )
    }
}


@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    parentNavHostController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.BreakingNewsScreen.route
    ) {
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
            BreakingNewsScreen(modifier, navHostController = parentNavHostController)
        }
        composable(
            route = Screen.CustomNewsScreen.route + "query={query}/screen={screen}",
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
//            navArgument(name = "query") {
//                type = NavType.StringType
//                defaultValue = "trending"
//            }

            val query = it.arguments?.getString("query") ?: "trending"
            val screen = it.arguments?.getString("screen") ?: "Trending News"
            CustomNewsScreen(
                query = query,
                modifier = modifier,
                parentNavHostController = parentNavHostController,
                screen = screen
            )
        }

    }
}