package apps.sumit.apitest.features.presentation.newsComponents.customNews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import apps.sumit.apitest.features.presentation.common.SingleNews
import apps.sumit.apitest.features.presentation.newsComponents.customNews.viewmodel.CustomNewsViewModel

@Composable
fun CustomNewsScreen(
    query: String,
    modifier: Modifier = Modifier,
    viewModel: CustomNewsViewModel = hiltViewModel(),
) {
    LaunchedEffect(true) {
        if (viewModel.changed.value) {
            viewModel.changed.value = false
            viewModel.getNews(query)
        }
    }

    val state = viewModel.state.value

    LazyColumn(modifier = modifier.fillMaxSize()) {
        state.newsList?.newsList?.let { it ->
            items(it.size) { i ->
                if (state.newsList.newsList[i].title != "[Removed]")
                    SingleNews(news = state.newsList.newsList[i])
            }
        }
    }
}