package apps.sumit.apitest.features.presentation.newsComponents.breakingNews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import apps.sumit.apitest.features.presentation.common.SingleNews
import apps.sumit.apitest.features.presentation.newsComponents.breakingNews.viewmodel.BreakingNewsViewModel
import apps.sumit.apitest.features.presentation.ui.theme.DeepBlue

@Composable
fun BreakingNewsScreen(
    modifier: Modifier = Modifier,
    viewModel: BreakingNewsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    LaunchedEffect(true) {
        if (viewModel.changed.value) {
            viewModel.changed.value = false
            viewModel.getNews()
        }
    }

    if (state.isLoading) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(DeepBlue)
        ) {
            state.newsList?.newsList?.let { it ->
                items(it.size) { i ->
                    if (state.newsList.newsList[i].title != "[Removed]")
                        SingleNews(news = state.newsList.newsList[i])
                }
            }
        }
    }
}