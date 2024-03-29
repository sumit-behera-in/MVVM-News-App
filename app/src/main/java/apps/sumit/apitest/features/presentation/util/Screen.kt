package apps.sumit.apitest.features.presentation.util

sealed class Screen(val route: String) {
    data object BreakingNewsScreen : Screen("BreakingNewsScreen")
    data object CustomNewsScreen : Screen("CustomNewsScreen")
    data object MainScreen : Screen("MainScreen")
    data object SplashScreen : Screen("SplashScreen")
}