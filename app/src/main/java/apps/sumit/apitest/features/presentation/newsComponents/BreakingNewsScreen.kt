package apps.sumit.apitest.features.presentation.newsComponents

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import apps.sumit.apitest.features.presentation.newsComponents.viewmodel.BreakingNewsViewModel

@Composable
fun BreakingNewsScreen(
    modifier: Modifier = Modifier,
    viewModel: BreakingNewsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    state.newsList?.let { Text(text = it.type) }
    LazyColumn(modifier = modifier.fillMaxSize()) {
        state.newsList?.newsList?.let { it ->
            items(it.size) { i ->
                if (state.newsList.newsList[i].title != "[Removed]")
                    SingleNews(news = state.newsList.newsList[i])
            }
        }
    }
}