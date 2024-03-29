package apps.sumit.apitest.features.presentation.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import apps.sumit.apitest.features.presentation.common.NewsScreenTopSection
import apps.sumit.apitest.features.presentation.newsComponents.breakingNews.BreakingNewsScreen
import apps.sumit.apitest.features.presentation.newsComponents.customNews.CustomNewsScreen
import apps.sumit.apitest.features.presentation.ui.theme.DeepBlue
import apps.sumit.apitest.features.presentation.util.Screen

@Composable
fun MainScreen() {

    var screen by remember {
        mutableStateOf("Breaking News")
    }

    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            NewsScreenTopSection(text = screen)
        },
        bottomBar = {

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
    NavHost(navController = navController, startDestination = Screen.CustomNewsScreen.route) {
        composable(Screen.BreakingNewsScreen.route) {
            BreakingNewsScreen(modifier)
        }
        composable(Screen.CustomNewsScreen.route) {
            CustomNewsScreen(query = "tesla", modifier = modifier)
        }
    }
}