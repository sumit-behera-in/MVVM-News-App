package apps.sumit.apitest.features.presentation.util

sealed class Screen(val route: String) {
    data object BreakingNewsScreen : Screen("BreakingNewsScreen")
}